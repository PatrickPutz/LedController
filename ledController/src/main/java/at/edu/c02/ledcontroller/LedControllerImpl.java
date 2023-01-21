package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public void demoId(int id) throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLight(id);
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        if (lights.length() < 1){
            System.out.println("Specified id does not exist!");
        }
        else{
            // read the first json object of the lights array
            JSONObject lightByID = lights.getJSONObject(0);
            // read int and string properties of the light
            System.out.println("light id is: " + lightByID.getInt("id"));
            System.out.println("light color is: " + lightByID.getString("color"));
        }
    }

    @Override
    public JSONArray getGroupLeds() throws IOException {

        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");

        JSONArray groupLeds = new JSONArray();
        for(int i = 0; i < lights.length(); i++){
            String group = lights.getJSONObject(i).getJSONObject("groupByGroup").getString("name");
            if(group.equalsIgnoreCase("E")){
                groupLeds.put(lights.getJSONObject(i));
            }
        }
        return groupLeds;
    }

}
