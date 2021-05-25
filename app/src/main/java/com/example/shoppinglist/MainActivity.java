package com.example.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int ITEM_SELECT = 1;
    private ViewGroup viewGroup;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewGroup = findViewById(R.id.linearLayout);
    }

    //    Start item select activity
    public void addItem(View view) {
        if (count == viewGroup.getChildCount()) {
            Toast toast = Toast.makeText(this, "List full", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Intent intent = new Intent(this, SelectItems.class);
            startActivityForResult(intent, ITEM_SELECT);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView textView;
        if (requestCode == ITEM_SELECT && resultCode == RESULT_OK) {
            String item = data.getStringExtra(SelectItems.EXTRA_ITEM);
            textView = (TextView) viewGroup.getChildAt(count);

            textView.setText(item);
            textView.setVisibility(View.VISIBLE);
            count++;

        }
    }

    public void clearList(View view) {
        TextView textView;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            textView = (TextView) viewGroup.getChildAt(i);
            textView.setText("");
            textView.setVisibility(View.INVISIBLE);
        }
        count = 0;
    }
}