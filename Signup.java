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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JTextField txtbalance;
	private JButton btncreateaccount;
	private JButton btnback;
	User user;
	RandomAccessFile raf;

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
					Signup frame = new Signup();
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
	public Signup() 
	{
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
		contentPane.setBackground(Color.PINK);
		JLabel lblNewLabel = new JLabel("Sign-Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Username:");
		
		JLabel lblNewLabel_2 = new JLabel("Enter Password :");
		
		JLabel lblNewLabel_3 = new JLabel("Enter Balance   :");
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		
		txtbalance = new JTextField();
		txtbalance.setColumns(10);
		
		btncreateaccount = new JButton("Create Account");

		
		
		btnback = new JButton("Back");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtusername, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btncreateaccount)
							.addPreferredGap(ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
							.addComponent(btnback))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtbalance, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
								.addComponent(txtpassword, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtpassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(txtbalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnback)
						.addComponent(btncreateaccount))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void createevents()
	{
		
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				StoreHome storehome = new StoreHome();
				storehome.setVisible(true);
				dispose();
			}
		});
		
		btncreateaccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				boolean exists = false;
				if(txtusername.getText().equals("") || txtpassword.getText().equals("") || txtbalance.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill all the Fields first!!");
				}
				else
				{
						
						try {
							double balance = Double.parseDouble(txtbalance.getText());
							user = new User(txtusername.getText(),txtpassword.getText(),Double.parseDouble(txtbalance.getText()));
							raf = new RandomAccessFile("Users.txt","rw");
							int looplength = (int) raf.length()/user.Usersize();
							User user1 = new User();
							for(int i=0 ; i<looplength ; i++)
							{
								user1.readUser(raf);
								if(user1.getName().equals(user.getName()))
									exists = true;
							}
							
							if(!exists)
							{
								raf.seek(raf.length());
								user.writeUser(raf);
								JOptionPane.showMessageDialog(null, "Account Created");
								txtusername.setText("");
								txtpassword.setText("");
								txtbalance.setText("");
							}
							else
								JOptionPane.showMessageDialog(null, "Username alredy Exists");
						}catch(NumberFormatException e2)
						{
							JOptionPane.showMessageDialog(null, "Invalid Balance Amount");
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				}
			}
		});
		
	}

}
