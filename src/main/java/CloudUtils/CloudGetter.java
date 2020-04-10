package CloudUtils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class CloudGetter
{
    private HttpRequestFactory requestFactory;
    private String baseURL = "https://todoserver222.herokuapp.com";
    private String team4URL = "/team4/todos";

    public CloudGetter() { requestFactory = new NetHttpTransport().createRequestFactory(); }

    public String getTodoItemJsonString() throws IOException
    {
        HttpRequest getRequest = requestFactory.buildGetRequest( new GenericUrl(baseURL + team4URL));
        String rawResponse = getRequest.execute().parseAsString();
        return rawResponse;
    }
}
