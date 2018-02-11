/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.ExamServer;

/**
 *
 * @author cornwall
 */
public class GetAssessment {
     public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name ="ExamServer";
            Registry registry = LocateRegistry.getRegistry(1099);
            ExamServer server = (ExamServer) registry.lookup(name);
            int test = server.login(12, "string");
            System.out.println(test);
        } catch (Exception e) {
            System.err.println("GetAssessment exception:");
            e.printStackTrace();
        }
    }    
}

