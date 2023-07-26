import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public final class order
{
	private String ordernum;
	private String itemname;
	private int quantity;
	private double total;

	public order(String itemname,int q,double t)
	{
		this.itemname = itemname;
		quantity = q;
		total = t;
	}

    public order() 
        {
            
        }
        
    
    public void writeorderall(RandomAccessFile raf, String username) throws IOException
    {
    	raf.writeUTF(username);
    	for(int i=0 ; i<30-username.length() ; i++)
    		raf.writeByte(30);
    	raf.writeUTF(itemname);
    	for(int i=0 ; i<30-itemname.length() ; i++)
    		raf.writeByte(30);
    	
    		raf.writeUTF(ordernum);
    		for(int i=0 ; i<30-ordernum.length() ; i++)
    		raf.writeByte(30);
    		
    	raf.writeInt(quantity);
    	raf.writeDouble(total);
    }
    
    public String readorderall(RandomAccessFile raf) throws IOException
    {
    	String username = raf.readUTF();
    	for(int i=0 ; i<30-username.length() ; i++)
    		raf.readByte();
    	itemname=raf.readUTF();
    	for(int i=0 ; i<30-itemname.length() ; i++)
    		raf.readByte();
    	ordernum=raf.readUTF();
    	for(int i=0 ; i<30-ordernum.length() ; i++)
    		raf.readByte();
    	quantity = raf.readInt();
    	total = raf.readDouble();
    	
    	return username;
    }
    
    public void writeorder(RandomAccessFile raf) throws IOException
    {
    	raf.writeUTF(itemname);
    	for(int i=0 ; i<30-itemname.length() ; i++)
    		raf.writeByte(30);
    	raf.writeUTF(ordernum);
    	for(int i=0 ; i<30-ordernum.length() ; i++)
    		raf.writeByte(30);

    	raf.writeInt(quantity);
    	raf.writeDouble(total);
    }
    
    public void readorder(RandomAccessFile raf) throws IOException
    {
    	itemname=raf.readUTF();
    	for(int i=0 ; i<30-itemname.length() ; i++)
    		raf.readByte();
    	ordernum=raf.readUTF();
    	for(int i=0 ; i<30-ordernum.length() ; i++)
    		raf.readByte();

    	quantity = raf.readInt();
    	total = raf.readDouble();
    }
    public String getitemname()
    {
    	return itemname;
    }
    public void setitemname(String itemname)
    {
    	this.itemname = itemname;
    }
	public String getordernum()
	{
		return ordernum;
	}
	public void setquantity(int q)
	{
		quantity=q;
	}
	public int getquantity()
	{
		return quantity;
	}
	public double gettotal()
	{
		return total;
	}
	public void setordernum(String o)
	{
		ordernum = o;
	}
	
	public String toString()
	{
		return "Order #"+getordernum()+"\nTotal Price: "+total+"\n";
	}
	public int ordersize()
	{
		return 72;
	}
	public int ordersizeall()
	{
		return 102;
	}
        
      
        
        
}