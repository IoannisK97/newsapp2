package com.example.jandroid.newsapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_view);

        List<String> categorySpinner = new ArrayList<>();
        categorySpinner.add("Business");
        categorySpinner.add("Politics");
        categorySpinner.add("Science");
        categorySpinner.add("Health");
        categorySpinner.add("Entertainment");
        categorySpinner.add("Sport");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorySpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);


        Button button = findViewById(R.id.button_ok);
        button.setOnClickListener(v -> {
            String item = (String) spinner.getSelectedItem();
            String url = "https://content.guardianapis.com/search?q=" + item.toLowerCase() + "&api-key=test";

            Intent newsIntent = new Intent(MainActivity.this, NewsActivity.class);
            Bundle b = new Bundle();
            b.putString("url", url);
            newsIntent.putExtras(b);
            startActivity(newsIntent);

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}