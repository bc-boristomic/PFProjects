package ba.bitcamp.android.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class ScheduleActivity extends AppCompatActivity {

    public static final String KEY = "data";

    private Button mSeeSchedule;
    private NumberPicker mNumberPicker;
    private int mSelectedDay;

    private Schedule monday = new Schedule(R.string.day_one);
    private Schedule tuesday = new Schedule(R.string.day_two);
    private Schedule wednesday = new Schedule(R.string.day_three);
    private Schedule thursday = new Schedule(R.string.day_four);
    private Schedule friday = new Schedule(R.string.day_five);
    private Schedule saturday = new Schedule(R.string.day_six);
    private Schedule sunday = new Schedule(R.string.day_seven);


    private int getScheduleForSelectedDay(int selectedDay) {
        if(selectedDay == 1) {
            return monday.getSchedule();
        } else if(selectedDay == 2) {
            return tuesday.getSchedule();
        } else if(selectedDay == 3) {
            return wednesday.getSchedule();
        } else if(selectedDay == 4) {
            return thursday.getSchedule();
        } else if(selectedDay == 5) {
            return friday.getSchedule();
        } else if(selectedDay == 6) {
            return  saturday.getSchedule();
        }
        return sunday.getSchedule();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mNumberPicker = (NumberPicker) findViewById(R.id.number_pick);
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(7);


        mSeeSchedule = (Button) findViewById(R.id.schedule);
        mSeeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedDay = mNumberPicker.getValue();
                Intent i = new Intent(ScheduleActivity.this, ScheduleResult.class);
                i.putExtra(KEY, getScheduleForSelectedDay(mSelectedDay));
                startActivity(i);
            }
        });

    }
}
