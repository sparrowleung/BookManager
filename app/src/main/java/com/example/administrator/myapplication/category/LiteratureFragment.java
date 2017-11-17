package com.example.administrator.myapplication.category;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.recycleview.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samsung on 2017/11/17.
 */

public class LiteratureFragment extends BaseFragment {

    private View _rootView;

    private RecyclerView _recyclerView;
    private List<Category> mList;
    private CategoryRecyclerView mCategoryRecyclerView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=layoutInflater.inflate(R.layout.fragment_literature,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        _recyclerView=(RecyclerView) getActivity().findViewById(R.id.litera_recylcerview);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList=new ArrayList<>();
        for(int i=0;i<3;i++){
            Category a1=new Category(R.drawable.book,"钢铁是怎样炼成的","Zone-Ball","LosAngel-Laker","Non");
            mList.add(a1);
            Category a2=new Category(R.drawable.book,"佛光普照","Klay-Tompson","GoldenState-Warroior","Non");
            mList.add(a2);
            Category a3=new Category(R.drawable.book,"忍者神龟","Resull-WestBokk","Amahool-Thunder","Yes");
            mList.add(a3);
        }
        mCategoryRecyclerView=new CategoryRecyclerView(mList);
        _recyclerView.setAdapter(mCategoryRecyclerView);

    }

    class CategoryRecyclerView extends RecyclerView.Adapter<CategoryRecyclerView.ViewHolder>{

        private List<Category> _list;

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView _image;
            TextView _name;
            TextView _author;
            TextView _press;
            TextView _status;

            public ViewHolder(View view){
                super(view);
                _image=(ImageView) view.findViewById(R.id.category_image);
                _name=(TextView) view.findViewById(R.id.category_name);
                _author=(TextView)view.findViewById(R.id.category_author);
                _press=(TextView) view.findViewById(R.id.category_press);
                _status=(TextView) view.findViewById(R.id.category_status);
            }
        }

        public CategoryRecyclerView(List<Category> Categorylist){
            _list=Categorylist;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type){
            View view=LayoutInflater.from(getContext()).inflate(R.layout.recycler_category,viewGroup,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position){
            Category _category=_list.get(position);
            viewHolder._name.setText(_category.getName());
            viewHolder._author.setText(_category.getAuthor());
            viewHolder._press.setText(_category.getPress());
            viewHolder._status.setText(_category.getStatus());
            Glide.with(getContext()).load(_category.getImageId()).into(viewHolder._image);
        }

        @Override
        public int getItemCount(){
            return _list.size();
        }
    }
}
