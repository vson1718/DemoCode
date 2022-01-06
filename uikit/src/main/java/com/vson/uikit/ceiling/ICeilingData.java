package com.vson.uikit.ceiling;

public interface ICeilingData {


    /**
     * 是否是组的第一个Item
     *
     * @param position RecycleView的Item位置
     * @return true 是否|false 反之
     */
    boolean isFirstItemOfGroup(int position);

    /**
     * 获取组名
     *
     * @param position RecycleView的Item位置
     * @return 组名
     */
    String getGroupName(int position);

}
