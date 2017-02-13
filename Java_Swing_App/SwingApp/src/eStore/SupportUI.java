package eStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class SupportUI extends JFrame
{
	private JPanel contentPane;
	private JTextField supportTicketID;
	private JTextField problemDetails;
	private JTextField reporter;
	private JTextField assignee;
	private JTextField proposedSolution;
	
	private JComboBox<String> status;
	
	static int i =0; 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupportUI frame = new SupportUI("main");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SupportUI(String user)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome "+user);
		lblWelcome.setBounds(12, 13, 327, 29);
		contentPane.add(lblWelcome);
		
		supportTicketID = new JTextField();
		supportTicketID.setBounds(246, 55, 216, 22);
		contentPane.add(supportTicketID);
		supportTicketID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Support Ticket ID");
		lblNewLabel.setBounds(22, 58, 156, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Problem Details");
		lblNewLabel_1.setBounds(22, 111, 112, 16);
		contentPane.add(lblNewLabel_1);
		
		problemDetails = new JTextField();
		problemDetails.setBounds(246, 108, 527, 69);
		contentPane.add(problemDetails);
		problemDetails.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Reporter");
		lblNewLabel_2.setBounds(22, 218, 130, 22);
		contentPane.add(lblNewLabel_2);
		
		reporter = new JTextField();
		reporter.setBounds(246, 218, 216, 22);
		contentPane.add(reporter);
		reporter.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Assignee");
		lblNewLabel_3.setBounds(24, 277, 85, 16);
		contentPane.add(lblNewLabel_3);
		
		assignee = new JTextField();
		assignee.setBounds(246, 277, 216, 22);
		contentPane.add(assignee);
		assignee.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Proposed Solution");
		lblNewLabel_4.setBounds(22, 329, 156, 16);
		contentPane.add(lblNewLabel_4);
		
		proposedSolution = new JTextField();
		proposedSolution.setBounds(246, 312, 537, 115);
		contentPane.add(proposedSolution);
		proposedSolution.setColumns(10);
		
		 String[] choices = { "Open","In Progress", "Closed"};
		 DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(choices);
		 status = new JComboBox(comboModel);
		 
		 status.setBounds(246,442,216,22);
		 contentPane.add(status);
		 
		 JLabel lblNewLabel_5 = new JLabel("Status");
			lblNewLabel_5.setBounds(22, 445, 156, 16);
			contentPane.add(lblNewLabel_5);
			
			ArrayList<SupportTicket> s=dbutilities.getSupportTicketList(user);
			
			
			JButton btnSave = new JButton("Save");
			 
			if(s.isEmpty())
			{
				JOptionPane.showMessageDialog(contentPane,"No Ticket for you..!!! "); 
				
			}
			else
			{
				supportTicketID.setText(String.valueOf(s.get(i).getSupportTicketID()) );
				problemDetails.setText(s.get(i).getProblemDetails() );
				reporter.setText(s.get(i).reporter);
				assignee.setText(user);
				
			}
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SupportTicket n=new SupportTicket();
					
					
						n.supportTicketID=s.get(i).getSupportTicketID();
						n.problemDetails=s.get(i).getProblemDetails();
						n.reporter=s.get(i).getReporter();
						n.assignee=user;
						n.proposedSolution=proposedSolution.getText();
						n.status=(String) status.getSelectedItem();
						dbutilities.updateSupportListDetails(n);
						JOptionPane.showMessageDialog(contentPane,"Ticket has been updated");
					
				}
			});
			btnSave.setBounds(242, 504, 97, 25);
			contentPane.add(btnSave);
			
			JButton btnShowNext = new JButton("Show Next");
			btnShowNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					i++;
					if(s.size()>i)
					{
						supportTicketID.setText(String.valueOf(s.get(i).getSupportTicketID() ));
						problemDetails.setText(s.get(i).getProblemDetails() );
						reporter.setText(s.get(i).reporter);
						assignee.setText(user);
					}
					else
					{
						JOptionPane.showMessageDialog(contentPane,"No Ticket for you..!!! ");
					}
				}
			});
			btnShowNext.setBounds(365, 504, 97, 25);
			contentPane.add(btnShowNext);
			
			JButton btnLogout = new JButton("Logout");
			btnLogout.setBounds(497, 504, 97, 25);
			contentPane.add(btnLogout);
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
		        	frame.dispose();

		        	new foodApp().frame.setVisible(true);
				}});
		
		
	}
}