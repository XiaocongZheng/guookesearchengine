package Secondaryfunction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Filesreader.Seg;

public class ReadLinkandEmail {
 private static File fi=new File("D:\\W2C");
 
 
 public   static   String  StringFilteremil(String str)   throws   PatternSyntaxException   {     
	   String repl = "";  
	   String regEx="[`~!#$%^&*()-+=|{}':;',\\[\\]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
	   Pattern p = Pattern.compile(regEx);
	   java.util.regex.Matcher m = p.matcher(str); 
	   repl = m.replaceAll(" ");
	   return repl; 
	   }  
 
	 	 
	 private static boolean checkEmaile(String emaile){
	        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	   
	        Pattern p = Pattern.compile(RULE_EMAIL);
	       
	        Matcher m = p.matcher(emaile);
	       
	        return m.matches();
	    }
	 
	 public   static   String  StringFilter(String str)   throws   PatternSyntaxException   {     
   String repl = "";  
   String regEx="[`~!$%^&*()+=|{}';',\\[\\]?~！￥%……&*（）——+|{}【】‘；”“’。，、？]";  
   Pattern p = Pattern.compile(regEx);
   java.util.regex.Matcher m = p.matcher(str); 
   repl = m.replaceAll(" ");
   return repl; 
   }  
	 
	
	 
	 private  boolean  checkdomain(String emaile){
	  
		 Pattern reg = Pattern.compile("http://([^/]*).*");  
		 Matcher m = reg.matcher(emaile);  
		 return m.matches();
       
	    }
	 
	 
	 public void loadcheckcomnet(){
			File[] files = fi.listFiles();
			String fileforlder="D:\\links.txt";
			boolean a;
			try {
			for(File input:files) {
		   
			Document doc = Jsoup.parse(input,"UTF-8","http://www.w3c.net/");
			
			Element link = doc.select("a").first();    
			String text = doc.body().text();    
			
			text =text.replaceAll("<td>", "");
			text =text.replaceAll("/<td>", "");
			text =text.replaceAll("<li>", "");
			text =text.replaceAll("/<li>", "");
			
			text=StringFilter(text);
		    String filesname=input.getPath();
			
			
			Seg f=new Seg(); 
			String [] afterspilt=f.Seg(text);
			
			 File filenew = new File(fileforlder);
		
			 FileWriter out = new FileWriter(filenew,true);
	        
			for(int i=0;i<afterspilt.length;i++) {
			
				a=checkdomain(afterspilt[i]);
				if(a==true) {
					//System.out.println(afterspilt[i]+" found in "+filesname);
					 out.write(afterspilt[i]+";"+filesname);  
					 out.write("\r\n");
				}
			 }
			out.close();
			}
			
			}catch(Exception e)
			{
				System.out.println(e.getMessage()); 
			}
		}
	 

	 public void loadcheckemail(){
			File[] files = fi.listFiles();
			String fileforlder="D:\\email.txt";
			boolean a;
			try {
			for(File input:files) {
				//String fil=input.getPath();
				//System.out.println(fil);
		   
			Document doc = Jsoup.parse(input,"UTF-8","http://www.w3c.net/");
			
			Element link = doc.select("a").first();//查找第一个a元素    
			String text = doc.body().text();    
			//text =text.replaceAll("<.*>.*</[\\w-\\W-]*>", "");
			text =text.replaceAll("<td>", "");
			text =text.replaceAll("/<td>", "");
			text =text.replaceAll("<li>", "");
			text =text.replaceAll("/<li>", "");
			text =text.replaceAll("\"", "");
			text=StringFilteremil(text);
		    //取文档路径
			String filenames=input.getPath();
			 File filenew = new File(fileforlder);
				
			 FileWriter out = new FileWriter(filenew,true);
			Seg f=new Seg(); 
			String [] afterspilt=f.Seg(text);
	        
			for(int i=0;i<afterspilt.length;i++) {
				a=checkEmaile(afterspilt[i]);
				//System.out.println(a);
				if(a==true) {
					System.out.println(afterspilt[i]+" found in "+filenames);
					 out.write(afterspilt[i]+";"+filenames);  
					 out.write("\r\n");
					
				}
			
			}
			out.close();
			}
			}catch(Exception e)
			{
				System.out.println(e.getMessage()); 
			}
		}
	 
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadLinkandEmail R=new ReadLinkandEmail();
		R.loadcheckcomnet();
		R.loadcheckemail();

	}

}
