package com.example.settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView recordsCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_start_button).setOnClickListener(this);

        recordsCountTextView = findViewById(R.id.main_records_count_text_view);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, KeyValueActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int count = data.getIntExtra("count", 0);
        recordsCountTextView.setText(count + " records");
    }
}
