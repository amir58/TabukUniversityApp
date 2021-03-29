package com.tabuk.app.adatper;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.tabuk.app.R;
import com.tabuk.app.databinding.ItemLectureBinding;
import com.tabuk.app.databinding.ItemUserBinding;
import com.tabuk.app.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.LectureHolder> {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<User> users;

    public UsersAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LectureHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        User user = users.get(position);
        holder.binding.setUser(user);

        holder.binding.btnStopActive.setText(user.isActive() ? "Stop " : "Active");

        holder.binding.btnDelete.setOnClickListener(v -> {
            firestore.collection("users").document(user.getAuthId()).delete();
            Toasty.success(v.getContext(), "Deleted").show();
        });

        holder.binding.btnStopActive.setOnClickListener(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("active", !user.isActive());

            firestore.collection("users").document(user.getAuthId()).update(map);
        });

        holder.binding.btnRule.setOnClickListener(
                v -> new AlertDialog.Builder(v.getContext())
                        .setMessage("Choose rule ")
                        .setPositiveButton("Admin", (dialog, which) -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("rule", "admin");

                            firestore.collection("users").document(user.getAuthId()).update(map);
                        })
                        .setNegativeButton("Management", (dialog, which) -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("rule", "management");

                            firestore.collection("users").document(user.getAuthId()).update(map);
                        })
                        .show()
        );
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class LectureHolder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;

        public LectureHolder(@NonNull ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
