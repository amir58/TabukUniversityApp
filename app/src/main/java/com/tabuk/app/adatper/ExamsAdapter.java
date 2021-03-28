package com.tabuk.app.adatper;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tabuk.app.R;
import com.tabuk.app.databinding.ItemExamBinding;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.LectureHolder> {


    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LectureHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_exam, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class LectureHolder extends RecyclerView.ViewHolder {
        ItemExamBinding binding;

        public LectureHolder(@NonNull ItemExamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
