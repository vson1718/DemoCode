package com.vson.uikit.ceiling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CeilingItemDecoration extends RecyclerView.ItemDecoration {

    private int mHeadHeight;
    private int mLineHeight;
    private Paint headPaint;
    private Paint headOverPaint;
    private Paint drawTextPaint;
    private Paint drawOverTextPaint;

    private Rect textRect;

    public CeilingItemDecoration(Context context) {
        mHeadHeight = dp2px(context, 50);
        mLineHeight = dp2px(context, 1);
        // 每一组的头部的Paint
        headPaint = new Paint();
        headPaint.setColor(Color.RED);

        headOverPaint = new Paint();
        headOverPaint.setColor(Color.YELLOW);

        drawTextPaint = new Paint();
        drawTextPaint.setTextSize(50);
        drawTextPaint.setColor(Color.BLACK);
        drawTextPaint.setAntiAlias(true);
        drawTextPaint.setDither(true);
        drawTextPaint.setTextAlign(Paint.Align.CENTER);

        drawOverTextPaint = new Paint();
        drawOverTextPaint.setTextSize(50);
        drawOverTextPaint.setColor(Color.BLUE);
        drawOverTextPaint.setAntiAlias(true);
        drawOverTextPaint.setTextAlign(Paint.Align.CENTER);

        textRect = new Rect();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof ICeilingData) {
            ICeilingData iCeilingData = (ICeilingData) adapter;
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (iCeilingData.isFirstItemOfGroup(childAdapterPosition)) {
                outRect.set(0, mHeadHeight, 0, 0);
            } else {
                outRect.set(0, mLineHeight, 0, 0);
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof ICeilingData) {
            ICeilingData iCeilingData = (ICeilingData) adapter;
            //获取当前屏幕内item的总数
            int childCount = parent.getChildCount();
            int paddingStart = parent.getPaddingStart();
            int paddingEnd = parent.getWidth() - paddingStart;
            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                if (view.getTop() - mHeadHeight - parent.getPaddingTop() >= 0) {
                    int childLayoutPosition = parent.getChildLayoutPosition(view);
                    boolean isHeadGroup = iCeilingData.isFirstItemOfGroup(childLayoutPosition);
                    if (isHeadGroup) {
                        c.drawRect(paddingStart, view.getTop() - mHeadHeight, paddingEnd, view.getTop(), headPaint);
                        String groupName = iCeilingData.getGroupName(childLayoutPosition);
                        drawTextPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                        c.drawText(groupName, textRect.width() / 2f + paddingStart, view.getTop() - mHeadHeight / 2f + textRect.height() / 2f, drawTextPaint);
                    } else {//普通itemView分割线
                        c.drawRect(paddingStart, view.getTop() - mLineHeight, paddingEnd, view.getTop(), headPaint);
                    }
                }
            }
        }
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof ICeilingData) {
            ICeilingData iCeilingData = (ICeilingData) adapter;
            int paddingLeft = parent.getPaddingLeft();
            int paddingRight =parent.getWidth() - parent.getPaddingRight();
            int paddingTop = parent.getPaddingTop();
            int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            View itemView = parent.findViewHolderForAdapterPosition(position).itemView;
            boolean isFirstItemOfGroup = iCeilingData.isFirstItemOfGroup(position + 1);
            if (isFirstItemOfGroup) {
                int bottom = Math.min(paddingTop + mHeadHeight, itemView.getBottom());
                c.drawRect(paddingLeft, paddingTop, paddingRight, bottom, headOverPaint);
                String groupName = iCeilingData.getGroupName(position);
                drawOverTextPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                c.clipRect(paddingLeft, paddingTop, paddingRight, bottom);
                c.drawText(groupName, textRect.width() / 2f + paddingLeft,
                        bottom - mHeadHeight / 2f + textRect.height() / 2f, drawOverTextPaint);
            }else {
                c.drawRect(paddingLeft, paddingTop, paddingRight, paddingTop + mHeadHeight, headOverPaint);
                String groupName = iCeilingData.getGroupName(position);
                drawTextPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                // 绘制文字
                c.drawText(groupName, textRect.width() / 2f + paddingLeft,
                        paddingTop + mHeadHeight / 2f + textRect.height() / 2f, drawOverTextPaint);
            }
        }

    }

    private int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                context.getResources().getDisplayMetrics());
    }
}
