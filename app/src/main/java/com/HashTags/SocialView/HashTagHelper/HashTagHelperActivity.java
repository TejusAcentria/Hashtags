package com.HashTags.SocialView.HashTagHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.HashTags.R;
import com.fenlisproject.hashtagedittext.HashTagEditText;

public class HashTagHelperActivity extends AppCompatActivity {

    private HashTagEditText hashTagInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_tag_helper);

        hashTagInput = (HashTagEditText) findViewById(R.id.tag_input);
        hashTagInput.appendTag("Android");
        hashTagInput.appendTag("Java");
        hashTagInput.appendTag("Hashtag");
        hashTagInput.appendTag("EditText");
    }
}