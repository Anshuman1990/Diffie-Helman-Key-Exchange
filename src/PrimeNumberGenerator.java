

import java.math.BigInteger;
import java.util.Random;

public class PrimeNumberGenerator {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final int ERR_VAL = 100;

    public static void main(String args[]) {
        PrimeNumberGenerator png = new PrimeNumberGenerator();
        BigInteger prime = png.primeGenerate(10,10);
        System.out.println("Prime= " + prime);
    }

    public BigInteger generate() {
        Boolean chk = false;
        double prime = 0, formula = 0;

        BigInteger big = new BigInteger("2");
        BigInteger big1 = new BigInteger("1");
        BigInteger out = null;

        Random rnd = new Random();
        int num = 0;
        num = rnd.nextInt(6 - 2) + 2;
        out = big.pow(2 * num).add(big1);

        return out;
    }

    public static BigInteger nextPrime(BigInteger start) {
        if (isEven(start))
            start = start.add(ONE);
        else
            start = start.add(TWO);
        if (start.isProbablePrime(ERR_VAL))
            return (start);
        else
            return (nextPrime(start));
    }

    private static boolean isEven(BigInteger n) {
        return (n.mod(TWO).equals(ZERO));
    }

    public BigInteger primeGenerate(int length,int certainty){
    	BigInteger prime = null;
    	Random random = new Random();
    	prime = new BigInteger(length,certainty,random);
    	do{
			prime = new BigInteger(length,certainty,random);
		}
    	while(!prime.isProbablePrime(certainty));

    	return prime;
	}
}
