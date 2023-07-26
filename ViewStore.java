import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewStore extends JFrame {

	private JPanel contentPane;
	private JTextField txtitemname;
	private JTextField txtperunitprice;
	private JTextField txtquantity;
	private DefaultListModel ListItems = new DefaultListModel();
	User user;
	RandomAccessFile raf;
	item item;
	order order;
	private JButton btnback;
	private JList ItemList;
	private JLabel lblNewLabel_4;
	private JTextField txtamount;
	private JButton btnplaceorder;

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
					ViewStore frame = new ViewStore();
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
	public ViewStore() 
	{
		updatelist();
		initcomponents();
		createevents();
		
		
	}

	private void initcomponents() 
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.orange);
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Item Name :");
		
		txtitemname = new JTextField();
		txtitemname.setEditable(false);
		txtitemname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Per unit Price :");
		
		txtperunitprice = new JTextField();
		txtperunitprice.setEditable(false);
		txtperunitprice.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity :");
		
		txtquantity = new JTextField();
		txtquantity.setEditable(false);
		txtquantity.setColumns(10);
		
		btnback = new JButton("Back");
		
		JLabel lblNewLabel_3 = new JLabel("Select the item you want to view");
		lblNewLabel_3.setEnabled(false);
		
		lblNewLabel_4 = new JLabel("Enter the Amount You want to Order :");
		
		txtamount = new JTextField();
		txtamount.setColumns(10);
		
		btnplaceorder = new JButton("Place Order");

		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnback)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnplaceorder))
						.addComponent(txtitemname, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtperunitprice)
						.addComponent(lblNewLabel_2)
						.addComponent(txtquantity)
						.addComponent(lblNewLabel_4)
						.addComponent(txtamount, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtitemname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblNewLabel_1)
							.addGap(5)
							.addComponent(txtperunitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(lblNewLabel_2)
							.addGap(8)
							.addComponent(txtquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblNewLabel_4)
							.addGap(12)
							.addComponent(txtamount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnback)
								.addComponent(btnplaceorder))))
					.addContainerGap())
		);
		
		ItemList = new JList(ListItems);
		scrollPane.setViewportView(ItemList);
		contentPane.setLayout(gl_contentPane);
		
	}

	private void createevents()
	{
		
		
		btnplaceorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					if(txtamount.getText().equals("") || Integer.parseInt(txtamount.getText())>Integer.parseInt(txtquantity.getText()))
						JOptionPane.showMessageDialog(null, "Invalid Amount");
					else if(ItemList.getSelectedValue().equals(null))
						JOptionPane.showMessageDialog(null, "Select an item first");
					else
					{
						double total  = Double.parseDouble(txtperunitprice.getText())*Integer.parseInt(txtamount.getText());
						int ans = JOptionPane.showConfirmDialog(null, "Your Total Bill is "+total+"$ are you sure you want to Continue?");
						if(ans == JOptionPane.YES_OPTION)
						{
							raf = new RandomAccessFile("logged.txt","rw");
							user = new User();
							raf.seek(0);
							user.readUser(raf);
							if(user.getBalance()>=total)
							{
								user.setBalance(user.getBalance()-total);
								raf.seek(0);
								user.writeUser(raf);
								order = new order(txtitemname.getText(),Integer.parseInt(txtamount.getText()),total);
								
								RandomAccessFile raf1 = new RandomAccessFile("AllOrders.txt","rw");
								int ordernum = (int) raf1.length()/order.ordersizeall();
								order.setordernum("Order#"+ordernum);
								raf1.seek(raf1.length());
								order.writeorderall(raf1,user.getName());
								RandomAccessFile raf2 = new RandomAccessFile(user.getName()+"Orders.txt","rw");
								raf2.seek(raf2.length());
								order.writeorder(raf2);
								
								item = new item();
								raf2 = new RandomAccessFile("Stocks.txt","rw");
								int looplength = (int)raf2.length()/item.size();
								for(int i=0 ; i<looplength ; i++)
								{
									item.readFromFile(raf2);
									if(item.getname().equals(txtitemname.getText()))
									{
										if(item.getquantity()-Integer.parseInt(txtamount.getText())==0)
											{
											raf2.seek(0);
											item.deleteitem(raf2);
											break;
											}
										else 
										{
											
											item.setquantity(item.getquantity()-Integer.parseInt(txtamount.getText()));
											raf2.seek(raf2.getFilePointer()-item.size());
											item.writeToFile(raf2);
											
											break;
										}
									
									}
								}
								User user1 = new User();
								RandomAccessFile raf3 = new RandomAccessFile("Users.txt","rw");
								looplength = (int)raf3.length()/user1.Usersize();
								raf3.seek(0);
								for(int i=0 ; i<looplength ; i++)
								{
									user1.readUser(raf3);
									if(user1.getName().equals(user.getName()) && user1.getPassword().equals(user.getPassword()))
									{
										raf3.seek(raf3.getFilePointer()-user1.Usersize());
										user.writeUser(raf3);
										break;
										
									}
								}
								try 
								{
									raf2 = new RandomAccessFile("Sales.txt","rw");
									raf2.seek(0);
									double currentsales = raf2.readDouble();
									raf2.seek(0);
									raf2.writeDouble(total+currentsales);
								}catch(EOFException ex)
								{
									raf2.seek(0);
									raf2.writeDouble(total);
								}
								JOptionPane.showMessageDialog(null, "Your Order Has been Placed :) ");
								txtamount.setText("");
								txtitemname.setText("");
								txtperunitprice.setText("");
								txtquantity.setText("");
								updatelist();
								item = new item();
								raf = new RandomAccessFile("Stocks.txt","rw");
								raf.seek(0);
								looplength = (int)raf.length()/item.size();
								for(int i=0 ; i<looplength ; i++)
								{
									item.readFromFile(raf);
									if(item.toString().equals(ItemList.getSelectedValue()+""))
									{
										txtitemname.setText(item.getname());
										txtperunitprice.setText(item.getprice()+"");
										txtquantity.setText(item.getquantity()+"");
									}
								}
								
							}
							else
								JOptionPane.showMessageDialog(null, "Not Enough Balance!!");
							
						}
					}
				}catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid Amount");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		ItemList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try {
					item = new item();
					raf = new RandomAccessFile("Stocks.txt","rw");
					int looplength = (int)raf.length()/item.size();
					for(int i=0 ; i<looplength ; i++)
					{
						item.readFromFile(raf);
						if(item.toString().equals(ItemList.getSelectedValue()+""))
						{
							txtitemname.setText(item.getname());
							txtperunitprice.setText(item.getprice()+"");
							txtquantity.setText(item.getquantity()+"");
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
		});
		
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				UserView av = new UserView();
				av.setVisible(true);
				dispose();
			}
		});
		
	}
	
	public void updatelist()
	{
		ListItems.removeAllElements();
		try {
			item = new item();
			raf = new RandomAccessFile("Stocks.txt","rw");
			int looplength = (int)raf.length()/item.size();
			for(int i=0 ; i<looplength ; i++)
			{
				item.readFromFile(raf);
				ListItems.addElement(item.toString());
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