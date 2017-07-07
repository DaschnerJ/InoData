/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.daschnerj.ino.data;

import io.github.daschnerj.ino.config.Config;
import io.github.daschnerj.ino.logger.gui.Log;

/**
 *
 * @author justdasc
 */
public class InoData {
    
    public static Log l = new Log("log", true, "ilog");
    public static Config cp = new Config("properties", "ifig");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        l.showLog();
        // TODO code application logic here
    }
    
}
