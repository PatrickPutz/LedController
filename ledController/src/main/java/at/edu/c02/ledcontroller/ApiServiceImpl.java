package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class should handle all HTTP communication with the server.
 * Each method here should correspond to an API call, accept the correct parameters and return the response.
 * Do not implement any other logic here - the ApiService will be mocked to unit test the logic without needing a server.
 */
public class ApiServiceImpl implements ApiService {
    /**
     * This method calls the `GET /getLights` endpoint and returns the response.
     * TODO: When adding additional API calls, refactor this method. Extract/Create at least one private method that
     * handles the API call + JSON conversion (so that you do not have duplicate code across multiple API calls)
     *
     * @return `getLights` response JSON object
     * @throws IOException Throws if the request could not be completed successfully
     */
    @Override
    public JSONObject getLights() throws IOException
    {
        return createGetRequest(new URL("https://balanced-civet-91.hasura.app/api/rest/getLights"));
    }

    @Override
    public JSONObject getLight(int id) throws IOException {
        return createGetRequest(new URL("https://balanced-civet-91.hasura.app/api/rest/lights/" + id));
    }

    public JSONObject createGetRequest(URL url) throws IOException {

        HttpURLConnection connection = establishConnection(url);

        // The request was successful, read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // Save the response in this StringBuilder
        StringBuilder sb = new StringBuilder();

        int character;
        // Read the response, character by character. The response ends when we read -1.
        while((character = reader.read()) != -1) {
            sb.append((char) character);
        }

        String jsonText = sb.toString();
        // Convert response into a json object
        return new JSONObject(jsonText);
    }

    private HttpURLConnection establishConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setRequestProperty("X-Hasura-Group-ID", "996fb92427ae41e4649b934");

        int responseCode = connection.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: connection failed with no response code " + responseCode);
        }
        return connection;
    }

}