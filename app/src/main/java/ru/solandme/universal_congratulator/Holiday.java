package ru.solandme.universal_congratulator;

public class Holiday {
    int _id;
    String holidayName;
    String[] congratulates;
    int sex;

    public static final int UNIVERSAL = 0;
    public static final int FOR_HIM = 1;
    public static final int FOR_HER = 2;

    public Holiday() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}