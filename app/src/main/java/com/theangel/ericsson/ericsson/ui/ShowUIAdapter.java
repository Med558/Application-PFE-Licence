package com.theangel.ericsson.ericsson.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.model.Show;

import java.util.List;

public class ShowUIAdapter extends ArrayAdapter<Show> {
    private int layoutId;

    public ShowUIAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Show> objects) {
        super(context, resource, objects);
        layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layoutId, parent, false);

        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        RatingBar rating1 = (RatingBar) convertView.findViewById(R.id.rating1);

        Show currentShow = getItem(position);

        text1.setText(currentShow.getName());
        rating1.setRating((float) (currentShow.getAverage() / 2));

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
