import java.util.Scanner;
import java.io.*;

public class GroceryReport {

    public static void loadGroceryData(String filename, String[] names, double[] prices) {
        try {
            Scanner reader = new Scanner(new File(filename));
            int i = 0;
            while (reader.hasNextLine() && i < 50) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    names[i] = parts[0].trim();
                    prices[i] = Double.parseDouble(parts[1].trim());
                    i++;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public static double calculateAveragePrice(double[] prices, int count) {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += prices[i];
        }
        return total / count;
    }

    public static void writeReport(String[] names, double[] prices, int count, double average) {
        try {
            PrintWriter writer = new PrintWriter("grocery_report.txt");
            writer.println("Grocery Items Report");
            writer.println("--------------------");
            for (int i = 0; i < count; i++) {
                writer.printf("%s: $%.2f%n", names[i], prices[i]);
            }
            writer.printf("%nAverage Price: $%.2f%n", average);
            writer.close();
            System.out.println("Report written to grocery_report.txt");
        } catch (IOException e) {
            System.out.println("Error writing report.");
        }
    }

    public static void main(String[] args) {
        String[] names = new String[50];
        double[] prices = new double[50];
        int count = 0;

        loadGroceryData("groceries.txt", names, prices);

        for (int i = 0; i < 50; i++) {
            if (names[i] != null) count++;
        }

        double average = calculateAveragePrice(prices, count);
        writeReport(names, prices, count, average);
    }
}
