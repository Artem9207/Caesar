package Services;

import static Symbols.Symbols.*;

public class Encryption {
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
}