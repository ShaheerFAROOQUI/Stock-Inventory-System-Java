import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class StoreHome extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JButton btnlogin;
	private JTextField txtadminpass;
	private JButton btnsignup;
	User user;
	RandomAccessFile raf;
	private JButton btnadmin;

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
					StoreHome frame = new StoreHome();
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
	public StoreHome() 
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
		contentPane.setBackground(Color.YELLOW);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Username :");
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		
		btnlogin = new JButton("Login");
		
		
		JLabel lblNewLabel_2 = new JLabel("User Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnsignup = new JButton("Sign-up");
		
		btnadmin = new JButton("Enter as Admin");
		
		
		txtadminpass = new JTextField();
		txtadminpass.setEditable(false);
		
		txtadminpass.setText("Enter Admin Password here");
		txtadminpass.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpassword, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
								.addComponent(txtusername, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(85)
							.addComponent(btnlogin)
							.addPreferredGap(ComponentPlacement.UNRELATED, 10, Short.MAX_VALUE)
							.addComponent(btnsignup))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(102)
							.addComponent(txtadminpass, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(btnadmin)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(txtpassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnlogin)
						.addComponent(btnsignup))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadmin)
						.addComponent(txtadminpass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}

	private void createevents() 
	{
		
		btnadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtadminpass.getText().equals("") || txtadminpass.getText().equals("Enter Admin Password here") || !(txtadminpass.getText().equals("adminadmin")))
				{
					JOptionPane.showMessageDialog(null, "Invalid Password");
				}
				else
				{
					Adminview av = new Adminview();
					JOptionPane.showMessageDialog(null, "Admin Logged In!!");
					av.setVisible(true);
					dispose();
				}
				
			}
		});
		
		txtadminpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				txtadminpass.setText("");
				txtadminpass.setEditable(true);
				txtadminpass.requestFocus();
			}
		});
		
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Signup s = new Signup();
				s.setVisible(true);
				dispose();
			}
		});
		
		
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(txtusername.getText().equals("") || txtpassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill all the Fields first!!");
				}
				else {
					boolean logged = false;
					user = new User();
					try {
						raf = new RandomAccessFile("Users.txt","rw");
						int loopsize = (int)raf.length()/user.Usersize();
						for(int i =0 ; i<loopsize ; i++)
						{
							user.readUser(raf);
							if(user.getName().equals(txtusername.getText()) && user.getPassword().equals(txtpassword.getText()))
									{
										RandomAccessFile raf1 = new RandomAccessFile("logged.txt","rw");
										raf1.seek(0);
										user.writeUser(raf1);
										JOptionPane.showMessageDialog(null, "Login Successful");
										UserView uv = new UserView();
										uv.setVisible(true);
										dispose();
										logged = true;
									}
						
						}
						if(!logged)
						JOptionPane.showMessageDialog(null, "Invalid Username or Password!!");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
				
		});
		
	}
}
