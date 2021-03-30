package com.tabuk.app.adatper;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tabuk.app.R;
import com.tabuk.app.databinding.ItemExamBinding;
import com.tabuk.app.databinding.ItemNotificationBinding;
import com.tabuk.app.model.MyNotification;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.LectureHolder> {

    List<MyNotification> myNotifications;

    public NotificationsAdapter(List<MyNotification> myNotifications) {
        this.myNotifications = myNotifications;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LectureHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        holder.binding.setMessage(myNotifications.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return myNotifications.size();
    }

    class LectureHolder extends RecyclerView.ViewHolder {
        ItemNotificationBinding binding;

        public LectureHolder(@NonNull ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
