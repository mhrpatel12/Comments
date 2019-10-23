package com.mihir.comments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView tv;

    public ViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView;
    }
}