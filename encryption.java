/*
Name : Harshit Kaushik
Roll no : 16
Course : Mca-II
Subject : Networking
Assignment 1

Q :- Encryption and Decryption 
*/

import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the key (shift value): ");
        int key = scanner.nextInt();

        if (choice == 1) {
            String encryptedText = encrypt(text, key);
            System.out.println("Encrypted text: " + encryptedText);
        } else if (choice == 2) {
            String decryptedText = decrypt(text, key);
            System.out.println("Decrypted text: " + decryptedText);
        } else {
            System.out.println("Invalid choice");
        }

        scanner.close();
    }

    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char encryptedChar = (char) ((ch + key - 'a') % 26 + 'a');
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(ch);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                char decryptedChar = (char) ((ch - key - 'a' + 26) % 26 + 'a');
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(ch);
            }
        }

        return decryptedText.toString();
    }
}

/*
Output
1. Encrypt
2. Decrypt
Enter your choice: 1
Enter the text: hello
Enter the key (shift value): 3
Encrypted text: khoor

1. Encrypt
2. Decrypt
Enter your choice: 2
Enter the text: khoor
Enter the key (shift value): 3
Decrypted text: hello
*/
