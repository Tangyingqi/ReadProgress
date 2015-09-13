package com.tyq.readprogress;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by tyq on 2015/9/13.
 */
public class ViewActivity extends Activity {

    private ListView view_list;
    private DB db;
    private SQLiteDatabase dbRead,dbWrite;
    private SimpleCursorAdapter adapter;
    private CustomDialog dialog;
    private TextView tv_percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        tv_percent = (TextView) findViewById(R.id.tv_percent);
        db = new DB(ViewActivity.this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        adapter = new SimpleCursorAdapter(this,R.layout.book_list_cell,null,new String[]{"title"},new int[]{R.id.tv_title});
        view_list = (ListView) findViewById(R.id.view_list);
        view_list.setAdapter(adapter);
        refreshView();


        view_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(ViewActivity.this).setTitle("提醒").setMessage("是否要删除?").setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            Cursor cursor = adapter.getCursor();


                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbWrite.delete("book", "_id=?", new String[]{cursor.getInt(cursor.getColumnIndex("_id")) + " "});
                                refreshView();
                            }
                        }).show();

                return true;
            }
        });

        view_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog = new CustomDialog(ViewActivity.this);
                final EditText editText = (EditText) dialog.getEditText();
                Cursor c = adapter.getCursor();
                int page = c.getInt(c.getColumnIndex("page"));
                Log.i("tyq",page+" ");
//                dialog.setOnPositiveListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        tv_percent.setText(editText.getText());
//                        dialog.dismiss();
//                    }
//                });
                dialog.getNegBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        dialog.dismiss();
                    }
                });
//                dialog.setOnNegativeListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
        dialog.show();
    }
});


    }

    public void refreshView(){
        Cursor cursor = dbRead.query("book",null,null,null,null,null,null);
        adapter.changeCursor(cursor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db!=null){
            db.close();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getActionBar().setHomeButtonEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}
