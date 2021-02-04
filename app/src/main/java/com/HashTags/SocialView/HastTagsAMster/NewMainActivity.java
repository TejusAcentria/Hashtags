package com.HashTags.SocialView.HastTagsAMster;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.HashTags.R;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NewMainActivity extends AppCompatActivity {
    public static ArrayList<String> messagesList=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView messagesListView;
    private EditText messageEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        messagesListView = (ListView)findViewById(R.id.messagesListView);
        messageEditText = (EditText)findViewById(R.id.messageEditText);
        addButton = (Button)findViewById(R.id.addButton);

        //Extend an ArrayAdapter so that we can make
        //our TextView HashTag compatible
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                messagesList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView hashView;
                if (convertView == null) {
                    hashView = new TextView(NewMainActivity.this);
                } else
                    hashView = (TextView) convertView;

                //Get the message from list and set as text
                String message = messagesList.get(position);
                hashView.setText(message);

                //Pattern to find if there's a hash tag in the message
                //i.e. any word starting with a # and containing letter or numbers or _
                Pattern tagMatcher = Pattern.compile("[#]+[A-Za-z0-9-_]+\\b");

                //Scheme for Linkify, when a word matched tagMatcher pattern,
                //that word is appended to this URL and used as content URI
                String newActivityURL = "content://com.sourabhsoni.hashtags.tagdetailsactivity/";

                //Attach Linkify to TextView
                Linkify.addLinks(hashView, tagMatcher, newActivityURL);

                return hashView;
            }
        };

        //assign custom adapter to messages listview
        messagesListView.setAdapter(adapter);

        //Add text from EditText to our messages list on button click
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String message = messageEditText.getText().toString();
                if(message.trim()!="")
                {
                    messagesList.add(0,message);
                    adapter.notifyDataSetChanged();
                    messageEditText.setText("");
                }
            }
        });
    }

}