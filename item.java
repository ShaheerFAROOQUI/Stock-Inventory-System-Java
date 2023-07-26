import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class item
{
	public String name;
	public double price;
	public int quantity;
	
	public item()
	{
		
	}
	public item(String n,double p,int q)
	{
		name=n;
		price=p;
		quantity=q;
	}
	public void setname(String name)
	{
		this.name = name;
	}
	public String getname()
	{
		return name;
	}
	public void setquantity(int q)
	{
		quantity=q;
              
	}
	public void setprice(double p) 
	{
		price=p;
	}
	public int getquantity()
	{
            return quantity;
	}
	public double getprice()
	{
		return price;
	}
        
	public String toString()
	{
		return getname();
	}
        
        
        
        public void readFromFile(RandomAccessFile raf)
            throws IOException 
        {
        	name = raf.readUTF();
        	 for(int i=0; i<30-name.length() ; i++)
             	raf.readByte();
        	 quantity = raf.readInt();
        	 price = raf.readDouble();
        
 
        }
        
        
        
        
        
        
         public void writeToFile(RandomAccessFile raf)
            throws IOException {
        
        raf.writeUTF(name);
        for(int i=0; i<30-name.length() ; i++)
        	raf.writeByte(30);
        
        raf.writeInt(quantity);
        raf.writeDouble(price);
 
    }
         
         
         public void deleteitem(RandomAccessFile raf) throws IOException
         {
        	 RandomAccessFile raf1 = new RandomAccessFile("copy.txt","rw");
        	 raf1.setLength(0);
        	 int looplength = (int)raf.length()/this.size();
        	 item item1 = new item();
        	 raf.seek(0);
        	 for(int i=0 ; i<looplength ; i++)
        	 {
        		 
        		 item1.readFromFile(raf);
        		 if(item1.getname().equals(this.name))
        		 {
        			 long location = raf.getFilePointer()-this.size();
        			 int extraloop = (int)(raf.length()-raf.getFilePointer())/this.size();
        			 for(int k=0 ; k<extraloop ;k++)
        			 {
        				 item1.readFromFile(raf);
        				 item1.writeToFile(raf1);
        			 }
        			 
        			 raf.setLength(location);
        			 break;
        		 }
        	 }
        	 raf1.seek(0);
        	 raf.seek(raf.length());
        	 looplength = (int)raf1.length()/this.size();
        	 for(int i=0 ; i<looplength ;i++)
        	 {
        		 item1.readFromFile(raf1);
				 item1.writeToFile(raf);
        	 }
         }
         
         int size() {
             return 44;
         }
}