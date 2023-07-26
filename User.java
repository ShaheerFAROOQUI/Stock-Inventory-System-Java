import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class User{
   private String name;
   private String password;
   private double balance=0;

    public User()
    {
        
    }

    public User(String name,String password,double balance)
    {
    	this.name = name;
    	this.password = password;
    	this.balance = balance;
    }

	public String getName() 
	{
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	public void writeUser(RandomAccessFile raf) throws IOException
	{
		raf.writeUTF(name);
		for(int i=0 ; i<30-name.length() ; i++)
			raf.writeByte(30);
		raf.writeUTF(password);
		for(int i=0 ; i<30-password.length() ; i++)
			raf.writeByte(30);
		raf.writeDouble(balance);
	}
	
	public void readUser(RandomAccessFile raf) throws IOException
	{
		name = raf.readUTF();
		for(int i=0 ; i<30-name.length() ; i++)
			raf.readByte();
		password = raf.readUTF();
		for(int i=0 ; i<30-password.length() ; i++)
			raf.readByte();
		balance = raf.readDouble();
		
	}
	public int Usersize()
	{
		return 72;
	}
}
