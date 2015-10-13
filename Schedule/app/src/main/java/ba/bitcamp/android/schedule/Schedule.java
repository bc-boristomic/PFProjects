package ba.bitcamp.android.schedule;

/**
 * Created by boris.tomic on 13/10/15.
 */
public class Schedule {

    private int mResId;

    public Schedule(int resId) {
        this.mResId = resId;
    }

    public void setSchedule(int resId) {
        this.mResId = resId;
    }

    public int getSchedule() {
        return mResId;
    }
}
