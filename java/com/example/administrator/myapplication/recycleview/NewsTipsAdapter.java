package com.example.administrator.myapplication.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/26.
 */

public class NewsTipsAdapter extends RecyclerView.Adapter<NewsTipsAdapter.ViewHolder>{

    private List<News> _list;

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView _Title;
        private TextView _SubTitle;

        public ViewHolder(View view){
            super(view);
            _Title=(TextView) view.findViewById(R.id.newsAdapter_title);
            _SubTitle=(TextView) view.findViewById(R.id.newsAdapter_subtitle);
        }
    }

    public NewsTipsAdapter(List<News> list){
        this._list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup container, int type){
        View _view= LayoutInflater.from(container.getContext()).inflate(R.layout.recycler_news,container,false);
        ViewHolder _viewHolder=new ViewHolder(_view);
        return _viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,int position){
        News _news=_list.get(position);
        viewHolder._Title.setText(_news.getTitle());
        viewHolder._SubTitle.setText(_news.getSubTitle());
    }

    @Override
    public int getItemCount(){
        return _list.size();
    }
}
