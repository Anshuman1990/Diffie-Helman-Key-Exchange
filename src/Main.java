import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        String msg = "qwerty";
        BigInteger g = BigInteger.ONE;

        PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();
        BigInteger p = primeNumberGenerator.primeGenerate(10, 10);
        Setup set = new Setup();
        g = set.start(p);
        while (g == null) {
            p = primeNumberGenerator.primeGenerate(10, 10);
            g = set.start(p);
        }

        System.out.println("p= " + p);
        System.out.println("g= " + g);

        Integer a = 4;
        BigInteger A = set.A(g, p, a);

        Integer b = 3;
        BigInteger B = set.A(g, p, b);

        BigInteger s_Alice = set.S(B, a, p);

        BigInteger s_Bob = set.S(A, b, p);

        String encMsg = EncryptionDecryption.encryption(msg, s_Alice);

        String decMsg = EncryptionDecryption.decryption(encMsg, s_Bob);

        System.out.println("Encrypted Message:-" + encMsg);

        System.out.println("Decrypted Message:-" + decMsg);


    }
}
