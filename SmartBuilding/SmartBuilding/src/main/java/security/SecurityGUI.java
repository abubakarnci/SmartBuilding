package security;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
import security.SecurityServiceGrpc.SecurityServiceBlockingStub;
import security.SecurityServiceGrpc.SecurityServiceStub;


public class SecurityGUI {

	
	private static SecurityServiceBlockingStub blockingStub2;
	private static  SecurityServiceStub asyncStub2;
	
	private ServiceInfo securityServiceInfo;
	
	private JFrame frame;
	private JTextArea textResponse;
	private JTextArea textArea;
	private JTextArea textArea2;

	private JTextField textNumber1;
	private JTextField textNumber2;

	private JTextField textNumber3;
	private JTextField textNumber4;
	
	ArrayList<String> name=new ArrayList<String>();
	ArrayList<String> id=new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecurityGUI window = new SecurityGUI();
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
	public SecurityGUI() {
		
		String security_service_type = "_security._tcp.local.";
		discoverSecurityService(security_service_type);
		
		String host = "localhost"; //heatServiceInfo.getHostAddresses()[0];
		int port = 50053; //heatServiceInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();
		
		blockingStub2 = SecurityServiceGrpc.newBlockingStub(channel);

		asyncStub2 = SecurityServiceGrpc.newStub(channel);
		
		
		
		initialize();
	}
	
	

	private void discoverSecurityService(String service_type) {
		// TODO Auto-generated method stub
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Security Service resolved: " + event.getInfo());

					securityServiceInfo = event.getInfo();

					int port = securityServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + securityServiceInfo.getNiceTextString());
					System.out.println("\t host: " + securityServiceInfo.getHostAddresses()[0]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Security Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Security Service added: " + event.getInfo());

					
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
	
	
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		JLabel lblNewLabel_1 = new JLabel("Security Switch");
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
				
				SecurityRequest request= SecurityRequest.newBuilder().setSecurity(input).build();
				
				SecurityResponse response= blockingStub2.securitySwitch(request);
				
				if(response.getSecurity()==true) {
					System.out.println("Server response: Security turned ON ");
					textResponse.append("Server response: Security turned ON \n");
					
				}
				else {
					System.out.println("Server response: Security turned OFF ");
					textResponse.append("Server response: Security turned OFF \n");
					
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
		
		JLabel lblNewLabel_2 = new JLabel("Staff in office");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_2.add(lblNewLabel_2);
		
		JButton btnFinal = new JButton("Check now!!!");
		
		btnFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				
				ListRequest request= ListRequest.newBuilder().setAsk("How many people are in building").build();
				
				StreamObserver<ListResponse> responseObserver= new StreamObserver<ListResponse>(){

					int count =0;
					@Override
					public void onNext(ListResponse value) {
						// TODO Auto-generated method stub
						
						System.out.println("Next Staff: " + value.getAns());
						
						textArea.append("Next Staff: " + value.getAns()+"\n");
						count += 1;
						
						
					}

					@Override
					public void onError(Throwable t) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						
						System.out.println("stream is completed ... received "+ count);
						textArea.append("stream is completed ... received "+ count);
						
						
					}

				
					
				};
				asyncStub2.liststaff(request, responseObserver);
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
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("Grant Staff Access");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Name:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(lblNewLabel_4);
		
		textNumber1 = new JTextField();
		panel_3.add(textNumber1);
		textNumber1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Id:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(lblNewLabel_5);
		
		textNumber2 = new JTextField();
		panel_3.add(textNumber2);
		textNumber2.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//String date = textNumber4.getText();
				
				name.add(textNumber1.getText());
				id.add(textNumber2.getText());
				JOptionPane.showMessageDialog(null,"Details Added");
			}
		});
		panel_3.add(btnAdd);
		
		JButton btnDone = new JButton("Final!!!");
		
		btnDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent o) {
				// TODO Auto-generated method stub
				
				StreamObserver<AccessResponse> responseObserver = new StreamObserver<AccessResponse>() {

					@Override
					public void onNext(AccessResponse value) {
						// TODO Auto-generated method stub
						System.out.println(value.getReply());
						textArea2.append(value.getReply()+"\n");
					}

					@Override
					public void onError(Throwable t) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						System.out.println("stream is completed ...");
						textArea2.append("stream is completed ...");
						
						
					}					
				};
				StreamObserver<AccessRequest> requestObserver = asyncStub2.grantAccess(responseObserver);
				
				try {
					// sending stream of requests
					for(int i=0; i<name.size(); i++) {
						requestObserver.onNext(AccessRequest.newBuilder().setName(name.get(i)).setId(id.get(i)).build());
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
		
panel_3.add(btnDone);
		
		textArea2 = new JTextArea(2,30);
		
		textArea2 .setLineWrap(true);
		textArea2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		panel_3.add(scrollPane2);
		
		
		
	}

}
