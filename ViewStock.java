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
import javax.swing.JList;
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

public class ViewStock extends JFrame {

	private JPanel contentPane;
	private JTextField txtitemname;
	private JTextField txtperunitprice;
	private JTextField txtquantity;
	private DefaultListModel ListItems = new DefaultListModel();; 
	RandomAccessFile raf;
	item item;
	private JButton btnback;
	private JList ItemList;

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
					ViewStock frame = new ViewStock();
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
	public ViewStock() 
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
		contentPane.setBackground(Color.CYAN);
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel)
							.addComponent(txtitemname, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1)
							.addComponent(txtperunitprice)
							.addComponent(lblNewLabel_2)
							.addComponent(txtquantity))
						.addComponent(btnback))
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtitemname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(txtperunitprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(txtquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
							.addComponent(btnback)))
					.addContainerGap())
		);
		
		ItemList = new JList(ListItems);
		scrollPane.setViewportView(ItemList);
		contentPane.setLayout(gl_contentPane);
		
	}

	private void createevents()
	{
		
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
				Adminview av = new Adminview();
				av.setVisible(true);
				dispose();
			}
		});
		
	}
	
	private void updatelist()
	{
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
