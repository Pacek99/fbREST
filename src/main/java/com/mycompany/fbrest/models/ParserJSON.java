/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import eventagent.persistence.entities.EventDefaultType;
import events.entities.Event;
import events.entities.Location;
import events.entities.Place;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 *
 * @author Patrik Rojek
 */
public class ParserJSON {

    //Vstupom je JSON ulozeny v type String 
    public static List<Event> parsujJSON(String textJSON, String source) throws JSONException {
        List<Event> result = new ArrayList<>();

        JSONObject obj = new JSONObject(textJSON);

        JSONArray arr = obj.getJSONArray("data");

        //Este by sa tu hodilo "next" - to je URL pre udalosti na "dalsej strane"
        for (int i = 0; i < arr.length(); i++) { //Pre kazdu udalost            
            //Zakladne udaje
            String id = arr.getJSONObject(i).getString("id");

            //Volitelne udaje
            String start_time = "";
            String end_time = "";
            String description = "";
            String name = "";

            if (arr.getJSONObject(i).has("start_time")) {
                start_time = arr.getJSONObject(i).getString("start_time");
            }

            if (arr.getJSONObject(i).has("end_time")) {
                end_time = arr.getJSONObject(i).getString("end_time");
            }

            if (arr.getJSONObject(i).has("description")) {
                description = arr.getJSONObject(i).getString("description");
            }

            if (arr.getJSONObject(i).has("name")) {
                name = arr.getJSONObject(i).getString("name");
            }

            //Volitelne udaje - miesto
            String place_name = "";

            String city = "";
            String country = "";
            Double latitude = -1.0;
            Double longitude = -1.0;
            String street = "";
            String zip = "";

            JSONObject placeJsonObj = null;
            if (arr.getJSONObject(i).has("place")) {
                placeJsonObj = arr.getJSONObject(i).getJSONObject("place");
            }

            if (placeJsonObj != null) {
                if (placeJsonObj.has("name")) {
                    place_name = placeJsonObj.getString("name");
                }

                JSONObject locationJsonObj = null;
                if (placeJsonObj.has("location")) {
                    locationJsonObj = placeJsonObj.getJSONObject("location");
                }
                if (locationJsonObj != null) {
                    if (locationJsonObj.has("city")) {
                        city = locationJsonObj.getString("city");
                    }
                    if (locationJsonObj.has("country")) {
                        country = locationJsonObj.getString("country");
                    }
                    if (locationJsonObj.has("latitude")) {
                        latitude = locationJsonObj.getDouble("latitude");
                    }
                    if (locationJsonObj.has("longitude")) {
                        latitude = locationJsonObj.getDouble("longitude");
                    }
                    if (locationJsonObj.has("street")) {
                        street = locationJsonObj.getString("street");
                    }
                    if (locationJsonObj.has("zip")) {
                        zip = locationJsonObj.getString("zip");
                    }
                }
            }

            Location l = new Location();
            l.city = city;
            l.country = country;
            GeoPoint gp = new GeoPoint(latitude, longitude);
            l.coordinates = gp;
            l.street = street;
            l.zip = zip;

            Place pl = new Place();
            pl.name = place_name;
            pl.location = l;

            Event newEvent = new Event();
            newEvent.id = "fb-" + id;
            newEvent.place = pl;
            newEvent.description = description;
            newEvent.startTime = LocalDateTime.parse(start_time.substring(0, start_time.length() - 5));
            newEvent.endTime = LocalDateTime.parse(end_time.substring(0, end_time.length() - 5));
            newEvent.url = "https://www.facebook.com/events/" + id;
            newEvent.eventSourceUrl = "https://www.facebook.com/" + source;
            newEvent.eventType = EventDefaultType.unspecified.toString();

            result.add(newEvent);
        }

        return result;
    }
}
