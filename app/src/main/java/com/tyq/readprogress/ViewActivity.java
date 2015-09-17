package com.tyq.readprogress;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
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
import android.widget.Toast;

import com.tyq.readprogress.Adapter.BookListAdapter;
import com.tyq.readprogress.bean.Book;

/**
 * Created by tyq on 2015/9/13.
 */
public class ViewActivity extends Activity {

    private ListView view_list;
    private DB db;
    private SQLiteDatabase dbRead,dbWrite;
    private BookListAdapter adapter;
    private CustomDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        db = new DB(ViewActivity.this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        Cursor cursor = dbRead.query("book",null,null,null,null,null,null);
        adapter = new BookListAdapter(this,R.layout.book_list_cell,cursor,new String[]{"title","page","percent"},new int[]{R.id.tv_title,R.id.tv_page,R.id.tv_percent});
        view_list = (ListView) findViewById(R.id.view_list);
        view_list.setAdapter(adapter);
        refreshView();

        //长按删除
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

        //点击弹出对话框
        view_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                dialog = new CustomDialog(ViewActivity.this,R.style.dialog_style);
                 final EditText editText = (EditText) dialog.getEditText();
                 Cursor c = adapter.getCursor();

                 final int page = c.getInt(c.getColumnIndex("page"));
                   final int ID = c.getInt(c.getColumnIndex("_id"));


//                dialog.getNegBtn().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });


                dialog.getPosBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!editText.getText().toString().equals("") ) {
                               int currentPage = Integer.parseInt(editText.getText().toString());
                             int percent = (int)(((double)currentPage/page)*100);
                            ContentValues cv = new ContentValues();
                            cv.clear();
                            cv.put("percent", percent);
                            cv.put("cpage",currentPage);
                            dbWrite.update("book", cv, "_id=?", new String[]{ID+""});

                            refreshView();
                            dialog.dismiss();

                            }else{
                            dialog.dismiss();
                        }
                        }
                    }
                );
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
        Cursor c = adapter.getCursor();
        if(c!=null&&!c.isClosed()){
            c.close();
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
