package ru.solandme.universal_congratulator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.solandme.universal_congratulator.R;
import ru.solandme.universal_congratulator.adapter.HolidayListAdapter;
import ru.solandme.universal_congratulator.dto.HolidayDTO;

public class HolidaysFragment extends AbstractTabFragment {

    public static final int LAYOUT = R.layout.fragment_holidays;

    public static HolidaysFragment getInstance(Context context){

        Bundle args = new Bundle();
        HolidaysFragment fragment = new HolidaysFragment();
        fragment.setArguments(args);

        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_holidays));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.holidayRecycleView);

        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new HolidayListAdapter(createListHolidayData()));
        return view;
    }

    private List<HolidayDTO> createListHolidayData() {

        List<HolidayDTO> data = new ArrayList<>();


        data.add(new HolidayDTO("NewYear", "Новый Год", R.drawable.ic_december, 0, "Праздник, знаменующий начало года", 31, 12, getDays(31, 12)));
        data.add(new HolidayDTO("Valentine", "День св. Валентина", R.drawable.ic_february, 0, "Праздник всех влюбленных", 14, 2, getDays(14, 2)));
        data.add(new HolidayDTO("WomanDay", "8 марта", R.drawable.ic_8march, 0, "Международный женский день", 8, 3, getDays(8, 3)));
        data.add(new HolidayDTO("MansDay", "23 февраля", R.drawable.ic_23february, 0, "День защитника отечества", 23, 2, getDays(23, 2)));


        return data;

    }


    public void setContext(Context context) {
        this.context = context;
    }

    private String getDays(int day, int month) {
        Calendar todayCalendar = new GregorianCalendar();
        int year = todayCalendar.get(Calendar.YEAR);
        int daysInYear = todayCalendar.getActualMaximum(Calendar.DAY_OF_YEAR); //максимум дней в этом году
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        int days = (int) ((calendar.getTimeInMillis() - todayCalendar.getTimeInMillis()) / 1000) / 86400;

        if (days >= 1) {
            return getResources().getQuantityString(R.plurals.days, days, days);
        } else if (days < -2) {
            return getResources().getQuantityString(R.plurals.days, daysInYear + days, daysInYear + days);
        } else if (days == 0) {
            return " " + getString(R.string.textNow);
        } else {
            return " " + getString(R.string.textFinish);
        }
    }

}
