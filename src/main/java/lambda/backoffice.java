package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;

public class backoffice implements RequestStreamHandler {
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            JSONObject event = (JSONObject) parser.parse(reader);
            String eventBody = (String) event.get("body");
            if (!eventBody.equals("")) {
                System.out.println(eventBody);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
