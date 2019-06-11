package com.theangel.ericsson.ericsson.callbacks;

import com.theangel.ericsson.ericsson.model.GridItem;

import java.util.List;

public interface OnImagesDownloaded<T> {
    void onImagesDownloaded(List<GridItem<T>> items);
}
