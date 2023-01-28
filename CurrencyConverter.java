import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();
        System.out.println("Enter the currency to convert from:");
        String fromCurrency = scanner.next();
        System.out.println("Enter the currency to convert to:");
        String toCurrency = scanner.next();
        try {
            double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.println(amount + " " + fromCurrency + " = " + convertedAmount + " " + toCurrency);
        } 
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }   
        scanner.close();
    }
    private static double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        URL url = new URL("https://api.exchangerate-api.com/v4/latest/" + fromCurrency);

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String response = reader.readLine();

        int start = response.indexOf("\"" + toCurrency + "\":") + ("\"" + toCurrency + "\":").length();
        int end = response.indexOf(",", start);
        String rate = response.substring(start, end);

        return Double.parseDouble(rate);
    }
}