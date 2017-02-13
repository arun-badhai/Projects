package eStore;
import java.sql.*;
import eStore.dbutilities;
import eStore.Customer;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
//INSERT INTO `inventory`.`account` (`customeID`, `customerFName`, `customerLName`, `customerAddress`, `addressState`, `zip`, `nameonCard`, `cardNumber`, `CVV`, `expiryDate`, `password`) VALUES ('gout.kannan', 'Goutham', 'Kannan', '2801 S King Dr', 'IL', '60616', 'Goutham Kannan', '525252525252525252', '100', '02/20', 'yo');
public class Cdetails extends JFrame {

	private JPanel contentPane;
	private JTextField textFName;
	private JTextField textLName;
	private JTextField textuserid;
	
	private JTextField textNameCard;
	private JTextField textZip;
	private JTextField textcardNumber;
	private JTextField textCVV;
	private JTextField textExp;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textState;
	private JTextPane textAddress;


	/**
	 * Create the frame.
	 */
	public Cdetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 693);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFirstName = new JLabel("First Name ");
		lblFirstName.setBounds(43, 48, 102, 22);
		
		JLabel lblLastName = new JLabel("Last Name ");
		lblLastName.setBounds(43, 97, 102, 22);
		
		JLabel lblEmailId = new JLabel("User ID ");
		lblEmailId.setBounds(43, 149, 102, 22);
		
		JLabel lblFirstName_1 = new JLabel("Password");
		lblFirstName_1.setBounds(43, 208, 102, 22);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(43, 257, 122, 22);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(43, 315, 102, 22);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(43, 377, 102, 22);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(374, 380, 61, 22);
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setBounds(43, 468, 82, 16);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(43, 520, 82, 16);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setBounds(43, 554, 24, 16);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setBounds(43, 596, 64, 16);
		
		textFName = new JTextField();
		textFName.setToolTipText("Enter First Name");
		textFName.setBounds(181, 48, 179, 22);
		textFName.setColumns(10);
		
		textLName = new JTextField();
		textLName.setToolTipText("Enter Last Name");
		textLName.setBounds(181, 97, 179, 22);
		textLName.setColumns(10);
		
		textuserid = new JTextField();
		textuserid.setToolTipText("Enter Login Id");
		textuserid.setBounds(181, 149, 179, 22);
		textuserid.setColumns(10);
		

		
		textAddress = new JTextPane();
		textAddress.setBounds(181, 301, 194, 63);
		
		textState = new JTextField();
		textState.setBounds(181, 385, 116, 22);
		textState.setColumns(10);
		
		textNameCard = new JTextField();
		textNameCard.setBounds(189, 465, 186, 22);
		textNameCard.setColumns(10);
		
		textZip = new JTextField();
		textZip.setBounds(447, 380, 116, 22);
		textZip.setColumns(10);
		
		textcardNumber = new JTextField();
		textcardNumber.setBounds(189, 514, 186, 22);
		textcardNumber.setColumns(10);
		
		textCVV = new JTextField();
		textCVV.setBounds(189, 554, 186, 22);
		textCVV.setColumns(10);
		
		textExp = new JTextField();
		textExp.setBounds(189, 595, 116, 22);
		textExp.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(332, 592, 90, 25);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
			// Needs to insert data into the customer table  
				String msg[]=new String[25];
				int i=0,flag=0;
				try {
					Customer cobj= new Customer(); 
					
					
					if(textFName.getText().equals(""))
					{
						msg[i]="No data entered for first name"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null,"No Data Entered");
					}
					else if(!(textFName.getText().matches("[a-zA-Z]*")))
					{
						msg[i]="Enter only alphabets for first name"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "Enter only alphabets");
					}
					else
						cobj.custFName = textFName.getText();

					
					if(textLName.getText().equals(""))
					{
						msg[i]="No data entered for last name"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null,"No Data Entered");
					}
					else if(!(textLName.getText().matches("[a-zA-Z]*")))
					{
						msg[i]="Enter only alphabets for last name"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "Enter only alphabets");
					}
					else
						cobj.custLName  = textLName.getText();

					//String EMAIL_REGEX = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
					
					if(textuserid.getText().equals(""))
					{
						msg[i]="No data entered for email address"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "Enter valid email address");
					}
					else
						cobj.custID = textuserid.getText();
					/*if(!(cobj.custID.matches(EMAIL_REGEX)))
					{
						JOptionPane.showMessageDialog(null, "Enter valid email address");
					}*/

					
					if(textAddress.getText().equals(""))
					{
						
						msg[i]="Enter valid address,";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null,"No Data Entered");
					}
					else
						cobj.custAddress = textAddress.getText();

					
					if(textState.getText().equals(""))
					{
						msg[i]="No Data entered for state"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null,"No Data Entered");
					}
					else
						cobj.custState = textState.getText();
					
					

					if(passwordField.getText().equals(passwordField_1.getText()))
						cobj.custPswd = passwordField.getText().toString();
					else
						cobj.custPswd="temp";
					
				
					
					if(textZip.getText().length() !=5)
					{

						msg[i]="Enter valid zip"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "Enter Valid Zip");
					}
					else
						cobj.custZip = Integer.parseInt(textZip.getText());
					
					
					if(textNameCard.getText().equals(""))
					{
						msg[i]="No Data entered for name on card"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null,"No Data Entered");
					}
					
						
					else if(!(textNameCard.getText().matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"   )))
					{
						msg[i]="Enter valid name on card"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "Enter valid Name");
					}
					else
						cobj.nameonCard = textNameCard.getText();
				
					
					
					if( textcardNumber.getText().length()!=16  )
					{
						msg[i]="Enter valid card number"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "Enter valid card number");
					}
					else
						cobj.cardNumber = Long.parseLong(textcardNumber.getText());
						
					
					
					
					
					if(textCVV.getText().length()!=3)
					{
						msg[i]="CVV should be only 3 numbers"+"\n";
						i++;
						++flag;
						//JOptionPane.showMessageDialog(null, "CVV must be 3 numbers");
					}
					else
						cobj.cvv = Integer.parseInt(textCVV.getText());
					
					
					if(!(textExp.getText().matches("\\d{2}\\/\\d{2}")))
					{
						msg[i]="Enter correct date"+"\n";
						i++;
						++flag;
					}
					else
						cobj.exp = textExp.getText();
	
					
				
					// get Db connection 
					if(flag!=0)
					{
						StringBuilder strBuilder = new StringBuilder();
						for (int j = 0; j < msg.length; j++) {
							if(msg[j]!=null)
								strBuilder.append(msg[j]);
						}
						String newString = strBuilder.toString();
						if(flag!=0)
						JOptionPane.showMessageDialog(null,newString);
					}
					else
					{
					
						dbutilities db = new dbutilities();
					
						int err = db.updateCustomerDetails(cobj);
						if (err==1)
						{
							JOptionPane.showMessageDialog(contentPane,"Welcome aboard "+ cobj.custID +" your account has been registered");
							contentPane.setVisible(false);
							JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
				        	frame.dispose();
	
				        	new foodApp().frame.setVisible(true);
						}
						else if(err==2)
						{
							JOptionPane.showMessageDialog(contentPane,"Sorry "+ cobj.custID +" already exists, try a different one");
						}
						else
						{
							JOptionPane.showMessageDialog(contentPane,"Sorry registration falied ");
						}
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,"Sorry registration falied ");
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,"Sorry registration falied ");
				}
			
			
			
			      
			}
		});
		btnSubmit.setBackground(new Color(154, 205, 50));
		
		JLabel lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setBounds(17, 16, 116, 19);
		lblAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblCardDetails = new JLabel("Card Details");
		lblCardDetails.setBounds(17, 420, 546, 19);
		lblCardDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter Password");
		passwordField.setBounds(181, 199, 179, 22);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(183, 257, 177, 22);
		contentPane.setLayout(null);
		contentPane.add(lblFirstName);
		contentPane.add(lblLastName);
		contentPane.add(lblEmailId);
		contentPane.add(lblFirstName_1);
		contentPane.add(textuserid);
		contentPane.add(textLName);
		contentPane.add(textFName);
		contentPane.add(passwordField);
		contentPane.add(lblConfirmPassword);
		contentPane.add(lblAddress);
		contentPane.add(lblState);
		contentPane.add(lblCardNumber);
		contentPane.add(lblNameOnCard);
		contentPane.add(lblCvv);
		contentPane.add(lblExpiryDate);
		contentPane.add(textExp);
		contentPane.add(btnSubmit);
		contentPane.add(textCVV);
		contentPane.add(textcardNumber);
		contentPane.add(textNameCard);
		contentPane.add(lblZip);
		contentPane.add(textZip);
		contentPane.add(passwordField_1);
		
		contentPane.add(lblAccountDetails);
		contentPane.add(lblCardDetails);
	
		contentPane.add(textAddress);
		contentPane.add(textState);
		
		  JButton btnCancel_1 = new JButton("Cancel");
	        btnCancel_1.setBounds(447, 592, 97, 25);
	        contentPane.add(btnCancel_1);
	        btnCancel_1.setBackground(new Color(154, 205, 50));
	        btnCancel_1.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                contentPane.setVisible(false);
	                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
	                frame.dispose();
	                new foodApp().frame.setVisible(true);
	            }
	        });
	}
}
