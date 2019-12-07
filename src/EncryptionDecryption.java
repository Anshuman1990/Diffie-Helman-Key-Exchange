import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;


public class EncryptionDecryption {

    public static void main(String args[]) throws IOException {


    }


    public static String encryption(String msg, BigInteger key) {
        StringBuilder stringBuilder = new StringBuilder(msg);
        int messageLength = msg.length();
        for (int i = 0; i < messageLength; i++) {
            stringBuilder.setCharAt(i, (char) (msg.charAt(i) ^ key.intValue()));
        }
        return stringBuilder.toString();
    }

    public static String decryption(String encryptedMessage, BigInteger key) {
        StringBuilder stringBuilder = new StringBuilder(encryptedMessage);
        int messageLength = encryptedMessage.length();
        for (int i = 0; i < messageLength; i++) {
            stringBuilder.setCharAt(i, (char) (encryptedMessage.charAt(i) ^ key.intValue()));
        }
        return stringBuilder.toString();
    }
}

