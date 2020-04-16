package todoTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todo.TodoItem;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest
{
    TodoItem item;

    @BeforeEach
    void setup()
    {
        item = new TodoItem("Assignment1", "Remember to complete Assignment1 by next week",
                2020, 4, 8, 11, 59);
    }

    @Test
    void getTitle()
    {
        assertEquals("Assignment1", item.getTitle());
    }

    @Test
    void setTitle()
    {
        item.setTitle("Assignment2");
        assertEquals("Assignment2", item.getTitle());
    }

    @Test
    void getOwner()
    {
        assertEquals("team4", item.getOwner());
    }

    /*      maybe put back in if we need to change owners
    @Test
    void setOwner()
    {
        item.setOwner("TeamZero");
        assertEquals("TeamZero", item.getOwner());
    }
    */

    @Test
    void getDescription()
    {
        assertEquals("Remember to complete Assignment1 by next week", item.getDescription());
    }

    @Test
    void setDescription()
    {
        item.setDescription("Remember to complete Assignment2 by next week");
        assertEquals("Remember to complete Assignment2 by next week", item.getDescription());
    }

    @Test
    void getConsecutiveIds()
    {
        TodoItem item2 = new TodoItem("Assignment2", "Remember to complete Assignment2 by next week",
                2021, 5, 5, 1, 10);
        TodoItem item3 = new TodoItem("Assignment3", "Remember to complete Assignment3 by next week",
                2023, 1, 16, 18, 30);

        assertEquals(item2.getId(), item.getId() + 1);
        assertEquals(item3.getId(), item2.getId() + 1);
    }

    @Test
    void checkIfCompleted()
    {
        assertEquals(false, item.checkIfCompleted());
    }

    @Test
    void completeItem()
    {
        item.completeItem();
        assertEquals(true, item.checkIfCompleted());
    }

    @Test
    void changeToIncomplete()
    {
        item.changeToIncomplete();
        assertFalse(item.checkIfCompleted());
    }

    @Test
    void getDeadlineTime()
    {
        LocalDateTime expected = LocalDateTime.of(2020, 4, 8, 11, 59);
        assertEquals(expected, item.getDeadlineTime());
    }

    @Test
    void snoozeDeadlineTime()
    {
        item.snoozeDeadlineTime(1, 1, 1, 1, 1);
        LocalDateTime expected = LocalDateTime.of(2021, 5, 9, 13, 00);
        assertEquals(expected, item.getDeadlineTime());
    }

    @Test
    void snoozeDeadlineTimeEmpty()
    {
        item.snoozeDeadlineTime(0, 0, 0, 0, 0);
        LocalDateTime expected = LocalDateTime.of(2020, 4, 8, 11, 59);
        assertEquals(expected, item.getDeadlineTime());
    }


}