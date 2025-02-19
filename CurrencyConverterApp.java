package package1;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONObject;
public class CurrencyConverterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.println("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();
        System.out.println("Enter the amount to convert: ");
        double amount = scanner.nextDouble();
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate != -1) {
            double convertedAmount = amount * exchangeRate;
            System.out.println(amount + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);
        } else {
            System.out.println("Error fetching exchange rate.");
        }
        scanner.close();
    }
    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String apiKey = "5bf4d936008687a60f389548";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            if (jsonResponse.getString("result").equals("success")) {
                JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");
                return conversionRates.getDouble(targetCurrency);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}