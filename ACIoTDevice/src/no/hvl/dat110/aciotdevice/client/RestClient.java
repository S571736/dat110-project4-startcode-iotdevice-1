package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class RestClient {

    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String logpath = "http://localhost:8080/accessdevice/log";
    private static final String codepath = "http://localhost:8080/accessdevice/code";
    private OkHttpClient client;

    public RestClient() {
        // TODO Auto-generated constructor stub


    }

    public void doPostAccessEntry(String message) {
        client = new OkHttpClient();
        Gson g = new Gson();
        message = "{\n" +
                "\"message\":" + message + "}";
        AccessMessage accessMessage = new AccessMessage(message);
        RequestBody body = RequestBody.create(JSON, g.toJson(accessMessage));
        System.out.println("Message: " + accessMessage);

        Request req = new Request.Builder()
                .url(logpath)
                .post(body)
                .build();

        try (Response response = client.newCall(req).execute()) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: implement a HTTP POST on the service to post the message
        // TODO: SJEKK - Meget sannsynlig at dette er feil
    }

    public AccessCode doGetAccessCode() {
        client = new OkHttpClient();
        Gson gson = new Gson();

        AccessCode code = null;

        Request request = new Request.Builder()
                .url(codepath)
                .get()
                .build();


        try (Response response = client.newCall(request).execute()) {

            String bodyString = "{\n" +
                    "    \"accesscode\":" + response.body().string() + "}";

            code = gson.fromJson(bodyString, AccessCode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: implement a HTTP GET on the service to get current access code
        // TODO: SJEKK - Meget sannsynlig at dette er feil

        return code;
    }
}
