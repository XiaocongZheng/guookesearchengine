package Filesreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;  
import java.util.Set;  



public class Indexbuild {

	public int [] readFileforfr(String filename) throws IOException {
        FileReader fr=new FileReader(filename);
        BufferedReader br=new BufferedReader(fr);
        String line="";
        ArrayList List = new ArrayList(); 
       
		while ((line=br.readLine())!=null) {
        	//System.out.println(line);only for testing
        	String [] arrs=line.split(";");
        	List.add(arrs[1]);
         }
        br.close();
        fr.close();
        int i=List.size();
        String[] recordings=new String[i];  
        for(int j=0;j<List.size();j++){  
        	recordings[j]=(String)List.get(j);  
        }  
        
        int[] recording=new int[i];
        for(int j=0;j<recording.length;j++){ 
    
        	recording[j] = Integer.parseInt(recordings[j]);
      
        }
        return recording;
    }
	
	
	

	public String [] readFileforvalue(String filename) throws IOException {
	        FileReader fr=new FileReader(filename);
	        BufferedReader br=new BufferedReader(fr);
	        String line="";
	        ArrayList List = new ArrayList(); 
	       
			while ((line=br.readLine())!=null) {
	        	//System.out.println(line);only for testing
	        	String [] arrs=line.split(";");
	        	List.add(arrs[0]);
	           //only for testing
	           // System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);//only for testing
	        }
	        br.close();
	        fr.close();
	        int i=List.size();
	        String[] recordings=new String[i];  
	        for(int j=0;j<List.size();j++){  
	        	recordings[j]=(String)List.get(j);  
	        }  
	        
	        return recordings;
	    }
	
	public String [] readFileformulu(String filename) throws IOException {
        FileReader fr=new FileReader(filename);
        BufferedReader br=new BufferedReader(fr);
        String line="";
        ArrayList List = new ArrayList(); 
       
		while ((line=br.readLine())!=null) {
        	//System.out.println(line);only for testing
        	String [] arrs=line.split(";");
        	List.add(arrs[2]);
           //only for testing
           // System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);//only for testing
        }
        br.close();
        fr.close();
        int i=List.size();
        String[] recordings=new String[i];  
        for(int j=0;j<List.size();j++){  
        	recordings[j]=(String)List.get(j);  
        }  
        
        return recordings;
    }
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 Indexbuild index=new Indexbuild();
		 int [] a=index.readFileforfr("D:\\gather.txt");
		 String [] b=index.readFileforvalue("D:\\gather.txt");
		String[] c=index.readFileformulu("D:\\gather.txt");
	/*	
		 int [] d=index.readFileforfr("D:\\mainindex.txt");
		 String [] e=index.readFileforvalue("D:\\mainindex.txt");
		String[] f=index.readFileformulu("D:\\mainindex.txt");
		
		//only for testing 
		
		  for(int j=0;j<c.length;j++){  
		 
			  System.out.println(a[j]); 
	        }
	      */   
		
		System.out.println(a.length);
		System.out.println(b.length);
		System.out.println(c.length);
	
		
		 
		Map<String, List<Integer >> map = new HashMap<String, List<Integer >>();
		for(int i=0;i<b.length;i++){
			if(map.containsKey(b[i])) {
			 List<Integer> listtest=new ArrayList<Integer>();
			 listtest=map.get(b[i]);
			 int k=listtest.get(0);
			  if(k<a[i]) {
				      listtest.set(0,a[i]);
					  listtest.set(1,i);				      
				      map.put(b[i], listtest);
				      }else{}
			}
			else {
				List<Integer> listtest=new ArrayList<Integer>();
				listtest.add(a[i]);
				listtest.add(i);
				map.put(b[i],listtest);
				Set<String> set=map.keySet();
						
			}  
		} 
	//collect all words 
	List<String> listtest=new ArrayList<String>();
	for (int i=0; i<b.length; i++) {    
        if(!listtest.contains(b[i])) {    
            listtest.add(b[i]);    
        }    
    }  
	
	String strings[]=new String[listtest.size()];
	for(int i=0,j=listtest.size();i<j;i++){
	strings[i]=listtest.get(i);
	}
	/* for testing
	for(int i=0;i<strings.length;i++) {
    List<Integer> list=new ArrayList<Integer>();
	list=map.get(strings[i]);
	System.out.println(strings[i]+"'s fr is "+list.get(0) +" and the location is "+list.get(1));

	}
	*/
	//write into the files
	File filenew = new File("D:\\mainindex.txt");
	
	 FileWriter out = new FileWriter(filenew);
	   for(int j=0;j<strings.length;j++){  
		    List<Integer> list=new ArrayList<Integer>();
			list=map.get(strings[j]);
		    out.write(strings[j]+";"+list.get(0)+";"+c[list.get(1)]);  
		    out.write("\r\n");
		   
		   }    
		  out.close();  
	
	}
}
