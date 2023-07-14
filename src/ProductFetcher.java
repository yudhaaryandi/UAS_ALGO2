
    import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

    public class Ascending {
        private static final String URL_ADDRESS = "https://dummyjson.com/products";
        private static final String X_CONS_ID_HEADER = "X-Cons_ID";
        private static final String USER_KEY_HEADER = "user_key";
        private static final String X_CONS_ID_VALUE = "1234567";
        private static final String USER_KEY_VALUE = "faY738sH";

        public static void main(String[] args) {
            try {
                String jsonData = fetchDataFromURL();
                Product[] products = parseJsonData(jsonData);
                sortProductsByRating(products);
                displaySortedProducts(products);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String fetchDataFromURL() throws IOException {
            URL url = new URL(URL_ADDRESS);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty(X_CONS_ID_HEADER, X_CONS_ID_VALUE);
            connection.setRequestProperty(USER_KEY_HEADER, USER_KEY_VALUE);

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            return response.toString();
        }

        private static Product[] parseJsonData(String jsonData) {
            // Assuming the JSON data has a structure that can be mapped to a Product class
            // Modify this method accordingly based on the actual JSON structure

            // Example parsing implementation:
            // JSONArray productsArray = new JSONArray(jsonData);
            // Product[] products = new Product[productsArray.length()];
            // for (int i = 0; i < productsArray.length(); i++) {
            //     JSONObject productObject = productsArray.getJSONObject(i);
            //     String name = productObject.getString("name");
            //     int rating = productObject.getInt("rating");
            //     products[i] = new Product(name, rating);
            // }
            // return products;

            // For demonstration purposes, let's assume jsonData contains only one product
            return new Product[]{
                    new Product("Product A", 3),
                    new Product("Product B", 5),
                    new Product("Product C", 2),
                    new Product("Product D", 4),
                    new Product("Product E", 1)
            };
        }

        private static void sortProductsByRating(Product[] products) {
            int n = products.length;
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (products[j].getRating() < products[minIndex].getRating()) {
                        minIndex = j;
                    }
                }
                swap(products, i, minIndex);
            }
        }

        private static void swap(Product[] array, int i, int j) {
            Product temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        private static void displaySortedProducts(Product[] products) {
            System.out.println("Sorted Products by Rating (Ascending):");
            for (Product product : products) {
                System.out.println("Name: " + product.getName() + ", Rating: " + product.getRating());
            }
        }

        // Product class representing the structure of each product entity
        private static class Product {
            private String name;
            private int rating;

            public Product(String name, int rating) {
                this.name = name;
                this.rating = rating;
            }

            public String getName() {
                return name;
            }

            public int getRating() {
                return rating;
            }
        }
    }



