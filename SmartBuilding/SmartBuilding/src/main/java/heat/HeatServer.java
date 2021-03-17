package heat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import heat.HeatServiceGrpc.HeatServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class HeatServer extends HeatServiceImplBase{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HeatServer heatserver=new HeatServer();
		
		Properties prop = heatserver.getProperties();
		heatserver.registerService(prop);
		
		//int port= 50052;
		int port = Integer.valueOf( prop.getProperty("service_port") );

		try {
			Server server = ServerBuilder.forPort(port)
					.addService(heatserver)
					.build()
					.start();
			
			System.out.println("Server started, listening on " + port);
			
			server.awaitTermination();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//setting properties 
private Properties getProperties() {
		
		Properties prop = null;		
		
		 try (InputStream input = new FileInputStream("src/main/resources/heat.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Math Service properies ...");
	            System.out.println("\t service_type: " + prop.getProperty("service_type"));
	            System.out.println("\t service_name: " +prop.getProperty("service_name"));
	            System.out.println("\t service_description: " +prop.getProperty("service_description"));
		        System.out.println("\t service_port: " +prop.getProperty("service_port"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	
		 return prop;
	}
	
private  void registerService(Properties prop) {
	
	 try {
           // Create a JmDNS instance
           JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
           
           String service_type = prop.getProperty("service_type") ;
           String service_name = prop.getProperty("service_name")  ;
          // int service_port = 1234;
           int service_port = Integer.valueOf( prop.getProperty("service_port") );

           
           String service_description_properties = prop.getProperty("service_description")  ;
           
           // Register a service
           ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
           jmdns.registerService(serviceInfo);
           
           System.out.printf("Registrering service with type %s and name %s \n", service_type, service_name);
           
           // Wait a bit
           Thread.sleep(1000);

           // Unregister all services
           //jmdns.unregisterAllServices();

       } catch (IOException e) {
           System.out.println(e.getMessage());
       } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
}
	
	
	
	//unary rpc 
	public void heatSwitch(HeatRequest request,
            StreamObserver<HeatResponse> responseObserver) {
		System.out.println("-------------- Unary --------------");
		Boolean check= request.getHeat();
		
		if (check==true) {
			System.out.println("Heating is turning ON ");
		}
		else {
			System.out.println("Heating is turning OFF ");

		}
		// Send a response with value back
		HeatResponse responce=HeatResponse.newBuilder().setHeat(check).build();
		responseObserver.onNext(responce);
		
		// server protofiles has completed the processing
		responseObserver.onCompleted();
		
    }
	
	//Server side streaming 
	public void changeTemperature(TemperatureRequest request,
			StreamObserver<TemperatureResponse> responseObserver) {
		System.out.println("-------------- Server Side Streaming --------------");
		System.out.println("Request received: "+request.getNumbers()+" increments of temperature "+ request.getIncrement()+"°C.\nStarting from "+ request.getStart()+"°C");
		
		for(int i=1; i<=request.getNumbers(); i++) {
			
			int input= request.getStart()+(request.getIncrement() *i);
			//System.out.println(input);
			TemperatureResponse reply=TemperatureResponse.newBuilder().setTemperature(input).build();
			
			responseObserver.onNext(reply);
			
			try {
				//wait for a second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		responseObserver.onCompleted();
		
	}
	
	public StreamObserver<SuggestRequest> suggestTemperature(StreamObserver <SuggestResponse> responseObserver){
		
		System.out.println("-------------- Bidirectional --------------");
		return new StreamObserver<SuggestRequest>() {

			@Override
			public void onNext(SuggestRequest value) {
				// TODO Auto-generated method stub
				System.out.println("Receiving Date: "+ value.getDate() + " & Temperature guess: "+ value.getTemp()+"°C");
				String input="";
				if(value.getTemp() >=30) {
					 input="TO HIGH";
				}
				else if(value.getTemp() <=10) {
					 input="TO LOW";
				}
				else {
					input="Nearly Same";
				}
				
				SuggestResponse reply= SuggestResponse.newBuilder().setDate(value.getDate()).setTemp(value.getTemp()).setGuess(input).build();
				
				responseObserver.onNext(reply);
			
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("Receiving suggestTemperature completed ");
				responseObserver.onCompleted();
			}
			
		};
		
	}
	
	
	
}
