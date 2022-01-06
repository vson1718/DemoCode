package com.vson.code.ceiling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vson.code.R;
import com.vson.uikit.ceiling.ICeilingData;

import java.util.List;


public class CeilingAdapter extends RecyclerView.Adapter<CeilingAdapter.CeilingViewHolder>
        implements ICeilingData {

    private Context context;
    private List<CeilingModel> starList;

    public CeilingAdapter(Context context, List<CeilingModel> starList) {
        this.context = context;
        this.starList = starList;
    }


    @NonNull
    @Override
    public CeilingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ceiling, null);
        return new CeilingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CeilingViewHolder holder, int position) {
        holder.tv.setText(starList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return starList == null ? 0 : starList.size();
    }

    @Override
    public boolean isFirstItemOfGroup(int position) {
        if (position == 0) {
            return true;
        } else {
            // 拿到当前位置的和前一个位置的 组名
            String currentItemGroupName = getGroupName(position);
            String preItemGroupName = getGroupName(position - 1);
            // 如果相等，则表示position的item不是第一个，否则是的
            if (preItemGroupName.equals(currentItemGroupName)) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public String getGroupName(int position) {
        return starList.get(position).getGrounpName();
    }

    public static class CeilingViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public CeilingViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_star);
        }
    }
}
