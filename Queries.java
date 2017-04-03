import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Queries {
 
	 @SuppressWarnings("null")
	public static void main(String[] args)
     {
		 
		  String queries;
		  int select=-1;
		  do
		  {
			  System.out.println("Select a query:");
			  System.out.println("Press 1: Find name, street, city infromation for all account holders? The query is sumbmitted at NYC.");
			  System.out.println("Press 2: Find customer name and balance for any account at Aksarben branch? The query is submitted in SFO.");
			  System.out.println("Press 3: Find customer street and city infromation account number 'A10352'. The query is submitted at SFO");
			  System.out.println("Press 4: To execute my own query");
			  System.out.println("Press 5: To Exit");
			  Scanner sc =new Scanner(System.in);
			  select=sc.nextInt();
			  switch (select)
			  {
			     case 0: break;
			     case 1: break;
			     case 2: System.out.println("Selecting account-number from account table where branch-name is equal to chinatown at location San Francisco");
				         SanFrancisco s=new SanFrancisco();
				         int j=0;
				         for(int i=1;i<s.account_table.length;i++)
				         {
					       if(s.account_table[i][1]=="Chinatown")
				     	   {
						     j++;
					       }
				         }
				         String q21[][]=new String[j+1][1];
                         q21[0][0]=s.account_table[0][0];              
                         j=1;
                         //System.out.println(q21[0][0]);
				         for(int i=1;i<s.account_table.length;i++)
				         {
					       if(s.account_table[i][1]=="Chinatown")
				     	   {
						     q21[j][0]=s.account_table[i][0];
						   //  System.out.println(q21[j][0]);
						     j++;
						     
					       }
				         } //correct
				         
				         System.out.println("Sending account-number of customers of chinatown branch to NewYork");
				         System.out.println("The temporary table with account number is sent to NYC");
				         NewYork n=new NewYork();
				         Hash_join hj=new Hash_join();
				         System.out.println("Joining depositor table and temp table at NYC");
                         List<String[]>output1=hj.hashJoin(n.depositor_table,q21,"account-number","depositor_table","temp_with_account_number"); 
                         System.out.println("Displaying Temporary table with customer-name and account-number");
                         for(int i=0;i<output1.size();i++)
                         {
                        	 String output_tuple[]=output1.get(i);
                        	 for(int k=0;k<output_tuple.length;k++)
                        	 {
                        	      System.out.print(output_tuple[k] +" ");	 
                        	 }
                        	 System.out.println();
                         }
                         String aaa[]= output1.get(0);
                         int sa=aaa.length;
                         String [][]q22=new String[output1.size()][sa];
                         for(int i=0;i<output1.size();i++)
                         {
                        	 q22[i]=output1.get(i);
                         }
                         System.out.println("Temporary table with customer-name and account-number is sent form NYC to SFO");
                         List<String[]>output2=hj.hashJoin(s.account_table,q22,"account-number","account_table","temp_with_account_number_and_customer-name");
                         for(int i=0;i<output2.size();i++)
                         {
                        	 if(i==0){
                        	   String output_tuple[]=output2.get(i);
                        	   for(int k=0;k<output_tuple.length;k++)
                        	   {
                        	      System.out.print(output_tuple[k] +"   ");	 
                        	   }
                        	   System.out.println();
                        	 }
                        	 else
                        	 {
                        		 String output_tuple[]=output2.get(i);
                          	     for(int k=0;k<output_tuple.length;k++)
                          	     {
                          	       System.out.print(output_tuple[k] +"         ");	 
                          	     }
                          	     System.out.println();
                        	 }
                         }
                         String bbb[] =output2.get(0);
                         int sb=bbb.length;
                         String q23[][]=new String[output2.size()][sb];
                         System.out.println("Output for Query 2 is:");
                         for(int i=0;i<output2.size();i++)
                         {
                        	 q23[i]=output2.get(i);
                        	 for(int ma=2;ma<sb;ma++)
                        	 {
                        		 if(i==0)
                        		 System.out.print(q23[i][ma] + " ");
                        		 else
                        		 {
                        			 System.out.print(q23[i][ma] + "    ");
                        		 }
                        	 }
                           System.out.println();
                         }
				         break;
			     case 3: break;
			     case 4: break;
			     case 5: System.out.println("Done executing queries");
			     	     break;
		  }
     }while(select!=5);
 }
}
