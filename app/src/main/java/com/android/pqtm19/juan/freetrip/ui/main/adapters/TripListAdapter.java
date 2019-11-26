package com.android.pqtm19.juan.freetrip.ui.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.pqtm19.juan.freetrip.R;
import com.android.pqtm19.juan.freetrip.data.entities.Trip;

import java.util.List;
import java.util.UUID;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder>{

    private final LayoutInflater layoutInflater;
    private List<Trip> mTripList;
    private OnItemClickListener mListener;

    public TripListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.trip_list_recyclerview_item,
                parent,false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripListAdapter.TripViewHolder holder, int position) {
        Trip currentTrip = mTripList.get(position);
        holder.tripTextView.setText(currentTrip.getName());
    }

    public void setmTripList(List<Trip> trips) {
        this.mTripList = trips;
        notifyDataSetChanged();
    }

    public UUID getUUIDTripAt(int i){
        return mTripList.get(i).getUUID();
    }

    @Override
    public int getItemCount() {
        if(mTripList != null) { return mTripList.size(); }

        return 0;
    }

    public interface OnItemClickListener { void onItemClick (UUID uuid); }

    public void setOnItemClickListener(OnItemClickListener listener){ this.mListener = listener; }

    class TripViewHolder extends RecyclerView.ViewHolder {

        private final TextView tripTextView;

        TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripTextView = itemView.findViewById(R.id.textView_trip_list_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (mListener!= null && position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(mTripList.get(position).getUUID());
                    }
                }
            });
        }
    }
}
