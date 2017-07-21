package Maven.googleSheet;

import java.io.IOException;

import com.google.api.client.auth.oauth2.TokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class AuthorizationCodeTokenRequest extends TokenRequest{
	
	public static void main(String[] args) throws IOException {
		requestAccessToken();
	}


  public AuthorizationCodeTokenRequest(HttpTransport transport, JsonFactory jsonFactory, GenericUrl tokenServerUrl,
			String grantType) {
		super(transport, jsonFactory, tokenServerUrl, grantType);
		// TODO Auto-generated constructor stub
	}

static void requestAccessToken() throws IOException {
    try {
      TokenResponse response =
          new AuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
              new GenericUrl("https://accounts.google.com/o/oauth2/token"), "client_credentials")
              //.setRedirectUri("https://client.example.com/rd")
          .setScopes("https://spreadsheets.google.com/feeds")
              .setClientAuthentication(
                  new BasicAuthentication("JR4ggJPWhczNL5EPLajRAMmT", "664703740687-fgu0stb7oscljbfim0r6r6hencg3lfqp.apps.googleusercontent.com")).execute();
      System.out.println("Access token: " + response.getAccessToken());
    } catch (TokenResponseException e) {
      if (e.getDetails() != null) {
        System.err.println("Error: " + e.getDetails().getError());
        if (e.getDetails().getErrorDescription() != null) {
          System.err.println(e.getDetails().getErrorDescription());
        }
        if (e.getDetails().getErrorUri() != null) {
          System.err.println(e.getDetails().getErrorUri());
        }
      } else {
        System.err.println(e.getMessage());
      }
    }
  }


private TokenRequest setScopes(String string) {
	// TODO Auto-generated method stub
	return null;
}
}
 