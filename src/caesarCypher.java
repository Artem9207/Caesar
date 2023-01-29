import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

// Artem Tarasyuk ROMEO

class caesarCypher {

    public static final Character[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
            'ь', 'э', 'ю', 'я'};
    public static final Character[] alphabetUP = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й',
            'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    public static final Character[] symbols = {'.', ',', ':', '-', '!', '?', '"', '"', '/', '@', '#', '$', '%',
            '^', '%', '&', '*', '(', ')', '[', ']', '{', '}', '|', '<', '>', ';', '`', '~', '_', '=', '+', '—',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        Path originalText = Path.of("text.txt");
        Path encryptText = Path.of("textEncrypt.txt");
        Path decryptedText = Path.of("decryptedText.txt");
        List<String> originalList = Files.readAllLines(originalText);

        for (int i = 0; i < originalList.size(); i++) {
            String str = originalList.get(i);
            originalList.set(i, encryption(str));
        }

        try {
            Files.write(encryptText, originalList, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Original text is encrypted.\n\nText: ");

        if (Files.exists(originalText)) {
            try {
                byte[] bytes = Files.readAllBytes(originalText);
                String text = new String(bytes, StandardCharsets.UTF_8);

                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("\nEncrypt text: ");

        if (Files.exists(encryptText)) {
            try {
                byte[] bytes = Files.readAllBytes(encryptText);
                String text = new String(bytes, StandardCharsets.UTF_8);

                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        String start = "\nChoose mode:\n 1) Decrypt with key\n 2) Brute force \n press 1 or 2";
        System.out.println(start);
        int choice = console.nextInt();

        if (choice == 1) {
            System.out.println("Deciphered text is: ");
            for (int i = 0; i < originalList.size(); i++) {
                String str = originalList.get(i);
                originalList.set(i, decryption(str));
            }
            try {
                Files.write(decryptedText, originalList, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Files.exists(decryptedText)) {
                try {
                    byte[] bytes = Files.readAllBytes(decryptedText);
                    String text = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(text);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Decrypted text now in 'decryptedText.txt'");

        } else if (choice == 2) {
            for (int i = 0; i < originalList.size(); i++) {
                String str = originalList.get(i);
                originalList.set(i, bruitForce(str,1));
                System.out.println(bruitForce(str, 1));
            }
            System.out.println("\nThis text is readable?\nYes - 1/No - 2\npress 1 or 2\n");
            int choose = console.nextInt();

            if (choose == 2) {
                do {
                    for (int i = 0; i < originalList.size(); i++) {
                        String str = originalList.get(i);
                        originalList.set(i, bruitForce(str, 1));
                        System.out.println(bruitForce(str, 1));
                    }
                    System.out.println("\nThis text is readable?\nYes - 1/No - 2\npress 1 or 2\n");
                } while (console.nextInt() == 2);

                try {
                    Files.write(decryptedText, originalList, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\nDecrypted text now in 'decryptedText.txt'");


            } if (choose == 1) {
                try {
                    Files.write(decryptedText, originalList, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\nDecrypted text now in 'decryptedText.txt'");
            }
        }
    }
    public static String encryption(String text) {
        String result = "";
        int key = 3;

        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length; i++) {
            int index = 0;
            boolean Space = Character.isWhitespace(textChar[i]);
            if (!Space) {
                if (Character.isLowerCase(textChar[i])) {
                    while (textChar[i] != alphabet[index]) index++;
                    index += key;
                    if (index > 32) {
                        index -= 33;
                    }
                    textChar[i] = alphabet[index];
                } else if (Character.isUpperCase(textChar[i])) {
                    while (textChar[i] != alphabetUP[index]) index++;
                    index += key;
                    if (index > 32) {
                        index -= 33;
                    }
                    textChar[i] = alphabetUP[index];
                } else if (!Character.isAlphabetic(textChar[i])) {
                    while (textChar[i] != symbols[index]) index++;
                    index += key;
                    if (index > 42) {
                        index -= 41;
                    }
                    textChar[i] = symbols[index];
                }
            }
        }
        for (char c : textChar) {
            result += c;

        }
        return result;
    }

    public static String decryption(String text) {
        String result = "";
        int key = 3;

        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length; i++) {
            int index = 0;
            boolean Space = Character.isWhitespace(textChar[i]);
            if (!Space) {
                if (Character.isLowerCase(textChar[i])) {
                    while (textChar[i] != alphabet[index]) index++;
                    index -= key;
                    if (index <= -1) {
                        index = 32;
                    }
                    textChar[i] = alphabet[index];
                } else if (Character.isUpperCase(textChar[i])) {
                    while (textChar[i] != alphabetUP[index]) index++;
                    index -= key;
                    if (index <= -1) {
                        index -= 32;
                    }
                    textChar[i] = alphabetUP[index];
                } else if (!Character.isAlphabetic(textChar[i])) {
                    while (textChar[i] != symbols[index]) index++;
                    index -= key;
                    if (index <= -1) {
                        index -= 41;
                    }
                    textChar[i] = symbols[index];
                }
            }
        }
        for (char c : textChar) {
            result += c;

        }
        return result;
    }

    public static String bruitForce(String text, int key) {
        String result = "";

        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length; i++) {
            int index = 0;
            boolean Space = Character.isWhitespace(textChar[i]);
            if (!Space) {
                if (Character.isLowerCase(textChar[i])) {
                    while (textChar[i] != alphabet[index]) index++;
                    index -= key;
                    if (index <= -1) {
                        index = 32;
                    }
                    textChar[i] = alphabet[index];
                } else if (Character.isUpperCase(textChar[i])) {
                    while (textChar[i] != alphabetUP[index]) index++;
                    index -= key;
                    if (index <= -1) {
                        index -= 32;
                    }
                    textChar[i] = alphabetUP[index];
                } else if (!Character.isAlphabetic(textChar[i])) {
                    while (textChar[i] != symbols[index]) index++;
                    index -= key;
                    if (index <= -1) {
                        index -= 41;
                    }
                    textChar[i] = symbols[index];
                }
            }
        }
        for (char c : textChar) {
            result += c;

        }
        return result;
    }
}