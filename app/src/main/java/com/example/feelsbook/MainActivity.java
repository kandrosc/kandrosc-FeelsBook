package com.example.feelsbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> labels = new ArrayList<String>();
    ArrayList<Emotion> emotions;
    public final static String EXTRA_MESSAGE = "com.example.feelsbook.MESSAGE";
    // Initialize emotion counts
    private int joycount=0;
    private int lovecount=0;
    private int surprisecount=0;
    private int angercount=0;
    private int sadnesscount=0;
    private int fearcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,labels);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Set emotion counts to text views
        final EditText et = (EditText) findViewById(R.id.comment);
        final TextView jc=(TextView)  findViewById(R.id.joycount);
        final TextView lc=(TextView)  findViewById(R.id.lovecount);
        final TextView spc=(TextView)  findViewById(R.id.surprisecount);
        final TextView ac=(TextView)  findViewById(R.id.angercount);
        final TextView sac=(TextView)  findViewById(R.id.sadnesscount);
        final TextView fc=(TextView)  findViewById(R.id.fearcount);




        load(adapter);


        // Updates emotion counts
        jc.setText(String.valueOf(joycount));
        lc.setText(String.valueOf(lovecount));
        spc.setText(String.valueOf(surprisecount));
        ac.setText(String.valueOf(angercount));
        sac.setText(String.valueOf(sadnesscount));
        fc.setText(String.valueOf(fearcount));


        // Joy Button
        Button joy = findViewById(R.id.joy);
        joy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joycount++;
                jc.setText(String.valueOf(joycount));

                Date date = new Date();
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strdate=formatdate.format(date);

                String comment;
                comment=et.getText().toString();

                Emotion e = new Emotion("Joy",strdate,comment);
                emotions.add(e);
                labels.add(e.getType());
                adapter.notifyDataSetChanged();
                save();

            }
        });


        // Love Button
        Button love = findViewById(R.id.love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lovecount++;
                lc.setText(String.valueOf(lovecount));

                Date date = new Date();
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strdate=formatdate.format(date);

                String comment;
                comment=et.getText().toString();

                Emotion e = new Emotion("Love",strdate,comment);
                emotions.add(e);
                labels.add(e.getType());
                adapter.notifyDataSetChanged();
                save();

            }
        });

        // Surprise Button
        Button surprise = findViewById(R.id.surprise);
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surprisecount++;
                spc.setText(String.valueOf(surprisecount));

                Date date = new Date();
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strdate=formatdate.format(date);

                String comment;
                comment=et.getText().toString();

                Emotion e = new Emotion("Surprise",strdate,comment);
                emotions.add(e);
                labels.add(e.getType());
                adapter.notifyDataSetChanged();
                save();

            }
        });

        // Anger Button
        Button anger = findViewById(R.id.anger);
        anger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angercount++;
                ac.setText(String.valueOf(angercount));

                Date date = new Date();
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strdate=formatdate.format(date);

                String comment;
                comment=et.getText().toString();

                Emotion e = new Emotion("Anger",strdate,comment);
                emotions.add(e);
                labels.add(e.getType());
                adapter.notifyDataSetChanged();
                save();

            }
        });

        // Sadness Button
        Button sadness = findViewById(R.id.sadness);
        sadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sadnesscount++;
                sac.setText(String.valueOf(sadnesscount));

                Date date = new Date();
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strdate=formatdate.format(date);

                String comment;
                comment=et.getText().toString();

                Emotion e = new Emotion("Sadness",strdate,comment);
                emotions.add(e);
                labels.add(e.getType());
                adapter.notifyDataSetChanged();
                save();

            }
        });

        // Fear Button
        Button fear = findViewById(R.id.fear);
        fear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fearcount++;
                fc.setText(String.valueOf(fearcount));

                Date date = new Date();
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strdate=formatdate.format(date);

                String comment;
                comment=et.getText().toString();

                Emotion e = new Emotion("Fear",strdate,comment);
                emotions.add(e);
                labels.add(e.getType());
                adapter.notifyDataSetChanged();
                save();

            }
        });








        // View a saved emotion
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                save();
                Intent intent = new Intent(MainActivity.this, DisplayEmotion.class);
                String message=String.valueOf(position);
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }


        });


    }
    // Saves emotion history
    private void save() {
        SharedPreferences sp = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        Gson gson = new Gson();
        String json= gson.toJson(emotions);
        editor.putString("elist",json);
        editor.apply();

    }

    // Loads emotion history and updates emotion counts
    private void load(ArrayAdapter adapter) {
        SharedPreferences sp = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("elist",null);
        Type type = new TypeToken<ArrayList<Emotion>>(){}.getType();
        emotions = gson.fromJson(json,type);
        if (emotions==null) {
            emotions=new ArrayList<>();
        }
        else {
            sort(emotions.size()-1);
            for(int i=0;i<emotions.size();i++){
                labels.add(emotions.get(i).getType());
                if (emotions.get(i).getType().equals("Joy")==true) {
                    joycount++;
                }
                else if (emotions.get(i).getType().equals("Love")==true) {
                    lovecount++;
                }
                else if (emotions.get(i).getType().equals("Surprise")==true) {
                    surprisecount++;
                }
                else if (emotions.get(i).getType().equals("Anger")==true) {
                    angercount++;
                }
                else if (emotions.get(i).getType().equals("Sadness")==true) {
                    sadnesscount++;
                }
                else if (emotions.get(i).getType().equals("Fear")==true) {
                    fearcount++;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    // Sorts emotion history by time
    private void sort(int n){
        if (n>0) {
            sort(n-1);
            for(int i=n;i>0;i--) {
                if (emotions.get(i).getTime().compareTo(emotions.get(i-1).getTime())<0) {
                    // swap the two
                    Emotion temp=emotions.get(i);
                    emotions.set(i,emotions.get(i-1));
                    emotions.set(i-1,temp);
                }
            }
        }
    }

}
