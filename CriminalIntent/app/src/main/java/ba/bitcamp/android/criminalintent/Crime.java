package ba.bitcamp.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by boris.tomic on 14/10/15.
 */
public class Crime {

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mID = id;
        mDate = new Date();
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

//    public Date getDate() {
//        return mDate;
//    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getDate() {
        return (String)android.text.format.DateFormat.format("EEE, dd MMMM yyyy", mDate);
    }

    public Date getRealDate() {
        return mDate;
    }

}