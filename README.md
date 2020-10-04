# Room Persistence Library

The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
The library helps you create a cache of your app's data on a device that's running your app. This cache, which serves as your app's single source of truth, allows users to view a consistent copy of key information within your app, regardless of whether users have an internet connection

### Dependency 

```xml

def room_version = "2.2.3"

implementation "androidx.room:room-runtime:$room_version"
annotationProcessor "androidx.room:room-compiler:$room_version"

```
### Database Configuration 

```java

@Database(entities = {Books.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}

```

### Data Access Object Configuration

```java
import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Books... books);

    @Query("SELECT * FROM books")
    List<Books> display();

    @Update
    void update(Books... books);

    @Delete
    void delete(Books ...books);
}

```

### Table Entity Configuration

```java
@Entity
public class Books {

    @PrimaryKey
    @ColumnInfo(name = "book_id")
    private Integer bookId;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "author_name")
    private String authorName;
    @ColumnInfo(name = "book_price")
    private Double bookPrice;
    @ColumnInfo(name = "book_description")
    private String bookDescription;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}

```
### Configuration

```java

BookDao bookDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "books.db").allowMainThreadQueries().build().bookDao();

```


### Insert Records

```java
        books = new Books();

        books.setBookName(book_name);
        books.setAuthorName(author_name);
        books.setBookPrice(Double.parseDouble(book_price));
        books.setBookDescription(book_details);
        bookDao.insert(books);

        setResult(RESULT_OK);
        SimpleToast.ok(AddBooks.this, "Record Insert Successfully");

```

### Display Records

```java

        booksList = bookDao.display();
        bookAdapter = new BookAdapter(MainActivity.this, booksList);
        listView.setAdapter(bookAdapter);

```
### Update Records

```java
                
                String book_name = nameTxt.getText().toString();
                String author_name = authorTxt.getText().toString();
                String book_price = priceTxt.getText().toString();
                String book_detail = detailTxt.getText().toString();

                books.setBookName(book_name);
                books.setAuthorName(author_name);
                books.setBookPrice(Double.parseDouble(book_price));
                books.setBookDescription(book_detail);

                setResult(RESULT_OK);
                bookDao.update(books);
                SimpleToast.ok(MainActivity.this, "Record Update Successfully");
                bookAdapter.notifyDataSetChanged();

```

### Delete Records

```java

        books = new Books();
        books = booksList.get(position);
        bookDao.delete(books);
        booksList.remove(position);

        setResult(RESULT_OK);

        SimpleToast.error(MainActivity.this, "Record Delete Successfully");

        bookAdapter.notifyDataSetChanged();
        listView.setAdapter(bookAdapter);

```

## Work Done
