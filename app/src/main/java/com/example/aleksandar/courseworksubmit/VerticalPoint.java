package com.example.aleksandar.courseworksubmit;

/**
 * Created by Aleksandar Krastev S1433655 on 28/03/2018.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalPoint extends RecyclerView.ItemDecoration {
        int point;

        public VerticalPoint(int point){
        this.point = point;
        }

//    @Override
    public void getItemPosition(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=point;
        outRect.bottom=point;
        outRect.right=point;
        if (parent.getChildLayoutPosition(view)==0){
            outRect.top=point;
        }
    }
}