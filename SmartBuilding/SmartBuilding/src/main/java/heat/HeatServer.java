package heat;

import java.io.IOException;

import heat.HeatServiceGrpc.HeatServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;



public class HeatServer extends HeatServiceImplBase{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HeatServer heatserver=new HeatServer();
		
		int port= 50052;
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
