package com.tyq.readprogress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyq.readprogress.ImageLoader;
import com.tyq.readprogress.R;
import com.tyq.readprogress.bean.Book;

import java.util.List;

/**
 * Created by tyq on 2015/9/7.
 */
public class SearchAdapter extends BaseAdapter {
    List<Book> mList;
    LayoutInflater inflater;
    public SearchAdapter(List list,Context context){
        mList = list;
        inflater =  LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        String url = mList.get(i).getBitmap();
        if(view==null){
            view = inflater.inflate(R.layout.search_item,null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_author = (TextView) view.findViewById(R.id.tv_author);
            holder.iv = (ImageView) view.findViewById(R.id.iv_icon);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.iv.setImageResource(R.drawable.defaultbook);
        holder.iv.setTag(url);
        new ImageLoader().showImageByAsynctask(holder.iv,url);
        holder.tv_title.setText(mList.get(i).getTitle());
        holder.tv_author.setText("作者:"+mList.get(i).getAuthor());
        return view;
    }
    public void setData(List<Book> list){
        mList = list;
    }
    class ViewHolder{
        public ImageView iv;
        public TextView tv_title,tv_author;
    }
}
