package todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList
{
    private String nameOfTodoList = ""; //final?
    private static int nextNameDigit = 0;
    private List<TodoItem> itemsInTodoList;

    public TodoList()
    {
        nameOfTodoList = "List " + getNextNameDigit();
        itemsInTodoList = new ArrayList<>();
    }

    public String getNameOfTodoList() {
        return nameOfTodoList;
    }

    public List<TodoItem> getItemsInTodoList() {
        return itemsInTodoList;
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
        for (TodoItem todoItem : itemsInTodoList) {
            if (todoItem == item) {
                resultingItem = item;
            }
        }
        return(resultingItem);
    }

    //public TodoItem getAllItemsFromTodoList() {

    //}

    public void addItemToTodoList(TodoItem item)
    {
        this.itemsInTodoList.add(item);
        //put CloudSender.add(item) or CloudGetter.add(item) here
    }

    public void deleteItemFromTodoList(int id)
    {
        TodoItem deletingItem = null;
        for (TodoItem todoItem : itemsInTodoList) {
            if (todoItem.getId() == id) {
                deletingItem = todoItem;
            }
        }
        itemsInTodoList.remove(deletingItem);
    }

    public void completedItemFromTodoList(int id){
        for (TodoItem todoItem : itemsInTodoList) {
            if (todoItem.getId() == id) {
                todoItem.changeToCompleted();
            }
        }
    }

    public void printItemIdAndTitleFromTodoList(){
            for (TodoItem Item: itemsInTodoList){
                System.out.println(Item.getTitle());
                System.out.println(Integer.toString(Item.getId()));
            }
    }

    public static void main(String[] args) {
        TodoItem Item = new TodoItem("Assignment1", "TeamFour",
                "Remember to complete Assignment1 by next week", 2020, 4,8,11,59);
        TodoItem Item2 = new TodoItem("Assignment2", "TeamFour",
                "Remember to complete Assignment1 by next week", 2020, 4,8,11,59);
        TodoList a =new TodoList();
        a.addItemToTodoList(Item);
        a.addItemToTodoList(Item2);
        a.printItemIdAndTitleFromTodoList();
    }





}
