package eStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class helpUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					helpUI frame = new helpUI("temp");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public helpUI(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToCustomer = new JLabel("Welcome to Customer Support");
		lblWelcomeToCustomer.setBounds(5, 5, 604, 38);
		contentPane.add(lblWelcomeToCustomer);
		
		JLabel lblTypeYouQuery = new JLabel("Type you query");
		lblTypeYouQuery.setBounds(15, 68, 138, 27);
		contentPane.add(lblTypeYouQuery);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(25, 100, 547, 149);
		contentPane.add(textArea);
		
		JButton btnContactUs = new JButton("Send your query");
		btnContactUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbutilities.createSupportTicket(user, textArea.getText());
				JOptionPane.showMessageDialog(contentPane,"Your concern has been noted as we will resolve it as soon as possible");
				contentPane.setVisible(false);
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
	        	frame.dispose();
			}
		});
		btnContactUs.setBounds(25, 284, 146, 25);
		contentPane.add(btnContactUs);
	}
}
