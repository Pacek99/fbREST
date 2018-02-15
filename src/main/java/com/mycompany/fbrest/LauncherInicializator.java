/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fbrest;

import events.Launcher;

/**
 *
 * @author Patrik Rojek
 */
public class LauncherInicializator {
    
    public static void initLauncher(Object o){
        if(o == null) {
         Launcher.main(null);
         Launcher.initializer.ensureInitialize();
      }
    }    
}
