package com.vson.code.slide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vson.code.R;
import com.vson.code.ceiling.CeilingModel;
import com.vson.uikit.ceiling.ICeilingData;

import java.util.List;


public class SlideCardAdapter extends RecyclerView.Adapter<SlideCardAdapter.SlideCardViewHolder> {

    private Context context;
    private List<SlideCardBean> starList;

    public SlideCardAdapter(Context context, List<SlideCardBean> starList) {
        this.context = context;
        this.starList = starList;
    }


    @NonNull
    @Override
    public SlideCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_swipe_card, null);
        return new SlideCardViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SlideCardViewHolder holder, int position) {
        SlideCardBean slideCardBean = starList.get(position);
        holder.tvName.setText(slideCardBean.getName());
        holder.tvPrecent.setText(slideCardBean.getPostition() + "/" + starList.size());
        holder.iv.setImageResource(slideCardBean.getId());

    }

    @Override
    public int getItemCount() {
        return starList == null ? 0 : starList.size();
    }

    public static class SlideCardViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvPrecent;
        public ImageView iv;

        public SlideCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrecent = itemView.findViewById(R.id.tvPrecent);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
