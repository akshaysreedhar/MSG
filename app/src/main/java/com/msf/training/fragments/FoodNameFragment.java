package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.msf.training.R;
import com.msf.training.adapters.FoodNameAdapter;
import com.msf.training.constants.Constants;
import com.msf.training.data.FoodData;
import com.msf.training.databinding.FragmentRecyclerViewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FoodNameFragment extends BaseFragment {
    private final List<FoodData> mFoodData = new ArrayList<>();
    FragmentRecyclerViewBinding binding;

    public FoodNameFragment() {

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
        SwipeRefreshLayout swipeRefreshLayout = binding.swipeRefresh;
        ImageButton imageButtonSettings = getActivity().findViewById(R.id.ibSettings);
        ImageButton imageButtonBack = getActivity().findViewById(R.id.ibBack);
        ImageButton imageButtonClose = getActivity().findViewById(R.id.ibClose);
        ImageButton imageButtonDelete = getActivity().findViewById(R.id.ibDelete);
        SwitchCompat switchCompat = getActivity().findViewById(R.id.swToggle);

        PopupMenu popupMenu = new PopupMenu(requireContext(), imageButtonSettings);
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(R.menu.settings_menu, menu);
        imageButtonSettings.setOnClickListener(v -> popupMenu.show());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)));
        recyclerView.addItemDecoration(dividerItemDecoration);

        addToFoodList();

        FoodNameAdapter adapter = new FoodNameAdapter(mFoodData);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            showToast(requireContext(), getString(R.string.refreshed));
            getParentFragmentManager().beginTransaction().replace(R.id.flItemContainer, new FoodNameFragment()).commit();
        });
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.ascending) {
                Collections.sort(mFoodData, (o1, o2) -> o1.getName().compareTo(o2.getName()));
                item.setChecked(true);
            } else if (item.getItemId() == R.id.descending) {
                Collections.sort(mFoodData, (o1, o2) -> o2.getName().compareTo(o1.getName()));
                item.setChecked(true);
            } else if (item.getItemId() == R.id.select_all) {
                adapter.selectAll();
                setVisibility(imageButtonSettings, imageButtonBack, switchCompat, imageButtonClose, imageButtonDelete, Constants.one);
                imageButtonClose.setOnClickListener(v -> {
                    adapter.deselectAll();
                    adapter.notifyDataSetChanged();
                    setVisibility(imageButtonSettings, imageButtonBack, switchCompat, imageButtonClose, imageButtonDelete, Constants.two);
                });
                imageButtonDelete.setOnClickListener(v -> alert(getString(R.string.delete_all), getString(R.string.delete_all_confirmation), (dialog, which) -> {
                    mFoodData.removeAll(mFoodData);
                    adapter.notifyDataSetChanged();
                    showToast(getContext(), getString(R.string.all_deleted));
                    adapter.deselectAll();
                    setVisibility(imageButtonSettings, imageButtonBack, switchCompat, imageButtonClose, imageButtonDelete, Constants.two);
                }, (dialog, which) -> {
                    adapter.notifyDataSetChanged();
                    adapter.deselectAll();
                    setVisibility(imageButtonSettings, imageButtonBack, switchCompat, imageButtonClose, imageButtonDelete, Constants.two);
                }));
            }
            adapter.notifyDataSetChanged();

            return false;
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    assert viewHolder != null;
                    viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.grey));
                }
            }

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = ItemTouchHelper.START;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mFoodData, from, to);
                adapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int itemPosition = viewHolder.getAdapterPosition();
                alert(mFoodData.get(itemPosition).getName(), getString(R.string.delete_item), (dialog, which) -> {
                    mFoodData.remove(itemPosition);
                    adapter.notifyItemRemoved(itemPosition);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(requireContext(), mFoodData.get(itemPosition).getName() + R.string.is_deleted_from + (itemPosition + 1), Toast.LENGTH_LONG).show();
                }, (dialog, which) -> adapter.notifyDataSetChanged());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    protected void setVisibility(ImageButton imageButtonSettings, ImageButton imageButtonBack, SwitchCompat switchCompat, ImageButton imageButtonClose, ImageButton imageButtonDelete, String type) {
        if (type.equals(Constants.one)) {
            imageButtonSettings.setVisibility(View.INVISIBLE);
            imageButtonBack.setVisibility(View.INVISIBLE);
            switchCompat.setVisibility(View.INVISIBLE);
            imageButtonClose.setVisibility(View.VISIBLE);
            imageButtonDelete.setVisibility(View.VISIBLE);
        } else if (type.equals(Constants.two)) {
            imageButtonSettings.setVisibility(View.VISIBLE);
            imageButtonBack.setVisibility(View.VISIBLE);
            switchCompat.setVisibility(View.VISIBLE);
            imageButtonClose.setVisibility(View.INVISIBLE);
            imageButtonDelete.setVisibility(View.INVISIBLE);
        }
    }

    protected void addToFoodList() {
        mFoodData.add(new FoodData("Salad"));
        mFoodData.add(new FoodData("Sandwich"));
        mFoodData.add(new FoodData("Bread"));
        mFoodData.add(new FoodData("Steak"));
        mFoodData.add(new FoodData("Pasta"));
        mFoodData.add(new FoodData("French Fries"));
        mFoodData.add(new FoodData("Ice Cream"));
        mFoodData.add(new FoodData("Fried rice"));
        mFoodData.add(new FoodData("Pancakes"));
        mFoodData.add(new FoodData("Burger"));
        mFoodData.add(new FoodData("Pizza"));
        mFoodData.add(new FoodData("Pumpkin pie"));
        mFoodData.add(new FoodData("Apple pie"));
        mFoodData.add(new FoodData("Muffins"));
        mFoodData.add(new FoodData("Cheesecake"));
        mFoodData.add(new FoodData("Banana Bread"));
        mFoodData.add(new FoodData("Potato Chips"));
        mFoodData.add(new FoodData("Salsa"));
        mFoodData.add(new FoodData("Kiwi"));
        mFoodData.add(new FoodData("Biscuits"));
        mFoodData.add(new FoodData("Hot Dogs"));
        mFoodData.add(new FoodData("Bacon"));
        mFoodData.add(new FoodData("Brownie"));
        mFoodData.add(new FoodData("Sausage"));
        mFoodData.add(new FoodData("Roasted Chicken"));
    }
}
