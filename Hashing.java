import java.util.HashMap;
import java.util.List;

public class Hashing {
	HashMap<Integer, List<String[]>> hmap = new HashMap<Integer, List<String[]>>();
	public HashMap<Integer, List<String[]>> hashARelation(String[][] relation, String joinattribute, String relationname, int hftype)
	{
	     float blocksize=0;
	     int numberofblocks=0; 
		 int index=-1;
		 int row=relation.length;
		 int col=relation[0].length;
		 int total=row*col;
		 Partition p =new Partition();
		   for(int i=0; i<relation[0].length;i++)
	       {
	    	   if(joinattribute.equals(relation[0][i])==true)
	    	   {
	    		   index=i;
	    	   }
	       }
		   if(relationname.equals("customer_table")==true)
		   { 
			   blocksize=8;
		   }
		   else if(relationname.equals("brach_table")==true)
		   {
			   blocksize=7;
		   }
		   else if(relationname.equals("account_table")==true){
			   blocksize=10;
			   
		   }
		   else if(relationname.equals("depositor_table")==true){
			   blocksize=15;
		   }
		   else
		   {
			   if((total/15)>0){
			     numberofblocks=total/15;
			   }
			   else
			   {
				   numberofblocks=1;
			   }
			   blocksize=9999;
			   
		   }
		   if(relation.length> blocksize){
			   numberofblocks=(int) Math.ceil((relation.length-1)/blocksize);   
		   }
	       for(int i=1,j=0;i<relation.length;i++){
	    	   j++;
	    	   if(i==1 && numberofblocks<=4)
	    	   {
	    		   System.out.println("Bringing " + numberofblocks + " blocks of "+ relationname +" to memory");
	    	   }
	    	   else if(i==1 && numberofblocks>4)
	    	   {
	    		   System.out.println("Bringing 4 blocks of "+ relationname +" to memory");
	    	   }
	           hmap= p.createPartition(relation[i] ,index, hftype);
	           if(j==blocksize && blocksize!=0)
	           {
	        	   System.out.println("Output block is full, writing to disk");
	        	   j=0;
	           }
	           if(i % (4*blocksize) == 0 && blocksize!=0)
	           {
	        	   numberofblocks=numberofblocks-4;
	        	   if(numberofblocks>4)
	        	       System.out.println("Bringing 4 blocks of "+ relationname +" to memory");
	        	   else
	        		   System.out.println("Bringing " +numberofblocks + " blocks of "+ relationname +" to memory");
	           }
	           if(i==relation.length-1)
	           {
	        	   System.out.println("Partition of "+relationname+ " "+" completed.");
	           }
	       }
	       
	       return hmap;
	}
	
}