package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SelectItems extends AppCompatActivity implements View.OnClickListener {
    private ViewGroup mlayout;
    public static final String EXTRA_ITEM = "com.example.shoppinglist.extra.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items);

        mlayout = findViewById(R.id.mainlayout);
//        Attach onclick listener to each child of the view group
        for (int i = 0; i < mlayout.getChildCount(); i++) {
            Button b = (Button) mlayout.getChildAt(i);
            b.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button btnItem;
        btnItem = findViewById(v.getId());

        if (btnItem != null) {
            String item = btnItem.getText().toString();
            Intent itemIntent = new Intent();
            itemIntent.putExtra(EXTRA_ITEM, item);
            setResult(RESULT_OK, itemIntent);
            finish();
        }
    }
}