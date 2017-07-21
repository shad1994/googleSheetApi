package Maven.googleSheet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class requests {

	// HTTP GET request
	public String sendGet(String url) throws Exception {
		String newurl=url+"?access_token=ya29.GluOBCTE9FLrudNVUqrHaxHwHpDD2nH2SvgIX4rUOytSrCBQYgDODdFdDZur5VhU92eygnnxWiivMLBbA0jgBq2z9xgGL16TvEhkyxtuslTJbYBouKbqLS-Hw28Q";
		URL obj = new URL(newurl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + newurl);
		System.out.println("Response Code : " + responseCode);
		if (responseCode == 404) {
			return "not found";
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			return response.toString();
		}

	}

	// HTTP POST request

	public String sendPost(String url, String param) throws Exception{
		
		
	String newurl=url+"?access_token=ya29.GluOBCTE9FLrudNVUqrHaxHwHpDD2nH2SvgIX4rUOytSrCBQYgDODdFdDZur5VhU92eygnnxWiivMLBbA0jgBq2z9xgGL16TvEhkyxtuslTJbYBouKbqLS-Hw28Q";

		URL obj = new URL(newurl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");

		// Send post request
		con.setDoOutput(true);
	
		if (param == null) {

		} else {
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
		}

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + newurl);
		System.out.println("Post parameters : " + param);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
		
	
		
	}

	public String putrequest(String url, String data) throws IOException {
		String newurl=url+"?access_token=ya29.GluOBCTE9FLrudNVUqrHaxHwHpDD2nH2SvgIX4rUOytSrCBQYgDODdFdDZur5VhU92eygnnxWiivMLBbA0jgBq2z9xgGL16TvEhkyxtuslTJbYBouKbqLS-Hw28Q";
		URL url1 = new URL(newurl);
		HttpURLConnection httpCon = (HttpURLConnection) url1.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");

		if (data == null) {
			System.out.println("no data");
		} else {
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			out.write(data);
			out.close();
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		httpCon.getInputStream();
		return response.toString();
	}

	public String deleteRequest(String url) throws IOException {
		try {

			URL url1 = new URL(url);

			HttpURLConnection httpCon = (HttpURLConnection) url1.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("DELETE");
			httpCon.connect();
			if (httpCon.getResponseCode() == 500) {
				return "not exist";
			}

			else {

				BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

				return response.toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

}
