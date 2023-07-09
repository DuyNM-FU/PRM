package com.example.fe_prm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_prm.R;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonViewHolder> {
    private List<String> buttonNameList;
    private int selectedItemPosition = -1;

    public ButtonAdapter(List<String> buttonNameList) {
        this.buttonNameList = buttonNameList;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_selection, parent, false);

        ButtonViewHolder viewHolder = new ButtonViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {

        holder.btn_selection.setText(buttonNameList.get(position));
        holder.btn_selection.setSelected(position == selectedItemPosition);
        holder.btn_selection.setOnClickListener(v -> {
            int previousSelectedPosition = selectedItemPosition;
            selectedItemPosition = holder.getAdapterPosition();
            notifyItemChanged(previousSelectedPosition);
            notifyItemChanged(selectedItemPosition);
        });
    }

    @Override
    public int getItemCount() {
        return buttonNameList.size();
    }

}
