import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherInfoApp extends Application {
    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key
    private VBox historyBox = new VBox();
    private VBox forecastBox = new VBox();
    private Label weatherLabel = new Label();
    private ImageView weatherIcon = new ImageView();
    private BackgroundPane backgroundPane = new BackgroundPane();
    private List<String> searchHistory = new ArrayList<>();
    private boolean isCelsius = true;

    @Override
    public void start(Stage primaryStage) {
        TextField cityInput = new TextField();
        cityInput.setPromptText("Enter city name");

        Button searchBtn = new Button("Get Weather");
        Button switchUnitBtn = new Button("Switch Units");

        searchBtn.setOnAction(e -> fetchWeatherData(cityInput.getText()));
        switchUnitBtn.setOnAction(e -> toggleUnits());

        HBox controls = new HBox(10, cityInput, searchBtn, switchUnitBtn);
        controls.setPadding(new Insets(10));

        weatherIcon.setFitWidth(100);
        weatherIcon.setFitHeight(100);

        VBox mainBox = new VBox(10, controls, weatherLabel, weatherIcon, forecastBox, new Label("Search History:"), historyBox);
        mainBox.setPadding(new Insets(10));

        backgroundPane.setCenter(mainBox);

        Scene scene = new Scene(backgroundPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather Information App");
        primaryStage.show();

        updateBackground();
    }

    private void fetchWeatherData(String city) {
        try {
            String units = isCelsius ? "metric" : "imperial";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=" + units;
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");
            JSONObject weather = json.getJSONArray("weather").getJSONObject(0);

            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            double windSpeed = wind.getDouble("speed");
            String condition = weather.getString("main");
            String iconCode = weather.getString("icon");

            String tempUnit = isCelsius ? "°C" : "°F";
            String windUnit = isCelsius ? "m/s" : "mph";
            weatherLabel.setText("Temp: " + temp + tempUnit + ", Humidity: " + humidity + "%" + ", Wind: " + windSpeed + windUnit + ", " + condition);

            Image icon = new Image("https://openweathermap.org/img/wn/" + iconCode + "@2x.png");
            weatherIcon.setImage(icon);

            String timestamp = java.time.LocalDateTime.now().toString();
            searchHistory.add(city + " @ " + timestamp);
            updateHistory();
            updateBackground();
            fetchForecast(city);
        } catch (Exception e) {
            weatherLabel.setText("Error fetching data. Please check the city name.");
            weatherIcon.setImage(null);
            forecastBox.getChildren().clear();
        }
    }

    private void fetchForecast(String city) {
        forecastBox.getChildren().clear();
        try {
            String units = isCelsius ? "metric" : "imperial";
            String urlString = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + API_KEY + "&units=" + units;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(result.toString());
            for (int i = 0; i < 5; i++) {
                JSONObject item = json.getJSONArray("list").getJSONObject(i);
                JSONObject main = item.getJSONObject("main");
                String date = item.getString("dt_txt");
                double temp = main.getDouble("temp");
                String description = item.getJSONArray("weather").getJSONObject(0).getString("description");
                forecastBox.getChildren().add(new Label(date + ": " + temp + (isCelsius ? "°C" : "°F") + " - " + description));
            }
        } catch (Exception e) {
            forecastBox.getChildren().add(new Label("Forecast unavailable."));
        }
    }

    private void updateHistory() {
        historyBox.getChildren().clear();
        for (String entry : searchHistory) {
            historyBox.getChildren().add(new Label(entry));
        }
    }

    private void updateBackground() {
        LocalTime now = LocalTime.now();
        if (now.isAfter(LocalTime.of(18, 0)) || now.isBefore(LocalTime.of(6, 0))) {
            backgroundPane.setStyle("-fx-background-color: #2c3e50;"); // Night
        } else if (now.isAfter(LocalTime.of(6, 0)) && now.isBefore(LocalTime.of(12, 0))) {
            backgroundPane.setStyle("-fx-background-color: #87ceeb;"); // Morning
        } else {
            backgroundPane.setStyle("-fx-background-color: #ffa500;"); // Afternoon/Evening
        }
    }

    private void toggleUnits() {
        isCelsius = !isCelsius;
    }

    public static void main(String[] args) {
        launch(args);
    }

    class BackgroundPane extends BorderPane {}
}
