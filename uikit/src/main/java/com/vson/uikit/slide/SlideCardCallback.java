package com.vson.uikit.slide;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SlideCardCallback<DATA,T extends RecyclerView.Adapter> extends ItemTouchHelper.SimpleCallback {
    private RecyclerView mRv;
    private RecyclerView.Adapter adapter;
    private List<DATA> mDatas;

    public SlideCardCallback(RecyclerView mRv, T  adapter, List<DATA> mDatas) {
        // 设置方向，拖拽，滑动  上下左右 0x1111
        super(0, 15);//1111
        this.mRv = mRv;
        this.adapter = adapter;
        this.mDatas = mDatas;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // 数据循环使用
        // 移除最上面的
        DATA remove = mDatas.remove(viewHolder.getLayoutPosition());
        // 添加到数组的第一个位置
        mDatas.add(0, remove);
        // 刷新
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        double maxDistance = recyclerView.getWidth() * 0.5f;
        double distance = Math.sqrt(dX * dX + dY * dY);
        // 放大的系数
        double fraction = distance / maxDistance;
        if (fraction > 1) {
            fraction = 1;
        }
        // 当前显示在界面的item数，0，1，2，3
        int itemCount = recyclerView.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getChildAt(i);

            int level = itemCount - i - 1;

            // 对子View进行缩放平移处理
            if (level > 0) {
                // level < 3
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {// 2,1
                    // 最大达到它的上一个Item的效果
                    view.setTranslationY((float) (CardConfig.TRANS_Y_GAP * level - fraction * CardConfig.TRANS_Y_GAP));
                    view.setScaleX((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                    view.setScaleY((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                }
            }
        }
    }
/*
    // 回弹时间
    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 1000;
    }
*/

    // 回弹距离
    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.2f;
    }
}

