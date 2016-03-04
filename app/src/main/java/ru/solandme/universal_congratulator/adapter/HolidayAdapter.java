package ru.solandme.universal_congratulator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.solandme.universal_congratulator.Holiday;
import ru.solandme.universal_congratulator.R;

public class HolidayAdapter extends BaseAdapter {

    private List<Holiday> holidayList;
    private LayoutInflater layoutInflater;

    public HolidayAdapter(Context context, List<Holiday> holidayList) {
        this.holidayList = holidayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return holidayList.size();
    }

    @Override
    public Object getItem(int position) {
        return holidayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.holiday_item_layout, parent, false);
        }

        Holiday holiday = getHoliday(position);

        TextView textView = (TextView) view.findViewById(R.id.textDisplayHolidayName);
        TextView textDays = (TextView) view.findViewById(R.id.textDays);
        ImageView imageHoliday = (ImageView) view.findViewById(R.id.imageHoliday);

        textView.setText(holiday.getDescription());

        if (holiday.getKey().equals("Birthday")){
            textDays.setVisibility(View.GONE);
        } else {
            textDays.setVisibility(View.VISIBLE);
            textDays.setText(holiday.getDaysToHoliday());
        }
        imageHoliday.setImageResource(holiday.getPictureId());

        return view;
    }


    private Holiday getHoliday(int position){

        return (Holiday) getItem(position);

    }
}
