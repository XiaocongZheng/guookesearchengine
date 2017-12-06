package Filesreader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;

import coreMethod.SeparateChainingHashTable;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Filewrite {
	
	private static File fi=new File("D:\\indextext");
	
	public void write (String[] content,String filename,int[] fr)throws Exception 
	 {
		 File filenew = new File(filename);
		 filename=filename.replaceAll(".txt", ".htm");
		 filename=filename.replaceAll("indextext", "W2C");
		 FileWriter out = new FileWriter(filenew);
		   for(int j=0;j<content.length;j++){  
			   if(fr[j]!=0) {
			    out.write(content[j]+";"+fr[j]+";"+filename);  
			    out.write("\r\n");
			   }
			   }    
			  out.close();  
		
	 }
	
	   public static void appendMethod(String fileName, String content) {
	        try {
	            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	            FileWriter writer = new FileWriter(fileName, true);
	            writer.write(content);
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	public void gatherall() throws Exception{
		File[] files = fi.listFiles();
		// File filenew = new File("D:\\gather.txt");
		 StringBuilder result = new StringBuilder();
		// FileWriter out = new FileWriter(filenew);
		 ArrayList List = new ArrayList(); 
		for(File input:files) {
		In in=new In(input);
		 String read=in.readAll();
		 appendMethod("D:\\gather.txt",read);
		}
        
	}
	
	//only for testing
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		      Filewrite fw=new Filewrite();
			  fw.gatherall();
	}

}
