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

        ImageView filmImage = rowView.findViewById(R.id.icon);
        TextView title = rowView.findViewById(R.id.title);
        TextView date = rowView.findViewById(R.id.date);
        TextView director = rowView.findViewById(R.id.director);
        TextView producer = rowView.findViewById(R.id.producer);

        if (i==0)
        {
            filmImage.setImageResource(R.drawable.starwars1);
        }
        if (i==1)
        {
            filmImage.setImageResource(R.drawable.starwars2);
        }
        if (i==2)
        {
            filmImage.setImageResource(R.drawable.starwars3);
        }
        if (i==3)
        {
            filmImage.setImageResource(R.drawable.starwars4);
        }
        if (i==4)
        {
            filmImage.setImageResource(R.drawable.starwars5);
        }
        if (i==5)
        {
            filmImage.setImageResource(R.drawable.starwars6);
        }
        if (i==6)
        {
            filmImage.setImageResource(R.drawable.starwars7);
        }

        title.setText(item.getTitle());
        date.setText(item.getRelease_date());
        director.setText(item.getDirector());
        producer.setText(item.getProducer());

        return rowView;
    }
}
