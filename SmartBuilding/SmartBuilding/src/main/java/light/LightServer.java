package light;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import light.LightServiceGrpc.LightServiceImplBase;

public class LightServer extends LightServiceImplBase{

	public static void main(String[] args) {

		LightServer lightserver=new LightServer();
		
		int port= 50051;
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
	
	//unary rpc 
	@Override
    public void powerSwitch(PowerRequest request,
            StreamObserver<PowerResponse> responseObserver) {
        
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
		
		
		return new StreamObserver<BillRequest>() {

			@Override
			public void onNext(BillRequest value) {
				// TODO Auto-generated method stub
				System.out.println("Receiving meter reading: "+ value.getReading() + ". Price per unit: "+ value.getPrice() + ". Available discount "+ value.getDiscount()+"%");
				
				double dis= 1-(value.getDiscount()/100);
				double bill= ((value.getReading() * value.getPrice()) * dis);
				
				BillResponse reply= BillResponse.newBuilder().setReading(value.getReading()).setBill(bill).build();
				
				responseObserver.onNext(reply);
				
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
