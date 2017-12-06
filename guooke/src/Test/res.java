package Test;

import java.io.IOException;
import java.util.ArrayList;

import Filesreader.Indexbuild;
import coreMethod.BruteForceMatch;
import coreMethod.Editdistance;

public class res {
	   public String result=" ";
	   public String filename=" ";
	   public String fr=" ";
	   public String getdate=" ";
   
	public String searchresultforfile(String text) throws Exception {		
		 Indexbuild index=new Indexbuild();
		 int [] d=index.readFileforfr("D:\\mainindex.txt");
		 String [] e=index.readFileforvalue("D:\\mainindex.txt");
		 String[] f=index.readFileformulu("D:\\mainindex.txt");
		 long start,end;
		 start=System.nanoTime();
		 BruteForceMatch B=new BruteForceMatch();
		 int offset1a = B.search1(text, e);
		 if(offset1a !=-1) {
			 result=e[offset1a];
			 filename=f[offset1a];
			 fr=String.valueOf(d[offset1a]);
			 end=System.nanoTime();
			 getdate=String.valueOf((end-start)/1000000);
			 return f[offset1a];
		 }
		 else {
			 Editdistance E= new Editdistance();
			 int k=E.minDistance(e, text);
			 result=" we can't find it but the most similar one is "+e[k];
			 filename=f[k];
			 fr=String.valueOf(d[k]);
			 end=System.nanoTime();
			 getdate=String.valueOf((end-start)/1000000);
			 return f[k];
		 }
		
	}
	
	// no use anymore
	public int searchresultforfr(String text) throws Exception {		
		 Indexbuild index=new Indexbuild();
		 int [] d=index.readFileforfr("D:\\mainindex.txt");
		 String [] e=index.readFileforvalue("D:\\mainindex.txt");
		 String[] f=index.readFileformulu("D:\\mainindex.txt");
		 BruteForceMatch B=new BruteForceMatch();
		 int offset1a = B.search1(text, e);
		 if(offset1a !=-1) {
			 result=e[offset1a];
			 filename=f[offset1a];
			 return d[offset1a];
		 }
		 else {
			 Editdistance E= new Editdistance();
			 int k=E.minDistance(e, text);
			 result=" we can't find it but the most same one is "+e[k];
			 filename=f[k];
			 return d[k];
		 }
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String test="The";
        res r=new res();
        String filesname=r.searchresultforfile(test);
       
        System.out.println(r.result);
        System.out.println(r.filename);
        System.out.println(r.fr);
	}

}
