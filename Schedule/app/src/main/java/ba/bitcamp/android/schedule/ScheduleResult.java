package ba.bitcamp.android.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScheduleResult extends AppCompatActivity {

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_result);

        mResult = (TextView) findViewById(R.id.schedule_result);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra(ScheduleActivity.KEY, 0);
        mResult.setText(intValue);

    }
}
