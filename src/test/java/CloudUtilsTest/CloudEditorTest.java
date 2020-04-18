package CloudUtilsTest;

import cloudutils.CloudEditor;
import cloudutils.CloudGetter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todo.TodoItem;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CloudEditorTest
{
    CloudEditor cloudEditor;
    CloudGetter cloudGetter;

    TodoItem item0;
    TodoItem item1;
    TodoItem item2;

    Boolean empty;



    @BeforeEach
    void setup() throws IOException
    {
        cloudEditor = new CloudEditor();
        cloudGetter = new CloudGetter();

        item0 = new TodoItem("test task 0", "just for testing 0",
                "2020-05-18T12:30");
        item1 = new TodoItem("test task 1", "just for testing 2",
                "2020-04-18T12:30");
        item2 = new TodoItem("test task 2", "just for testing 2",
                "2020-03-18T12:30");
    }



    @Test
    void addTodoItem() throws IOException {
        var resultingID = cloudEditor.addTodoItem(item0);
        var expected = "{\n" +
                "  \"title\": \"" + item0.getTitle() + "\",\n" +
                "  \"owner\": \"" + item0.getOwner() + "\",\n" +
                "  \"description\": \"" + item0.getDescription() + "\",\n" +
                "  \"creation time\": \"" + item0.getCreationTime() + "\",\n" +
                "  \"deadline time\": \""  + item0.getDeadlineTime() + "\",\n" +
                "  \"status\": \"" + item0.checkIfCompleted() + "\",\n" +
                "  \"id\": " + resultingID +
                "\n}";
        var actual = cloudGetter.getTodoItemJsonString(resultingID);

        assertEquals(expected, actual);

    }



    @Test
    void deleteExistingTodoItem() throws IOException
    {

        var resultingID = cloudEditor.addTodoItem(item0);
        var deleteResult = cloudEditor.deleteTodoItem(resultingID);
        assertTrue(deleteResult);
    }



    @Test
    void deleteNotExistingTodoItem() throws IOException
    {
        var nonExistingIDdeleteResult = cloudEditor.deleteTodoItem(152434354);
        assertFalse(nonExistingIDdeleteResult);
    }

    /*
    @Test
    void updateExistingTodoItem() throws IOException
    {
        var resultingID = cloudEditor.addTodoItem(item0);

        var updated = cloudEditor.updateTodoItem(item0, "test task 0", "just for testing 0", true,
                "2020-04-05T12:30");

        assertTrue(updated);

        var expected = "{\n" +
                "  \"title\": \"test task 0\",\n" +
                "  \"owner\": \"team4\",\n" +
                "  \"description\": \"just for testing 0\",\n" +
                "  \"creation time\": \"" + item0.getCreationTime() + "\",\n" +
                "  \"deadline time\": \""  + item0.getDeadlineTime() + "\",\n" +
                "  \"status\": \"" + item0.checkIfCompleted() + "\",\n" +
                "  \"id\": " + resultingID + "\n" +
                "}";
        var actual = cloudGetter.getTodoItemJsonString(resultingID);
        assertEquals(expected, actual);
    }
    */

    @Test
    void updateNotExistingTodoItem() throws IOException
    {

        var updated = cloudEditor.updateTodoItem(item1, "hello1", "hellohello", true,
                "2020-04-05T12:30");
        assertFalse(updated);
    }





}


