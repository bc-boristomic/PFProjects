package ba.bitcamp.android.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boris on 10/21/15.
 */
public class Persons implements Parcelable {

    private static Persons sPersons;
    private List<PersonModel> mPersonList;
    private int mData;

    private Persons() {
        mPersonList = new ArrayList<>();
    }

    public void addPerson(PersonModel person) {
        mPersonList.add(person);
    }

    public List<PersonModel> getPersons() {
        return mPersonList;
    }

    public void removePerson(PersonModel person) {
        mPersonList.remove(person);
    }

    public static Persons get() {
        if (sPersons == null) {
            return new Persons();
        }
        return sPersons;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
    }

    public static final Parcelable.Creator<Persons> CREATOR = new Parcelable.Creator<Persons>() {

        public Persons createFromParcel(Parcel in) {
            return new Persons(in);
        }

        public Persons[] newArray(int size) {
            return new Persons[size];
        }
    };

    private Persons(Parcel in) {
        mData = in.readInt();
    }
}
