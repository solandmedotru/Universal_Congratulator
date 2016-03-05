package ru.solandme.universal_congratulator.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.solandme.universal_congratulator.R;
import ru.solandme.universal_congratulator.dto.HolidayDTO;


public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayViewHolder>{

    private List<HolidayDTO> data;

    public HolidayListAdapter(List<HolidayDTO> data) {
        this.data = data;
    }

    @Override
    public HolidayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_item, parent, false);

        return new HolidayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HolidayViewHolder holder, int position) {

        HolidayDTO item = data.get(position);

        holder.holidayName.setText(item.getDisplayHolidayName());

        holder.textDays.setText(item.getDaysToHoliday());

        holder.imageHoliday.setImageResource(item.getPictureId());

        if (item.getKey().equals("Birthday")){
            holder.textDays.setVisibility(View.GONE);
        } else {
            holder.textDays.setVisibility(View.VISIBLE);
            holder.textDays.setText(item.getDaysToHoliday());
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static  class HolidayViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView holidayName;
        TextView textDays;
        ImageView imageHoliday;


        public HolidayViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

            holidayName = (TextView) itemView.findViewById(R.id.textDisplayHolidayName);

            textDays = (TextView) itemView.findViewById(R.id.textDays);

            imageHoliday = (ImageView) itemView.findViewById(R.id.imageHoliday);
        }
    }
}
