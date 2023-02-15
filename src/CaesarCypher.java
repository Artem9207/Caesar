import Services.Decryption;
import Services.FileReader;
import Services.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import static Files.Texts.*;
import static Services.Encryption.encryption;


// Artem Tarasyuk ROMEO

class CaesarCypher {
    public static void main(String[] args) throws IOException {

        Scanner console = new Scanner(System.in);

      List<String> originalList = Files.readAllLines(originalText);

      for (int i = 0; i < originalList.size(); i++) {
          String str = originalList.get(i);
          originalList.set(i, encryption(str));
      }

        FileWriter.fileWriter(encryptText, originalList);

        System.out.println("Original text is encrypted.\n\nText: ");

        FileReader.fileReader(originalText);

        System.out.println("\nEncrypt text: ");

        FileReader.fileReader(encryptText);

        System.out.println("\nChoose mode:\n 1) Decrypt with key\n 2) Brute force \n press 1 or 2");
        int choice = console.nextInt();

        if (choice == 1) {
            System.out.println("Deciphered text is: ");
            for (int i = 0; i < originalList.size(); i++) {
                String str = originalList.get(i);
                originalList.set(i, Decryption.decryptionKey(str));
            }
            FileWriter.fileWriter(decryptedText, originalList);

            FileReader.fileReader(decryptedText);

            System.out.println("Decrypted text now in 'decryptedText.txt'");

        } else if (choice == 2) {
            for (int i = 0; i < originalList.size(); i++) {
                String str = originalList.get(i);
                originalList.set(i, Decryption.bruitForce(str, 1));
                System.out.println(Decryption.bruitForce(str, 1));
            }
            System.out.println("\nThis text is readable?\nYes - 1/No - 2\npress 1 or 2\n");
            int choose = console.nextInt();

            if (choose == 2) {
                do {
                    for (int i = 0; i < originalList.size(); i++) {
                        String str = originalList.get(i);
                        originalList.set(i, Decryption.bruitForce(str, 1));
                        System.out.println(Decryption.bruitForce(str, 1));
                    }
                    System.out.println("\nThis text is readable?\nYes - 1/No - 2\npress 1 or 2\n");
                } while (console.nextInt() == 2);

                FileWriter.fileWriter(decryptedText, originalList);

                System.out.println("\nDecrypted text now in 'decryptedText.txt'");

            } if (choose == 1) {
                FileWriter.fileWriter(decryptedText, originalList);

                System.out.println("\nDecrypted text now in 'decryptedText.txt'");
            }
        }
    }
}