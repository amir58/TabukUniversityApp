package com.tabuk.app.adatper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.R;
import com.tabuk.app.ShowPlaceOnMapsActivity;
import com.tabuk.app.databinding.ItemLectureBinding;
import com.tabuk.app.model.MyLecture;

import java.util.ArrayList;
import java.util.List;

public class LecturesAdapter extends RecyclerView.Adapter<LecturesAdapter.LectureHolder> {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<MyLecture> myLectures;
    String rule;

    public LecturesAdapter(List<MyLecture> myLectures, String rule) {
        this.myLectures = myLectures;
        this.rule = rule;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LectureHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_lecture, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        MyLecture myLecture = myLectures.get(position);

        holder.binding.setData(myLecture);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ShowPlaceOnMapsActivity.class);
            intent.putExtra("lat", myLecture.getLat());
            intent.putExtra("lng", myLecture.getLng());
            v.getContext().startActivity(intent);
        });

        if (rule.equals("management")) {
            holder.binding.btnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.binding.btnDelete.setVisibility(View.GONE);
        }

        holder.binding.btnDelete.setOnClickListener(v -> firestore.collection("lectures").document(myLecture.getId()).delete());
    }

    @Override
    public int getItemCount() {
        return myLectures.size();
    }

    class LectureHolder extends RecyclerView.ViewHolder {
        ItemLectureBinding binding;

        public LectureHolder(@NonNull ItemLectureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
