package main.java;

import java.io.*;
import java.net.*;

import org.json.JSONObject;



public class TCPClassifier {
	
	public static void main(String argv[]) throws Exception {
	 
		String sentence;	
  
  		//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
  		Socket clientSocket = new Socket("localhost", 51419);	  
  		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
  		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  		
  		boolean notStop= true;
  		int loop = 1;
  		while (notStop) {
  			sentence = inFromServer.readLine();	
  			//System.out.println( " sentence " + sentence  );
  
  			if (isActivation(sentence)) {
  				outToServer.writeBytes("Activation classified\n");
  			} else {
  				//outToServer.writeBytes("NOT Activation\n");
  			}
  			
  			loop++;
  			if (loop > 2000) {
  				notStop = false;
  			}
  			
  		}
  
		//clientSocket.close();
  
	}
 

	public static boolean isActivation(String data) {
		   
	    try {  
	       JSONObject emp=(new JSONObject(data));  
	       String label=emp.getString("label");
      
	       if  (label.equals("ACTIVATION")) {
	    	   return true;
	       } else {
	    	   return false;
	       }
	       
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }  

	}
	
}

