import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Run test cases to validate translation logic
        TestSuite.run();

        // Define the books for translation
        Book[] books = {
            new Book("Romeo and Juliet", "https://www.gutenberg.org/cache/epub/1513/pg1513.txt"),
            new Book("The Happy Tree", "https://www.gutenberg.org/cache/epub/74787/pg74787.txt")
        };

        // Process and translate each book
        Arrays.stream(books).forEach(App::processBook);
    }

    private static void processBook(Book book) {
        // Load the book from its source
        System.out.println("Loading book: " + book.getTitle());
        book.loadFromSource();

        // Preview the original content
        System.out.println("Preview of \"" + book.getTitle() + "\":");
        book.previewContent(0, 2);

        // Translate the book content into Pig Latin
        Book translatedBook = PigLatinTranslator.convert(book);

        // Preview the translated content
        System.out.println("--- Translated Preview: " + book.getTitle() + " ---");
        translatedBook.previewContent(0, 2);

        // Save the translated content to a file
        String outputFileName = book.getTitle().replace(" ", "_") + "_PigLatin.txt";
        System.out.println("Saving translated book to: " + outputFileName);
        translatedBook.saveToFile(outputFileName);
    }
}