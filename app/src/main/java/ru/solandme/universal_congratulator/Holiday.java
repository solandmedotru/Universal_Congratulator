package ru.solandme.universal_congratulator;

public class Holiday {
    public static final int UNIVERSAL = 0;
    public static final int FOR_HIM = 1;
    public static final int FOR_HER = 2;

    private String key;
    private String displayHolidayName;
    private String[] congratulates;
    private int pictureId;
    private int filter;
    private String description;
    private int day;
    private int month;
    private String daysToHoliday;


    public Holiday() {
    }

    public Holiday(String key, String displayHolidayName, int pictureId, int filter, String description) {
        this.key = key;
        this.displayHolidayName = displayHolidayName;
        this.pictureId = pictureId;
        this.filter = filter;
        this.description = description;
    }

    public Holiday(String key, String displayHolidayName, int pictureId, int filter, String description, int day, int month, String daysToHoliday) {
        this.key = key;
        this.displayHolidayName = displayHolidayName;
        this.pictureId = pictureId;
        this.filter = filter;
        this.description = description;
        this.day = day;
        this.month = month;
        this.daysToHoliday = daysToHoliday;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDisplayHolidayName() {
        return displayHolidayName;
    }

    public void setDisplayHolidayName(String displayHolidayName) {
        this.displayHolidayName = displayHolidayName;
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

    public String getDaysToHoliday() {
        return daysToHoliday;
    }

    public void setDaysToHoliday(String daysToHoliday) {
        this.daysToHoliday = daysToHoliday;
    }
}

