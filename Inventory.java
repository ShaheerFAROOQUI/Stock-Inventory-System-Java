import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

class inventory
{
	 public ArrayList<item> items = new ArrayList();
	 public ArrayList<order> orders = new ArrayList();
	 public double inventoryBudget;
	 
         public int bank1;
         
         public inventory(){
            
         }

    
         
         public void setBudget(double budget)
         {
        	 inventoryBudget = budget;
         }
         public double getBudget()
         {
        	return inventoryBudget;
         }
         
         
	 public void cashpurchase() throws FileNotFoundException, IOException
	 {
		 Scanner in = new Scanner(System.in);
		System.out.println("Add from the following ITEMS:-");
                System.out.println("Enter any one:-\n shirts \n pants \n accessories");
		String type=in.next().toLowerCase();
		if(type.contentEquals("shirts"))
		{
			System.out.println("shirts:-");
			System.out.println("Enter Item Name:");
			String name = in.next();
			System.out.println("Enter Price:");
			double price = in.nextDouble();
			System.out.println("Enter Quantity:");
                        
                        
			int quantity = in.nextInt();
                        if (bank1>price){
                     double total;
                     total = price*quantity;
			item i= new item(name,price,quantity);
			RandomAccessFile raf = new RandomAccessFile(name+".txt", "rw");
                        i.writeToFile(raf);
			 items.add(i);
                        
                        bank1=(int) (bank1-price);}
                        
                        else {
                            System.out.println("Your bank balance is insufficient for this purchase");
                        }
                         
		}
		else if(type.contentEquals("pants"))
		{
			System.out.println("Pants:-");
			System.out.println("Enter Item Name:");
			String name = in.next();
			System.out.println("Enter Price:");
			double price = in.nextDouble();
			System.out.println("Enter Quantity:");
			int quantity = in.nextInt();
                        
                        if (bank1>price){
                        double total;
                        total = price*quantity;
			item i= new item(name,price,quantity);
			 items.add(i);
			 RandomAccessFile raf = new RandomAccessFile(name+".txt","rw");
                         i.writeToFile(raf);
                         bank1=(int) (bank1-price);}
                          else {
                            System.out.println("Your bank balance is insufficient for this purchase");
                        }
		}
		else if(type.contentEquals("accessories"))
		{
			System.out.println("Accessories:-");
			System.out.println("Enter Item Name:");
			String name = in.next();
			System.out.println("Enter Price:");
			double price = in.nextDouble();
			System.out.println("Enter Quantity:");
			int quantity = in.nextInt();
                        
                        if (bank1>price){
                            double total;
                            total = price * quantity;
                      
			item i= new item(name,price,quantity);
			 items.add(i);
			 RandomAccessFile raf = new RandomAccessFile(name+".txt","rw");
                        i.writeToFile(raf);
                         bank1=(int) (bank1-price);}
                          else {
                            System.out.println("Your bank balance is insufficient for this purchase");
                        }
		}
		
		else
			System.out.println("Invalid");
		
	 }
         
         public void creditpurchase() throws IOException{
              Scanner in = new Scanner(System.in);
              System.out.println("Have you burchased items from this supplier before?");
                        System.out.println("1.YES \n 2.NO");
                        int supp = in.nextInt();
                        if (supp==2){
                         System.out.println("Please enter the name of the supplier ");
                         String supplier = in.next();}
                        
		System.out.println("Add from the following ITEMS:-");
                System.out.println("Enter any one:-\n shirts \n pants \n accessories");
		String type=in.next().toLowerCase();
		System.out.println("Enter the name of the product ");
                String name = in.next();
                System.out.println("Enter the quantity that you want to purchase ");
                int quantity = in.nextInt();
                System.out.println("Please enter the per unit price ");
                int price = in.nextInt();
                
                double total = price*quantity;
                item i= new item();
                RandomAccessFile raf = new RandomAccessFile(name+".txt","rw");
                
                i.writeToFile(raf);
                
                          
           }
	 public void cashsales(int num) throws IOException
		{
		 	boolean y = false;
		 	int i = 1;
		 	Scanner in = new Scanner(System.in);
		 	while(i==1)
		 	{
		 	order o = new order(num);
			orders.add(o);
			//System.out.println("Order the Following Items:-");
			//System.out.println(showinvent());
			if(showinvent().equals("Inventory Empty ")) break;
			System.out.println("\n Enter Name: ");
			String n= in.next();
			System.out.println("\n Enter Quantity: ");
			int q= in.nextInt();
                        System.out.println("\n Enter price ");
                        int p = in.nextInt();
			for(item obj : items)
			{	
				if(obj.getname().equals(n))	
				{ 
					y=true;
					while(q>obj.quantity)
					{
						System.out.println("\nEnter A VALID Quantity: ");
						q= in.nextInt();
					}
					if(q<=obj.quantity)
						
						o.setquantity(q);
						
						o.additem(obj);
						
						RandomAccessFile raf = new RandomAccessFile(n+"txt","rw"); 
                                                obj.writeToFile(raf);
                                                
						System.out.println("\nYour Order Details:-\n ");
						for(order abc : orders)
						{
							if(abc.getordernum()==num)
							System.out.println(abc.fororder());
						}
						
					}
					 System.out.println("Enter:\n1.For adding more Items\n2.For Completing Order");
					 i=in.nextInt();
				}
			
			}	
			if(!y) System.out.println("NOT in Inventory:");
		 	if(showinvent().equals("Empty"))
		 		{
		 		System.out.println("Noting in Inventory!!:\n");
		 		double total = 0;
		 		System.out.println("Order Details:-\n");
		 		for(order abc : orders)
		 		{
		 			if(abc.getordernum()==num)
		 				total+=abc.getprice();
		 		}
		 			
		 		System.out.println("Order# "+num +"\nTotal Price: "+total);
		 		for(order abc : orders)	
				{
		 			if(abc.getordernum()==num)
					System.out.println(abc.fororder());
				}System.out.println("// Order Added //");
		 		}
		 	
		}
         
         public void creditsales () throws IOException{
          
             
             boolean y = false;
		 	int i = 1;
		 	Scanner in = new Scanner(System.in);
		 	while(i==1)
		 	{
		 	order o = new order();
			orders.add(o);
			//System.out.println("Order the Following Items:-");
			//System.out.println(showinvent());
			if(showinvent().equals("Inventory Empty ")) break;
			System.out.println("\n Enter Name: ");
			String n= in.next();
			System.out.println("\n Enter Quantity: ");
			int q= in.nextInt();
                        System.out.println("ENTER THE PER UNIT PRICE ");
                        int p = in.nextInt();
			for(item obj : items)
			{	
				if(obj.getname().equals(n))	
				{ 
					y=true;
					while(q>obj.quantity)
					{
						System.out.println("\nEnter A VALID Quantity: ");
						q= in.nextInt();
					}
					if(q<=obj.quantity)
						
						o.setquantity(q);
						
						o.additem(obj);
						 RandomAccessFile raf = new RandomAccessFile(n+".txt","rw");
                                 obj.writeToFile(raf);
                                		 }
                                                
                                             
    
    
    
}
                        if(showinvent().equals("Empty"))
		 		{
		 		System.out.println("NOT AVAILABLE");
		 	}

       
         }}
	 public String showinvent()
	 {
		 String ae = "";
		 for(item obj : items)
			{
			 if(obj.getquantity()!=0)
			 ae = ae + obj.toString()+" // Quantity :"+obj.quantity+"\n";
			}
		 if(ae.equals("")) return "Inventory Empty";
		 return ae;
	 }
	 

   
        }