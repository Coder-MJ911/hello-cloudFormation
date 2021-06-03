package lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.joda.time.LocalDate;
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
                System.out.println(new LocalDate() + "Body from request: " + eventBody);
            }
            AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();
            String topic = "arn:aws:sns:ap-southeast-2:160071257600:MyCustomTopic";
            snsClient.publish(topic, eventBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
