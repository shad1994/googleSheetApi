package Maven.googleSheet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthSigner;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;

public class TestGoogleSheetApi {
	
	public static void main(String[] args) throws IOException, ServiceException {
		String client_id="664703740687-fgu0stb7oscljbfim0r6r6hencg3lfqp.apps.googleusercontent.com";
		String client_secret="JR4ggJPWhczNL5EPLajRAMmT";
		loginOAuth2(client_id,client_secret);
	}
	
	public static void loginOAuth2(String clientID, String clientSecret) throws IOException, ServiceException {

		String SCOPES = "https://spreadsheets.google.com/feeds";
		GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();

	
		oauthParameters.setOAuthConsumerKey(clientID);

		// Initialize the OAuth Signer.
		OAuthSigner signer = null;
		oauthParameters.setOAuthConsumerSecret(clientSecret);
		signer = new OAuthHmacSha1Signer();

	
		GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(signer);

		// STEP 2: Get the Authorization URL

		// Set the scope for this particular service.
		oauthParameters.setScope(SCOPES);

		// This method also makes a request to get the unauthorized request
		// token,
		// and adds it to the oauthParameters object, along with the token
		// secret
		// (if it is present).
		try {
			oauthHelper.getUnauthorizedRequestToken(oauthParameters);
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get the authorization url. The user of your application must visit
		// this url in order to authorize with Google. If you are building a
		// browser-based application, you can redirect the user to the
		// authorization
		// url.
		String requestUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
		System.out.println(requestUrl);
		System.out.println("Please visit the URL above to authorize your OAuth "
				+ "request token.  Once that is complete, press any key to " + "continue...");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// STEP 3: Get the Access Token

		// Once the user authorizes with Google, the request token can be
		// exchanged
		// for a long-lived access token. If you are building a browser-based
		// application, you should parse the incoming request token from the url
		// and
		// set it in GoogleOAuthParameters before calling getAccessToken().
		String token = null;
		try {
			token = oauthHelper.getAccessToken(oauthParameters);
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OAuth Access Token: " + token);
		System.out.println();

		// STEP 4: Make an OAuth authorized request to Google

		// Initialize the variables needed to make the request
		URL feedUrl = null;
		try {
			feedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sending request to " + feedUrl.toString());
		System.out.println();
		SpreadsheetService googleService = new SpreadsheetService("oauth-sample-app");

		// Set the OAuth credentials which were obtained from the step above.
		try {
			googleService.setOAuthCredentials(oauthParameters, signer);
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Make the request to Google

		// Make a request to the API and get all spreadsheets.
		SpreadsheetFeed feed = googleService.getFeed(feedUrl, SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		System.out.println("Response Data:");
		System.out.println("=====================================================");
		if (spreadsheets != null) {

			// Iterate through all of the spreadsheets returned
			for (SpreadsheetEntry spreadsheet : spreadsheets) {
				// Print the name of each spreadshet
				System.out.println(spreadsheet.getTitle().getPlainText());
			}
		}

		System.out.println("=====================================================");
		System.out.println();

		////////////////////////////////////////////////////////////////////////////
		// STEP 5: Revoke the OAuth token
		////////////////////////////////////////////////////////////////////////////

		System.out.println("Revoking OAuth Token...");
		try {
			oauthHelper.revokeToken(oauthParameters);
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OAuth Token revoked...");
	}


}
