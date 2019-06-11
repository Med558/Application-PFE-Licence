package com.theangel.ericsson.ericsson.model;

import android.graphics.Bitmap;

public class GridItem<T> {
    private T item;
    private Bitmap image;

    public GridItem(T item, Bitmap image) {
        this.item = item;
        this.image = image;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
