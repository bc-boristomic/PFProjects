package ba.bitcamp.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by boris.tomic on 21/10/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
