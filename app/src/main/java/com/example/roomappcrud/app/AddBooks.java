package com.example.roomappcrud.app;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.roomappcrud.R;
import com.example.roomappcrud.database.AppDatabase;
import com.example.roomappcrud.database.BookDao;
import com.example.roomappcrud.entity.Books;
import com.github.pierry.simpletoast.SimpleToast;
import com.google.android.material.textfield.TextInputEditText;

public class AddBooks extends AppCompatActivity {

    private TextInputEditText name, author, price, detail;
    private Books books;
    private BookDao bookDao;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        bookDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "books.db").allowMainThreadQueries().build().bookDao();

        //layout = findViewById(R.id.parent);
        name = findViewById(R.id.book_name_edit_text);
        author = findViewById(R.id.author_name_edit_text);
        price = findViewById(R.id.book_price_edit_text);
        detail = findViewById(R.id.book_description_edit_text);

    }

    private void insertBooks() {

        String book_name = name.getText().toString();
        String author_name = author.getText().toString();
        String book_price = price.getText().toString();
        String book_details = detail.getText().toString();

        if (book_name.isEmpty()) {
            SimpleToast.warning(AddBooks.this, "Ingrese El Titulo ...");
            return;
        }

        if (author_name.isEmpty()) {
            SimpleToast.warning(AddBooks.this, "Ingrese El Autor ...");
            return;
        }

        if (book_price.isEmpty()) {
            SimpleToast.warning(AddBooks.this, "Ingrese El Precio ...");
            return;
        }

        if (book_details.isEmpty()) {
            SimpleToast.warning(AddBooks.this, "Ingrese La Descripcion ...");
            return;
        }

        books = new Books();

        books.setBookName(book_name);
        books.setAuthorName(author_name);
        books.setBookPrice(Double.parseDouble(book_price));
        books.setBookDescription(book_details);
        bookDao.insert(books);

        setResult(RESULT_OK);
        SimpleToast.ok(AddBooks.this, "Record Insert Successfully");
        name.setText("");
        author.setText("");
        price.setText("");
        detail.setText("");
    }

    public void addBooks(View view) {
        insertBooks();
    }
}
