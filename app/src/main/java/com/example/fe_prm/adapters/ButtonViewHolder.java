package com.example.fe_prm.adapters;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.fe_prm.R;

public class ButtonViewHolder extends ViewHolder {
    Button btn_selection;
    public ButtonViewHolder(@NonNull View itemView) {
        super(itemView);

        btn_selection = itemView.findViewById(R.id.btn_selection);
    }
}
