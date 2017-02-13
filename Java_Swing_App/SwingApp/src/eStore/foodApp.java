package eStore;

import java.awt.EventQueue;
import eStore.dbutilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import eStore.DbInit;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class foodApp {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					foodApp window = new foodApp();
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
	public foodApp() {
		DbInit db = new DbInit(); 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 697, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setBounds(48, 149, 56, 16);
		frame.getContentPane().add(lblUserid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(48, 196, 56, 16);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(116, 146, 207, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 193, 207, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e)
			{
				//check for match in ID 
			if(textField.getText().contains("@foods"))
			{
				if (validate_login(textField.getText(),passwordField.getText(),"Employee"))
				{
					String pv = dbutilities.getPrivilege(textField.getText()); 
					if (pv.equals("DL")) //Dealer
					{
						frame.setVisible(false);
						
						dealerUI dui =  new dealerUI(textField.getText()); 
						dui.setVisible(true);
					}
					else if(pv.equals("DA")) //Delivery admin
					{
						frame.setVisible(false);
						deliveryUI dl = new deliveryUI();
						dl.setVisible(true);
						
					}
					else if (pv.equals("SU")) //Support
					{
						frame.setVisible(false);
						SupportUI sui = new SupportUI(textField.getText());
						sui.setVisible(true);
						
					}
					else if (pv.equals("MR")) //Support
					{
						frame.setVisible(false);
						managerUI mui = new managerUI();
						mui.setVisible(true);
						
					}
					
					else
					{
						JOptionPane.showMessageDialog(frame, "Sorry, User ID/Password doesn't match ");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Sorry, User ID/Password doesn't match ");
				}
			}
			else
			{
				if (validate_login(textField.getText(),passwordField.getText(),"Account"))
					{
						frame.setVisible(false);
						
						new homePage(textField.getText()).setVisible(true);
					}
				else
					JOptionPane.showMessageDialog(frame, "Sorry, User ID/Password doesn't match ");
					
					
				}
			}

			private boolean validate_login(String text,String password,String type)
			{
				
				return (password.equals(dbutilities.validatepwd(text,type )));
				
			}
		});
		btnLogin.setBounds(356, 192, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton btnNewButton = new JButton("New User ? Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Cdetails custDetail = new Cdetails();
				custDetail.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(121, 254, 221, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblWelcomeToFood = new JLabel("Welcome to Food Store...!! ");
		lblWelcomeToFood.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblWelcomeToFood.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToFood.setForeground(new Color(160, 82, 45));
		lblWelcomeToFood.setBounds(30, 29, 476, 53);
		frame.getContentPane().add(lblWelcomeToFood);
		
		JLabel lblNewLabel = new JLabel("Login to shop. ");
		lblNewLabel.setBounds(116, 107, 110, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
