package CloudUtilsTest;

import CloudUtils.CloudEditor;
import CloudUtils.CloudGetter;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CloudEditorTest
{
    CloudEditor cloudEditor;
    CloudGetter cloudGetter;
    Boolean empty;

    /*
        Important note: running CloudEditorTest will result in
        any TodoItems that were on the cloud prior to be removed
    */

    @BeforeEach
    void setup() throws IOException
    {
        cloudEditor = new CloudEditor();
        cloudGetter = new CloudGetter();
    }

    @Test
    void addTodoItem() throws IOException {
        var resultingID = cloudEditor.addTodoItem("fake task");
        var expected = "{\n" +
                "  \"title\": \"fake task\",\n" +
                "  \"owner\": \"team4\",\n" +
                "  \"id\": " + resultingID + "\n" +
                "}";
        var actual = cloudGetter.getTodoItemJsonString(resultingID);

        assertEquals(expected, actual);

        empty = cloudEditor.clearCloud();
        assertTrue(empty);
    }

    @Test
    void deleteExistingTodoItem() throws IOException
    {
        empty = cloudEditor.clearCloud();
        assertTrue(empty);

        var resultingID = cloudEditor.addTodoItem("hello task");
        var deleteResult = cloudEditor.deleteTodoItem(resultingID);
        assertTrue(deleteResult);
    }

    @Test
    void deleteNotExistingTodoItem() throws IOException
    {
        var nonExistingIDdeleteResult = cloudEditor.deleteTodoItem(152434354);
        assertFalse(nonExistingIDdeleteResult);
    }


    @Test
    void updateExistingTodoItem() throws IOException
    {
        var resultingID = cloudEditor.addTodoItem("test task 1");
        var updated = cloudEditor.updateTodoItem(resultingID, "test task 2");

        assertTrue(updated);

        var expected = "{\n" +
                "  \"title\": \"test task 2\",\n" +
                "  \"owner\": \"team4\",\n" +
                "  \"id\": " + resultingID + "\n" +
                "}";
        var actual = cloudGetter.getTodoItemJsonString(resultingID);
        assertEquals(expected, actual);
    }

    @Test
    void updateNotExistingTodoItem() throws IOException
    {
        var updated = cloudEditor.updateTodoItem(214323, "Hello1");
        assertFalse(updated);
    }

    @Test
    void clearCloud() throws IOException
    {
        cloudEditor.addTodoItem("clear task 1");
        cloudEditor.addTodoItem("clear task 2");
        cloudEditor.addTodoItem("clear task 3");

        var empty = cloudEditor.clearCloud();
        assertTrue(empty);
    }

    @Test
    void clearingAnEmptyCloud() throws IOException
    {
        empty = cloudEditor.clearCloud();
        assertTrue(empty);

        empty = cloudEditor.clearCloud();
        assertTrue(empty);
    }

    @Test
    void getAllTeam4TodoItems() throws IOException
    {
        empty = cloudEditor.clearCloud();
        assertTrue(empty);

        cloudEditor.addTodoItem("thing to do 1");
        cloudEditor.addTodoItem("thing to do 2");
        cloudEditor.addTodoItem("thing to do 3");
        var actual = cloudEditor.getAllTeam4TodoItems().size();
        assertEquals(3, actual);

        empty = cloudEditor.clearCloud();
        assertTrue(empty);
    }

}
