package com.msf.training.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.msf.training.R;
import com.msf.training.data.FoodData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodNameAdapter extends RecyclerView.Adapter<FoodNameAdapter.ItemViewHolder> {

    private final List<FoodData> foodDataList;
    private boolean isSelectedAll;


    public FoodNameAdapter(List<FoodData> foodList) {
        this.foodDataList = foodList;
    }

    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(rootView);
    }

    public void selectAll() {
        isSelectedAll = true;
    }

    public void deselectAll() {
        isSelectedAll = false;
    }

    @Override
    public void onBindViewHolder(@NotNull ItemViewHolder holder, int position) {
        if (!foodDataList.isEmpty()) {
            FoodData object = foodDataList.get(position);
            String name = object.getName();
            holder.foodName.setText(name);
        }
        if (isSelectedAll) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.grey));
        } else if (!isSelectedAll) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.white));
        }
        ImageButton imageButton = holder.itemView.findViewById(R.id.ibDelete);
        imageButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            String itemName = foodDataList.get(position).getName();
            builder.setTitle(itemName);
            builder.setMessage(R.string.delete_item);
            builder.setPositiveButton(R.string.yes, (dialog, which) -> {
                Toast.makeText(v.getContext(), itemName + " is deleted from the position " + (position + 1), Toast.LENGTH_LONG).show();
                foodDataList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            });
            builder.setNegativeButton(R.string.no, (dialog, which) -> notifyDataSetChanged());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return foodDataList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tvRecyclerName);
            itemView.setOnClickListener(v -> Toast.makeText(itemView.getContext(), "Clicked " + foodName.getText() + " in the position " + (getAdapterPosition() + 1), Toast.LENGTH_LONG).show());
        }
    }
}