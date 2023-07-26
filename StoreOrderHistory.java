import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class StoreOrderHistory extends JFrame {

	private JPanel contentPane;
	private JTextField txtordernum;
	private JTextField txtitemname;
	private JTextField txtquantity;
	private JTextField txttotal;
	private DefaultListModel OrderList = new DefaultListModel();
	private JList lstOrder;
	private JButton btnviewdetails;
	RandomAccessFile raf;
	User user;
	private JLabel lblNewLabel_4;
	private JTextField txtusername;
	private JButton btnback;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreOrderHistory frame = new StoreOrderHistory();
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
	public StoreOrderHistory() 
	{
		updatelist();
		initcomponents();
		createevents();
		
	}

	private void initcomponents() 
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.pink);
		JScrollPane scrollPane = new JScrollPane();
		
		btnviewdetails = new JButton("View Details");
		
		
		JLabel lblNewLabel = new JLabel("Order# :");
		
		txtordernum = new JTextField();
		txtordernum.setEditable(false);
		txtordernum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name :");
		
		txtitemname = new JTextField();
		txtitemname.setEditable(false);
		txtitemname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity :");
		
		txtquantity = new JTextField();
		txtquantity.setEditable(false);
		txtquantity.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Total Bill :");
		
		txttotal = new JTextField();
		txttotal.setEditable(false);
		txttotal.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Ordered By :");
		
		txtusername = new JTextField();
		txtusername.setEditable(false);
		txtusername.setColumns(10);
		
		btnback = new JButton("Back");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtquantity, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtitemname, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtordernum, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
						.addComponent(txtusername, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_3)
						.addComponent(txttotal, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnviewdetails)
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
							.addComponent(btnback)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(24)
									.addComponent(txtusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(txtordernum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtitemname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(9))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txttotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnviewdetails)
						.addComponent(btnback))
					.addContainerGap())
		);
		
		lstOrder = new JList(OrderList);
		scrollPane.setViewportView(lstOrder);
		contentPane.setLayout(gl_contentPane);
		
	}

	private void createevents() 
	{
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Adminview av = new Adminview();
				av.setVisible(true);
				dispose();
			}
		});
		
		btnviewdetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(lstOrder.getSelectedValue()!=null)
				{
					user = new User();
					try 
					{
						raf = new RandomAccessFile("AllOrders.txt","rw");
						order order = new order();
						int looplenght = (int) raf.length()/order.ordersizeall();
						raf.seek(0);
						for(int i=0 ; i<looplenght ; i++)
						{
							String name = order.readorderall(raf);
							if(order.getordernum().equals(lstOrder.getSelectedValue()))
							{
								txtusername.setText(name);
								txtordernum.setText(order.getordernum());
								txtitemname.setText(order.getitemname());
								txtquantity.setText(order.getquantity()+"");
								txttotal.setText(order.gettotal()+"$");
								break;
							}
						}
						
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(null, "Sellect an Order First");
				
			}
		});
		
	}
	
	private void updatelist()
	{
		user = new User();
		try 
		{
			raf = new RandomAccessFile("AllOrders.txt","rw");
			raf.seek(0);
			order order = new order();
			int looplenght = (int) raf.length()/order.ordersizeall();
			raf.seek(0);
			for(int i=0 ; i<looplenght ; i++)
			{
				order.readorderall(raf);
				OrderList.addElement(order.getordernum());
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
