import java.io.*;
import java.util.Scanner;

public class text {
    
    // Method to write content to a file
    public static void writeFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename, true)) { // true = append mode
            writer.write(content + "\n");
            System.out.println("✅ Content written successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing file: " + e.getMessage());
        }
    }

    // Method to read content from a file
    public static void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("📖 File Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // Method to modify a file (replace a word)
    public static void modifyFile(String filename, String oldWord, String newWord) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            StringBuilder fileContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replaceAll(oldWord, newWord);
                fileContent.append(line).append("\n");
            }
            scanner.close();

            FileWriter writer = new FileWriter(filename);
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("✏️ File modified successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error modifying file: " + e.getMessage());
        }
    }

    // Main method with menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename = "sample.txt"; // File name

        while (true) {
            System.out.println("\n=== FILE HANDLING UTILITY ===");
            System.out.println("1. Write to File");
            System.out.println("2. Read File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text to write: ");
                    String text = sc.nextLine();
                    writeFile(filename, text);
                    break;

                case 2:
                    readFile(filename);
                    break;

                case 3:
                    System.out.print("Enter word to replace: ");
                    String oldWord = sc.nextLine();
                    System.out.print("Enter new word: ");
                    String newWord = sc.nextLine();
                    modifyFile(filename, oldWord, newWord);
                    break;

                case 4:
                    System.out.println(" Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println(" Invalid choice, try again.");
            }
        }
    }
}
