package Backend;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;

public class EncryptDecryptFile {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String SECRET_KEY = "ThisIsASecretKey";

//    public static void main(String[] args) {
//        encryptFile("Files/test.txt", "Files/test.txt");
//        decryptFile("Files/test.txt", "Files/test.txt");
//    }
    
    public static void encrypt() {
    	encryptFile("Files/users.txt", "Files/users.txt");
    }
    public static void decrypt() {
    	decryptFile("Files/users.txt", "Files/users.txt");
    }

    public static void encryptFile(String inputFilePath, String outputFilePath) {
        try {
            Key secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFilePath);
            byte[] inputBytes = new byte[(int) new File(inputFilePath).length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

//            System.out.println("File encrypted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String inputFilePath, String outputFilePath) {
        try {
            Key secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFilePath);
            byte[] inputBytes = new byte[(int) new File(inputFilePath).length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

//            System.out.println("File decrypted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
