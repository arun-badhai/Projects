package eStore;

import java.awt.BorderLayout;
import eStore.dbutilities;
import eStore.Customer;
import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class homePage extends JFrame {
	

	private JPanel contentPane;
	private JTable table;
	static int i=1;
	// new code 
	private JTable Checkouttable;
	private JTabbedPane tabbedPane;
	private Label Totallabel, lblYourTotal, lblAmountPayableOn;
	private JLabel lblShippingAddress;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtMmyy;
	private JTextField textField_2;
	private float total = 0;
	
	private JTextField textField_3;
	// new code end
	/**
	 * Create the panel.
	 */
	public  void showtable()
	{
		//dbutilities.getDafaultConnection();
		ArrayList<Store> data = new ArrayList<Store>();		
		data = dbutilities.getStoreList();
		//table.setModel(DbUtils.resultSetToTableModel(rs));
		
		String[] cols= {"Product Name","Description","icon","stock","Price","Discount","Add to checkout","Select Qty" };
		Object[][] rows = new Object[data.size()][8];
		for(int i=0;i < data.size();i++)
		{
			rows[i][0] = data.get(i).getItemName();
			rows[i][1] = data.get(i).getItemDescription();
			if(data.get(i).getIcon() !=null)
			{
				ImageIcon image =  new ImageIcon(new ImageIcon(data.get(i).getIcon() ).getImage().getScaledInstance(150, 120,Image.SCALE_DEFAULT) );
				rows[i][2] = image;
			}
			
			rows[i][3] = data.get(i).getStock();
			rows[i][4] = data.get(i).getPrice();
			rows[i][5] = data.get(i).getDiscount();
		
			rows[i][6] = data.get(i).isSelected();
			rows[i][7] = data.get(1).getSelectedQty();
			
			
		}
		tableModel tm = new tableModel(rows, cols);
		table.setModel(tm);
		table.setRowHeight(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		// For getting the checkbox - column 6 is the location of the check box 
		
	    TableColumn tc = table.getColumnModel().getColumn(6);   
	    tc.setCellEditor(table.getDefaultEditor(Boolean.class));   
	    tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));  
	    //tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));   
	}
	/**
	 * Create the frame.
	 */
	public homePage(String user) {
		
		String shipping_address =  dbutilities.getAddress(user); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1182, 884);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 	
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(32, 109, 1132, 715);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Shop", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 13, 858, 659);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);
			
	
		
		
		JLabel lblNewLabel = new JLabel("Welcome to Food Store "+user);
		lblNewLabel.setBounds(30, 23, 238, 49);
		contentPane.add(lblNewLabel);
		
		JTextArea txtrInstructionsToShop = new JTextArea();
		txtrInstructionsToShop.setBounds(265, 50, 835, 32);
		contentPane.add(txtrInstructionsToShop);
		txtrInstructionsToShop.setBackground(new Color(0, 139, 139));
		txtrInstructionsToShop.setForeground(new Color(255, 255, 255));
		txtrInstructionsToShop.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtrInstructionsToShop.setText("Instructions to shop: 1. Add Products to Cart ----> 2. Check out Cart ----> 3.Make Payment");
		
		/// new code 
		JPanel Checkoutpanel = new JPanel();
		Checkoutpanel.setForeground(new Color(0, 0, 0));
		
		tabbedPane.addTab("Checkout", null, Checkoutpanel, null);
		Checkoutpanel.setLayout(null);
		
		
		
		JScrollPane Scrollpanel1 = new JScrollPane();
		Scrollpanel1.setBounds(24, 13, 858, 500);
		Checkoutpanel.add(Scrollpanel1);
		Checkouttable = new JTable();
		Scrollpanel1.setViewportView(Checkouttable);
		Checkouttable.setBackground(Color.WHITE);
		Totallabel = new Label("Total Label");
		Totallabel.setBounds(605, 519, 200, 24);
		Checkoutpanel.add(Totallabel);
		Totallabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		JButton btnProceedToPayment = new JButton("Proceed to Payment");
		btnProceedToPayment.setBounds(917, 150, 167, 43);
		Checkoutpanel.add(btnProceedToPayment);
		
		JButton btnContinueShopping = new JButton("Continue Shopping");
		btnContinueShopping.setBounds(917, 221, 167, 43);
		Checkoutpanel.add(btnContinueShopping);
		
		JPanel Paymentpanel = new JPanel();
		Paymentpanel.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Payment", null, Paymentpanel, null);
		Paymentpanel.setLayout(null);
		//JScrollPane Scrollpanel2 = new JScrollPane();
		Paymentpanel.setBounds(24, 13, 858, 500);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(39, 55, 666, 478);
		Paymentpanel.add(tabbedPane_1);
		
		// Cash Tab
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Cash", null, panel_1, null);
		panel_1.setLayout(null);

		lblAmountPayableOn = new Label("Amount Payable On Delivery");
		lblAmountPayableOn.setBounds(31, 48, 364, 53);
		lblAmountPayableOn.setFont(new Font("Serif", Font.BOLD, 20));
		panel_1.add(lblAmountPayableOn);
		
		JLabel lblExtraCodCharges = new JLabel("Extra COD charges ($5) will be applied for this option");
		lblExtraCodCharges.setBounds(31, 129, 390, 43);
		lblExtraCodCharges.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_1.add(lblExtraCodCharges);
		
		JLabel lblTip = new JLabel("Tip:- ");
		lblTip.setBounds(31, 358, 69, 28);
		lblTip.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTip.setForeground(Color.blue);
		panel_1.add(lblTip);
		
		JLabel lblAvoidExtraCod = new JLabel("Avoid extra COD charges when you pay by Credit Card or Debit Card ");
		lblAvoidExtraCod.setBounds(55, 387, 571, 28);
		lblAvoidExtraCod.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		panel_1.add(lblAvoidExtraCod);
		
		lblShippingAddress = new JLabel("Shipping Address");
		lblShippingAddress.setBounds(31, 196, 160, 35);
		panel_1.add(lblShippingAddress);
				
		textField_3 = new JTextField();
		textField_3.setBounds(31, 244, 227, 101);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(shipping_address);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("Credit", null, panel_2, null);
		panel_2.setLayout(null);
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(32, 38, 135, 28);
		panel_2.add(lblCardNumber);
		
		textField = new JTextField();
		textField.setBounds(32, 79, 256, 36);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNameOnThe = new JLabel("Name on the Card");
		lblNameOnThe.setBounds(32, 144, 175, 28);
		panel_2.add(lblNameOnThe);
		
		textField_1 = new JTextField();
		textField_1.setBounds(32, 185, 256, 36);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblExpirationDate = new JLabel("Expiration Date");
		lblExpirationDate.setBounds(32, 245, 135, 36);
		panel_2.add(lblExpirationDate);
		
		txtMmyy = new JTextField();
		txtMmyy.setText("mm/yy");
		txtMmyy.setBounds(32, 294, 116, 36);
		panel_2.add(txtMmyy);
		txtMmyy.setColumns(10);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setBounds(32, 354, 91, 28);
		panel_2.add(lblCvv);
		
		textField_2 = new JTextField();
		textField_2.setBounds(32, 395, 72, 36);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		lblShippingAddress = new JLabel("Shipping Address");
		lblShippingAddress.setBounds(394, 44, 208, 22);
		panel_2.add(lblShippingAddress);
		
		textField_3 = new JTextField();
		textField_3.setBounds(394, 79, 198, 73);
		panel_2.add(textField_3);
		textField_3.setText(shipping_address);
		textField_3.setColumns(10);
		
	    
