public class PigLatinTranslator {
    public static Book convert(Book originalBook) {
        Book pigLatinBook = new Book();
        pigLatinBook.setTitle(originalBook.getTitle());

        // Translate each line in the original book
        for (int i = 0; i < originalBook.getTotalLines(); i++) {
            String originalLine = originalBook.getLine(i);
            String translatedLine = (originalLine == null || originalLine.trim().isEmpty())
                    ? originalLine
                    : translateLine(originalLine);

            pigLatinBook.addLine(translatedLine);
        }

        return pigLatinBook; // Return the translated book for saving
    }

    private static String translateLine(String line) {
        if (line.trim().isEmpty()) {
            return line; // Preserve whitespace-only lines
        }

        StringBuilder result = new StringBuilder();
        String[] words = line.split(" ", -1); // Include trailing spaces
        for (int i = 0; i < words.length; i++) {
            result.append(translateWord(words[i]));
            if (i < words.length - 1) {
                result.append(" "); // Add spaces between words
            }
        }
        return result.toString();
    }

    private static String translateWord(String word) {
        if (word == null || word.isEmpty()) return word;

        String punctuation = "";
        while (!word.isEmpty() && !Character.isLetterOrDigit(word.charAt(word.length() - 1))) {
            punctuation = word.charAt(word.length() - 1) + punctuation;
            word = word.substring(0, word.length() - 1);
        }

        if (word.isEmpty()) return punctuation;

        if (word.contains("-")) {
            String[] parts = word.split("-");
            StringBuilder translated = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                translated.append(translateWord(parts[i]));
                if (i < parts.length - 1) {
                    translated.append("-");
                }
            }
            return translated + punctuation;
        }

        char firstChar = word.charAt(0);
        boolean isCapital = Character.isUpperCase(firstChar);

        if (isVowel(Character.toLowerCase(firstChar))) {
            return isCapital ? capitalize(word + "ay") + punctuation : word + "ay" + punctuation;
        }

        int firstVowelIndex = findFirstVowel(word);
        if (firstVowelIndex == -1) return word + punctuation;

        String pigLatinWord = word.substring(firstVowelIndex) + word.substring(0, firstVowelIndex) + "ay";
        return isCapital ? capitalize(pigLatinWord) + punctuation : pigLatinWord + punctuation;
    }

    private static int findFirstVowel(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) return i;
        }
        return -1;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(Character.toLowerCase(c)) >= 0;
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    public static String translate(String input) {
        return translateLine(input);
    }
}