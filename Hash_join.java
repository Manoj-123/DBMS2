import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Hash_join
{
    public List<String[]> hashJoin(String[][] relation1,String[][] relation2,String joinattribute,String relationname1,String relationname2){
     
       int count=0,c=0;
       int attri_count1=relation1[0].length;
       int attri_count2=relation2[0].length;
       int output_attri_count= attri_count1+attri_count2-1;
       int join_attri_index_rel1=-1;
       int join_attri_index_rel2=-1;
       String out_tuple[]=new String[output_attri_count];
       for(int i=0;i<relation1[0].length;i++)
       {
    	   if(relation1[0][i].equals(joinattribute)==true)
    	   {
    		   join_attri_index_rel1=i;
    		   
    	   }
       }
       for(int i=0;i<relation2[0].length;i++)
       {
    	   if(relation2[0][i].equals(joinattribute)==true)
    	   {
    		   join_attri_index_rel2=i;
    	   }
       }
       for(c=0;c<attri_count1;c++)
       {
    	   out_tuple[c]=relation1[0][c];
       }
       for(int i=0;i<attri_count2 && i!=join_attri_index_rel2;i++)
       {
    	   out_tuple[c]=relation2[0][i];
    	   c++;
       }
       List<String[]> output=new ArrayList<String[]>();
       output.add(out_tuple);
       Hashing h1=new Hashing();
       Hashing h2=new Hashing();
       HashMap<Integer, List<String[]>> temp= h1.hashARelation(relation1, joinattribute,relationname1,1);
       HashMap<Integer, List<String[]>> j1 = new HashMap<Integer, List<String[]>>();
       j1=duplicate(temp);
       //display(j1);
       Partition p=new Partition();
       p.reuselist();
       HashMap<Integer, List<String[]>> j2 = new HashMap<Integer, List<String[]>>();
       temp= new HashMap<Integer, List<String[]>>();
       temp= h1.hashARelation(relation2, joinattribute, relationname2,1);
       j2=duplicate(temp);
      // display(j2);
       p.reuselist();
       System.out.println("Partition of "+ relationname1+" and "+ relationname2+" completed.");
       int ct=0;
       for(int i=0;i<5;i++)
       {
    	   System.out.println("Using second hash function to partition, "+i+" partition of "+relationname1+".");
    	   if(j1.get(i)!= null && j2.get(i)!=null){
    		    ct++;
    		   	List<String[]> rel1 = j1.get(i);
    		   	List<String[]> rel2 = j2.get(i);
    		    int col1=relation1[0].length;
    		   // System.out.println("col1"  +" "+ col1);
    		    int row1=rel1.size();
    		   // System.out.println("row1"  +" "+ row1);
    		    String re1[][]=new String[row1+1][col1];
    		    re1[0]=relation1[0];
    		//    System.out.println(re1[0][0] + "  "+re1[0][1]);
    		    int k=1;
    		   	for(int j=0; j<row1; j++) {
    		   		re1[k]=rel1.get(j);
    		   	//	System.out.println(re1[k][0] + "  "+re1[k][1]);
    		   		k++;
    		   		count++;
                }
    		   	HashMap<Integer, List<String[]>> temp1=h2.hashARelation(re1, joinattribute, "partition "+i,2);
    		   	HashMap<Integer, List<String[]>> subj1 = new HashMap<Integer, List<String[]>>();
    		   	subj1=duplicate(temp1);
    		   	p.reuselist();
    		  //	display(subj1);
    		   //	System.out.println("done");
    		   	
    		   	int col2=relation2[0].length;
    		   //	System.out.println("col2"  +" "+ col2);
    		    int row2=rel2.size();
    		   // System.out.println("row2"  +" "+ row2);
    		    String re2[][]=new String[row2+1][col2];
    		    re2[0]=relation2[0];
    		  //  System.out.println( "  "+ re2[0][0]);
    		    k=1;
    		   	for(int j=0; j<row2; j++) {
    		   		re2[k]=rel2.get(j);
    		   	//	System.out.println( "  "+ re2[k][0]);
    		   		k++;
                }
    		   	//actual join
    		   	k=1;
       		   	for(int j=1;j<=row2;j++)
    		   	{   
    		   		HashFunction2 hf2=new HashFunction2();
    		   		int hash= hf2.getHash(re2[j][join_attri_index_rel2]);
    		   	//	System.out.println("Hash " +hash);
    		   	//	if(subj1.get(hash)!=null)
    		   		//{
    		   			List<String[]> t =new ArrayList<String[]>();
    		   			t=subj1.get(hash);
    		   			for(int z=0;z<t.size();z++)
    		   			{
    		   				String a[]=new String[attri_count1];
    		   				a=t.get(z);
    		   				
    		   			}
    		   			for(int z=0;z<t.size();z++)
    		   			{
    		   				String f[]= new String[attri_count1];
    		   						f=t.get(z);
    		   				if(f[join_attri_index_rel1].equals(re2[j][join_attri_index_rel2])==true)
    		   				{
    		   					out_tuple=new String[output_attri_count];
    	                        
    		   					for(c=0;c<attri_count1;c++)
    		   			       {
    		   			    	   out_tuple[c]=f[c];
    		   			       }
    		   			       for(int m=0;m<attri_count2 && m!=join_attri_index_rel2;m++)
    		   			       {
    		   			    	   out_tuple[c]=re2[j][m];
    		   			    	   c++;
    		   			       }
    		   			       
    		   					output.add(out_tuple);
    		   				}
    		   				
    		   			}
    		   		}
    		   	}
           // } //if 
    	}
       return output;
    }
 
    public void display(HashMap<Integer, List<String[]>> a )
    {
       for (HashMap.Entry<Integer, List<String[]>> entry : a.entrySet()) {
           int key = entry.getKey();
           List<String[]> values = entry.getValue();
           System.out.println("partition = " + key);
           for (int i = 0; i < values.size(); i++) {
               String[] strings =values.get(i);
               for (int j = 0; j < strings.length; j++) {
                   System.out.print(strings[j] + " ");
               }
               System.out.println();
           }

       }
    }
   public HashMap<Integer, List<String[]>> duplicate(HashMap<Integer, List<String[]>> original)
   {
	   HashMap<Integer, List<String[]>> copy=new HashMap<Integer, List<String[]>>();
	   for (Map.Entry<Integer, List<String[]>> entry : original.entrySet())
	    {
	        copy.put(entry.getKey(),new ArrayList<String[]>(entry.getValue()));
	    }
	   return copy;
   }
}
