package com.tyq.readprogress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;
import java.util.Random;


public class MainActivity extends Activity {

    private RelativeLayout rl_view,rl_search;
    private ImageView iv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_view = (RelativeLayout) findViewById(R.id.rl_view);
        rl_search = (RelativeLayout) findViewById(R.id.rl_search);
        iv_main = (ImageView) findViewById(R.id.iv_main_back);

        Random random = new Random();
        int i = Math.abs(random.nextInt())%5+1;
        try {
            Field field = R.drawable.class.getField("main_back"+i);
            int j = field.getInt(new R.drawable());
            iv_main.setImageResource(j);
        } catch (Exception e) {
            iv_main.setImageResource(R.drawable.main_back2);
        }

        rl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchAcitvity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
