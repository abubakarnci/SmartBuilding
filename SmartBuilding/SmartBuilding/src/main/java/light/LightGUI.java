package light;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import light.LightServiceGrpc.LightServiceBlockingStub;
import light.LightServiceGrpc.LightServiceStub;

public class LightGUI {
	
	private static LightServiceBlockingStub blockingStub;
	private static LightServiceStub asyncStub;

	private ServiceInfo lightServiceInfo;
	

	private JFrame frame;
	private JTextArea textResponse;
	private JTextArea textArea;
	private JTextArea textArea2;

	private JTextField textNumber1;
	private JTextField textNumber2;

	private JTextField textNumber3;
	private JTextField textNumber4;
	
	ArrayList<String> color=new ArrayList<String>();
	ArrayList<Integer> readi=new ArrayList<Integer>();
	ArrayList<Double> pri=new ArrayList<Double>();
	ArrayList<Double> dis=new ArrayList<Double>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LightGUI window = new LightGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LightGUI() {
		
		String light_service_type = "_light._tcp.local.";
		discoverLightService(light_service_type);
		
		String host = "localhost"; //heatServiceInfo.getHostAddresses()[0];
		int port = 50051; //heatServiceInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub=	LightServiceGrpc.newBlockingStub(channel);
		asyncStub = LightServiceGrpc.newStub(channel);
		
		initialize();
	}

	private void discoverLightService(String service_type) {
		// TODO Auto-generated method stub
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Light Service resolved: " + event.getInfo());

					lightServiceInfo = event.getInfo();

					int port = lightServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + lightServiceInfo.getNiceTextString());
					System.out.println("\t host: " + lightServiceInfo.getHostAddresses()[0]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Light Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Light Service added: " + event.getInfo());

					
				}
			});
			
			// Wait a bit
			Thread.sleep(20000);
			
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.getContentPane().setLayout(bl);
	
		//1st panel
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		JLabel lblNewLabel_1 = new JLabel("Light Switch");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboOperation = new JComboBox();
		comboOperation.setModel(new DefaultComboBoxModel(new String[] {"ON", "OFF"}));
		panel_1.add(comboOperation);
		
		JButton btnCalculate = new JButton("Done!!!");
		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int index = comboOperation.getSelectedIndex();
				boolean input=false;
				if(index==0) {
					input=true;
				}
				else {
					input=false;
				}
				
				System.out.println("Index: "+input);
				
				PowerRequest request= PowerRequest.newBuilder().setPower(input).build();
				
				// sending the message request & also receiving response 
				PowerResponse response=	blockingStub.powerSwitch(request);
				
				//priniting response from server
				
				if(response.getPower()==true) {
					System.out.println("Server response: Light turned ON ");
					textResponse.append("Server response: Light turned ON \n");
					
				}
				else {
					System.out.println("Server response: Light turned OFF ");
					textResponse.append("Server response: Light turned OFF \n");
					
				}
			}
		});
		
		panel_1.add(btnCalculate);
		textResponse = new JTextArea(3, 20);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_1.add(scrollPane);
		
		//2nd panel
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("Add Light Colours");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Colour:");
		panel_2.add(lblNewLabel_3);
		
		textNumber1 = new JTextField();
		panel_2.add(textNumber1);
		textNumber1.setColumns(5);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//String date = textNumber4.getText();
				
				color.add(textNumber1.getText());
				JOptionPane.showMessageDialog(null,"Colour Added");
			}
		});
		panel_2.add(btnAdd);
	
		JButton btnFinal = new JButton("Final!!!");
		
		btnFinal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent o) {
				// TODO Auto-generated method stub
				
				StreamObserver<ColourResponse> responseObserver = new StreamObserver<ColourResponse>() {

					@Override
					public void onNext(ColourResponse value) {
						// Print out response
						System.out.println("Colour has been set to " + value.getColour());
						textArea.append("Colour has been set to " + value.getColour()+"\n");
						
					}

					@Override
					public void onError(Throwable t) {

					}

					@Override
					public void onCompleted() {
						//once completed
						System.out.println("stream is completed ...");
						textArea.append("stream is completed ...");
						
					}
				};
				
				StreamObserver<ColourRequest> requestObserver = asyncStub.changeColour(responseObserver);
				try {
					// sending stream of requests
					for(int i=0; i<color.size(); i++) {
						requestObserver.onNext(ColourRequest.newBuilder().setColour(color.get(i)).build());
						Thread.sleep(1500);
					}				
					requestObserver.onCompleted();
					
					Thread.sleep(1000);
					// catch any errors
				} catch (RuntimeException e) {
		            requestObserver.onError(e);
		            throw e;
		            } catch (InterruptedException e) {

		                e.printStackTrace();
		        }	
				
			}	
		});
		
		panel_2.add(btnFinal);
		
		textArea = new JTextArea(2,30);
		
		textArea .setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane1 = new JScrollPane(textArea);
		panel_2.add(scrollPane1);
		
		//3rd panel
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("Calculate Bill");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Reading:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_3.add(lblNewLabel_5);
		
		textNumber2 = new JTextField();
		panel_3.add(textNumber2);
		textNumber2.setColumns(7);
		
		JLabel lblNewLabel_6 = new JLabel("Price:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_3.add(lblNewLabel_6);
		
		textNumber3 = new JTextField();
		panel_3.add(textNumber3);
		textNumber3.setColumns(7);
		
		JLabel lblNewLabel_7 = new JLabel("Discount:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_3.add(lblNewLabel_7);
		
		textNumber4 = new JTextField();
		panel_3.add(textNumber4);
		textNumber4.setColumns(9);
		
		JButton btnAddd = new JButton("Add");
		btnAddd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				readi.add(Integer.parseInt(textNumber2.getText()));
				dis.add(Double.parseDouble(textNumber4.getText()));
				pri.add(Double.parseDouble(textNumber3.getText()));
				JOptionPane.showMessageDialog(null,"ADDED");
			}
		});
		panel_3.add(btnAddd);
		
		JButton btnFinish = new JButton("Finish!!!");
		btnFinish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent p) {
				// TODO Auto-generated method stub
			
				StreamObserver<BillResponse> responseObserver= new StreamObserver<BillResponse>() {

					int count=0;
					
					@Override
					public void onNext(BillResponse value) {
						// TODO Auto-generated method stub
						System.out.println("Receiving calculated bill €" + value.getBill() + " of reading: "+ value.getReading() );
						textArea2.append("Receiving calculated bill €" + value.getBill() + " of reading: "+ value.getReading()+"\n");
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
						System.out.println("Stream is completed ... received "+ count+ " calculated bills");
						textArea2.append("Stream is completed ... received "+ count+ " calculated bills");
					}
				};
				StreamObserver<BillRequest> requestObserver = asyncStub.calculateBill(responseObserver);
				
				
				try {

					for(int i=0; i<pri.size(); i++) {
						requestObserver.onNext(BillRequest.newBuilder().setReading(readi.get(i)).setPrice(pri.get(i)).setDiscount(dis.get(i)).build());
						Thread.sleep(500);
					}
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
			
		});
		panel_3.add(btnFinish);
		
		textArea2 = new JTextArea(3,30);
		
		textArea2 .setLineWrap(true);
		textArea2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		
		
		panel_3.add(scrollPane2);
		
		
		
	}

}
