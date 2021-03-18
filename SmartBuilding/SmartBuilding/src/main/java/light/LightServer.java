package light;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import light.LightServiceGrpc.LightServiceImplBase;

public class LightServer extends LightServiceImplBase{

	public static void main(String[] args) {

		LightServer lightserver=new LightServer();
		// peoperties file
		Properties prop= lightserver.getProperties();
		
		lightserver.registerService(prop);
		
		//int port= 50051;
		
		int port = Integer.valueOf( prop.getProperty("service_port") );
		try {
			Server server = ServerBuilder.forPort(port)
					.addService(lightserver)
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
		
		//reading file
		 try (InputStream input = new FileInputStream("src/main/resources/light.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Light Service properies ...");
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
	@Override
    public void powerSwitch(PowerRequest request,
            StreamObserver<PowerResponse> responseObserver) {
		System.out.println("-------------- Unary --------------");

		Boolean check= request.getPower();
		
		if (check==true) {
			System.out.println("Switch is turning ON ");
		}
		else {
			System.out.println("Switch is turning OFF ");

		}
		// Send a response with value back
		PowerResponse responce=PowerResponse.newBuilder().setPower(check).build();
		responseObserver.onNext(responce);
		
		// server protofiles has completed the processing
		responseObserver.onCompleted();
		
    }

	public StreamObserver<ColourRequest> changeColour(final StreamObserver <ColourResponse> responseObserver){
		System.out.println("-------------- Client Side Streaming  --------------");

		return new StreamObserver<ColourRequest>() {

			String colour="";
			

			@Override
			public void onNext(ColourRequest value) {
				// TODO Auto-generated method stub
				
				colour= value.getColour();
				System.out.println("Request to change colour: " + colour);
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				ColourResponse response = ColourResponse.newBuilder().setColour(colour).build();
				responseObserver.onNext(response);
				responseObserver.onCompleted();
				
			}
			
		};
		
	}
	
	public StreamObserver<BillRequest> calculateBill(StreamObserver<BillResponse> responseObserver){
		System.out.println("-------------- Bidirectional --------------");

		
		return new StreamObserver<BillRequest>() {

			@Override
			public void onNext(BillRequest value) {
				// TODO Auto-generated method stub
				System.out.println("Receiving meter reading: "+ value.getReading() + ". Price per unit: "+ value.getPrice() + ". Available discount "+ value.getDiscount()+"%");
				
				double dis= 1-(value.getDiscount()/100);
				double bill= ((value.getReading() * value.getPrice()) * dis);
				
				BillResponse reply= BillResponse.newBuilder().setReading(value.getReading()).setBill(bill).build();
				
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
				
				System.out.println("Receiving calculateBill completed ");
				//completed too
				responseObserver.onCompleted();
				
			}
			
			
		};
		
	}
	
	
	
	
	
	
}// class ending 
