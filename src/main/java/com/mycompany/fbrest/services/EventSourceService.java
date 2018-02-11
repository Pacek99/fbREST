/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import com.mycompany.fbrest.models.EventSource;
import java.util.List;
import eventagent.persistence.dao.mysql.MySQLEventsSourceDAO;
import eventagent.persistence.entities.EventsSource;
import eventagent.persistence.entities.LastCheckResult;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Patrik Rojek
 */
public class EventSourceService {

    private MySQLEventsSourceDAO eventsSourceDAO;
    
    public EventSourceService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("MySQLPersistenceBeans.xml");
		//get the dao defined in Bean
        eventsSourceDAO = (MySQLEventsSourceDAO) context.getBean("eventsSourceDAO");
    }
    
    public EventsSource addNewSource(EventSource source) {
        EventsSource es = new EventsSource();
        es.setSourceURL(source.getSource());
        es.setEventDefaultType(source.getDefaultType());
        LastCheckResult lcr = LastCheckResult.not_checked;
        es.setLastCheckResult(lcr);
        es.setLastCheckTime(new Date());
        es.setNextCheckTime(DateUtils.addHours(es.getLastCheckTime(), source.getFrequency()));
        es.setDownloadFrequencyInHours(source.getFrequency());
        es.setAdded(new Date());
        es.setSourceType(source.getSourceType());
        
        eventsSourceDAO.addNewEventsSource(es);
        return es;
    }
    
    public List<EventsSource> getSources() {
        List<EventsSource> list = new ArrayList<>();
        list = eventsSourceDAO.getAllEventsSources();
        return list;
    }
    
    public void deleteSource(String source) {
        EventsSource es = new EventsSource();
        es.setSourceURL(source);
        eventsSourceDAO.deleteEventsSource(eventsSourceDAO.get(es));
    }

    public EventsSource updateFrequency(String source, Integer newFrequency) {
        EventsSource eventsSource = new EventsSource();
        eventsSource.setSourceURL(source);
        eventsSource = eventsSourceDAO.get(eventsSource);
        eventsSource.setDownloadFrequencyInHours(newFrequency);
        eventsSourceDAO.update(eventsSource);
        return eventsSource;
    }
    
}
