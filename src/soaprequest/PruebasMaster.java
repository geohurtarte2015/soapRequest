package soaprequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class PruebasMaster {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new PruebasMaster().requestSoap("50660013314", "subscriptin-manager"); 
    }
    
    public void sendSoap(String URLs,String data) {
       
        try {
                 
            URL ur = new URL(URLs);
            HttpURLConnection con = (HttpURLConnection) ur.openConnection();
            con.setRequestMethod("POST");
            
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("Content-Length", String.valueOf(data.length()));
            con.setRequestProperty("SOAPAction", "");
            
            con.setDoOutput(true);
            con.setDoInput(true);
                        
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
           
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

        } catch (Exception e) {
                e.printStackTrace();
        }

        

    }
    
    
    public void requestSoap(String numberValue, String snCode){
        
        String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spr=\"http://SPR_WS.telecom.sv/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <spr:requestProfile>\n" +
                        "         <!--Optional:-->\n" +
                        "         <msisdn>"+numberValue+"</msisdn>\n" +
                        "         <!--Optional:-->\n" +
                        "         <SNCode>"+snCode+"</SNCode>\n" +
                        "      </spr:requestProfile>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        PruebasMaster ma = new PruebasMaster();

        ma.sendSoap("http://172.16.204.215:10002/spr/soap/sm",data);
    
    
    }

}
