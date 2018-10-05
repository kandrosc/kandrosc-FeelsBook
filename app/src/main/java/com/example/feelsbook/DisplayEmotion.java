package com.example.feelsbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DisplayEmotion extends AppCompatActivity {
    ArrayList<Emotion> emotions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_emotion);
        Intent intent=getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final int position=Integer.valueOf(message);
        load();

        final EditText type=(EditText) findViewById(R.id.type);
        final EditText time=(EditText) findViewById(R.id.time);
        final EditText comment=(EditText) findViewById(R.id.comment);

        Emotion emotion=emotions.get(position);

        type.setText(emotion.getType(),TextView.BufferType.EDITABLE);
        time.setText(emotion.getTime(),TextView.BufferType.EDITABLE);
        comment.setText(emotion.getComment(),TextView.BufferType.EDITABLE);




        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emotions.get(position).setType(type.getText().toString());
                emotions.get(position).setTime(time.getText().toString());
                emotions.get(position).setComment(comment.getText().toString());

                save();
                Intent intent = new Intent(DisplayEmotion.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button delete=findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotions.remove(position);
                save();
                Intent intent = new Intent(DisplayEmotion.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void load() {
        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("elist", null);
        Type type = new TypeToken<ArrayList<Emotion>>() {
        }.getType();
        emotions = gson.fromJson(json, type);
        if (emotions == null) {
            emotions = new ArrayList<>();
        }
    }
    private void save() {



        SharedPreferences sp = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        Gson gson = new Gson();
        String json= gson.toJson(emotions);
        editor.putString("elist",json);
        editor.apply();

    }
}