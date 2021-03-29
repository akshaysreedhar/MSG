package com.msf.training.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.msf.training.R;
import com.msf.training.data.BrandData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BrandNameAdapter extends RecyclerView.Adapter<BrandNameAdapter.ItemViewHolder> {

    private final List<BrandData> brandDataList;

    public BrandNameAdapter(List<BrandData> brandList) {
        this.brandDataList = brandList;
    }

    @NotNull
    @Override
    public BrandNameAdapter.ItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new BrandNameAdapter.ItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NotNull BrandNameAdapter.ItemViewHolder holder, int position) {
        if(!brandDataList.isEmpty()){
            BrandData object = brandDataList.get(position);
            String name = object.getName();
            holder.brandName.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return brandDataList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView brandName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.tvRecyclerName);
        }
    }
}