/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import com.mycompany.fbrest.SingletonForMySQL;
import eventagent.persistence.dao.mysql.MySQLAdminDAO;
import eventagent.persistence.entities.Admin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Patrik Rojek
 */
public class AdminService {
    
    private MySQLAdminDAO adminDAO;
    
    public AdminService() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("MySQLPersistenceBeans.xml");
	//	//get the dao defined in Bean
        //adminDAO = (MySQLAdminDAO) context.getBean("adminDAO");
        adminDAO = SingletonForMySQL.getMySQLAdminDAO();
    }
    
    public String isAdmin(String admin) {
        Admin a = new Admin();
        a.setAdminFbId(admin);
        if (adminDAO.get(a) != null) {
            return "true";
        } else {
            return "false";
        }
    }

    public void add(Admin admin) {
        adminDAO.addNewAdmin(admin);
    }
}
