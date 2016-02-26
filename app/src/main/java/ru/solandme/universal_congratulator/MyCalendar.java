package ru.solandme.universal_congratulator;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

class MyCalendar {
    public String name;
    public String id;

    public MyCalendar(String _name, String _id) {
        name = _name;
        id = _id;
    }

//    @Override
//    public String toString() {
//        return name;
//    }
//
//    private MyCalendar m_calendars[];
//
//    private void getCalendars() {
//        String[] l_projection = new String[]{"_id", "displayName"};
//        Uri l_calendars;
//        if (Build.VERSION.SDK_INT >= 8) {
//            l_calendars = Uri.parse("content://com.android.calendar/calendars");
//        } else {
//            l_calendars = Uri.parse("content://calendar/calendars");
//        }
//        Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, null, null, null);    //all calendars
//        //Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, "selected=1", null, null);   //active calendars
//        if (l_managedCursor.moveToFirst()) {
//            m_calendars = new MyCalendar[l_managedCursor.getCount()];
//            String l_calName;
//            String l_calId;
//            int l_cnt = 0;
//            int l_nameCol = l_managedCursor.getColumnIndex(l_projection[1]);
//            int l_idCol = l_managedCursor.getColumnIndex(l_projection[0]);
//            do {
//                l_calName = l_managedCursor.getString(l_nameCol);
//                l_calId = l_managedCursor.getString(l_idCol);
//                m_calendars[l_cnt] = new MyCalendar(l_calName, l_calId);
//                ++l_cnt;
//            } while (l_managedCursor.moveToNext());
//        }
//    }
}