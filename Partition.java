import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Partition {
     static List<String[]> partition0 = new ArrayList<String[]>();
	 static List<String[]> partition1 = new ArrayList<String[]>();
	 static List<String[]> partition2 = new ArrayList<String[]>();
	 static List<String[]> partition3 = new ArrayList<String[]>();
	 static List<String[]> partition4 = new ArrayList<String[]>();
	 static List<String[]> partition5 = new ArrayList<String[]>();
	 static List<String[]> partition6 = new ArrayList<String[]>();
	 static List<String[]> partition7 = new ArrayList<String[]>();
	 static List<String[]> partition8 = new ArrayList<String[]>();
	 static List<String[]> partition9 = new ArrayList<String[]>();
	 static List<String[]> partition10 = new ArrayList<String[]>();
	 static HashMap<Integer, List<String[]>> hashmap = new HashMap<Integer, List<String[]>>();
public HashMap<Integer, List<String[]>> createPartition(String relation[], int joinattribute_index, int hftype )
 { 
	int hash=-2;
	List<String[]> addpartition = new ArrayList<String[]>();
	if(hftype==1){
	  HashFunction1 hf1=new HashFunction1();
	  hash = hf1.getHash(relation[joinattribute_index]);
	}
	else
	 {
		HashFunction2 hf2=new HashFunction2();
	    hash=hf2.getHash(relation[joinattribute_index]);
	}
	switch(hash)
         {
	        case 0:     partition0.add(relation);
	                    addpartition=partition0;
	                    break;
	        case 1:     partition1.add(relation);
	                    addpartition=partition1;
	        			break;
            case 2:     partition2.add(relation);
                        addpartition=partition2;
                        break;
	        case 3:     partition3.add(relation);
                        addpartition=partition3;

            			break;
	        case 4:     partition4.add(relation);
                        addpartition=partition4;
             			break;
	        case 5:     partition5.add(relation);
	                    addpartition=partition5;
            			break;
	        case 6:     partition6.add(relation);
	                    addpartition=partition6;
	                    break;
	        case 7:     partition7.add(relation);
	                    addpartition=partition7;
            			break;
	        case 8:     partition8.add(relation);
	        			addpartition=partition8;
	                    break;
	        case 9:     partition9.add(relation);
	        			addpartition=partition9;			
	        			break;
	        case 10:    partition10.add(relation);
	        			addpartition=partition10;
	                    break;
	        default:    System.out.println("This won't happen");
	                 	break;

         } 	
	hashmap.put(hash, addpartition);
	return hashmap;
    
  }
public void reuselist()
 {
   partition0.clear();
   partition1.clear();
   partition2.clear();
   partition3.clear();
   partition4.clear();
   partition5.clear();
   partition6.clear();
   partition7.clear();
   partition8.clear();
   partition9.clear();
   partition10.clear();
 }
}