//		JPanel panel_3 = new JPanel();
//		tabbedPane_1.addTab("Debit", null, panel_3, null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(805, 365, 189, 43);
		Paymentpanel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				contentPane.setVisible(false);
//	        	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
//				frame.dispose();
//
//	        	new foodApp().frame.setVisible(true);
				tabbedPane.setSelectedIndex(1);
			}
			});
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBounds(805, 444, 189, 43);
		Paymentpanel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				contentPane.setVisible(false);
	        	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
				frame.dispose();

	        	new foodApp().frame.setVisible(true);
			}
			});
		
		lblYourTotal = new Label("Your Total: ");
		lblYourTotal.setBounds(805, 187, 189, 34);
		lblYourTotal.setFont(new Font("Serif", Font.BOLD, 20));
		Paymentpanel.add(lblYourTotal);

		// new code end//
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(929, 162, 97, 25);
		panel.add(btnCheckout);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(929, 222, 97, 25);
		panel.add(btnHelp);
		btnHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				new helpUI(user).setVisible(true);
	        	//new foodApp().frame.setVisible(true);
			}
			});
		
		
		ArrayList<Orders> myList = new ArrayList<Orders>(); 
		
		btnCheckout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// new button action
				
			
				String[] cols = {"Product Name","Price","Qty" };
				
	                for ( int i=0; i< table.getRowCount();i++)
	                {
	                    Boolean chked = Boolean.valueOf(table.getValueAt(i, 6).toString());
	                            String idProduct = table.getValueAt(i, 0).toString();
	                            Float price  =  (Float) table.getValueAt(i, 4) ;
	                            Float discount = (Float) table.getValueAt(i, 5);
	                            int qty =  (int)table.getValueAt(i, 7);
	                            if (chked) 
	                            {
		                            System.out.println("Value:::" + idProduct + price);
		                            Orders p= new Orders();
		                            p.idProduct = idProduct;
		                            p.qty = (qty);
		                            p.Price = price;
									
		                            myList.add(p);
	                            }
	                }
	                DefaultTableModel model = new DefaultTableModel();
	        		Checkouttable.setModel(model);
	        		Checkouttable.setRowHeight(50);
	        		model.setColumnIdentifiers(cols);
	        		for (Orders p : myList)
	        		{
	        		    model.addRow(new String[] {p.idProduct,String.valueOf(p.Price), String.valueOf(p.qty)});
	        		    total += ((p.Price)* (p.qty));
	        		}
	        		tabbedPane.setSelectedIndex(1);
	        		Totallabel.setText("SubTotal: "+total);
	        		
	        		// end new button action
			}
		});
		
		// payment page and checkout page button actions
		btnProceedToPayment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblYourTotal.setText(" Amount Payable: " +total);
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnContinueShopping.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		// end payment page and checkout page button actions
		showtable();
		
		
		// FunctioArrayList<Orders> orderData n which i tried to use for populating data in checkout tab without button click. But not working.
		ArrayList<Orders> orderData = showCheckout();

		JButton btnMakePayment = new JButton("Make Payment");
		btnMakePayment.setBounds(805, 282, 189, 43);
		Paymentpanel.add(btnMakePayment);
		 
  
		btnMakePayment.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int j=0;j<myList.size();j++)
				{
					//myList.get(j).idOrders = "Ord"+String.valueOf(i); 
					myList.get(j).idCustomer = user;
					myList.get(j).shippingAddress = shipping_address; 
					
					dbutilities.setOrders(myList.get(j));
					
				}
				JOptionPane.showMessageDialog(null,"Your order has been placed");
			}
			});
	}
	
	public  ArrayList<Orders> showCheckout()
	{
			
		String[] cols = {"Product Name","Price","Qty" };
		Object[][] rows = new Object[table.getRowCount()][3];
		ArrayList<Orders> myList = new ArrayList<Orders>();
		for ( int i=0; i< table.getRowCount();i++)
		{
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 7).toString());
					String dataCol1 = table.getValueAt(i, 0).toString();
					String dataCol2 = table.getValueAt(i, 4).toString();
					String dataCol3 = table.getValueAt(i, 5).toString();
					String dataCol4 = table.getValueAt(i, 6).toString();
					Object [] row = { dataCol1,dataCol2, dataCol3, dataCol4};
					if (chked) {
                        Orders p= new Orders();
                        p.idProduct = dataCol1;
                        p.qty = Integer.parseInt(dataCol4);
                        p.Price = Integer.parseInt(dataCol2);
                        myList.add(p);
						//model.addRow(row);
                        System.out.println("Value:::::::  "+dataCol1+dataCol2);
					}
					
		}
		DefaultTableModel model = new DefaultTableModel();
		Checkouttable.setModel(model);
		Checkouttable.setRowHeight(50);
		model.setColumnIdentifiers(cols);
		for (Orders p : myList)
		{
		    model.addRow(new String[] {p.idProduct, String.valueOf(p.qty), String.valueOf(p.Price)});
		}
		return myList;
	}
	
	class MyItemListener implements ItemListener   
	  {   
	    public void itemStateChanged(ItemEvent e) {   
	      Object source = e.getSource();   
	      if (source instanceof AbstractButton == false) return;   
	      boolean checked = e.getStateChange() == ItemEvent.SELECTED;   
	      for(int x = 0, y = table.getRowCount(); x < y; x++)   
	      {   
	        table.setValueAt(new Boolean(checked),x,0);   
	      }   
	    }   
	  }   

