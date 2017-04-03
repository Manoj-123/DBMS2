public class HashFunction1 {

     public int getHash(String joinattribute)
     {
    	 int hash=0;
         int len = joinattribute.length();
     	 for(int i=0; i<len; i++) {
     		hash += joinattribute.charAt(i);	
            }
     	return hash % 5;
     }
     
     
}
