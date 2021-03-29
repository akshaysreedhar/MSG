package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.msf.training.R;
import com.msf.training.adapters.BrandNameAdapter;
import com.msf.training.data.BrandData;
import com.msf.training.databinding.FragmentRecyclerViewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BrandNameFragment extends BaseFragment {

    private final List<BrandData> mBrandData = new ArrayList<>();
    FragmentRecyclerViewBinding binding;

    public BrandNameFragment() {

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
        addToBrandList();
        BrandNameAdapter adapter = new BrandNameAdapter(mBrandData);
        recyclerView.setAdapter(adapter);
    }

    protected void addToBrandList() {
        mBrandData.add(new BrandData("Adidas"));
        mBrandData.add(new BrandData("Ross Stores"));
        mBrandData.add(new BrandData("Richemont"));
        mBrandData.add(new BrandData("H&M"));
        mBrandData.add(new BrandData("Hermès"));
        mBrandData.add(new BrandData("TJX"));
        mBrandData.add(new BrandData("LVMH"));
        mBrandData.add(new BrandData("Nike"));
        mBrandData.add(new BrandData("Puma"));
        mBrandData.add(new BrandData("Inditex"));
        mBrandData.add(new BrandData("Kering"));
        mBrandData.add(new BrandData("L Brands"));
        mBrandData.add(new BrandData("Pandora"));
        mBrandData.add(new BrandData("Fast Retailing"));
        mBrandData.add(new BrandData("Next"));
        mBrandData.add(new BrandData("VF Corporation"));
        mBrandData.add(new BrandData("Luxottica"));
        mBrandData.add(new BrandData("Michael Kors"));
        mBrandData.add(new BrandData("Gap"));
        mBrandData.add(new BrandData("Hanes Brands"));
        mBrandData.add(new BrandData("Burberry"));
        mBrandData.add(new BrandData("Blackberry"));
        mBrandData.add(new BrandData("Samsung"));
        mBrandData.add(new BrandData("LG"));
        mBrandData.add(new BrandData("Huawei"));
    }
}