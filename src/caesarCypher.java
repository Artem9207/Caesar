import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class caesarCypher {

    public static final Character[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
            'ь', 'э', 'ю', 'я'};
    public static final Character[] alphabetUP = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й',
            'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    public static final Character[] punctuationMarks = {'.', ',', ':', '-', '!', '?', '"', '"', '/', '@', '#', '$', '%',
            '^', '%', '&', '*', '(', ')', '[', ']', '{', '}', '|', '<', '>', ';', '`', '~', '_', '=', '+'};

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        Path originalText = Path.of("Text.txt");
        Path encryptText = Path.of("TextEncrypt.txt");

        List<String> list = Files.readAllLines(originalText);
        for (String str1 : list) {
            Files.writeString(encryptText, encryption(str1));
            System.out.println("Text: " + str1);
            System.out.println("Encrypt text: " + encryption(str1));
        }

        String start = "\nChoose mode:\n 1) Decrypt with key\n 2) Brute force \n press 1 or 2";
        System.out.println(start);
        int choice = console.nextInt();

        List<String> list1 = Files.readAllLines(encryptText);
        if (choice == 1) {
            for (String str2 : list1) {
                System.out.println("Deciphered text is: " + decryption(str2));
            }
        } else if (choice == 2) {
            for (String str2 : list1) {
                brutForce brutForce = new brutForce();
                for(String encryptText1 : brutForce.plainTextMaker(str2)) {
                    System.out.println(encryptText1);
                }
            }
            System.out.println("\nSomething of that may be a look like a deciphered text");
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
                    if (index > alphabet.length) {
                        index -= 33;
                    }
                    textChar[i] = alphabet[index];
                } else if (Character.isUpperCase(textChar[i])) {
                    while (textChar[i] != alphabetUP[index]) index++;
                    index += key;
                    if (index > alphabetUP.length) {
                        index -= 33;
                    }
                    textChar[i] = alphabetUP[index];
                } else if (!Character.isAlphabetic(textChar[i])) {
                    while (textChar[i] != punctuationMarks[index]) index++;
                    index += key;
                    if (index > punctuationMarks.length) {
                        index -= 31;
                    }
                    textChar[i] = punctuationMarks[index];
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
                    if (index == -1) {
                        index = 32;
                    }
                    textChar[i] = alphabet[index];
                } else if (Character.isUpperCase(textChar[i])) {
                    while (textChar[i] != alphabetUP[index]) index++;
                    index -= key;
                    if (index == -1) {
                        index -= 32;
                    }
                    textChar[i] = alphabetUP[index];
                } else if (!Character.isAlphabetic(textChar[i])) {
                    while (textChar[i] != punctuationMarks[index]) index++;
                    index -= key;
                    if (index == -1) {
                        index -= 31;
                    }
                    textChar[i] = punctuationMarks[index];
                }
            }
        }
        for (char c : textChar) {
            result += c;
        }
        return result;
    }

    public static class brutForce {

        private final Character[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
                'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы',
                'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й',
                'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Э', 'Ю', 'Я', '.', ',', ':', '-', '!', '?', '"', '"', '/', '@', '#', '$', '%',
                '^', '%', '&', '*', '(', ')', '[', ']', '{', '}', '|', '<', '>', ';', '`', '~', '_', '=', '+'};
        private final String[] plainText;
        private final java.util.List<Character> alphabetList;

        public brutForce(){
            alphabetList = java.util.Arrays.asList(alphabet);
            plainText = new String[alphabet.length];
        }

        public String[] plainTextMaker(String cipherText) {
            char[] message = cipherText.toCharArray();

            for (int key = 0; key < alphabet.length; key++) {
                char[] decodedText = new char[message.length];
                for (int i = 0; i < message.length; i++) {
                    boolean Space = Character.isWhitespace(message[i]);
                    if (!Space) {
                        decodedText[i] = alphabet[(alphabetList.indexOf(message[i])+key) % alphabet.length];
                    } else {
                        decodedText[i] = ' ';
                    }
                }
                plainText[key] = String.valueOf(decodedText);
            }
            return plainText;
        }
    }
}