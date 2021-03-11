package light;

import java.util.Random;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import light.LightServiceGrpc.LightServiceBlockingStub;

public class LightClient {

	private static LightServiceGrpc.LightServiceBlockingStub blockingStub;
	private static LightServiceGrpc.LightServiceStub asyncStub;

	public static void main(String[] args) {

		String host="localhost";
		int port=50051;
		
		ManagedChannel channel=ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();
		
		//blocking means that this stubcan be used for the synchronous calls
		blockingStub=	LightServiceGrpc.newBlockingStub(channel);
		asyncStub = LightServiceGrpc.newStub(channel);
		
		powerSwitch();
		changeColour();
		calculateBill();
	}
		public static void calculateBill() {
		// TODO Auto-generated method stub
			
			StreamObserver<BillResponse> responseObserver= new StreamObserver<BillResponse>() {

				int count=0;
				
				@Override
				public void onNext(BillResponse value) {
					// TODO Auto-generated method stub
					System.out.println("Receiving calculated bill €" + value.getBill() + " of reading: "+ value.getReading() );
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
					System.out.println("stream is completed ... received "+ count+ " calculated bills");
					
				}
			};
			StreamObserver<BillRequest> requestObserver = asyncStub.calculateBill(responseObserver);
			
			
			try {

				requestObserver.onNext(BillRequest.newBuilder().setReading(101).setPrice(0.8).setDiscount(10.0).build());
				requestObserver.onNext(BillRequest.newBuilder().setReading(150).setPrice(0.5).setDiscount(9.0).build());
				requestObserver.onNext(BillRequest.newBuilder().setReading(200).setPrice(0.65).setDiscount(20.0).build());

				// Mark the end of requests
				requestObserver.onCompleted();


				// Sleep for a bit before sending the next one.
				Thread.sleep(new Random().nextInt(1000) + 500);


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
		
		
		// preparing the message request
		private static void powerSwitch() {
		// TODO Auto-generated method stub
			
			PowerRequest request= PowerRequest.newBuilder().setPower(true).build();
			
			// sending the message request & also receiving response 
			PowerResponse response=	blockingStub.powerSwitch(request);
			
			//priniting response from server
			
			if(response.getPower()==true) {
				System.out.println("Server response: Light turned ON ");
			}
			else {
				System.out.println("Server response: Light turned OFF ");
			}
	}
		

	private static void changeColour() {
		// TODO Auto-generated method stub
		
		StreamObserver<ColourResponse> responseObserver = new StreamObserver<ColourResponse>() {

			@Override
			public void onNext(ColourResponse value) {
				// Print out response
				System.out.println("Colour has been set to " + value.getColour());
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ...");
			}
		};
		
		StreamObserver<ColourRequest> requestObserver = asyncStub.changeColour(responseObserver);
		try {
			// sending stream of requests
			requestObserver.onNext(ColourRequest.newBuilder().setColour("Red").build());
			Thread.sleep(500);
			//System.out.println("Colour Change Request Sent");
			requestObserver.onNext(ColourRequest.newBuilder().setColour("Blue").build());
			Thread.sleep(500);
			requestObserver.onNext(ColourRequest.newBuilder().setColour("Green").build());
			Thread.sleep(500);
			requestObserver.onNext(ColourRequest.newBuilder().setColour("Yellow").build());
			Thread.sleep(500);
			requestObserver.onNext(ColourRequest.newBuilder().setColour("Purple").build());
			Thread.sleep(500);
			
			requestObserver.onCompleted();
			
			Thread.sleep(10000);
			// catch any errors
		} catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
            } catch (InterruptedException e) {

                e.printStackTrace();
        }
		
		
		
			
	}
		
}
