package AI;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroqClient {
    private static final String API_KEY = System.getenv("MI_API_KEY");
    private static final String URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final OkHttpClient client = new OkHttpClient();

    public static String preguntarIA(String prompt) throws Exception {
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        JSONArray messages = new JSONArray();
        messages.put(message);
        JSONObject body = new JSONObject();
        body.put("model", "llama-3.1-8b-instant");
        body.put("messages", messages);
        RequestBody requestBody = RequestBody.create(body.toString(), MediaType.parse("application/json"));
        Request request = (new Request.Builder()).url("https://api.groq.com/openai/v1/chat/completions").header("Authorization", "Bearer MI_API_KEY").header("Content-Type", "application/json").post(requestBody).build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        JSONObject json = new JSONObject(jsonString);

        try {
            return json.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        } catch (Exception var10) {
            return " eerror Groq API:\n" + jsonString;
        }
    }
}
