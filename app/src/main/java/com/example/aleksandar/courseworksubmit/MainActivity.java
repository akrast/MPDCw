package com.example.aleksandar.courseworksubmit;

/**
 * Created by Aleksandar Krastev S1433655 on 28/03/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    public Button startButton;
    public Button startButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        startButton2 = (Button) findViewById(R.id.startButton2);
        startButton2.setOnClickListener(this);
    }

    public void onClick(View aView)
    {
        switch(aView.getId()) {
            case R.id.startButton:
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                RssFeed rssFeed = new RssFeed(this, recyclerView);
                rssFeed.execute();
                break;

            case R.id.startButton2:
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                RssFeed2 rssFeed2 = new RssFeed2(this, recyclerView);
                rssFeed2.execute();
                break;
        }
    }
}