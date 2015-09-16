package com.tyq.readprogress.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.tyq.readprogress.R;
import com.tyq.readprogress.ViewActivity;

/**
 * Created by tyq on 2015/9/14.
 */
public class BookListAdapter extends SimpleCursorAdapter {

    private LayoutInflater mInflater;
    public  TextView tv_percent;
    private TextView tv_title;
    private TextView tv_page;
    private ProgressBar progressBar;
    public BookListAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        mInflater = LayoutInflater.from(context);
    }


    public void bindView(View view, Context context, Cursor cursor) {
       super.bindView(view, context, cursor);
        View convertView = null;
        if(view == null){
            convertView = mInflater.inflate(R.layout.book_list_cell,null);
        }else{
            convertView =view;
        }
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String percent = cursor.getString(cursor.getColumnIndex("percent"));
        String cpage = cursor.getString(cursor.getColumnIndex("cpage"));
        tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        tv_page = (TextView) convertView.findViewById(R.id.tv_page);
        tv_percent = (TextView) convertView.findViewById(R.id.tv_percent);

        progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
        tv_title.setText(title);
        tv_page.setText(cpage+" 页");
        tv_percent.setText(percent+"%");
        progressBar.setMax(100);
        progressBar.setProgress(Integer.parseInt(percent));
    }
}


