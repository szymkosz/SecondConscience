package com.example.bella.secondconscience;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnect {

	static final String USER_AGENT = "Mozilla/5.0";

	// public static void main(String[] args) throws Exception {
  //
	// 	HttpURLConnect http = new HttpURLConnect();
  //
	// 	System.out.println("Testing 1 - Send Http GET request");
	// 	// System.out.println(http.sendGet("you a hoe"));
  //   System.out.println(findScore(http.sendGet("you a hoe")));
  //
	// }

	// HTTP GET request
	static String sendGet(String query) throws Exception {

    String url = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/84b1f33f-ec76-4539-a01e-1d0dada1f10a?subscription-key=20d2344d1a06408caa9465dc4bd4d380&staging=true&spellCheck=true&bing-spell-check-subscription-key=4946a79341fa49268fb57c4a01aa1166&verbose=true&timezoneOffset=-360&q=";

    // Remove the spaces and replace them with '%20'
    String[] splitArray = query.split("\\s+");
    String newQuery = splitArray[0];
    for (int i = 1; i < splitArray.length; i++) {
//    	String.join("%20", splitArray);
    	newQuery = newQuery + "%20" + splitArray[i];
	}

    // Append the query to our bot url
    url += newQuery;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		//int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
      // System.out.println(inputLine);
			response.append(inputLine);
		}
		in.close();

		//print result
		// System.out.println(response.toString().getClass().getName());
    // System.out.println(response);

    String ret = response.toString();

    return ret;

	}

  public static float findScore(String input) {
        int scoreIndexAfter = input.indexOf("score") + 8;
        String num = input.substring(scoreIndexAfter, scoreIndexAfter+12); //get num
        num = num.replaceAll("\\s",""); //remove whitespace
	  	Log.i("Tag", num);
        float numFinal = Float.parseFloat(num); //cast to float
        return numFinal;
      }

}
