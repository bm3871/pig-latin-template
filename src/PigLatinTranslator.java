import java.lang.*;

public class PigLatinTranslator
{
    public static String translate(String input)
    {
        if (input.trim().isEmpty()) return input; // Return whitespace as is

        // Split the input into words and translate each word
        String[] words = input.split(" ");
        StringBuilder translatedSentence = new StringBuilder();

        for (String word : words) {
            translatedSentence.append(translateWord(word)).append(" ");
        }

        // Return the translated sentence, trimming the trailing space
        return translatedSentence.toString().trim();
    }

    private static String translateWord(String input)
    {
        if (input.isEmpty()) return input; // Handle empty string case

        // Handle initials or abbreviations with periods (e.g., "M.C.")
        if (input.matches("([A-Za-z]\\.)+")) {
            String[] parts = input.split("\\.");
            StringBuilder translatedParts = new StringBuilder();
            for (String part : parts) {
                translatedParts.append(translatePart(part)).append(".");
            }
            return translatedParts.toString();
        }

        // Handle punctuation at the end of the word
        String punctuation = "";
        if (input.matches(".*[.,!?;]$")) {
            punctuation = input.substring(input.length() - 1);
            input = input.substring(0, input.length() - 1);
        }

        // Handle hyphenated words (split by hyphen and process separately)
        if (input.contains("-")) {
            String[] parts = input.split("-");
            StringBuilder translatedParts = new StringBuilder();
            for (String part : parts) {
                translatedParts.append(translatePart(part)).append("-");
            }
            // Remove the last hyphen and add punctuation if any
            return translatedParts.toString().substring(0, translatedParts.length() - 1) + punctuation;
        }

        // Translate the word normally and return it with punctuation
        return translatePart(input) + punctuation;
    }

    private static String translatePart(String input)
    {
        if (input.isEmpty()) return input; // Handle empty string case

        // Capitalization preservation logic
        String lowerCaseInput = input.toLowerCase();
        boolean isFirstUpper = Character.isUpperCase(input.charAt(0));

        // Check if the word starts with a vowel
        if ("aeiou".indexOf(lowerCaseInput.charAt(0)) != -1) {
            // If the word starts with a vowel, add "ay" at the end
            return (isFirstUpper ? capitalizeFirstLetter(input) : input) + "ay";
        }

        // Find the first vowel in the word
        int firstVowelIndex = -1;
        for (int i = 0; i < lowerCaseInput.length(); i++) {
            if ("aeiou".indexOf(lowerCaseInput.charAt(i)) != -1) {
                firstVowelIndex = i;
                break;
            }
        }

        if (firstVowelIndex != -1) {
            // Move the consonants before the first vowel to the end and add "ay"
            String translated = lowerCaseInput.substring(firstVowelIndex) + lowerCaseInput.substring(0, firstVowelIndex) + "ay";
            return (isFirstUpper ? capitalizeFirstLetter(translated) : translated);
        }

        // If no vowels found, just add "ay" at the end
        return (isFirstUpper ? capitalizeFirstLetter(input) : input) + "ay";
    }

    // Helper method to capitalize the first letter of a word
    private static String capitalizeFirstLetter(String input)
    {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}