import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList; 

public class Book
{
    private String title;
    private String sourceURL;
    private ArrayList<String> content;

    // Constructor
    public Book() {
        this.content = new ArrayList<>();
    }

    public Book(String title, String sourceUrl) {
        this();
        this.title = title;
        this.sourceUrl = sourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void loadFromSource() {
        if (sourceUrl == null || sourceUrl.isEmpty()) {
            System.err.println("Source URL is not provided for the book: " + title);
            return;
        }

        try {
            URL url = new URL(sourceUrl);
            try (Scanner scanner = new Scanner(url.openStream())) {
                while (scanner.hasNextLine()) {
                    content.add(scanner.nextLine());
                }
            }
            System.out.println("Successfully loaded book: " + title);
        } catch (IOException e) {
            System.err.println("Error loading book from URL: " + e.getMessage());
        }
    }

    public void previewContent(int start, int lines) {
        System.out.println("--- Preview: " + title + " ---");
        for (int i = start; i < start + lines; i++) {
            if (i < content.size()) {
                System.out.println((i + 1) + ": " + content.get(i));
            } else {
                System.out.println((i + 1) + ": [No more content]");
            }
        }
    }

    public String getLine (int lineNumber) {
        return lineNumber >= 0 && lineNumber < content.size() ? content.get(lineNumber) : "";
    }

    public int getTotalLines() {
        return content.size();
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Content saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving content to file: " + e.getMessage());
        }
    }

    public void addLine(String line) {
        content.add(line);
    }
}