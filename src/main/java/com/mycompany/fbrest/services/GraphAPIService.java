/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import com.mycompany.fbrest.LauncherInicializator;
import com.mycompany.fbrest.Listener;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.mycompany.fbrest.models.ParserJSON;
import eventagent.persistence.entities.EventsSource;
import eventagent.persistence.entities.LastCheckResult;
import events.Launcher;
import events.entities.Event;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Patrik Rojek
 */
public class GraphAPIService {

    public void saveEvents(EventsSource es, Listener listener) throws JSONException {
        LauncherInicializator.initLauncher(Launcher.eventService);
        String source = es.getSourceURL();
        
        List<Event> allRecievedEvents = GraphAPIService.getEventsFromSource(source);
        List<Event> newRecievedEvents = new ArrayList<>();
        for (Event recievedEvent : allRecievedEvents) {
            if (!Launcher.eventService.existsById(recievedEvent.id)) {
                newRecievedEvents.add(recievedEvent);
            }
        }
        //3. Vytiahneš všetky staré z databázy (najskôr všetky, tu si nie som istý).
        Iterable<Event> eventsFromDB = Launcher.eventService.findAll();

        //4. Uložíš ešte aj nové eventy...
        Launcher.eventService.saveAll(newRecievedEvents);

        //5. Vytvoríš dvojice nových a starých + dvojice nových navzájom. 
        // + aj 6. Uložíš všetky dvojice dvojice ako EventsSimilarities.
        for (Event newE : newRecievedEvents) {
            for (Event e : eventsFromDB) {
                Launcher.eventsSimilarityService.saveNew(e.id, newE.id, Launcher.similarityCalculator.calculateSimilarityCoefficient(e, newE));
            }
        }

        // tu este treba dvojice novych navzajom
        for (int i = 0; i < newRecievedEvents.size(); ++i) {
            for (int j = i + 1; j < newRecievedEvents.size(); ++j) {
                Launcher.eventsSimilarityService.saveNew(newRecievedEvents.get(i).id, newRecievedEvents.get(j).id, Launcher.similarityCalculator.calculateSimilarityCoefficient(newRecievedEvents.get(i), newRecievedEvents.get(j)));
            }
        }
        
        //7. updatovat dany EventsSource (lastcheckresult, lastcheck, nextchecktime vyratane podla frekvencie)
        EventsSource updated = es;
        if (!newRecievedEvents.isEmpty()) {
            updated.setLastCheckResult(LastCheckResult.new_event_found);
        } else {            
            updated.setLastCheckResult(LastCheckResult.none_new_event_found);
        }
        updated.setLastCheckTime(new Date());
        updated.setNextCheckTime(DateUtils.addHours(updated.getLastCheckTime(), updated.getDownloadFrequencyInHours()));
        
        //8. zavolas metodu hotovo 
        listener.hotovo(updated);
    }

    public static List<Event> getEventsFromSource(String source) throws JSONException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://graph.facebook.com/v2.11/" + source + "/events?access_token=168254080466042%7C966c59b40855fd96c57e06735cd894d6");

        List<Event> data = new ArrayList<>();
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);

            data = ParserJSON.parsujJSON(content, source);

            //System.out.println(data.size());            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Event> getEventsFromURL(String url, String source) throws JSONException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        List<Event> data = new ArrayList<>();
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);

            data = ParserJSON.parsujJSON(content, source);

            //System.out.println(data.size());            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /*
    public static void main(String[] args) throws JSONException {
        //System.out.println(GraphAPIService.getEventsFromSource("331514527167").size()); 
        if (Launcher.eventService == null) {
            System.out.println("je prazdny");
        }
        Launcher.main(null);
        if (Launcher.eventService == null) {
            System.out.println("je prazdny");
        } else {
            System.out.println("nie je prazdny");
        }
    }
    */   
}
