package jmdns;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class SmartBuildingServiceRegistration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			 // Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
		
			// Register a service
			ServiceInfo light = ServiceInfo.create("_http._tcp.local.", "light", 50051, "Controlling lights");
            jmdns.registerService(light);
			
            ServiceInfo heat = ServiceInfo.create("_http._tcp.local.", "heat", 50052, "Controlling heating");
            jmdns.registerService(heat);
            
            System.out.println("Registering service active");
            
            Thread.sleep(15000);
            
            System.out.println("Ready to unregister services");
            
            
            jmdns.unregisterAllServices();
            
            Thread.sleep(10000);
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
