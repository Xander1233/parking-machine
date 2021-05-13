public class Time {
    private int Minute;

    private int Hour;

    Time(int Minute, int Hour) {

        this.Hour = Hour;

        this.Minute = formatMinutes(Minute);
    }

    private int formatMinutes(int minutes) {
        if (minutes / 60 == 0) return minutes;
        this.Hour += minutes / 60;
        return minutes % 60;
    }

    public int getHour() {
        return this.Hour;
    }

    public int getMinute() {
        return this.Minute;
    }

    @Override
    public String toString() {
        return this.Hour + ":" + this.Minute;
    }
}
