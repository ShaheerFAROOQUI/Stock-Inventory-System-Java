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

public class UserOrderHistory extends JFrame {

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
					UserOrderHistory frame = new UserOrderHistory();
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
	public UserOrderHistory() 
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
		contentPane.setBackground(Color.green);
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
		
		btnback = new JButton("Back");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtordernum, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
						.addComponent(lblNewLabel_1)
						.addComponent(txtitemname, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtquantity, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3)
						.addComponent(txttotal, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(btnback))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnviewdetails)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(txtordernum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtitemname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txttotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnviewdetails)
								.addComponent(btnback)))))
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
				UserView uv = new UserView();
						uv.setVisible(true);
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
						raf = new RandomAccessFile("logged.txt","rw");
						raf.seek(0);
						user.readUser(raf);
						order order = new order();
						raf = new RandomAccessFile(user.getName()+"Orders.txt","rw");
						int looplenght = (int) raf.length()/order.ordersize();
						raf.seek(0);
						for(int i=0 ; i<looplenght ; i++)
						{
							order.readorder(raf);
							if(order.getordernum().equals(lstOrder.getSelectedValue()))
							{
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
			raf = new RandomAccessFile("logged.txt","rw");
			raf.seek(0);
			user.readUser(raf);
			order order = new order();
			raf = new RandomAccessFile(user.getName()+"Orders.txt","rw");
			int looplenght = (int) raf.length()/order.ordersize();
			raf.seek(0);
			for(int i=0 ; i<looplenght ; i++)
			{
				order.readorder(raf);
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
