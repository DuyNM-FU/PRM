package com.example.fe_prm.view_your_reservation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_prm.R;
import com.example.fe_prm.view_your_reservation.dto.ReservationInformationDto;

import java.util.List;

public class ReservationRecycleViewAdapter extends RecyclerView.Adapter<ReservationRecycleViewAdapter.ViewHolder> {
    private List<ReservationInformationDto> reservations;
    private Context context;

    public void setReservations(List<ReservationInformationDto> reservations) {
        this.reservations = reservations;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ReservationRecycleViewAdapter(List<ReservationInformationDto> reservations, Context context) {
        this.reservations = reservations;
        this.context = context;
    }

    public ReservationRecycleViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_holder_reservation_information, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReservationInformationDto reservation = reservations.get(position);
        holder.tvTitle.setText("Paragon Restaurant, ");
        holder.tvDate.setText(reservation.getDate());
        holder.tvTime.setText(reservation.getTime());
        holder.tvSeats.setText("" + reservation.getSeat());
        holder.tvTable.setText("" + reservation.getTable());
        holder.tvTableType.setText(reservation.isPrivate() == true ?"Private":"Public");
        holder.tvOrderFood.setText(reservation.getOrderFoodID());

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle, tvDate, tvTime, tvTable, tvSeats, tvTableType, tvOrderFood;
        public ImageView btEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_reservationKey);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTable = itemView.findViewById(R.id.tv_table);
            tvSeats = itemView.findViewById(R.id.tv_seat);
            tvTableType = itemView.findViewById(R.id.tv_tableType);
            tvOrderFood = itemView.findViewById(R.id.tv_orderFood);
            btEdit = itemView.findViewById(R.id.iv_edit);
        }
    }

}
