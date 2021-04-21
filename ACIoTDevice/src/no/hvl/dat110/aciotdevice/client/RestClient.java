package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class RestClient {

    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String logpath = "/accessdevice/log";
    private static String codepath = "/accessdevice/code";
    private OkHttpClient client;

    public RestClient() {
        // TODO Auto-generated constructor stub
        this.client = new OkHttpClient();

    }

    public void doPostAccessEntry(String message) {

        Gson g = new Gson();
        RequestBody body = RequestBody.create(JSON, g.toJson(message));

        Request request = new Request.Builder().url(logpath).put(body).build();

		System.out.println(request.toString());

		try(Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO: implement a HTTP POST on the service to post the message
		// TODO: SJEKK - Meget sannsynlig at dette er feil
    }

    public AccessCode doGetAccessCode() {

        AccessCode code = null;
        Request request = new Request.Builder()
                .url(codepath)
                .get()
                .build();

        System.out.println(request.toString());

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: implement a HTTP GET on the service to get current access code
        // TODO: SJEKK - Meget sannsynlig at dette er feil

        return code;
    }
}
