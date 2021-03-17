package heat;

import java.util.Random;

import heat.HeatServiceGrpc.HeatServiceBlockingStub;
import heat.HeatServiceGrpc.HeatServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class HeatClient {

	private static HeatServiceBlockingStub blockingStub1;
	private static HeatServiceStub asyncStub1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String host="localhost";
		int port=50052;
		
		ManagedChannel channel=ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();

		blockingStub1= HeatServiceGrpc.newBlockingStub(channel);
		asyncStub1= HeatServiceGrpc.newStub(channel);
		
		
		heatSwitch();
		changeTemperature();
		suggestTemperature();
		
	}
	private static void suggestTemperature() {
		// TODO Auto-generated method stub
		System.out.println("-------------- Bidirectional --------------");

		StreamObserver<SuggestResponse> responseObserver= new StreamObserver<SuggestResponse>() {

			int count=0;
			@Override
			public void onNext(SuggestResponse value) {
				// TODO Auto-generated method stub
				System.out.println("Your guess "+ value.getTemp()+" is " + value.getGuess() + ". Date: "+ value.getDate() );
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("Stream is completed ... received "+ count+ " gusses");
				
			}
			
		};

		StreamObserver<SuggestRequest> requestObserver = asyncStub1.suggestTemperature(responseObserver);
		
		try {

			requestObserver.onNext(SuggestRequest.newBuilder().setDate("02.02.2022").setTemp(60).build());
			Thread.sleep(500);
			requestObserver.onNext(SuggestRequest.newBuilder().setDate("08.03.2022").setTemp(9).build());
			Thread.sleep(500);
			requestObserver.onNext(SuggestRequest.newBuilder().setDate("13.04.2022").setTemp(20).build());
			Thread.sleep(500);
			// Mark the end of requests
			requestObserver.onCompleted();


			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000));


		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void changeTemperature() {
		// TODO Auto-generated method stub
		System.out.println("-------------- Server Side Streaming --------------");

		TemperatureRequest request= TemperatureRequest.newBuilder()
				.setNumbers(5).setStart(1).setIncrement(3).build();
		
		StreamObserver<TemperatureResponse> responseObserver= new StreamObserver<TemperatureResponse>(){

			int count =0;
			
			@Override
			public void onNext(TemperatureResponse value) {
				// TODO Auto-generated method stub
				System.out.println("Next temperature: " + value.getTemperature()+"°C");
				count += 1;
				
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("stream is completed ... received "+ count);
				
			}
			
		};
		asyncStub1.changeTemperature(request, responseObserver);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void heatSwitch() {
		// TODO Auto-generated method stub
		System.out.println("-------------- Unary --------------");

		boolean input=false;
		System.out.println("Want to turned ON heating? "+input);
		
		HeatRequest request= HeatRequest.newBuilder().setHeat(input).build();
		
		HeatResponse response= blockingStub1.heatSwitch(request);
		
		if(response.getHeat()==true) {
			System.out.println("Server response: Heating turned ON ");
		}
		else {
			System.out.println("Server response: Heating turned OFF ");
		}
		
		
	}

}
