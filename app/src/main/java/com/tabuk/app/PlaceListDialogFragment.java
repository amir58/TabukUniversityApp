package com.tabuk.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.tabuk.app.model.MyLocation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     PlaceListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */

public class PlaceListDialogFragment extends BaseBottomSheet implements TextWatcher {
    private List<MyLocation> myLocations;
    private PlacesI placesI;

    private PlaceAdapter adapter;
    private EditText editTextSearch;

    public PlaceListDialogFragment(List<MyLocation> myLocations, PlacesI placesI) {
        this.myLocations = myLocations;
        this.placesI = placesI;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_places_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTextSearch = view.findViewById(R.id.et_search);
        editTextSearch.addTextChangedListener(this);
        final RecyclerView recyclerView = view.findViewById(R.id.places_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PlaceAdapter(myLocations, placesI);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        List<MyLocation> filterLocations = new ArrayList<>();
        for (MyLocation myLocation : this.myLocations) {
            if (myLocation.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                filterLocations.add(myLocation);
            }
        }
        adapter.updateList(filterLocations);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.item_place, parent, false));
            text = itemView.findViewById(R.id.text);
        }
    }

    private class PlaceAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<MyLocation> locations;
        private PlacesI placesI;

        public PlaceAdapter(List<MyLocation> locations, PlacesI placesI) {
            this.locations = locations;
            this.placesI = placesI;
        }

        public void updateList(List<MyLocation> locations) {
            this.locations = locations;
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyLocation myLocation = locations.get(position);

            holder.text.setText(myLocation.getName());

            holder.itemView.setOnClickListener(v -> {
                dismiss();
                placesI.onPlaceClick(myLocation);
            });
        }

        @Override
        public int getItemCount() {
            return locations.size();
        }

    }

    public interface PlacesI {
        void onPlaceClick(MyLocation myLocation);
    }

}