import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Setup {


    public BigInteger start(BigInteger val) {
        BigInteger result = null;
        BigInteger k = BigInteger.ONE;
        while (val.compareTo(k) == 1) {
            BigInteger count = prime_modulo(k, val);
            count = count.add(BigInteger.ONE);
            if (count.compareTo(val) == 0) {
                result = k;
                break;
            }
            k = k.add(BigInteger.ONE);
        }
        return result;
    }

    public ArrayList<BigInteger> co_prime(BigInteger val, BigInteger i)  //change void to int in course of time
    {
        ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
        Vector vex = new Vector();
        Boolean chk = false;
        int count = 0;

        for (BigInteger j = i; j.compareTo(BigInteger.ONE) > 0; j = j.subtract(BigInteger.ONE)) {
            if (val.mod(j).equals(BigInteger.ZERO) && i.mod(j).equals(BigInteger.ZERO)) {
                chk = true;
                count++;
            }
        }
        if (chk == false) {
            arr.add(i);
        }
        //System.out.println(arr.size());
        return arr;
    }

    public BigInteger prime_modulo(BigInteger pm, BigInteger val) {
        BigInteger pow;
        BigInteger count = BigInteger.ZERO;
        int i = 1;
        while (true) {
            pow = pm.pow(i);
            BigInteger val1 = val.subtract(BigInteger.ONE);
            if (pow.mod(val).equals(BigInteger.ONE) || val1.equals(count)) {
                count = count.add(BigInteger.ONE);
                break;
            } else// if(count>size)
            {
                i++;
                count = count.add(BigInteger.ONE);
            }
        }
        return count;
    }

    public BigInteger A(BigInteger g, BigInteger prime, int a) {
        BigInteger out = (g.pow(a)).mod(prime);
        return out;
    }

    public BigInteger S(BigInteger val, int rnd, BigInteger prime) {
        BigInteger out = val.pow(rnd).mod(prime);
        return out;
    }


}