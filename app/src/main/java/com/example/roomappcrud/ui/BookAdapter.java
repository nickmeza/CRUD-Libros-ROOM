package com.example.roomappcrud.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roomappcrud.R;
import com.example.roomappcrud.app.AddBooks;
import com.example.roomappcrud.entity.Books;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private List<Books> booksList;

    public BookAdapter(Context context, List<Books> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int position) {
        return booksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.book_list_layout, null);
        }


        TextView name = convertView.findViewById(R.id.book_name_text_view);
        TextView author = convertView.findViewById(R.id.author_name_text_view);
        TextView price = convertView.findViewById(R.id.book_price_text_view);

        final Books books = booksList.get(position);

        name.setText(books.getBookName());
        author.setText(books.getAuthorName());
        price.setText("$ " + books.getBookPrice());

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddBooks.class);
                intent.putExtra("book_name", books.getBookName());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
