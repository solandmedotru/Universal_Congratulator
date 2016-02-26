package ru.solandme.universal_congratulator;

public class Holiday {
    public static final int UNIVERSAL = 0;
    public static final int FOR_HIM = 1;
    public static final int FOR_HER = 2;

    int _id;
    String holidayName;
    String[] congratulates;
    int pictureId;
    int filter;
    String description;
    int day;
    int month;
    long daysToHoliday;


    public Holiday() {
    }

    public Holiday(String holidayName, int day, int month, int pictureId, int filter, String description) {
        this.holidayName = holidayName;
        this.day = day;
        this.month = month;
        this.pictureId = pictureId;
        this.filter = filter;
        this.description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public long getDaysToHoliday() {
        return daysToHoliday;
    }

    public void setDaysToHoliday(long daysToHoliday) {
        this.daysToHoliday = daysToHoliday;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String[] getCongratulates() {
        return congratulates;
    }

    public void setCongratulates(String[] congratulates) {
        this.congratulates = congratulates;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}

