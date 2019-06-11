package com.theangel.ericsson.ericsson.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.model.GridItem;
import com.theangel.ericsson.ericsson.model.Show;

import java.util.List;

public class ImagesUIAdapter extends ArrayAdapter<GridItem<Show>> {
    private static final String TAG = "c3trace";

    public ImagesUIAdapter(@NonNull Context context, @NonNull List<GridItem<Show>> objects) {
        super(context, R.layout.show_list_item, objects);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getItem().getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: " + position);

        ImageView imageView = null;

        if(convertView == null) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new GridView.LayoutParams(350, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else {
            imageView = (ImageView) convertView;
        }

        GridItem<Show> gridItem = getItem(position);
        imageView.setImageBitmap(gridItem.getImage());

        return imageView;
    }
}
