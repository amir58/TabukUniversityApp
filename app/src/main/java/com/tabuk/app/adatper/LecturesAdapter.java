package com.tabuk.app.adatper;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tabuk.app.R;
import com.tabuk.app.databinding.ItemLectureBinding;

public class LecturesAdapter extends RecyclerView.Adapter<LecturesAdapter.LectureHolder> {


    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LectureHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_lecture, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class LectureHolder extends RecyclerView.ViewHolder {
        ItemLectureBinding binding;

        public LectureHolder(@NonNull ItemLectureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
