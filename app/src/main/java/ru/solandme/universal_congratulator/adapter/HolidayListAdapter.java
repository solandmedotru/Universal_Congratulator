package ru.solandme.universal_congratulator.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.solandme.universal_congratulator.R;
import ru.solandme.universal_congratulator.TextActivity;
import ru.solandme.universal_congratulator.dto.HolidayDTO;


public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayViewHolder>{

    private List<HolidayDTO> data;
    public final static String EXTRA_MESSAGE = "ru.solandme.universal_congratulator.MESSAGE";

    Context context;

    public HolidayListAdapter(List<HolidayDTO> data) {
        this.data = data;
    }

    @Override
    public HolidayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_item, parent, false);
        context = parent.getContext();


        return new HolidayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HolidayViewHolder holder, final int position) {

        final HolidayDTO item = data.get(position);

        holder.holidayName.setText(item.getDisplayHolidayName());

        holder.textHolidayDescription.setText(item.getDescription());

        holder.textDays.setText(item.getDaysToHoliday());

        holder.imageHoliday.setImageResource(item.getPictureId());

        if (item.getKey().equals("Birthday")){
            holder.textDays.setVisibility(View.GONE);
        } else {
            holder.textDays.setVisibility(View.VISIBLE);
            holder.textDays.setText(item.getDaysToHoliday());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TextActivity.class);

                String key = item.getKey();

                switch (key) {
                    case "Birthday":
                        intent.putExtra(EXTRA_MESSAGE, "Birthday");
                        context.startActivity(intent);
                        break;
                    case "NewYear":
                        intent.putExtra(EXTRA_MESSAGE, "NewYear");
                        context.startActivity(intent);
                        break;
                    case "Valentine":
                        intent.putExtra(EXTRA_MESSAGE, "Valentine");
                        context.startActivity(intent);
                        break;
                    case "WomanDay":
                        intent.putExtra(EXTRA_MESSAGE, "WomanDay");
                        context.startActivity(intent);
                        break;
                    case "MansDay":
                        intent.putExtra(EXTRA_MESSAGE, "MansDay");
                        context.startActivity(intent);
                        break;
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static  class HolidayViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView holidayName;
        TextView textDays;
        TextView textHolidayDescription;
        ImageView imageHoliday;



        public HolidayViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

            holidayName = (TextView) itemView.findViewById(R.id.textDisplayHolidayName);

            textHolidayDescription = (TextView) itemView.findViewById(R.id.textHolidayDescription);

            textDays = (TextView) itemView.findViewById(R.id.textDays);

            imageHoliday = (ImageView) itemView.findViewById(R.id.imageHoliday);
        }
    }
}
