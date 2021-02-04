package com.HashTags.SocialView.interfaces;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.HashTags.R;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;

    ArrayAdapter<String> adapter;
    EditText inputSearch;


    ArrayList<String> iplArralist;
    ArrayList<String> softwareArralist;

    ArrayList<String> allTagList;

    Button button2;
    String getTags = "", stripEndValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        allTagList = new ArrayList<>();
        iplArralist = new ArrayList<>();

        iplArralist.add("#ipl");
        iplArralist.add("#iplindia");
        iplArralist.add("#ipldubai");
        iplArralist.add("#ipl21");


        softwareArralist = new ArrayList<>();

        softwareArralist.add("#software");
        softwareArralist.add("#softwares");
        softwareArralist.add("#SoftBall");
        softwareArralist.add("#softGir");


        iplArralist.addAll(softwareArralist);

        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(v -> {

            if (isValid()) {

                for (int i = 0; i < allTagList.size(); i++) {
                    getTags = allTagList.get(i);
                    stripEndValue = StringUtils.stripStart(getTags, ",");

                }
                Toast.makeText(this, StringUtils.stripEnd(stripEndValue, ","), Toast.LENGTH_SHORT).show();
            }

        });

        lv = (ListView) findViewById(R.id.recyclerView);
        inputSearch = (EditText) findViewById(R.id.edittext);


        adapter = new ArrayAdapter<String>(this, R.layout.textview_adapter_user, R.id.textView, iplArralist);
        lv.setAdapter(adapter);


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {


                String text = s.toString();
                if (StringUtils.isNotBlank(text)){
                    if (text.lastIndexOf("#") > text.lastIndexOf(" ")) {
                        String tag = text.substring(text.lastIndexOf("#"), text.length());
                        int position = tag.indexOf("#so");
                        int sc = tag.indexOf("#i");

                        if (position == 0) {
                            lv.setVisibility(View.VISIBLE);
                            MainActivity.this.adapter.getFilter().filter(tag);

                        } else if (sc == 0) {
                            lv.setVisibility(View.VISIBLE);
                            MainActivity.this.adapter.getFilter().filter(tag);
                        }

                    } else {

                        lv.setVisibility(View.GONE);
                    }
                }else {
                    allTagList.clear();
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                Log.d("chjeckin->", "" + arg0.toString());

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                Log.d("chjeckin->", "" + arg0.toString());
            }
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Object o = lv.getItemAtPosition(position);
            String str = (String) o;
            Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();


            data = data + str + ",";
            allTagList.add(data);

            inputSearch.setText(inputSearch.getText().toString() + " " + str);

        });


    }

    String data = "";

    private boolean isValid() {
        boolean result = true;


        if (inputSearch.getText().toString().trim().equals("")) {
            result = false;
            Toast.makeText(this, "No tags available", Toast.LENGTH_SHORT).show();
        }

        return result;
    }


}