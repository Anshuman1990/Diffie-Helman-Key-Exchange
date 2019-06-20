import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UTFDataFormatException;
import java.util.BitSet;
import java.util.Random;
import java.util.TreeSet;


public class enc {
	
	public static void main(String args[]) throws IOException{
		String key = "12345678901";
		String msg1 = "asdfghjkl";
		String msg = "aswedsad";
		String path = "D://documents/dictionary.txt";
		String path1 = "D://test.txt";
		StringBuilder sbuf = new StringBuilder();

		enc en = new enc();
		//String plntxt = en.txt_to_String(path);
		//String cipher1 = en.txt_to_String(path1);
		String cipher1 = enc.encryption(msg1, "1234567");
//		//String cipher = enc.binary_to_String(cipher1);
		System.out.println("Cipher= "+cipher1);
//		PrintWriter out = new PrintWriter(new File("D://test.txt"));
//		out.write(cipher1);
//		out.close();

	    String plaintext = enc.decryption(cipher1, "1234567");
		System.out.println("Decrypted= "+plaintext);

//		PrintWriter out1 = new PrintWriter(new File("D://test.txt"));
//		out1.write(plaintext);
//		out1.close();
//
		String str3 = enc.key_break(key);
		System.out.println(str3);
		String[] aa = str3.split("-");
		for(int i=0;i<aa.length;i++)
		{
			System.out.println("words are= "+aa[i]);
			System.out.println(enc.convert_to_binary(aa[i], 8));
		}
		

//		String ptext = enc.convert_to_binary(msg,8);
//		System.out.println("ptext= "+ptext);
//
//		String skey = enc.convert_to_binary(key,8);
//		System.out.println("skey= "+skey);
//
//		String cipher_bin = enc.encrypt(ptext, skey);
//		System.out.println("cipher= "+cipher_bin);

//		String cipher = enc.binary_to_String(cipher_bin);
//		System.out.println("cipher= "+cipher_bin);




//		System.out.println("After decryption= "+enc.decrypt(cipher1, key));
			
		}
	
	public static String encryption(String msg,String key) throws IOException
	{
		String out = "";
		StringBuilder sbuf = new StringBuilder(msg);
		int mlen = msg.length();
		int klen = key.length();
		//String msg1[] = msg.split(" ");
		//String key1[] = key.split(" ");
		int count=0,cnt=0;
		
		for(int i=0,j=0;i<mlen;i++,j++)
		{
			if(j>=klen)
                                                             {
				j=0;
			}
			sbuf.setCharAt(i, (char)(msg.charAt(i)^key.charAt(j)));
		}
		
		return sbuf.toString();
	}
	
	public static String decryption(String msg,String key) throws IOException
        {
		StringBuilder sbuf = new StringBuilder(msg);
		int mlen = msg.length();
		int slen = key.length();
		String pkey = "";
		String newkey="";
		Random rnd = new Random();
		for(int i=0;i<slen;i++){
			pkey+=rnd.nextInt(9);
		}
		for(int i=0,j=0;i<mlen;i++,j++)
		{
			if(j>=key.length())
			{
				j=0;
			}
			sbuf.setCharAt(i, (char)(msg.charAt(i)^key.charAt(j)));
		}
		return sbuf.toString();
	}	
	
	public static String convert_to_binary(String msg,int pad){
		StringBuilder sbuf = new StringBuilder();
		byte[] bytes = msg.getBytes();
		for(byte b:bytes){
			int val = b;
			for(int i=1;i<=pad;i++)
			{
				//System.out.println(val);
			sbuf.append((val & 128)==0? 0 : 1);
			//System.out.println(sbuf);
			val = val<<1;
			}
			sbuf.append(' ');
		 }
		String out = sbuf.toString();
		return out;
	}

	public static void my_convert_to_binary(String msg){
		int len = msg.length();
		char[] ch = new char[len];
		int[] in = new int[len];
		String[] bin = new String[len];
		for(int i=0;i<len;i++)
		{
			ch[i] = msg.charAt(i);
			in[i] = ch[i];
			//System.out.println(in[i]);
		}
		int j=0;
		int temp;
		String out = "";
		while(j<len){
			out+=in[j]%2;
			j++;
		}
		System.out.println(out);
	}
	
