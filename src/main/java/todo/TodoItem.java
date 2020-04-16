package todo;

import java.time.LocalDateTime;

public class TodoItem
{
     private String title;
     private String owner = "team4";
     private String description;
     private final int id;
     private static int nextID = 1;
     private boolean status;

     private LocalDateTime creationTime;
     private LocalDateTime completionTime;
     private LocalDateTime deadlineTime;


     public TodoItem(String title, String description, int year, int month, int date, int hour, int minute)
     {
          this.title = title;
          this.description = description;
          this.id = getNextID();
          this.status = false;
          this.creationTime = LocalDateTime.now();
          this.completionTime = null;
          this.deadlineTime = LocalDateTime.of(year, month, date, hour, minute);
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

     /*
     public void setOwner(String owner)
     {
          this.owner = owner;
     }
     */

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public int getId() {
          return id;
     }

     public static int getNextID()
     {
          int id=nextID;
          nextID++;
          return id;
     }

     public boolean checkIfCompleted() {
          return status;
     }

     public void changeToCompleted()
     {
          this.status = true;
          setCompletionTime();
     }

     public void changeToIncomplete() {
          this.status = false;
     }

     public LocalDateTime getCreationTime() {
          return creationTime;
     }

     public LocalDateTime getCompletionTime() {
          return completionTime;
     }

     public void setCompletionTime()
     {
          this.completionTime = LocalDateTime.now();
     }

     public LocalDateTime getDeadlineTime() {
          return deadlineTime;
     }

     public void snoozeDeadlineTime(long yearsAdded, long monthsAdded, long daysAdded, long hoursAdded, long minutesAdded)
     {
          this.deadlineTime = this.deadlineTime.plusYears(yearsAdded);
          this.deadlineTime = this.deadlineTime.plusMonths(monthsAdded);
          this.deadlineTime = this.deadlineTime.plusDays(daysAdded);
          this.deadlineTime = this.deadlineTime.plusHours(hoursAdded);
          this.deadlineTime = this.deadlineTime.plusMinutes(minutesAdded);
     }



}
