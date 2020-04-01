package todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList
{
    private String nameOfTodoList = ""; //final?
    private static int nextNameDigit = 0;

    private List<TodoItem> itemsInTodoList = new ArrayList<TodoItem>();

    public TodoList()
    {
        nameOfTodoList = "List " + getNextNameDigit();
    }

    public static String getNextNameDigit()
    {
        String digit = Integer.toString(nextNameDigit);
        nextNameDigit++;
        return(digit);
    }

    public String getNameOfList()
    {
        return(nameOfTodoList);
    }

    public void setNameOfList(String name)
    {
        nameOfTodoList = name;
    }

    public TodoItem getItemFromTodoList(TodoItem item)
    {
        TodoItem resultingItem = null;
        for (int i = 0; i < itemsInTodoList.size(); i++)
        {
            if(itemsInTodoList.get(i) == item)
            {
                resultingItem = item;
            }
        }
        return(resultingItem);
    }

    //public TodoItem getAllItemsFromTodoList()
    {

    }

    public void addItemToTodoList(TodoItem item)
    {
        itemsInTodoList.add(item);
        //put CloudSender.add(item) or CloudGetter.add(item) here
    }

    public void deleteItemFromTodoList(TodoItem item)
    {
        itemsInTodoList.remove(item);
    }
}
