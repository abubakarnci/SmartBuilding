package jmdns;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class SmartBuildingServiceDiscovery implements ServiceListener {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SmartBuildingServiceDiscovery discoverService=new SmartBuildingServiceDiscovery();
		
		
		try {
			JmDNS jmdns=JmDNS.create(InetAddress.getLocalHost());
			
			String service_type="_http._tcp.local.";
			
			
			jmdns.addServiceListener(service_type, discoverService);
			
			Thread.sleep(30000);
			jmdns.close();

			
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

	public void serviceAdded(ServiceEvent event) {
		System.out.println("Service added: "+event.getInfo());
		
	}

	public void serviceRemoved(ServiceEvent event) {
		System.out.println("Service removed: "+event.getInfo());			
	}

	public void serviceResolved(ServiceEvent event) {
		System.out.println("Service resolved: "+event.getInfo());			
	
		ServiceInfo serviceinfo= event.getInfo();
		
		System.out.println("host: "+serviceinfo.getHostAddresses()[0]);
		System.out.println("port: "+serviceinfo.getPort());
		System.out.println("type: "+serviceinfo.getType());
		System.out.println("name: "+serviceinfo.getName());
		System.out.println("comp.name: "+serviceinfo.getServer().replace(".local.", ""));
		System.out.println("desc: "+serviceinfo.getNiceTextString());
		
	}
}
