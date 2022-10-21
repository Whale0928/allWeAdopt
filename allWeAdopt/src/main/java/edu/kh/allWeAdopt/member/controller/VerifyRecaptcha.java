package edu.kh.allWeAdopt.member.controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

//구글에서 제공하는 url주소로 시크릿 키를 통해 View 에서 하는 행동을 분석해서 답을 boolean타입으로 리턴해주는 클래스
public class VerifyRecaptcha {
	 public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	    private final static String USER_AGENT = "Mozilla/5.0";
	    private static String secret = ""; //local
	 
	    public static void setSecretKey(String key){
	        secret = key;
	    }
	    
	    public static boolean verify(String gRecaptchaResponse) throws IOException {
	        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
	            return false;
	        }
	        
	        try{
	        URL obj = new URL(url);
	        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	 
	        // add reuqest header
	        con.setRequestMethod("POST");
	        con.setRequestProperty("User-Agent", USER_AGENT);
	        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 
	        String postParams = "secret=" + secret + "&response="
	                + gRecaptchaResponse;
	 
	        // Send post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(postParams);
	        wr.flush();
	        wr.close();
	 
	        int responseCode = con.getResponseCode();
	        System.out.println("\nSending 'POST' request to URL : " + url);
	        System.out.println("Post parameters : " + postParams);
	        System.out.println("Response Code : " + responseCode);
	 
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	 
	        // print result
	        System.out.println(response);
	         
	        //parse JSON response and return 'success' value
	        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
	        JsonObject jsonObject = jsonReader.readObject();
	        jsonReader.close();
	         
	        return jsonObject.getBoolean("success");
	        
	        }catch(Exception e){
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    
		

}