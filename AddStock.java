import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class AddStock extends JFrame {

	private JPanel contentPane;
	private JTextField txtitemname;
	private JTextField txtperunitprice;
	private JTextField txtquantity;
	private JButton btnadditem;
	RandomAccessFile raf;
	item item;
	private JLabel lblNewLabel_3;
	private JLabel lblbalance;
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
					AddStock frame = new AddStock();
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
	public AddStock()
	{
		initcomponents();
		createevents();
		updatebalance();
	}
	
	private void initcomponents() 
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.MAGENTA);
		JLabel lblNewLabel = new JLabel("Enter Item Name :");
		
		txtitemname = new JTextField();
		txtitemname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Per Unit Price :");
		
		txtperunitprice = new JTextField();
		txtperunitprice.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Quantity :");
		
		txtquantity = new JTextField();
		txtquantity.setColumns(10);
		
		btnadditem = new JButton("Add Item");
		
		
		btnback = new JButton("Back");
		
		
		lblNewLabel_3 = new JLabel("Current Store Balance :");
		
		lblbalance = new JLabel("New label");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtitemname, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
						.addComponent(txtperunitprice, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
						.addComponent(txtquantity, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addComponent(btnadditem)
					.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
					.addComponent(btnback)
					.addGap(44))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblbalance)
					.addContainerGap(242, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtitemname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtperunitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadditem)
						.addComponent(btnback))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblbalance))
					.addContainerGap())
		);
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
		
		btnadditem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtitemname.getText().equals("") || txtperunitprice.getText().equals("") || txtquantity.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Fill all the Fileds First");
				else
				{

					try
					{
						item = new item(txtitemname.getText(),Double.parseDouble(txtperunitprice.getText()),Integer.parseInt(txtquantity.getText()));
						RandomAccessFile raf1 = new RandomAccessFile("StoreBalance.txt","rw");
						double currentbalance = raf1.readDouble();
						if(currentbalance>=(item.getprice()*item.getquantity()*0.90))
						{
							raf1.seek(0);
							raf1.writeDouble(currentbalance-(item.getprice()*item.getquantity()*0.90));
							updatebalance();
							raf = new RandomAccessFile("Stocks.txt","rw");
							raf.seek(raf.length());
							item.writeToFile(raf);
							JOptionPane.showMessageDialog(null, "Item Successfully Added to Stock ");
							raf = new RandomAccessFile("Invested.txt","rw");
							raf.seek(0);
							double currentinvested=0;
							try {
								currentinvested = raf.readDouble();
							}catch(EOFException ex)
							{
								
							}
							raf.seek(0);
							raf.writeDouble(currentinvested + (item.getprice()*item.getquantity()*0.90));
							txtitemname.setText("");
							txtperunitprice.setText("");
							txtquantity.setText("");
							txtitemname.requestFocus();
						}
						else
							JOptionPane.showMessageDialog(null, "Not Enough Balance!!");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Invalid Input!!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
	}
	
	private void updatebalance()
	{
		try {
			raf = new RandomAccessFile("StoreBalance.txt","rw");
			lblbalance.setText(raf.readDouble()+"$");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			lblbalance.setText("0.0$");
		}
	}

	

}
