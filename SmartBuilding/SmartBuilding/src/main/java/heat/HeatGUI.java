package heat;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Array;
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

import heat.HeatServiceGrpc.HeatServiceBlockingStub;
import heat.HeatServiceGrpc.HeatServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;

public class HeatGUI {

	private static HeatServiceBlockingStub blockingStub1;
	private static HeatServiceStub asyncStub1;
	
	private ServiceInfo heatServiceInfo;
	
	
	
	
	private JFrame frame;
	private JTextArea textResponse;
	private JTextArea textArea;
	private JTextArea textArea2;

	private JTextField textNumber1;
	private JTextField textNumber2;

	private JTextField textNumber3;
	private JTextField textNumber4;
	
	ArrayList<Integer> temp=new ArrayList<Integer>();
	ArrayList<String> date=new ArrayList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeatGUI window = new HeatGUI();
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
	public HeatGUI() {
		

		String heat_service_type = "_heat._tcp.local.";
		discoverHeatService(heat_service_type);
		
		// i was getting errors thats why specifying host and port  
		
		String host = "localhost"; //heatServiceInfo.getHostAddresses()[0];
		int port = 50052; //heatServiceInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub1 = HeatServiceGrpc.newBlockingStub(channel);

		asyncStub1 = HeatServiceGrpc.newStub(channel);
		
		
		initialize();
	}
	
	private void discoverHeatService(String service_type) {
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Heat Service resolved: " + event.getInfo());

					heatServiceInfo = event.getInfo();

					int port = heatServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + heatServiceInfo.getNiceTextString());
					System.out.println("\t host: " + heatServiceInfo.getHostAddresses()[0]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Heat Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Heat Service added: " + event.getInfo());

					
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
		
		
		// 1st panel 
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		JLabel lblNewLabel_1 = new JLabel("Heating Switch");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboOperation = new JComboBox();
		comboOperation.setModel(new DefaultComboBoxModel(new String[] {"ON", "OFF"}));
		panel_1.add(comboOperation);
		
		JButton btnCalculate = new JButton("Done!!!");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = comboOperation.getSelectedIndex();
				boolean input=false;
				if(index==0) {
					input=true;
				}
				else {
					input=false;
				}
				
				System.out.println("Index: "+input);
				
				HeatRequest request= HeatRequest.newBuilder().setHeat(input).build();
				
				HeatResponse response= blockingStub1.heatSwitch(request);
				
				if(response.getHeat()==true) {
					System.out.println("Server response: Heating turned ON ");
					textResponse.append("Server response: Heating turned ON \n");
					
				}
				else {
					System.out.println("Server response: Heating turned OFF ");
					textResponse.append("Server response: Heating turned OFF \n");
					
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
		
		JLabel lblNewLabel_2 = new JLabel("Temperature Increment");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Start Temp:");
		panel_2.add(lblNewLabel_3);
		
		textNumber1 = new JTextField();

		panel_2.add(textNumber1);
		textNumber1.setColumns(5);
		
		JLabel lblNewLabel_4 = new JLabel("Increment Jump:");
		panel_2.add(lblNewLabel_4);
		
		textNumber2 = new JTextField();
		panel_2.add(textNumber2);
		textNumber2.setColumns(5);
		
		JButton btnFinal = new JButton("Final!!!");
		
		btnFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				
				int num1 = Integer.parseInt(textNumber1.getText());
				int num2 = Integer.parseInt(textNumber2.getText());

				
				TemperatureRequest request= TemperatureRequest.newBuilder()
						.setNumbers(5).setStart(num1).setIncrement(num2).build();
				
				StreamObserver<TemperatureResponse> responseObserver= new StreamObserver<TemperatureResponse>(){

					int count =0;
					
					@Override
					public void onNext(TemperatureResponse value) {
						// TODO Auto-generated method stub
						System.out.println("Next temperature: " + value.getTemperature()+"°C");
						
						textArea.append("Next temperature: " + value.getTemperature()+"°C \n");
						
						
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
						textArea.append("stream is completed ... received "+ count);
						
					}
					
				};
				asyncStub1.changeTemperature(request, responseObserver);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException p) {
					// TODO Auto-generated catch block
					p.printStackTrace();
				}	
				

			}
		});
		
		
		
		panel_2.add(btnFinal);
		
		textArea = new JTextArea(3,30);
		
		textArea .setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane1 = new JScrollPane(textArea);
		
		
		panel_2.add(scrollPane1);
		
		
		
		
		
		
		//3rd panel
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JLabel lblNewLabel_5 = new JLabel("Temperature Guess");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_3.add(lblNewLabel_5);
		
		
		JLabel lblNewLabel_6 = new JLabel("Temperature:");
		panel_3.add(lblNewLabel_6);
		
		textNumber3 = new JTextField();
		panel_3.add(textNumber3);
		textNumber3.setColumns(7);
		
		JLabel lblNewLabel_7 = new JLabel("Date:");
		panel_3.add(lblNewLabel_7);
		
		textNumber4 = new JTextField();
		panel_3.add(textNumber4);
		textNumber4.setColumns(9);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//String date = textNumber4.getText();
				
				date.add(textNumber4.getText());
				temp.add(Integer.parseInt(textNumber3.getText()));
				JOptionPane.showMessageDialog(null,"ADDED");
			}
		});
		panel_3.add(btnAdd);
		
		JButton btnFinish = new JButton("Finish!!!");
		btnFinish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent l) {
				// TODO Auto-generated method stub
				
				
				StreamObserver<SuggestResponse> responseObserver= new StreamObserver<SuggestResponse>() {

					int count=0;
					@Override
					public void onNext(SuggestResponse value) {
						// TODO Auto-generated method stub
						System.out.println("Your guess "+ value.getTemp()+" is " + value.getGuess() + ". Date: "+ value.getDate() );
						textArea2.append("Your guess "+ value.getTemp()+" is " + value.getGuess() + ". Date: "+ value.getDate() );
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
						textArea2.append("Stream is completed ... received "+ count+ " gusses");
					}
					
				};

				StreamObserver<SuggestRequest> requestObserver = asyncStub1.suggestTemperature(responseObserver);
				
				try {

					for(int i=0; i<date.size(); i++) {
						requestObserver.onNext(SuggestRequest.newBuilder().setDate(date.get(i)).setTemp(temp.get(i)).build());
						Thread.sleep(500);
					}
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
			
		});
		
		panel_3.add(btnFinish);
				
		textArea2 = new JTextArea(3,30);
		
		textArea2 .setLineWrap(true);
		textArea2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		
		
		panel_3.add(scrollPane2);
		
	}
}
