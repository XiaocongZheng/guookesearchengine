package Filesreader;

import coreMethod.SeparateChainingHashTable;

public class Seg {
   public String [] Seg(String str) {
	   String [] arr = str.split("\\s+");
	   //for loop is only for test
/*	   for(String ss : arr){
	       System.out.println(ss);
	   }
*/
	   return arr;
   }
   
   //主函数只是用来测试。
   public static void main(String[] args) {
	   Seg f=new Seg();    
		 String test = "asadsasfd who i am 3c?dsa shdof    suahu   isoa      osdi     sid";
		 String []test1=f.Seg(test);
	  }  
}
