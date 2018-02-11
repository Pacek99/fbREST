/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.models;

import eventagent.persistence.entities.EventDefaultType;
import eventagent.persistence.entities.SourceType;
import java.util.stream.Stream;

/**
 *
 * @author Patrik Rojek
 */
public class Types {
    
    public static String[] sourceTypes = Stream.of(SourceType.values()).map(SourceType::name).toArray(String[]::new);
    
    public static String[] eventDefaultTypes = Stream.of(EventDefaultType.values()).map(EventDefaultType::name).toArray(String[]::new);
    /*
    public Types() {
        sourceTypes = Stream.of(SourceType.values()).map(SourceType::name).toArray(String[]::new);
        eventDefaultTypes = Stream.of(EventDefaultType.values()).map(EventDefaultType::name).toArray(String[]::new);
    }
    
    public static void main(String[] args){
        sourceTypes = Stream.of(SourceType.values()).map(SourceType::name).toArray(String[]::new);
        eventDefaultTypes = Stream.of(EventDefaultType.values()).map(EventDefaultType::name).toArray(String[]::new);
        
        for (int i = 0; i < sourceTypes.length; i++) {
                System.out.println(sourceTypes[i]);            
        }
        for (int i = 0; i < eventDefaultTypes.length; i++) {
                System.out.println(eventDefaultTypes[i]);            
        }
    }*/    
}
