package ru.solandme.universal_congratulator.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import ru.solandme.universal_congratulator.fragment.AbstractTabFragment;
import ru.solandme.universal_congratulator.fragment.BirthdaysFragment;
import ru.solandme.universal_congratulator.fragment.HolidaysFragment;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;

        initTabsMap(context);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        //tabs.put(0, NotificationFragment.getInstance(context));
        tabs.put(0, HolidaysFragment.getInstance(context));
        tabs.put(1, BirthdaysFragment.getInstance(context));
    }
}