//To inherit the usage of check box in jtable   
class CheckBoxHeader extends JCheckBox implements TableCellRenderer, MouseListener 
{   
  protected CheckBoxHeader rendererComponent;   
  protected int column;   
  protected boolean mousePressed = false;   
  public CheckBoxHeader(ItemListener itemListener) {   
    rendererComponent = this;   
    rendererComponent.addItemListener(itemListener);   
 }   
  public Component getTableCellRendererComponent(   
      JTable table, Object value,   
      boolean isSelected, boolean hasFocus, int row, int column) {   
    if (table != null) {   
      JTableHeader header = table.getTableHeader();   
      if (header != null) {   
        rendererComponent.setForeground(header.getForeground());   
        rendererComponent.setBackground(header.getBackground());   
        rendererComponent.setFont(header.getFont());   
        header.addMouseListener(rendererComponent);   
      }   
    }   
    setColumn(column);   
    rendererComponent.setText("Check All");   
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));   
    return rendererComponent;   
  }   
  protected void setColumn(int column) {   
    this.column = column;   
  }   
  public int getColumn() {   
    return column;   
  }   
  protected void handleClickEvent(MouseEvent e) {   
    if (mousePressed) {   
      mousePressed=false;   
      JTableHeader header = (JTableHeader)(e.getSource());   
      JTable tableView = header.getTable();   
      TableColumnModel columnModel = tableView.getColumnModel();   
      int viewColumn = columnModel.getColumnIndexAtX(e.getX());   
      int column = tableView.convertColumnIndexToModel(viewColumn);   
     
      if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {   
        doClick();   
      }   
    }   
  }   
  public void mouseClicked(MouseEvent e) {   
    handleClickEvent(e);   
    ((JTableHeader)e.getSource()).repaint();   
  }   
  public void mousePressed(MouseEvent e) {   
    mousePressed = true;   
  }   
  public void mouseReleased(MouseEvent e) {   
  }   
  public void mouseEntered(MouseEvent e) {   
  }   
  public void mouseExited(MouseEvent e) {   
  }   
}
class CheckBoxCell extends JCheckBox   
    implements TableCellRenderer, MouseListener {   
  protected CheckBoxCell rendererComponent;   
  protected int column;   
  protected boolean mousePressed = false;   
  public CheckBoxCell(ItemListener itemListener) {   
    rendererComponent = this;   
    rendererComponent.addItemListener(itemListener);   
  }   
  public Component getTableCellRendererComponent(   
      JTable table, Object value,   
      boolean isSelected, boolean hasFocus, int row, int column) {   
    if (table != null) {   
      JTableHeader header = table.getTableHeader();   
      if (header != null) {   
        rendererComponent.setForeground(header.getForeground());   
        rendererComponent.setBackground(header.getBackground());   
        rendererComponent.setFont(header.getFont());   
        header.addMouseListener(rendererComponent);   
      }   
    }   
    setColumn(column);   
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));   
    return rendererComponent;   
  }   
  protected void setColumn(int column) {   
    this.column = column;   
  }   
  public int getColumn() {   
    return column;   
  }   
  protected void handleClickEvent(MouseEvent e) {   
    if (mousePressed) {   
      mousePressed=false;   
      JTableHeader header = (JTableHeader)(e.getSource());   
      JTable tableView = header.getTable();   
      TableColumnModel columnModel = tableView.getColumnModel();   
      int viewColumn = columnModel.getColumnIndexAtX(e.getX());   
      int column = tableView.convertColumnIndexToModel(viewColumn);   
     
      if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {   
        doClick();   
      }   
    }   
  }   
  public void mouseClicked(MouseEvent e) {   
    handleClickEvent(e);   
    ((JTableHeader)e.getSource()).repaint();   
  }   
  public void mousePressed(MouseEvent e) {   
    mousePressed = true;   
  }   
  public void mouseReleased(MouseEvent e) {   
  }   
  public void mouseEntered(MouseEvent e) {   
  }   
  public void mouseExited(MouseEvent e) {   
  }   
} 
}
