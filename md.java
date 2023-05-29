/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Q4 Message digest
*/
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MessageDigestProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        try {
            String digest = calculateMessageDigest(message);
            System.out.println("Message Digest (SHA-256): " + digest);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static String calculateMessageDigest(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(message.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

/*
Output
Enter the message: Hello, World!
Message Digest (SHA-256): 4b9e7916a296046098b3d388bc292b9d1b8f3f306f9f01e7053d13d7a3386a3e
*/
