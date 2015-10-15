package ba.bitcamp.android.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mTextEditField;
    private Button mAddButton;
    private LinearLayout mCheckboxLayout;
    private static int counter = 0;
    private int message = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextEditField = (EditText) findViewById(R.id.editText);
        mAddButton = (Button) findViewById(R.id.button);
        mCheckboxLayout = (LinearLayout) findViewById(R.id.checkbox_layout);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox cb = new CheckBox(MainActivity.this);

                if (counter < 5) {
                    cb.setText(mTextEditField.getText());
                    mCheckboxLayout.addView(cb);
                } else {
                    message = R.string.max_task;
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                counter++;
            }
        });
    }
}
