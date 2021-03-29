package com.msf.training.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msf.training.R;
import com.msf.training.data.FlowerData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FlowerNameAdapter extends RecyclerView.Adapter<FlowerNameAdapter.ItemViewHolder> {

    private final List<FlowerData> flowerDataList;

    public FlowerNameAdapter(List<FlowerData> flowerList) {
        this.flowerDataList = flowerList;
    }

    @NotNull
    @Override
    public FlowerNameAdapter.ItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new FlowerNameAdapter.ItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NotNull FlowerNameAdapter.ItemViewHolder holder, int position) {
        if (!flowerDataList.isEmpty()) {
            FlowerData object = flowerDataList.get(position);
            String name = object.getName();
            holder.flowerName.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return flowerDataList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView flowerName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            flowerName = itemView.findViewById(R.id.tvRecyclerName);
        }
    }
}