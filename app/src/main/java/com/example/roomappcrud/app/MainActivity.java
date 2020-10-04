package com.example.roomappcrud.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.roomappcrud.R;
import com.example.roomappcrud.database.AppDatabase;
import com.example.roomappcrud.database.BookDao;
import com.example.roomappcrud.entity.Books;
import com.example.roomappcrud.ui.BookAdapter;
import com.github.pierry.simpletoast.SimpleToast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
        , AdapterView.OnItemLongClickListener {

    private Books books;
    private BookDao bookDao;
    private BookAdapter bookAdapter;
    private ListView listView;
    private List<Books> booksList;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookDao = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "books.db").allowMainThreadQueries().build().bookDao();

        dialog = new Dialog(this, R.style.AppDialog);

        booksList = new ArrayList<>();
        books = new Books();

        listView = findViewById(R.id.book_list_view);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        displayBooks();

    }

    private void displayBooks() {

        booksList = bookDao.display();
        bookAdapter = new BookAdapter(MainActivity.this, booksList);
        listView.setAdapter(bookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.setting:
                SimpleToast.info(MainActivity.this, "Setting Menu Item");
                break;

            case R.id.insert:
                startActivity(new Intent(MainActivity.this, AddBooks.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        books = booksList.get(position);

        dialog.setTitle("Edit Book");
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.edit_book_layout);

        final TextInputEditText nameTxt = dialog.findViewById(R.id.edit_book_name_edit_text);
        final TextInputEditText authorTxt = dialog.findViewById(R.id.edit_author_name_edit_text);
        final TextInputEditText priceTxt = dialog.findViewById(R.id.edit_book_price_edit_text);
        final TextInputEditText detailTxt = dialog.findViewById(R.id.edit_book_description_edit_text);
        MaterialButton editBtn = dialog.findViewById(R.id.editButton);

        nameTxt.setText(books.getBookName());
        authorTxt.setText(books.getAuthorName());
        priceTxt.setText("" + books.getBookPrice());
        detailTxt.setText(books.getBookDescription());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                SimpleToast.ok(MainActivity.this, "Se Actualizo Correctamente");
                bookAdapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        dialog.show();


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        books = new Books();
        books = booksList.get(position);
        bookDao.delete(books);
        booksList.remove(position);

        setResult(RESULT_OK);

        SimpleToast.error(MainActivity.this, "Se Borro Correctamente");

        bookAdapter.notifyDataSetChanged();
        listView.setAdapter(bookAdapter);

        return true;
    }
}
