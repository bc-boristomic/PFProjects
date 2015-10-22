package ba.bitcamp.android.recyclerview;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by boris.tomic on 21/10/15.
 */
public class PersonModel implements Serializable {

    private UUID mId;
    private String mFirstName;
    private String mLastName;
    private Date mDateAdded;

    public PersonModel(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
        mId = UUID.randomUUID();
        mDateAdded = new Date();
    }

    public UUID getUUID() {
        return mId;
    }

    public String getId() {
        return mId.toString().substring(0, 3);
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getDateAdded() {
        return (String) android.text.format.DateFormat.format("EEE, dd MMM yy", mDateAdded);
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    @Override
    public String toString() {
        return mFirstName + " " + mLastName + " id" + mId + " date" + mDateAdded;
    }
}
