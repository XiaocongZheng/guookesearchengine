package coreMethod;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Editdistance {
	  


	public int minDistance(String[] word1, String word2) {
		  int a[]=new int[word1.length];
	      for(int k=0;k<word1.length;k++) { 
		  int len1 = word1[k].length(), len2 = word2.length();
	       int[][] dp = new int[len1 + 1][len2 + 1];
	       
	       for(int i = 0; i <= len1; i++){
	    	   dp[i][0] = i;
	       }
	       for(int i = 0; i <= len2; i++){
	    	   dp[0][i] = i;
	       }
	       
	       for(int i = 1; i <= len1; i++){
	    	   for(int j = 1; j <= len2; j++){
	    		   if(word1[k].charAt(i - 1) == word2.charAt(j - 1))
	    			   dp[i][j] = dp[i - 1][j - 1];
	    		   else dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
	    	   }
	       }
	        a[k]=dp[len1][len2];
	      }
	      int min=a[0];
	      int count = 0;
	      for(int m=0;m<a.length;m++) {
	    	  if (a[m]<min)
	          {
	               min = a[m];
	               count=m;
	          }
	      }
	      return count;
	    }
	  public  String getRandomString(int length) { //length表示生成字符串的长度
		    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
		    Random random = new Random();   
		    StringBuffer sb = new StringBuffer();   
		    for (int i = 0; i < length; i++) {   
		        int number = random.nextInt(base.length());   
		        sb.append(base.charAt(number));   
		    }   
		    return sb.toString();   
		 }   

	  
	  public static void main( String [ ] args ) 
	    {
		   String [] ceshi1 = {"test2","word","test","123134544","??daw","dsadi","xczvzxv","dqfr"};
		   String ceshi2="word";
		   String ceshi3="words";
		   String ceshi4="xzcdvwegafsd";
		   Editdistance E= new Editdistance();
		   int a=E.minDistance(ceshi1, ceshi2);
		   int b=E.minDistance(ceshi1, ceshi3);
		   int c=E.minDistance(ceshi1, ceshi4);
		   System.out.println("word's num is "+a);
		   System.out.println("word's num is "+b);
		   System.out.println("word's num is "+c);
		 
       }
}


