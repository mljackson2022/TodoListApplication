package CloudUtils;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import todo.TodoItem;

import java.io.IOException;
import java.util.*;

public class CloudEditor
{
    private HttpRequestFactory requestFactory;
    private String baseURL = "https://todoserver222.herokuapp.com/";
    private String todosURL = baseURL + "todos/";       // https://todoserver222.herokuapp.com/team4/todos
    private String team4URL = baseURL + "team4/todos";
    private String owner = "team4";

    public CloudEditor()
    {
        requestFactory = new NetHttpTransport().createRequestFactory();
    }

    public int addTodoItem(String title) throws IOException
    {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("title", title);
        data.put("owner", owner);
        HttpContent content = new UrlEncodedContent(data);
        HttpRequest postRequest = requestFactory.buildPostRequest(
                new GenericUrl("https://todoserver222.herokuapp.com/todos"), content);
        String rawResponse = postRequest.execute().parseAsString();

        int indexOfID = rawResponse.indexOf("\"id\"");
        String IDWithEnding = rawResponse.substring(indexOfID + 6);
        String IDWithoutEnding = IDWithEnding.substring(0, IDWithEnding.length() - 2);

        return(Integer.valueOf(IDWithoutEnding));
    }


    public boolean deleteTodoItem(int id) throws IOException
    {
        HttpRequest deleteRequest = requestFactory.buildDeleteRequest(
                new GenericUrl(todosURL + id));
        try
        {
            String rawResponse = deleteRequest.execute().parseAsString();
        }
        catch (HttpResponseException hre)
        {
            return false;
        }
        return true;
    }


    public boolean updateTodoItem(int id, String title) throws IOException {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("title", title);
        data.put("owner", owner);
        data.put("id", id);
        HttpContent content = new UrlEncodedContent(data);
        HttpRequest putRequest = requestFactory.buildPutRequest(
                new GenericUrl(todosURL + id), content);
        try
        {
            String rawResponse = putRequest.execute().parseAsString();
        }
        catch (HttpResponseException hre)
        {
            return false;
        }

        return true;
    }

    public List<String> getAllTeam4TodoItems() throws IOException
    {
        HttpRequest getRequest = requestFactory.buildGetRequest(
                new GenericUrl(team4URL));
        String rawResponse = getRequest.execute().parseAsString();

        String[] split = rawResponse.split("},");
        List<String> itemList = new ArrayList<>();
        itemList = Arrays.asList(split);

        return(itemList);
    }

    public boolean clearCloud() throws IOException
    {
        List<String> allItems = this.getAllTeam4TodoItems();
        int indexOfID = -1;
        String IDWithEnding = null;
        int indexOfEnding = -1;
        String IDWithoutEnding = null;

        if(allItems.get(0).equals("[]"))
        {
            return(true);
        }

        for (int i = 0; i < allItems.size(); i++)
        {
            indexOfID = allItems.get(i).indexOf("\"id\"");
            IDWithEnding = allItems.get(i).substring(indexOfID + 6);
            indexOfEnding = IDWithEnding.indexOf(" ");
            IDWithoutEnding = IDWithEnding.substring(0, indexOfEnding-1);

            deleteTodoItem(Integer.valueOf(IDWithoutEnding));
        }

        HttpRequest getRequest = requestFactory.buildGetRequest(
                new GenericUrl(team4URL));
        String rawResponse = getRequest.execute().parseAsString();

        if(rawResponse.equals("[]"))
        {
            return(true);
        }
        else
        {
            return(false);
        }
    }
}
