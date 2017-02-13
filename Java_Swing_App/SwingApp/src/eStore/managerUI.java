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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class managerUI extends JFrame {

	private JPanel contentPane;
	static int i =0;
	private JComboBox<String> assignees;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerUI frame = new managerUI();
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
	public managerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTicketDescription = new JLabel("Ticket Description");
		lblTicketDescription.setBounds(37, 54, 121, 16);
		contentPane.add(lblTicketDescription);
		
		JTextArea problemDetails = new JTextArea();
		problemDetails.setBounds(37, 97, 682, 118);
		contentPane.add(problemDetails);
		
		JLabel lblAssignTo = new JLabel("Assign To");
		lblAssignTo.setBounds(51, 257, 56, 16);
		contentPane.add(lblAssignTo);
		
		List<String> ls= dbutilities.getEmployees();
		String[] choices = new String[ls.size()];
		choices = ls.toArray(choices);
		ArrayList<SupportTicket> s=dbutilities.getSupportTicketList();
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(choices);
		assignees = new JComboBox<String>(comboModel);
		
		assignees.setBounds(137, 254, 193, 22);
		contentPane.add(assignees);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbutilities.setAssignee(assignees.getSelectedItem().toString(),s.get(i).supportTicketID);
				JOptionPane.showMessageDialog(contentPane," Ticket has been assigned..!!! "); 
			}
		});
		
		btnAssign.setBounds(415, 253, 97, 25);
		contentPane.add(btnAssign);
		
		
		if(s.isEmpty())
		{
			JOptionPane.showMessageDialog(contentPane,"No Ticket for you..!!! "); 
			
		}
		else
		{
			//supportTicketID.setText(String.valueOf(s.get(i).getSupportTicketID()) );
			problemDetails.setText(s.get(i).getProblemDetails() );
			//reporter.setText(s.get(i).reporter);
		
			
		}
		
		
		JButton btnNextTicket = new JButton("Next Ticket");
		btnNextTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i++;
				if(s.size()>i)
				{
					
					problemDetails.setText(s.get(i).getProblemDetails() );
					
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane,"No Ticket for you..!!! ");
				}
			}
		});
		btnNextTicket.setBounds(530, 253, 136, 25);
		contentPane.add(btnNextTicket);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
	        	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
	        	frame.dispose();

	        	new foodApp().frame.setVisible(true);
			}
		});
		btnLogout.setBounds(639, 253, 97, 25);
		contentPane.add(btnLogout);
	}
}
