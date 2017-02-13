package eStore;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import java.awt.Font;

public class deliveryUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	
	public  void showtable()
	{
		ArrayList<Orders> orderData = new ArrayList<Orders>();		
		orderData = dbutilities.getOrders();
		
		
		String[] cols= {"Order ID","Customer Name","Product","Address","Delivered?","Delivery Status"};
		Object[][] rows = new Object[orderData.size()][cols.length];
		for(int i=0;i < orderData.size();i++)
		{
			
			rows[i][0] = orderData.get(i).getIdOrders();
			rows[i][1] = orderData.get(i).getIdCustomer();
			rows[i][2] = orderData.get(i).getIdProduct();
			rows[i][3] = orderData.get(i).getShippingAddress();
			rows[i][4] = orderData.get(i).getDeliveryStatus(); 
			rows[i][5] = orderData.get(i).getDelivery();
			
		}
		tabDeliveryModel tm = new tabDeliveryModel(rows, cols);
		table.setModel(tm);
		table.setRowHeight(50);
		TableColumn tc = table.getColumnModel().getColumn(4);
		 tc.setCellEditor(table.getDefaultEditor(Boolean.class));   
		 tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));  
	}

	/**
	 * Create the frame.
	 */
	public deliveryUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 71, 665, 399);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(202, 503, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(436, 503, 117, 29);
		contentPane.add(btnCancel);
		
		JLabel lblDeliveryList = new JLabel("Delivery List");
		lblDeliveryList.setFont(new Font("Georgia", Font.BOLD, 18));
		lblDeliveryList.setBounds(54, 13, 367, 45);
		contentPane.add(lblDeliveryList);
		
		btnSave.addActionListener(new ActionListener() {
	        @Override
			public void actionPerformed(ActionEvent e) {
	        	for (int i = 0; i < table.getRowCount(); i++) {
	        	     Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 4).toString());

	        	     if (isChecked) {
	        	    	 String order_id = String.valueOf(table.getValueAt(i, 0).toString());
	        	    	 dbutilities.updateOrderDelivery(order_id);
	        	    }
	        	     else{
	        	    	 String order_id = String.valueOf(table.getValueAt(i, 0).toString());
	        	    	 dbutilities.updateNonOrderDelivery(order_id);
	        	     }
	        	}
	        	JOptionPane.showMessageDialog(null, "Changes Saved");
			}
	        
	      });
		
		btnCancel.addActionListener(new ActionListener() {
	        @Override
			public void actionPerformed(ActionEvent e) {
	        	contentPane.setVisible(false);
	        	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
	        	frame.dispose();

	        	new foodApp().frame.setVisible(true);
	        	
				
			}
	      });
		
		
		showtable();
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
