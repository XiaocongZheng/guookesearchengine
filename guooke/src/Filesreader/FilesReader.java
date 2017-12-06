package Filesreader;

import coreMethod.SeparateChainingHashTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.regex.*; 
import java.io.IOException;  

import org.jsoup.Connection;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;


import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.PrintStream;  
import java.io.PrintWriter;  
import java.io.RandomAccessFile; 

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

//取文件路径,如果要
public class FilesReader {
    private static File fi=new File("D:\\W2C");
	public  void showDirectory(File file){
	        File[] files = fi.listFiles();
	        for(File a:files){
	            System.out.println(a.getAbsolutePath());
	            if(a.isDirectory()){
	                showDirectory(a);
	            }
	        }
	    }

//创建txt文件相关	

	public static void saveAsFileWriter(String content,String savefile) {  
		  
		 FileWriter fwriter = null;
		 try {  
		  fwriter = new FileWriter(savefile);  
		  fwriter.write(content);  
		 } catch (IOException ex) {  
		  ex.printStackTrace();  
		 } finally {  
		  try {  
		   fwriter.flush();  
		   fwriter.close();  
		  } catch (IOException ex) {  
		   ex.printStackTrace();  
		  }  
		 }  
		}  
	 
	
	//去除特殊字符
	 public   static   String  StringFilter(String str)   throws   PatternSyntaxException   {     
       
   String repl = "";  
   String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
   Pattern p = Pattern.compile(regEx);
   java.util.regex.Matcher m = p.matcher(str); 
   repl = m.replaceAll(" ");
   return repl; 
   }  
	
	
	public void Filesjsoup(){
	File[] files = fi.listFiles();
	try {
	for(File input:files) {
   
	Document doc = Jsoup.parse(input,"UTF-8","http://www.w3c.net/");
	
	Element link = doc.select("a").first();   
	String text = doc.body().text();    
	text =text.replaceAll("<.*>.*</[\\w-\\W-]*>", "");
	text =text.replaceAll("<td>", "");
	text =text.replaceAll("/<td>", "");
	text =text.replaceAll("<li>", "");
	text =text.replaceAll("/<li>", "");
	text =text.replaceAll("\"", "");
	text.replaceAll("^,*|,*$", "");
	text =text.replaceAll("-", " ");
	text =text.replaceAll("/?", "");
	text=StringFilter(text);
    
	String fileforlder=input.getPath();
	String filenamenew=fileforlder.replaceAll(".htm", ".txt");
	String filenameindex=fileforlder.replaceAll(".htm", ".txt");
	filenamenew=filenamenew.replaceAll("W2C", "txt");
	filenameindex=filenameindex.replaceAll("W2C", "indextext");
	//每一个文档分词之后建立原始文件要素保存在txt文件中
	Seg f=new Seg(); 
	Filewrite w=new Filewrite();
	String [] afterspilt=f.Seg(text);
	SeparateChainingHashTable<String> e=new SeparateChainingHashTable<String>();
	saveAsFileWriter(text,filenamenew);
	//构造单文本记录索引
	
	//从此开始构造单独文件的词汇频率表格：
	
	 for (int i=0;i<afterspilt.length;i++)
	    {
	   	 e.insert(afterspilt[i]);
	    }
	 String [] value = e.wholehashtable();
	 int [] fr=new int[value.length];
	 for (int i=0;i<value.length;i++)
	    {
		  fr[i]=e.getfr(value[i]);
		 // System.out.println("the value is "+value[i]+" and the fr is "+fr[i]+" the index is "+i);
	    }
	 
	
	
	    
	 //输出到文件
	w.write(value,filenameindex,fr);
	
	
	//System.out.println(text);
	//System.out.println(filenamenew);
    
	}
	}catch(Exception e)
	{
		System.out.println(e.getMessage()); 
	}
}

	
	 public static void main(String[] args) throws Exception {
		    FilesReader f=new FilesReader();    
		    //f.showDirectory(fi);
	        f.Filesjsoup();	    
	 }
	        

}
