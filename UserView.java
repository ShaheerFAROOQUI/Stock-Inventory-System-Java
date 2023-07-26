import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class UserView extends JFrame {

	private JPanel contentPane;
	private JLabel lblwelcomuser;
	RandomAccessFile raf;
	User user;
	private JLabel lblbalance;
	private JTextField txtEnterAmount;
	private JButton btnNewButton;
	private JTextField txtViewStore;
	private JTextField txtViewStor;
	private JTextField txtLogout;

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
					UserView frame = new UserView();
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
	public UserView() 
	{
		initcomponents();
		createevents();
		updateuserinfo();
		
		
	}

	private void initcomponents()
	{
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblwelcomuser = new JLabel("");
		lblwelcomuser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblbalance = new JLabel("New label");
		
		JLabel lblNewLabel_1 = new JLabel("Your Balance :");
		
		
		txtEnterAmount = new JTextField();
		
		txtEnterAmount.setText("Enter Amount");
		txtEnterAmount.setEditable(false);
		txtEnterAmount.setColumns(10);
		
		btnNewButton = new JButton("Add Balance");
		
		txtViewStore = new JTextField();
		
		txtViewStore.setText("View Store");
		txtViewStore.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtViewStore.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewStore.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtViewStore.setEditable(false);
		txtViewStore.setBackground(new Color(102, 204, 255));
		txtViewStore.setColumns(10);
		
		txtViewStor = new JTextField();
		
		txtViewStor.setText("View Order History");
		txtViewStor.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewStor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtViewStor.setEditable(false);
		txtViewStor.setColumns(10);
		txtViewStor.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtViewStor.setBackground(new Color(102, 204, 255));
		
		txtLogout = new JTextField();
		
		txtLogout.setText("Log-Out");
		txtLogout.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogout.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtLogout.setEditable(false);
		txtLogout.setColumns(10);
		txtLogout.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogout.setBackground(new Color(102, 204, 255));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblwelcomuser, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblbalance))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtViewStore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtViewStor, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtEnterAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLogout, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblwelcomuser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblbalance)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEnterAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtViewStore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtViewStor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtLogout, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}

	private void createevents() 
	{
		
		txtLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				StoreHome sh = new StoreHome();
				sh.setVisible(true);
				dispose();
			}
		});
		
		txtViewStor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				UserOrderHistory uoh = new UserOrderHistory();
				uoh.setVisible(true);
				dispose();
			}
		});
		
		txtViewStore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				ViewStore vs = new ViewStore();
				vs.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtEnterAmount.getText().equals("") || txtEnterAmount.getText().equals("Enter Amount"))
					JOptionPane.showMessageDialog(null, "Enter an amount First");
				else
				{
					try 
					{
						RandomAccessFile raf1 = new RandomAccessFile("logged.txt","rw");
						User user1 = new User();
						user1.readUser(raf1);
						user1.setBalance(Double.parseDouble(txtEnterAmount.getText())+user1.getBalance());
						raf1.seek(0);
						user1.writeUser(raf1);
						
						user = new User();
						raf = new RandomAccessFile("Users.txt","rw");
						int looplength = (int)raf.length()/user.Usersize();
						for(int i=0 ; i<looplength ; i++)
						{
							user.readUser(raf);
							if(user.getName().equals(user1.getName()) && user.getPassword().equals(user1.getPassword()))
							{
								raf.seek(raf.getFilePointer()-user.Usersize());
								user1.writeUser(raf);
								updateuserinfo();
								JOptionPane.showMessageDialog(null, "Transaction Complete");
								txtEnterAmount.setText("");
								txtEnterAmount.requestFocus();
								break;
								
							}
						}
						
						
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (NumberFormatException e1) 
					{
						JOptionPane.showMessageDialog(null, "Invalid Amount!!");
					}
					
							
				}
			}
		});
		
		txtEnterAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				txtEnterAmount.setEditable(true);
				txtEnterAmount.setText("");
				txtEnterAmount.requestFocus();
			}
		});
		
	}
	
	private void updateuserinfo()
	{
		try {
			raf = new RandomAccessFile("logged.txt","rw");
			user = new User();
			user.readUser(raf);
			lblwelcomuser.setText("Welcome "+user.getName());
			lblbalance.setText(user.getBalance()+"$");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
