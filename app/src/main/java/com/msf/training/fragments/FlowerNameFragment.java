package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.msf.training.R;
import com.msf.training.adapters.FlowerNameAdapter;
import com.msf.training.data.FlowerData;
import com.msf.training.databinding.FragmentRecyclerViewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FlowerNameFragment extends BaseFragment {
    private final List<FlowerData> mFlowerData = new ArrayList<>();
    FragmentRecyclerViewBinding binding;

    public FlowerNameFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = (FragmentRecyclerViewBinding) getBinding(inflater, R.layout.fragment_recycler_view, container);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = binding.rvFoodBrandFlowers;
        addToFlowerList();
        FlowerNameAdapter adapter = new FlowerNameAdapter(mFlowerData);
        recyclerView.setAdapter(adapter);
    }

    protected void addToFlowerList() {
        mFlowerData.add(new FlowerData("Rose"));
        mFlowerData.add(new FlowerData("Lily"));
        mFlowerData.add(new FlowerData("Tulip"));
        mFlowerData.add(new FlowerData("Orchid"));
        mFlowerData.add(new FlowerData("Carnation"));
        mFlowerData.add(new FlowerData("Freesia"));
        mFlowerData.add(new FlowerData("Hyacinth"));
        mFlowerData.add(new FlowerData("Peruvian Lily"));
        mFlowerData.add(new FlowerData("Chrysanthemum"));
        mFlowerData.add(new FlowerData("Gladiolus"));
        mFlowerData.add(new FlowerData("Anemone"));
        mFlowerData.add(new FlowerData("Daffodil"));
        mFlowerData.add(new FlowerData("Poppy"));
        mFlowerData.add(new FlowerData("Sunflower"));
        mFlowerData.add(new FlowerData("Aconitum"));
        mFlowerData.add(new FlowerData("African Daisy"));
        mFlowerData.add(new FlowerData("Alchemilla"));
        mFlowerData.add(new FlowerData("Allium roseum"));
        mFlowerData.add(new FlowerData("Alstroemeria"));
        mFlowerData.add(new FlowerData("Alyssum"));
        mFlowerData.add(new FlowerData("Amaranthus"));
        mFlowerData.add(new FlowerData("Amaryllis"));
        mFlowerData.add(new FlowerData("Anemone"));
        mFlowerData.add(new FlowerData("Angelonia"));
        mFlowerData.add(new FlowerData("Anthurium"));
    }
}