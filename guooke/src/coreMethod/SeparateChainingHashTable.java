package coreMethod;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

public class SeparateChainingHashTable<AnyType>
{
    /**
     * Construct the hash table.
     */
    public SeparateChainingHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size approximate table size.
     */
    public SeparateChainingHashTable( int size )
    {
        theLists = new LinkedList[ nextPrime( size ) ];
        theListsforfr = new LinkedList[ nextPrime( size ) ];
        for( int i = 0; i < theLists.length; i++ )
        {
        	theLists[ i ] = new LinkedList<>( );
        	theListsforfr[ i ] = new LinkedList<>( );
        }
            
    }

    /**
     * Insert into the hash table. If the item is
     * already present, then do nothing.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        List<AnyType> whichList = theLists[ myhash( x ) ];
        List<Integer> whichList2 = theListsforfr[ myhash( x ) ];
        if( !whichList.contains( x ) )
        {
            whichList.add(x);
            whichList2.add(0,1);
                // Rehash; see Section 5.5
            if( ++currentSize > theLists.length )
                rehash( );
        }
        else {
        	int k=whichList2.get(0)+1;
        	whichList2.set(0, k);
        }
    }
    
   
    
    public void insertlist( AnyType x )
    {
        List<AnyType> whichList = theLists[ myhash( x ) ];
      
        if( !whichList.contains( x ) )
        {
            whichList.add( x );
            
                // Rehash; see Section 5.5
            if( ++currentSize > theLists.length )
                rehash( );
        }
    }
    
    
    /**
     * Remove from the hash table.
     * @param x the item to remove.
     */
 /*
    public void remove( AnyType x )
    {
        List<AnyType> whichList = theLists[ myhash( x ) ];
        if( whichList.contains( x ) )
    {
        whichList.remove( x );
            currentSize--;
    }
    }
*/
    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return true if x isnot found.
     */
    public boolean contains( AnyType x )
    {
        List<AnyType> whichList = theLists[ myhash( x ) ];
        return whichList.contains( x );
    }
    
    
    public int getfr( AnyType x )
    {
        List<Integer> whichList = theListsforfr[ myhash( x ) ];
        List<AnyType> whichList2 = theLists[ myhash( x ) ];
        if( whichList2.contains( x ) )
        {
        int k=whichList.get(0);
      
        return k;
        }
        else {
        	return 0;
        }
    }
    
    public String getword( AnyType x )
    {      
        List<AnyType> whichList = theLists[ myhash( x ) ];
        String y;
        if( whichList.contains( x ) )
    {
        y=whichList.toString();
        y =y.replaceAll("\\[", "");
        y =y.replaceAll("\\]", "");
        return y;   
    }
        else {
        	return null;
        }
    }
    
    //遍历hash表
    public String [] wholehashtable()
    {    
    	  int i =0;
    	  String []y = new String[theLists.length];
    	  int j= 0;
    	  while(i < theLists.length) {
    		 List<AnyType> whichList = theLists[i];
    		 if(whichList.isEmpty()) {
    			 i=i+1;
    		 }
    		 else {
    			    y[j]=whichList.toString();
    			    y[j] =y[j].replaceAll("\\[", "");
    		        y[j] =y[j].replaceAll("\\]", "");   
    		        i=i+1;
    		        j=j+1;
    		 }
    	 }
    	  StringBuffer sb = new StringBuffer();
    	  for(int k=0; k<y.length; k++) {
              if(y[k] == null) {
                  continue;
              }
              sb.append(y[k]);
              if(k != y.length - 1) {
                  sb.append(";");
              }
          }
          //用String的split方法分割，得到数组
          y = sb.toString().split(";");
          
    	  return y;
    }

  
    
    
    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        for( int i = 0; i < theLists.length; i++ )
            theLists[ i ].clear( );
        currentSize = 0;    
    }

    /**
     * A hash routine for String objects.
     * @param key the String to hash.
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public static int hash( String key, int tableSize )
    {
        int hashVal = 0;

        for( int i = 0; i < key.length( ); i++ )
            hashVal = 37 * hashVal + key.charAt( i );

        hashVal %= tableSize;
        if( hashVal < 0 )
            hashVal += tableSize;

        return hashVal;
    }

    private void rehash( )
    {
    	List<AnyType> [ ]  oldLists = theLists;
        List<Integer> [ ]  oldList2 = theListsforfr;
            // Create new double-sized, empty table
        theLists = new List[ nextPrime( 2 * theLists.length ) ];
        theListsforfr = new List[ nextPrime( 2 * theLists.length ) ];
        for( int j = 0; j < theLists.length; j++ )
        {
        	theLists[ j ] = new LinkedList<>( );
        	theListsforfr[j] =new LinkedList<>( );
        }

            // Copy table over
        currentSize = 0;
        for( List<AnyType> list : oldLists )
        {
        	for( AnyType item : list )
        		insert( item );
        }
     
        
    }

    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= theLists.length;
        if( hashVal < 0 )
            hashVal += theLists.length;

        return hashVal;
    }
    
    private static final int DEFAULT_TABLE_SIZE = 101;

        /** The array of Lists. */
    private List<AnyType> [ ] theLists; 
    private List<Integer> [ ] theListsforfr;
    private int currentSize;

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }


//mian function is only for testing
public static void main(String[] args) {
	SeparateChainingHashTable f=new SeparateChainingHashTable();    
	 String [] test = {"asadsasfd","12","12","28","asdgeeg","qwe","qwe","iuo","iuo,fanuf","tye","eds","asadsasfd","eds","eds","eds","?<dads","asadsasfddasfihdsuigbiaubsgiuadbgbuiabgifbidgdigb","asadsasfddasfihdsuigbiaubsgiuadbgbuiabgifbidgdigb",""};
  
    for (int i=0;i<test.length;i++)
    {
   	 f.insert(test[i]);
    }
   String [] k=f.wholehashtable();
   int j[]=new int[k.length];
	  for (int l=0;l<k.length;l++) {
		  System.out.println(k[l]);
	  }
	  //getfr使用方法，取建立表以后的数值之后在使用
	  for (int i=0;i<k.length;i++)
	    {
	   	 j[i]=f.getfr(k[i]);
	   	 System.out.println("the fr for "+k[i]+" is "+j[i]+"and the index is "+i);
	    }
	  
  }
}
