package DataBase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import todo.TodoItem;
import exceptions.QuoteException;
import exceptions.QuoteIdExistsException;
import exceptions.QuoteIdNotExistsException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TodoItemManager {

    Dao<TodoItem, Integer> TodoItemDao;

    public TodoItemManager() {
        this("TodoItem.db");
    }

    public TodoItemManager(String dbName) {
        try {
            var connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + dbName);
            TableUtils.createTableIfNotExists(connectionSource, TodoItem.class);
            this.TodoItemDao = DaoManager.createDao(connectionSource, TodoItem.class);
        } catch (SQLException e) {
            throw new QuoteException("Something related to connection happened!", e);
        }
    }

    public TodoItem addQuote(TodoItem quote) {
        try {
            int id =quote.getId();
            if (TodoItemDao.idExists(id)){
                throw new QuoteIdExistsException(id);
            }else {
                TodoItem newItem = TodoItemDao.createIfNotExists(quote);
                return newItem;
            }
        } catch (SQLException e) {
            throw new QuoteException("Something happened when adding quote",e);
        }
    }

    public List<TodoItem> getAllItems() {
        try {
            return TodoItemDao.queryForAll();
        } catch (SQLException e) {
            throw new QuoteException("Something happened when getting all quotes", e);
        }
    }

    public TodoItem deleteItem(int id) {
        try {
            if(TodoItemDao.idExists(id)){
                TodoItem Item = TodoItemDao.queryForId(id);
                TodoItemDao.deleteById(id);
                return Item;
            }else{
                throw new QuoteIdNotExistsException(id);
            }
        } catch (SQLException e) {
            throw new QuoteException("Something happened when deleting quote!", e);
        }
    }

    public void disposeResources() {
        try {
            TodoItemDao.getConnectionSource().close();
        } catch (IOException e) {
            throw new QuoteException("Couldn't close the source!", e);
        }
    }
}