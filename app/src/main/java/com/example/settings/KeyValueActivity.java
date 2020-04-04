package com.example.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.Random;

public class KeyValueActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText keyEditText;
    private EditText valueEditText;
    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_value);
        findViewById(R.id.add_button).setOnClickListener(this);

        keyEditText = findViewById(R.id.key);
        valueEditText = findViewById(R.id.value);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final Map<String, ?> all = preferences.getAll();
        for (String key : all.keySet()) {
            String value = (String) all.get(key);
            addItem(key, value);
        }

    }

    @Override
    public void onClick(View v) {
        String key = keyEditText.getText().toString();
        String value = valueEditText.getText().toString();

        addItem(key, value);


        keyEditText.setText("");
        valueEditText.setText("");


        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.commit();

    }

    public void addItem(String key, String value) {

        container = findViewById(R.id.key_value_container);
        View itemView = getLayoutInflater().inflate(R.layout.item, null);



        itemView.setBackgroundColor(new Random().nextInt());

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView viewById = v.findViewById(R.id.item_key);
                container.removeView(v);
                getPreferences(MODE_PRIVATE).edit().remove(viewById.getText().toString()).commit();
                return true;
            }
        });

        TextView keyTextView = itemView.findViewById(R.id.item_key);
        TextView valueTextView = itemView.findViewById(R.id.item_value);

        keyTextView.setText(key);
        valueTextView.setText(value);

        container.addView(itemView);
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("count", container.getChildCount());
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
