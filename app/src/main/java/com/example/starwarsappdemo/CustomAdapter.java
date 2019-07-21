package com.example.starwarsappdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter
{

    private List<StarWarsResponse.ResultsBean> mFilmList;
    private Context mContext;
    private LayoutInflater inflater;

    public CustomAdapter(Context mContext, List<StarWarsResponse.ResultsBean> mFilmList) {
        this.mContext = mContext;
        this.mFilmList = mFilmList;
    }

    @Override
    public int getCount() {
        return mFilmList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFilmList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.each_list_item, parent, false);

        StarWarsResponse.ResultsBean item = (StarWarsResponse.ResultsBean)getItem(i);

        ImageView thumbnail = rowView.findViewById(R.id.icon);
        TextView title = rowView.findViewById(R.id.title);
        TextView details = rowView.findViewById(R.id.details);

        //String imageUrl
        title.setText(item.getTitle());
        details.setText(item.getDirector());


        return rowView;
    }
}
