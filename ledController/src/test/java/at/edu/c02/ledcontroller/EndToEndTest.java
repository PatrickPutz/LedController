package at.edu.c02.ledcontroller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndToEndTest {

    @Test
    public void testSetColorStateAndProve() throws Exception{
        ApiService apiService = new ApiServiceImpl();
        LedController ledController = new LedControllerImpl(apiService);
        apiService.setLight(43, "#FF5733", false);
        String test = "{\"color\":\"#FF5733\",\"id\":43,\"groupByGroup\":{\"name\":\"E\"},\"on\":true}";

    }

}
