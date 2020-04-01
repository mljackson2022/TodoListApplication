package todo;

import java.util.Calendar;
import java.util.Date;

public class TodoItem {
     private String title;
     private String owner;
     private String content;
     private static int id = 0;
     private boolean status;
     private long creationTime;
     private Calendar completionTime;
     private Calendar deadlineTime;

     public TodoItem(String title, String owner, String content, int year, int month, int date, int hour, int minute){
          this.title = title;
          this.owner = owner;
          this.content = content;
          this.id = id++;
          this.status = false;

          Date date1 = new Date();
          this.creationTime = date1.getTime();

          Calendar InitiaTime = Calendar.getInstance();
          InitiaTime.set(0,0,0,0,0);
          this.completionTime=InitiaTime;

          Calendar deadlineTime = Calendar.getInstance();
          deadlineTime.set(year, month-1, date, hour, minute);
          this.deadlineTime = deadlineTime;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public String getOwner() {
          return owner;
     }

     public void setOwner(String owner) {
          this.owner = owner;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {
          this.content = content;
     }

     public static int getId() {
          return id;
     }

     public boolean checkIfCompleted() {
          return status;
     }

     public void changeToCompleted() {
          this.status = true;
     }

     public void changeToIncomplete() {
          this.status = false;
     }

     public long getCreationTime() {
          return creationTime;
     }

     public void setCreationTime(long creationTime) {
          this.creationTime = creationTime;
     }

     public Calendar getCompletionTime() {
          return completionTime;
     }

     public void setCompletionTime(int year, int month, int date, int hour, int minute) {
          this.completionTime.set(year, month-1, date, hour, minute);
     }

     public Calendar getDeadlineTime() {
          return deadlineTime;
     }

     public void setDeadlineTime(int year, int month, int date, int hour, int minute) {
          this.deadlineTime.set(year, month-1, date, hour, minute);
     }

}
