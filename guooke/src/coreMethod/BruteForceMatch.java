package coreMethod;

/***************************************************************
*
*  Compilation:  javac Brtue.java
*  Execution:    java Brute pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using brute force.
*
*  % java Brute abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java Brute rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab                         
* 
*  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad

*
*  % java Brute bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
* 
*  % java Brute abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class BruteForceMatch {

  /***************************************************************************
   *  String versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public  int search1(String pat, String [] txt) {
       int M = pat.length();
       int N = txt.length;
       for(int k=0;k<N;k++) {
          if(txt[k].length()!=M) {
        	 
          }
          else {
        	   int j;
           for (j = 0; j < M; j++) {
               if (txt[k].charAt(j) != pat.charAt(j))
                   break;              
              }
           if (j == M) return k; 
           }
       }
      return -1;
                                   // not found
   }

   // return offset of first match or N if no match
   public  int search2(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (txt.charAt(i) == pat.charAt(j)) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   }


  /***************************************************************************
   *  char[] array versions
   ***************************************************************************/

   // return offset of first match or N if no match
   

   // test client
   public static void main(String[] args) {
       //String pat = args[0];
       //String txt = args[1];
	   
       // There are two implmentations of search
	   // one is with String and the other is an array of chars
	   BruteForceMatch B=new BruteForceMatch();
       String pat = "dsaff";
       String[] txt1= {"zxc","fewf","sdad","rqw","zzz","dsaff","word","match"};
       String txt = "A typical HDD design consists of a spindle that holds flat circular disks, also called platters, which hold the recorded data. The platters are made from a non-magnetic material, usually aluminum alloy, glass, or ceramic, and are coated with a shallow layer of magnetic material typically 10-20 nm in depth, with an outer layer of carbon for protection.[34][35][36] For reference, a standard piece of copy paper is 0.07-0.18 millimeters (70,000-180,000 nm).[37]\r\n"; 
       char[] pattern = pat.toCharArray();
       char[] text    = txt.toCharArray();

       int offset1a = B.search1(pat, txt1);
       int offset2a = B.search2(pat, txt);
       
       System.out.println("the offset is "+offset1a);

       // print results
      
   }
}