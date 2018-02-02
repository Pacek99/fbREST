/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.fbrest.models.EventDTO;
import com.mycompany.fbrest.models.EventElastic;
import com.mycompany.fbrest.models.ParserJSON;
import events.Launcher;
import events.entities.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/**
 *
 * @author Patrik Rojek
 */
public class GraphAPIService {
    
    
    public void saveEvents(String source) throws JSONException{
        List<Event> allRecievedEvents = GraphAPIService.getEventsFromSource(source);
        List<Event> newRecievedEvents = new ArrayList<>();
        for (Event recievedEvent : allRecievedEvents) {
            if (Launcher.eventService.existsById(recievedEvent.id)) {
                newRecievedEvents.add(recievedEvent);
            }
        }
        //3. Vytiahneš všetky staré z databázy (najskôr všetky, tu si nie som istý).
        Iterable<Event> eventsFromDB = Launcher.eventService.findAll();
        //4. Vytvoríš dvojice nových a starých + dvojice nových navzájom.
        Map<String, String> mapaIdcok = new HashMap<>();
        
        //5. Uložíš všetky dvojice dvojice ako EventsSimilarities. 
        for (Map.Entry<String, String> entry : mapaIdcok.entrySet()) {
            //Launcher.eventsSimilarityService.saveNew(entry.getKey(), entry.getValue(), Launcher.similarityCalculator.);
            
        }
        //6. Uložíš ešte aj nové eventy...
        
    }
    
    public static List<Event> getEventsFromSource(String source) throws JSONException{
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://graph.facebook.com/v2.11/" + source + "/events?access_token=EAACEdEose0cBAAjvMPeIsf9by0M1qcGICVTskfPtJOvZAStdBPFPRYdu7uKiomxOeAb2oRZAfNq5cYzQEfcaPWxYpxi0QIKcTRoKveASyM9NmNfizqXdwGddK6gSbqQwAKlzDxqeFK0EPLPn64cqUuo4cYvZCx7XeFxJ9EpTRVtUAugHpNc5m4ZATjCHLhwZD");

        List<Event> data = null;
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            
            ObjectMapper mapper = new ObjectMapper();
            data = ParserJSON.parsujJSON(content, source);
            
            System.out.println(data.size());            
        } catch (IOException e) {
            e.printStackTrace();
        }    
        return data; 
    }
    
    
    public static void main(String[] args) throws JSONException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://graph.facebook.com/v2.11/331514527167/events?access_token=EAACEdEose0cBAI3P7LW4yrjTDC32YbeL9oy9qQlOvkOnl8D3ZChGoYbV03K81bLih9lnTPAFF5diF4O2LLMq86skjA7ipVJMZBDr84c0rXMSUjPmb3zOx5T0gso3SiGbY3OmkDSm6dNBypZCnlG6w8YwpU1Rznr7964KntJlZBZBh9bo9fzxANx5bbIAGyBAZD");

        List<Event> data;
  
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            ObjectMapper mapper = new ObjectMapper();
            data = ParserJSON.parsujJSON(content, "331514527167");
        
            System.out.println(data.size());            
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
}
