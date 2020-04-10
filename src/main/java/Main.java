import todo.TodoItem;
import todo.TodoList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Welcome to the to-do list software！");
                System.out.println("If you want to add to-do items, please type 'add' ");
                System.out.println("If you want to delete to-do items, please type 'delete' ");
                System.out.println("If you want to mark completed to-do items, please type 'completed' ");
                System.out.println("If you want to snooze to-do items, please type 'snoozed' ");
                System.out.println("If you want to view the current to-do list,, please type 'view' ");
                System.out.println("If you want to exit the software, please type 'quit' ");
                TodoList list = new TodoList();
                String input = br.readLine();
                if (input.equals("quit")){
                    break;
                }else if(input.equals("add")){
                    System.out.println("Please enter a title");
                    String title = br.readLine();
                    System.out.println("Please enter a owner");
                    String owner = br.readLine();
                    System.out.println("Please enter a content");
                    String content = br.readLine();
                    System.out.println("Please enter a year");
                    int year = Integer.parseInt(br.readLine());
                    System.out.println("Please enter a month");
                    int month = Integer.parseInt(br.readLine());
                    System.out.println("Please enter a date");
                    int date = Integer.parseInt(br.readLine());
                    System.out.println("Please enter a hour");
                    int hour = Integer.parseInt(br.readLine());
                    System.out.println("Please enter a minute");
                    int minute = Integer.parseInt(br.readLine());
                    TodoItem addItem = new TodoItem(title, owner, content, year, month, date, hour, minute);
                    list.addItemToTodoList(addItem);
                }else if (input.equals("delete")){
                    System.out.println("Please enter the id of the to-do item you want to mark completed");
                    int deleteId = Integer.parseInt(br.readLine());
                    list.deleteItemFromTodoList(deleteId);
                }else if (input.equals("completed")){
                    System.out.println("Please enter the id of the to-do item you want to mark completed");
                    int completedId = Integer.parseInt(br.readLine());
                    list.completedItemFromTodoList(completedId);
                }else if (input.equals("snoozed")){
                    System.out.println("Please enter the id of the to-do item you want to mark completed");
                    int snoozedId = Integer.parseInt(br.readLine());
                }else if (input.equals("view")){
                    System.out.println(list.getItemsInTodoList().size());
                }
            }
        } catch (IOException e) {

        }
    }
}
