package com.vson.uikit.slide;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class SlideCardLayoutManager  extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        int bottomPosition;
        if (itemCount<CardConfig.MAX_SHOW_COUNT){
            bottomPosition=0;
        }else {
            bottomPosition=itemCount-CardConfig.MAX_SHOW_COUNT;
        }
        for (int i=bottomPosition;i<itemCount;i++){
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view,0,0);
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            // onLayout -- 布局所有子View
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));
            // 8-4-1  8-5-1 --> 3,2,1,0
            int level = itemCount - i - 1;
            // 对子View进行缩放平移处理
            if (level > 0) {
                // level < 3
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {// 2,1
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * level);
                    view.setScaleX(1 - CardConfig.SCALE_GAP * level);
                    view.setScaleY(1 - CardConfig.SCALE_GAP * level);
                } else {//3
                    // 如果是最底下那张，则效果与前一张一样
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * (level - 1));
                    view.setScaleX(1 - CardConfig.SCALE_GAP * (level - 1));
                    view.setScaleY(1 - CardConfig.SCALE_GAP * (level - 1));
                }
            }
        }
    }
}
