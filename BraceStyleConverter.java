import java.io.*;
import java.util.Scanner;
public class BraceStyleConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file (such as HelloWorld.java): ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println(fileName + " does not exist.");
            return;}
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");}
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;}
        String modifiedContent = convertBraceStyle(content.toString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(modifiedContent);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());}
        System.out.println(fileName + " is converted to the end-of-line brace style.");}
    private static String convertBraceStyle(String content) {
        StringBuilder modifiedContent = new StringBuilder();
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().equals("{")) {
                modifiedContent.deleteCharAt(modifiedContent.length() - 1); // Remove the newline
                modifiedContent.append(" {");
            } else {
                modifiedContent.append(line).append("\n");}}
        return modifiedContent.toString();}}
