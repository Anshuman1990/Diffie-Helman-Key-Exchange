import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;



public class setup_biginteger{
	public Vector vex = new Vector();
	
	public static void main(String er[])
	{
		setup_biginteger set = new setup_biginteger();
		BigInteger big = new BigInteger("13");
		BigInteger v = set.start(big);
		System.out.println(v);
	}
	
	public BigInteger start(BigInteger val)
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Vector<Integer> vex = new Vector<Integer>();
		int size=0;
		BigInteger it = BigInteger.ONE;
		int val1 = val.intValue();
		System.out.println("val1= "+val1);
//		while(it.compareTo(val)<0)
//		{
			//arr = co_prime(val, it);
			for(int j = 1;j<val1;j++)
			{
			vex.addElement(j);
			}
//			it=it.add(BigInteger.ONE);
//		}
		ArrayList<BigInteger> out = new ArrayList<BigInteger>();
		BigInteger result = null;
		size = vex.size();
		System.out.println("size= "+size);
		Boolean chk = true;
		int k=0;
		while(chk == true && k<vex.size()){
		//for(int k=0;k<vex.size();k++)
		//{
			//System.out.println("Co-Primes of "+val+" are "+vex.elementAt(k));
			
			int pm =  vex.elementAt(k);
			System.out.println("pm= "+pm);
			BigInteger pm1 = new BigInteger(pm+"");
			int count = prime_modulo(pm1, val);
			if(count==vex.size())
			{
				result = pm1;
				chk = false;
				//out.add(pm);
			}
			//System.out.println("-----------------------");
		//}
			k++;
		}
		return result;
	}
	
	public ArrayList<BigInteger> co_prime(BigInteger val,BigInteger i)  //change void to int in course of time
	{
		ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
		Vector vex = new Vector(); 
		Boolean chk = false;
		int count = 0;
		
		for(BigInteger j=i;j.compareTo(BigInteger.ONE)>0;j = j.subtract(BigInteger.ONE))
		{
			//System.out.println("Checking..... "+j);
			if(val.mod(j).equals(BigInteger.ZERO) && i.mod(j).equals(BigInteger.ZERO))
			{
			 chk = true;
			 count++;
			}
		}
		if(chk==false)
		{
			arr.add(i);
		}
		//System.out.println(arr.size());
		return arr;
	}
	
	public int prime_modulo(BigInteger pm,BigInteger val)
	{
		BigInteger pow;
		boolean chk = true;
	 	int count = 0,i=1;
	 			while(chk=true)
	 			{
	 			 pow = pm.pow(i);
	 			//System.out.println("pow= "+pow);
	 			BigInteger val1 = val.subtract(BigInteger.ONE);
	 			if(pow.mod(val).equals(BigInteger.ONE) || val1.equals(count))
	 			{
	 				//System.out.println("mod function = "+pow.mod(val));
	 				count++;
	 				chk = false;
	 				break;	 				
	 			}
	 			else// if(count>size)
	 			{
	 				//System.out.println("mod function = "+pow.mod(val));
	 				i++;
	 				count++;
	 			}
	 		}
	 			//System.out.println("st= "+pm);
	 	//System.out.println("count= "+count);
	 	return count;
	}
	public BigInteger A(BigInteger g,BigInteger prime,int a)
	{
		BigInteger out = (g.pow(a)).mod(prime);
		return out;
	}
	public BigInteger S(BigInteger val,int rnd,BigInteger prime)
                    {
                                         BigInteger out = val.pow(rnd).mod(prime);
                                         return out;
                    }
	
	
	
}