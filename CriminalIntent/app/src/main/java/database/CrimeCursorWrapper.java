package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import ba.bitcamp.android.criminalintent.Crime;

import static database.CrimeDbSchema.*;

/**
 * Created by boris.tomic on 29/10/15.
 */
public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String title = getString(getColumnIndex(Cols.TITLE));
        long date = getLong(getColumnIndex(Cols.DATE));
        int isSolved = getInt(getColumnIndex(Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        return crime;
    }
}
