/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import events.Launcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Patrik Rojek
 */

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Do stuff during webapp's startup.
        Launcher.main(null);
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }        
    
}
