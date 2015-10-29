//package ba.bitcamp.android.criminalintent;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.TimePicker;
//
//import java.sql.Time;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
///**
// * Created by boris.tomic on 26/10/15.
// */
//public class TimePickerFragment extends DialogFragment {
//
//    public static final String EXTRA_TIME = "ba.bitcamp.android.criminalintent.time";
//    private static final String ARG_TIME = "time";
//    private TimePicker mTimePicker;
//
//    public static TimePickerFragment newInstance(Date time) {
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_TIME, time);
//        TimePickerFragment fragment = new TimePickerFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Date date = (Date) getArguments().getSerializable(ARG_TIME);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int hour = calendar.get(Calendar.HOUR);
//        int minute = calendar.get(Calendar.MINUTE);
//
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
//
//        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_time_picker);
//        mTimePicker.setHour(hour);
//        mTimePicker.setMinute(minute);
//
//        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.time_picker_title)
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int hour = mTimePicker.getHour();
//                        int minute = mTimePicker.getMinute();
//
//                        Date date = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hour, minute).getTime();
//                        sendResult(Activity.RESULT_OK, date);
//                    }
//                }).create();
//    }
//
//    private void sendResult(int resultCode, Date date) {
//        if (getTargetFragment() == null) {
//            return;
//        }
//        Intent intent = new Intent();
//        intent.putExtra(EXTRA_TIME, date);
//        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
//    }
//
//
//
//}
