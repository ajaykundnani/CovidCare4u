package com.example.ahmedabadcoronabeds.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ahmedabadcoronabeds.Models.User;
import com.example.ahmedabadcoronabeds.R;

import java.util.ArrayList;
import java.util.List;

public class UserHolder extends RecyclerView.Adapter<UserHolder.ViewHolder> {


    private List<User> users = new ArrayList<>();
    private SelectedUser selectedUser;

    @NonNull
    @Override
    public UserHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View cardview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_manage,viewGroup,
                false);

        return new ViewHolder(cardview);
    }

    public interface SelectedUser{
        void selectedUser(User user);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.User_Name.setText(users.get(i).getName());
        viewHolder.User_Role.setText(users.get(i).getRole());
    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsersList(List<User> users,SelectedUser selectedUser)
    {
        this.users = users;
        this.selectedUser = selectedUser;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView User_Name;
        private final TextView User_Role;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            User_Name = itemView.findViewById(R.id.User_Name);
            User_Role = itemView.findViewById(R.id.User_Role);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedUser.selectedUser(users.get(getAdapterPosition()));
                }
            });
        }
    }
}
