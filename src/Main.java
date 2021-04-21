import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Main extends JFrame {

	public static Main m;

	public static AddAccident aa = new AddAccident(m);
	public static FindAccident fa = new FindAccident(m);
	public static SearchAccidents sa = new SearchAccidents(m);

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_3;
	private JTextField txtDoNotType;
	private JTextField txtDoNotType_1;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setTitle("CIS 452 Final Project - Cole Wagner");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1296, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		/*
		 * Note: Much of this class was created with the assistance of
		 * WindowBuilder. If editing, I highly suggest you use this extension
		 * from Eclipse, the naming conventions in this class are auto-generated
		 * and therefore mildly unorganized.
		 */
		
		JLabel lblNewLabel = new JLabel("Add a New Accident");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(59, 46, 164, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Find an Accident");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(343, 46, 137, 27);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(36, 105, 143, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Date: \"yyyy-mm-dd\"");
		lblNewLabel_3.setBounds(36, 90, 143, 17);
		contentPane.add(lblNewLabel_3);

		textField_1 = new JTextField();
		textField_1.setBounds(74, 135, 103, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("City:");
		lblNewLabel_4.setBounds(36, 137, 62, 19);
		contentPane.add(lblNewLabel_4);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 255, 230, 133);
		contentPane.add(textArea);

		JLabel lblNewLabel_5 = new JLabel("Vehicles Involved:");
		lblNewLabel_5.setBounds(20, 200, 162, 19);
		contentPane.add(lblNewLabel_5);

		textField_2 = new JTextField();
		textField_2.setBounds(73, 169, 105, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("State:");
		lblNewLabel_6.setBounds(35, 172, 62, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("vin:damages:driver_ssn,");
		lblNewLabel_7.setBounds(20, 216, 158, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_7_1 = new JLabel("vin:damages:driver_ssn,");
		lblNewLabel_7_1.setBounds(20, 230, 158, 14);
		contentPane.add(lblNewLabel_7_1);

		JButton btnNewButton = new JButton("Submit Accident Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || textArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new Main(), "Please fill out all fields!");
					return;
				}
				
				try {
					aa.rowCount("accidents");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				int aid = aa.rows+1;
				aa.insertAccident(aid, textField.getText(), textField_1.getText().toUpperCase(), textField_2.getText().toUpperCase());

				String driversInvolved[] = textArea.getText().split(",");
				for(int i = 0; i < driversInvolved.length; i++) {
					String split[] = driversInvolved[i].split(":");
					aa.insertInvolvement(aid, split[0], Integer.valueOf(split[1]), split[2]);
				}

				JOptionPane.showMessageDialog(new Main(), "Thank you for submitting your accident report!");

			}
		});
		buttonGroup.add(btnNewButton);
		btnNewButton.setBounds(20, 434, 212, 31);
		contentPane.add(btnNewButton);

		textField_3 = new JTextField();
		textField_3.setBounds(343, 101, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Accident ID:");
		lblNewLabel_8.setBounds(343, 86, 86, 17);
		contentPane.add(lblNewLabel_8);

		JTextArea txtrDoNotType = new JTextArea();
		txtrDoNotType.setText("do not type here..");
		txtrDoNotType.setBounds(322, 344, 260, 133);
		contentPane.add(txtrDoNotType);
		txtrDoNotType.setVisible(false);

		txtDoNotType = new JTextField();
		txtDoNotType.setText("do not type here..");
		txtDoNotType.setBounds(322, 182, 171, 20);
		contentPane.add(txtDoNotType);
		txtDoNotType.setColumns(10);
		txtDoNotType.setVisible(false);

		JLabel lblNewLabel_9 = new JLabel("Date of Accident:");
		lblNewLabel_9.setBounds(322, 166, 137, 19);
		contentPane.add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);

		txtDoNotType_1 = new JTextField();
		txtDoNotType_1.setText("do not type here..");
		txtDoNotType_1.setToolTipText("");
		txtDoNotType_1.setBounds(322, 239, 171, 19);
		contentPane.add(txtDoNotType_1);
		txtDoNotType_1.setColumns(10);
		txtDoNotType_1.setVisible(false);

		JLabel lblNewLabel_10 = new JLabel("Location of Accident:");
		lblNewLabel_10.setBounds(322, 218, 171, 31);
		contentPane.add(lblNewLabel_10);
		lblNewLabel_10.setVisible(false);

		JLabel lblNewLabel_11 = new JLabel("Vehicles Involved:");
		lblNewLabel_11.setBounds(322, 290, 137, 19);
		contentPane.add(lblNewLabel_11);
		lblNewLabel_11.setVisible(false);

		JLabel lblNewLabel_12 = new JLabel("vin:damages:driver_ssn,");
		lblNewLabel_12.setBounds(322, 329, 204, 14);
		contentPane.add(lblNewLabel_12);
		lblNewLabel_12.setVisible(false);

		JLabel lblNewLabel_12_1 = new JLabel("vin:damages:driver_ssn,");
		lblNewLabel_12_1.setBounds(322, 319, 204, 14);
		contentPane.add(lblNewLabel_12_1);
		lblNewLabel_12_1.setVisible(false);

		JButton btnNewButton_1 = new JButton("Find!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textField_3.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new Main(), "Please enter an accident id number!");
					return;
				}

				lblNewLabel_9.setVisible(true);
				lblNewLabel_10.setVisible(true);
				lblNewLabel_11.setVisible(true);
				lblNewLabel_12.setVisible(true);
				lblNewLabel_12_1.setVisible(true);
				txtrDoNotType.setVisible(true);
				txtDoNotType.setVisible(true);
				txtDoNotType_1.setVisible(true);

				txtrDoNotType.setText("");
				fa.involved.clear();

				fa.getByAccidentByID(Integer.valueOf(textField_3.getText()));
				txtDoNotType.setText(fa.date);
				txtDoNotType_1.setText(fa.location);
				fa.getByIvolvementByID(Integer.valueOf(textField_3.getText()));
				txtrDoNotType.setText("");
				for(int i = 0; i < fa.involved.size(); i++) {
					txtrDoNotType.setText(txtrDoNotType.getText() + fa.involved.get(i) + "\n");
				}


			}
		});
		buttonGroup_1.add(btnNewButton_1);
		btnNewButton_1.setBounds(340, 131, 89, 23);
		contentPane.add(btnNewButton_1);




		JLabel lblNewLabel_13 = new JLabel("From this date:");
		lblNewLabel_13.setBounds(728, 84, 114, 33);
		contentPane.add(lblNewLabel_13);

		textField_4 = new JTextField();
		textField_4.setBounds(728, 117, 103, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("To this date:");
		lblNewLabel_14.setBounds(852, 89, 103, 19);
		contentPane.add(lblNewLabel_14);

		textField_5 = new JTextField();
		textField_5.setBounds(850, 117, 110, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Average Damage To Vehicles");
		lblNewLabel_15.setBounds(761, 160, 178, 30);
		contentPane.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("From this price:");
		lblNewLabel_16.setBounds(728, 202, 103, 14);
		contentPane.add(lblNewLabel_16);

		textField_6 = new JTextField();
		textField_6.setBounds(728, 227, 103, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("To this price:");
		lblNewLabel_17.setBounds(852, 202, 103, 14);
		contentPane.add(lblNewLabel_17);

		textField_7 = new JTextField();
		textField_7.setBounds(853, 227, 107, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Total Damage to Vehicles");
		lblNewLabel_18.setBounds(761, 269, 164, 31);
		contentPane.add(lblNewLabel_18);

		JLabel lblNewLabel_16_1 = new JLabel("From this price:");
		lblNewLabel_16_1.setBounds(728, 305, 114, 14);
		contentPane.add(lblNewLabel_16_1);

		JLabel lblNewLabel_17_1 = new JLabel("To this price:");
		lblNewLabel_17_1.setBounds(852, 305, 126, 14);
		contentPane.add(lblNewLabel_17_1);

		textField_8 = new JTextField();
		textField_8.setBounds(728, 326, 103, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setBounds(852, 326, 103, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(988, 61, 264, 404);
		contentPane.add(textArea_1);
		textArea_1.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("aid:accident date:city, state");
		lblNewLabel_2.setBounds(1042, 31, 178, 19);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);

		JButton btnNewButton_2 = new JButton("Search!");
		btnNewButton_2.setBounds(794, 398, 89, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textArea_1.setVisible(true);
				lblNewLabel_2.setVisible(true);
				sa.finalSet.clear();
				
				sa.currentAIDs.clear();
				sa.avgDmg.clear();
				sa.justAID.clear();
				sa.sharedAID.clear();
				sa.totalDmg.clear();
				
				if(!textField_4.getText().isEmpty()) {
					sa.date1 = textField_4.getText();
				}
				if(!textField_5.getText().isEmpty()) {
					sa.date2 = textField_5.getText();
				}
				
				if(!textField_6.getText().isEmpty()) {
					sa.avgDmg1 = Integer.valueOf(textField_6.getText());
				}
				if(!textField_7.getText().isEmpty()) {
					sa.avgDmg2 = Integer.valueOf(textField_7.getText());
				}
				
				if(!textField_8.getText().isEmpty()) {
					sa.totalDmg1 = Integer.valueOf(textField_8.getText());
				}
				if(!textField_9.getText().isEmpty()) {
					sa.totalDmg2 = Integer.valueOf(textField_9.getText());
				}
				
				sa.getByAccidentByDateRange();
				sa.getByAccidentByAverageDamageRange();
				sa.getByAccidentByAvg();
				sa.getTotal();
				sa.getAvg();
				
				for(int i = 0; i < sa.currentAIDs.size(); i++) {
					sa.getFinalSet(sa.currentAIDs.get(i));
				}
				
				textArea_1.setText("");
				for(int i = 0; i < sa.finalSet.size(); i++) {
					textArea_1.setText(textArea_1.getText() + sa.finalSet.get(i) + "\n");
				}
				
				System.out.println(sa.currentAIDs);
				
			}
		});
		JLabel lblNewLabel_1_1 = new JLabel("Search for an Accident");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(738, 48, 217, 27);
		contentPane.add(lblNewLabel_1_1);
		textField_7.setColumns(10);

	}
}
