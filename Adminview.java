import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Adminview extends JFrame {

	private JPanel contentPane;
	private JTextField txtbalance;
	private JLabel lblinvested;
	private JLabel lblbalance;
	private JLabel lblsales;
	private JLabel lblprofit;
	private JTextField txtAddItemsTo;
	private JTextField txtViewStock;
	private JTextField txtViewPurchaseHistory;
	private JButton btnNewButton;
	RandomAccessFile raf;
	private JLabel lblnewlabel_1;
	private JTextField txtBackToHome;

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
					Adminview frame = new Adminview();
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
	public Adminview() 
	{
		initcomponents();
		createevents();
		updatebalance();
		double invested = updateinvested();
		double sales = updatesales();
		if(sales == 0 || invested ==0)
			lblprofit.setText("$0");
		else
		{
			double profit = sales-invested;
			
			if (profit>=0) {
			lblprofit.setText(profit+"$");}
			else 
				lblprofit.setText("("+(-1*profit)+")"+"$");
		}
		
	}

	private void initcomponents()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.cyan);
		lblbalance = new JLabel("New label");
		
		JLabel jlabel = new JLabel("Balance :");
		
		JLabel lblNewLabel = new JLabel("Add Balance :");
		
		txtbalance = new JTextField();
		txtbalance.setColumns(10);
		
		btnNewButton = new JButton("Add Amount");
		
		lblinvested = new JLabel("New label");
		
		JLabel lblNewLabel_3 = new JLabel("Sales :");
		
		lblsales = new JLabel("New label");
		
		JLabel lblNewLabel_2 = new JLabel("Profit :");
		
		lblprofit = new JLabel("New label");
		
		txtAddItemsTo = new JTextField();
		
		txtAddItemsTo.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddItemsTo.setText("Add Items to Stock");
		txtAddItemsTo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtAddItemsTo.setBackground(new Color(102, 204, 255));
		txtAddItemsTo.setEditable(false);
		txtAddItemsTo.setColumns(10);
		
		txtViewStock = new JTextField();
		
		txtViewStock.setText("View Stock");
		txtViewStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewStock.setEditable(false);
		txtViewStock.setColumns(10);
		txtViewStock.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtViewStock.setBackground(new Color(102, 204, 255));
		
		txtViewPurchaseHistory = new JTextField();
		
		txtViewPurchaseHistory.setText("View Purchase History");
		txtViewPurchaseHistory.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewPurchaseHistory.setEditable(false);
		txtViewPurchaseHistory.setColumns(10);
		txtViewPurchaseHistory.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtViewPurchaseHistory.setBackground(new Color(102, 204, 255));
		
		lblnewlabel_1 = new JLabel("Invested :");
		
		txtBackToHome = new JTextField();
		
		txtBackToHome.setText("Back to Home");
		txtBackToHome.setHorizontalAlignment(SwingConstants.CENTER);
		txtBackToHome.setEditable(false);
		txtBackToHome.setColumns(10);
		txtBackToHome.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtBackToHome.setBackground(new Color(102, 204, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtbalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblnewlabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
								.addComponent(jlabel, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblbalance)
								.addComponent(lblinvested)
								.addComponent(lblsales)
								.addComponent(lblprofit)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtAddItemsTo, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtViewStock, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtViewPurchaseHistory, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtBackToHome, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblbalance)
						.addComponent(jlabel)
						.addComponent(lblNewLabel)
						.addComponent(txtbalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblinvested)
						.addComponent(lblnewlabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblsales))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblprofit))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(txtBackToHome, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAddItemsTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtViewStock, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtViewPurchaseHistory, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void createevents() 
	{
		
		txtBackToHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				StoreHome sh = new StoreHome();
				sh.setVisible(true);
				dispose();
			}
		});
		
		txtViewPurchaseHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				StoreOrderHistory soh = new StoreOrderHistory();
				soh.setVisible(true);
				dispose();
			}
		});
		
		txtViewStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ViewStock vs = new ViewStock();
				vs.setVisible(true);
				dispose();
			}
		});
		
		txtAddItemsTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				AddStock as = new AddStock();
				as.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					raf = new RandomAccessFile("StoreBalance.txt","rw");
					double currentbalance = raf.readDouble();
					raf.seek(0);
					raf.writeDouble(currentbalance+Double.parseDouble(txtbalance.getText()));
					JOptionPane.showMessageDialog(null, "Transaction Complete");
					txtbalance.setText("");
					updatebalance();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					try {
						raf.seek(0);
						raf.writeDouble(Double.parseDouble(txtbalance.getText()));
						JOptionPane.showMessageDialog(null, "Transaction Complete");
						txtbalance.setText("");
						updatebalance();
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Invalid Amount!!");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}catch (NumberFormatException e1) 
				{
					JOptionPane.showMessageDialog(null, "Invalid Amount!!");
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
	private double updateinvested()
	{
		try {
			raf = new RandomAccessFile("Invested.txt","rw");
			double d = raf.readDouble();
			lblinvested.setText(d+"$");
			return d;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			lblinvested.setText("0.0$");
			return 0;
		}
	}
	private double updatesales()
	{
		try {
			raf = new RandomAccessFile("Sales.txt","rw");
			double d = raf.readDouble();
			lblsales.setText(d+"$");
			return d;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			lblsales.setText("0.0$");
			return 0;
		}
	}
}
