/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import Temporary.dataAccess.types.EventElastic;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Patrik Rojek
 */
public class GraphAPIService {
    
    public void getEventsFromSource(String source){
        
    }
    
    public static void main(String[] args) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://graph.facebook.com/v2.11/331514527167/events?access_token=EAACEdEose0cBAArwVufvZAYiNnI1KVhiczt0XOWFScByU349ha8wzTB1XCkZBZAELmbSDJQZCykQ4Bo37TQMKf7mIMv3bXnSFVoknuKZB0g4GwjFl98O1bzAavYprb02kBFYSiZCbPfv8JgMOz5s9zZB9LWtdOST6m44JtflI2wJ078tRIT62tlZCnr5I6Di9SYZD");

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            
            ObjectMapper mapper = new ObjectMapper();
            List<EventElastic> myObjects = Arrays.asList(mapper.readValue(content, EventElastic[].class)); 
            
            System.out.println(myObjects.size());            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
