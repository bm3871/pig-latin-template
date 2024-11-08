import java.lang.*;

public class PigLatinTranslator
{
  public static Book translate(Book input)
  {
    Book translatedBook = new Book();


    // Add code here to populate translatedBook with a translation of the input book.
    // Curent do-nothing code will return an empty book.

    return translatedBook;
  }

  public static String translate(String input)
  {
    // System.out.println("Translate String: '" + input + "'");

    // Replace this code to translate a string input.
    // The input to this function could be any English string. 
    // A sentence, paragraph, or a single word. 
    // It should call translateWord at least once.
    String result = translateWord(input);

    return result;
  }

  private static String translateWord(String input)
  {
    // System.out.println("translateWord: '" + input + "'");

    // Word that starts with vowel
    if ("AEIOUaeiou".indexOf(input.charAt(0)) != -1) {
      return input + "ay";
    }

    // Words that start with a consonant
    int firstVowelIndex = -1;
    for (int i = 0; i < input.length(); i++) {
      if ("AEIOUaeiou".indexOf(input.charAt(i)) != -1) {
        firstVowelIndex = i;
        break;
      }
    }
    
    // Replace this code to correctly translate a single word.
    // Start here first!
    String result = input;
    
    return result;
  }

  // Add additonal private methods here.
  // For example, I had one like this:
  // private static String capitalizeFirstLetter(String input)

}