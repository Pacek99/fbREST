/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import eventagent.persistence.dao.mysql.MySQLAdminDAO;
import eventagent.persistence.dao.mysql.MySQLEventsSourceDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Patrik Rojek
 */
public class SingletonForMySQL {
    
    private static MySQLEventsSourceDAO msqlesdao = null;
    
    private static MySQLAdminDAO msqladao = null;
    
    private static ApplicationContext context = new ClassPathXmlApplicationContext("MySQLPersistenceBeans.xml");
    
    public static MySQLEventsSourceDAO getMySQLEventsSourceDAO() {
      if(msqlesdao == null) {
         msqlesdao = (MySQLEventsSourceDAO) context.getBean("eventsSourceDAO");
      }
      return msqlesdao;
   }
    
    public static MySQLAdminDAO getMySQLAdminDAO() {
      if(msqladao == null) {
         msqladao = (MySQLAdminDAO) context.getBean("adminDAO");
      }
      return msqladao;
   }
    
}
