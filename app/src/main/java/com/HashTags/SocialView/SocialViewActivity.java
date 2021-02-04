package com.HashTags.SocialView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.HashTags.R;

import java.util.ArrayList;

public class SocialViewActivity extends AppCompatActivity {

    ArrayList<String> iplArralist;
    ArrayList<String> softwareArralist;
    private ArrayAdapter<Hashtag> defaultHashtagAdapter;
    SocialAutoCompleteTextView textView;

    Button button;

    ArrayList<String> getHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_view);
        getHash = new ArrayList<>();
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        iplArralist = new ArrayList<>();

        iplArralist.add("ipl");
        iplArralist.add("iplindia");
        iplArralist.add("ipldubai");
        iplArralist.add("ipl21");


        softwareArralist = new ArrayList<>();

        softwareArralist.add("software");
        softwareArralist.add("softwares");
        softwareArralist.add("SoftBall");
        softwareArralist.add("softGir");


        defaultHashtagAdapter = new HashtagArrayAdapter<>(this);

        for (int i = 0; i < iplArralist.size(); i++) {
            defaultHashtagAdapter.addAll(new Hashtag(iplArralist.get(i)), new Hashtag(softwareArralist.get(i)));
        }

        textView.setHashtagAdapter(defaultHashtagAdapter);


        textView.setHashtagTextChangedListener((view, text) ->


                getHash.add(text.toString())
        );

        button.setOnClickListener(v->{




            Toast.makeText(this, getHash.toString(), Toast.LENGTH_SHORT).show();

        });


    }


}