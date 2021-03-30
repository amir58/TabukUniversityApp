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
import com.tabuk.app.databinding.ItemExamBinding;
import com.tabuk.app.model.MyExam;
import com.tabuk.app.model.MyLecture;

import java.util.List;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.LectureHolder> {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<MyExam> myExams;
    String rule;

    public ExamsAdapter(List<MyExam> myExams, String rule) {
        this.myExams = myExams;
        this.rule = rule;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LectureHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_exam, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        MyExam myLecture = myExams.get(position);

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

        holder.binding.btnDelete.setOnClickListener(v -> firestore.collection("exams").document(myLecture.getId()).delete());

    }

    @Override
    public int getItemCount() {
        return myExams.size();
    }

    class LectureHolder extends RecyclerView.ViewHolder {
        ItemExamBinding binding;

        public LectureHolder(@NonNull ItemExamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
