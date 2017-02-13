package eStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class dealerUI extends JFrame {

	private JPanel contentPane;
	private JTextField textidItem;
	private JTextField textitemName;
	private JTextField textPrice;
	private JTextField textQty;
	private JTextField textDescription;
	private JTextField textDiscount;
	private JTextField texticonFilename;
	private JTable table;
	private JFileChooser fc;

	

	/**
	 * Create the frame.
	 */
	public dealerUI(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome "+user);
		lblWelcome.setBounds(12, 13, 327, 29);
		contentPane.add(lblWelcome);
		
		textidItem = new JTextField();
		textidItem.setBounds(146, 55, 116, 22);
		contentPane.add(textidItem);
		textidItem.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Item ID");
		lblNewLabel.setBounds(22, 58, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name");
		lblNewLabel_1.setBounds(22, 111, 112, 16);
		contentPane.add(lblNewLabel_1);
		
		textitemName = new JTextField();
		textitemName.setBounds(146, 108, 116, 22);
		contentPane.add(textitemName);
		textitemName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(385, 58, 85, 16);
		contentPane.add(lblNewLabel_2);
		
		textPrice = new JTextField();
		textPrice.setBounds(509, 55, 116, 22);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(385, 111, 85, 16);
		contentPane.add(lblNewLabel_3);
		
		textQty = new JTextField();
		textQty.setText("1");
		textQty.setBounds(509, 108, 116, 22);
		contentPane.add(textQty);
		textQty.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Description");
		lblNewLabel_4.setBounds(22, 159, 75, 16);
		contentPane.add(lblNewLabel_4);
		
		textDescription = new JTextField();
		textDescription.setBounds(146, 156, 116, 22);
		contentPane.add(textDescription);
		textDescription.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Discount");
		lblNewLabel_5.setBounds(385, 159, 85, 16);
		contentPane.add(lblNewLabel_5);
		
		textDiscount = new JTextField();
		textDiscount.setText("0");
		textDiscount.setBounds(509, 156, 116, 22);
		contentPane.add(textDiscount);
		textDiscount.setColumns(10);
		
		JButton saveButton = new JButton("Save Details");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textidItem.getText().isEmpty())
				{	
					Store s = new Store();
					s.idItem = textidItem.getText(); 
					s.itemName = textitemName.getText();
					s.dealerName = user; 
					s.itemDescription= textDescription.getText();
					s.price = Float.parseFloat(textPrice.getText());
					s.discount = Float.parseFloat(textDiscount.getText());
					s.icon_filename = texticonFilename.getText(); 
					s.stock = Integer.parseInt(textQty.getText());
					s.isnew ="Y";
					
					dbutilities.updateStoreListDetails(s);
				}
				table.setModel(DbUtils.resultSetToTableModel(dbutilities.dealerdata() ));
				
				
			
			
			
			}
		});
		saveButton.setBounds(695, 55, 125, 25);
		contentPane.add(saveButton);
		
		JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(22, 202, 56, 16);
		contentPane.add(lblIcon);
		
		texticonFilename = new JTextField();
		texticonFilename.setBounds(146, 199, 176, 22);
		contentPane.add(texticonFilename);
		texticonFilename.setColumns(10);
		fc = new JFileChooser(); 
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		JButton openbutton = new JButton("browse");
		openbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
		            int returnVal = fc.showOpenDialog(dealerUI.this);

		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                File file = fc.getSelectedFile();
		                texticonFilename.setText(file.getAbsolutePath().toString());
		            }
				
			}
		});
		openbutton.setBounds(365, 202, 97, 25);
		contentPane.add(openbutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 282, 837, 291);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
	        	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
	        	frame.dispose();

	        	new foodApp().frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(696, 107, 124, 25);
		contentPane.add(btnNewButton);
	}
}
