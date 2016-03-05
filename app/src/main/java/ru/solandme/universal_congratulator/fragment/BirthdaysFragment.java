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

public class BirthdaysFragment extends AbstractTabFragment {

    public static final int LAYOUT = R.layout.fragment_birthdays;

    public static BirthdaysFragment getInstance(Context context){

        Bundle args = new Bundle();
        BirthdaysFragment fragment = new BirthdaysFragment();
        fragment.setArguments(args);

        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_birthdays));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.birthdayRecycleView);

        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new HolidayListAdapter(createListHolidayData()));
        return view;
    }

    private List<HolidayDTO> createListHolidayData() {

        List<HolidayDTO> data = new ArrayList<>();

        data.add(new HolidayDTO("Birthday", "День рождения", R.drawable.ic_birthday, 0, "Пожелания на день рождения"));

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
