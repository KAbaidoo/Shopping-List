package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int ITEM_SELECT = 1;
    private ViewGroup viewGroup;
    private TextView textView;
    private ArrayList<String> list;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewGroup = findViewById(R.id.linearLayout);
        list = new ArrayList<>();


        // Restore the saved state.
        // See onSaveInstanceState() for what gets saved.
        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("list_visible");
            count = savedInstanceState.getInt("count");
            list = savedInstanceState.getStringArrayList("list_items");
            count = savedInstanceState.getInt("count");
            if (isVisible) {
//                TextView textView;
                for (int i = 0; i < count; i++) {
                    textView = (TextView) viewGroup.getChildAt(i);
                    textView.setText(list.get(i));
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (count != 0) {
            outState.putInt("count", count);
            outState.putStringArrayList("list_items", list);
            outState.putBoolean("list_visible", true);
        }
    }


    //    Start item select activity
    public void addItem(View view) {
        if (count == viewGroup.getChildCount()) {
            Toast toast = Toast.makeText(this, "List is full!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Intent intent = new Intent(this, SelectItems.class);
            startActivityForResult(intent, ITEM_SELECT);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ITEM_SELECT && resultCode == RESULT_OK) {

//            TextView textView;
            String item = data.getStringExtra(SelectItems.EXTRA_ITEM);
            textView = (TextView) viewGroup.getChildAt(count);
            textView.setText(item);
            list.add(count, item);
            textView.setVisibility(View.VISIBLE);
            count++;
        }
    }

    public void clearList(View view) {
//        TextView textView;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            textView = (TextView) viewGroup.getChildAt(i);
            textView.setText("");
            textView.setVisibility(View.INVISIBLE);
        }
        count = 0;
    }
}