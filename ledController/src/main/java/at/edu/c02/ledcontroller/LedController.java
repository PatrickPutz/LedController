package at.edu.c02.ledcontroller;

import org.json.JSONArray;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    void demoId(int id) throws IOException;
    JSONArray getGroupLeds() throws IOException;
    void act(String input) throws IOException;
}
