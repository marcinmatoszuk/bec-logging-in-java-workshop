package dk.bec.poland.aa.logging.task_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public final class Entity {
  //TODO: add Logger here
  private final int id;
  private final String category;

  public Entity(int id, String category) {
    this.id = id;
    this.category = category;
  }

  // TODO: create toString() implementation here

  // TODO: add logging to this method
  public String randString() throws IOException {
    String url = "https://random-word-api.herokuapp.com/";

    HttpURLConnection urlConnection =
        (HttpURLConnection) new URL(url + "/word?number=" + id).openConnection();
    urlConnection.setRequestMethod("GET");
    urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
    String response;

    if (urlConnection.getResponseCode() == 200) {
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
        response = reader.readLine();
      }
    } else {
      throw new RuntimeException("Failed to connect");
    }

    String serialized = serialize(response);
    int randomIndex = new Random().nextInt(10);
    return serialized.split(",")[randomIndex];
  }

  //TODO:  add logging to this method
  private static String serialize(String rawString) {
    return rawString.replace("[", "").replace("]", "").replace("\"", "");
  }
}