	public static String binary_to_String(String msg)
	{
		{ 
		    //String[] ss = msg.split( " " );
		    StringBuilder sb = new StringBuilder();
		    for ( int i = 0; i < msg.length(); i++ ) { 
		        sb.append( (char)Integer.parseInt( msg, 2 ) );                                                                                                                                                        
		    }   
		    return sb.toString();
		}   
	}
	
	public static String encrypt(String data, String key)
    {
        byte[] bytes1 = data.getBytes();
        StringBuilder binaryData = new StringBuilder();
        for (byte b : bytes1)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryData.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binaryData.append(' ');
        }
        //System.out.println(binaryData);

        byte[] bytes2 = key.getBytes();
        StringBuilder binaryKey = new StringBuilder();
        for (byte b : bytes2)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryKey.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binaryKey.append(' ');
        }
        //System.out.println(binaryKey);

        StringBuffer encryptedData = new StringBuffer();
        int x = 0;
        for (int i = 0; i < binaryData.length(); i++)
        {

            if (binaryData.charAt(i) == binaryKey.charAt(x))
            {
                encryptedData.append(0);
            }
            else
            {
                encryptedData.append(1);
            }
            if (x == binaryKey.length() - 1)
            {
                x = 0;
            }
            else
            {
                x++;
            }
        }
        return encryptedData.toString();
    }
	

	public static String decrypt(String encryptedDataTest, String key)
    {


        StringBuilder encryptedData = new StringBuilder();
        encryptedData.append(encryptedDataTest);
        StringBuffer unencryptedData = new StringBuffer();

        byte[] bytes2 = key.getBytes();
        StringBuilder binaryKey = new StringBuilder();
        for (byte b : bytes2)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryKey.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        int x = 0;
        for (int i = 0; i < encryptedData.length(); i++)
        {

            if (encryptedData.charAt(i) == binaryKey.charAt(x))
            {
                unencryptedData.append(0);
            }
            else
            {
                unencryptedData.append(1);
            }
            if (x == binaryKey.length() - 1)
            {
                x = 0;
            }
            else
            {
                x++;
            }
        }
        //System.out.println(unencryptedData);
        String out = "";
        char character = ' ';
        String testToString;
        String unencryptedDataToString = unencryptedData.toString();
        int currentBinary = 0;
        int currentInt = 0;
        for (int i = 0; i < unencryptedDataToString.length() / 8; i++)
        {
            unencryptedDataToString = unencryptedData.toString();
            testToString = (unencryptedDataToString.substring(i * 8, i * 8 + 8));
            currentBinary = Integer.parseInt(testToString, 2);
            currentInt = (int) currentBinary;
            character = (char) currentInt;
            out+=character;
            //System.out.print(character);
        }
        //System.out.println(out);
        //System.exit(0);
        return out;
    }
        public static String key_break(String key)
        {
        	String out="";
        	int hlv = key.length()/2;
        	System.out.println(key.length()/2);
        	int val=0;
        	
        		String str1 = key.substring(0, hlv);
        		String str2 = key.substring(hlv, key.length());
                String str3 = str1+"-"+str2;
                
        	return str3;
        }
        
        public static String txt_to_String(String path) throws IOException
        {
        	String msg = "";
          File f = new File(path);
        FileInputStream fin = new FileInputStream(f);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[256*1024];
		for (int readNum; (readNum = fin.read(buf)) != -1;) {
	        bos.write(buf, 0, readNum); //no doubt here is 0
	//Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
	        //System.out.println("read " + readNum + " bytes,");
	        }
	        
	        for(int i=0;i<buf.length;i++)
	        {
	        	int j=buf[i];
	        	char c=(char)j;
	        	msg+=c;
	        }
	        System.out.println(msg);
	        return msg;
        }
    }

