/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest.services;

import eventagent.persistence.dao.mysql.MySQLAdminDAO;
import eventagent.persistence.entities.Admin;
import java.util.List;

/**
 *
 * @author Patrik Rojek
 */
public class AdminService {
    
    private MySQLAdminDAO adminDAO;
    
    public AdminService() {
        adminDAO = new MySQLAdminDAO();
    }
    
    public boolean isAdmin(String admin) {
        Admin a = new Admin();
        a.setAdminFbId(admin);
        if (adminDAO.get(a) != null) {
            return true;
        } else {
            return false;
        }
    }
}
