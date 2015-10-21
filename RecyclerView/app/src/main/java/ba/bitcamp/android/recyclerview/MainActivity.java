package ba.bitcamp.android.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mFirstNameText;
    private EditText mLastNameText;
    private RecyclerView mRecyclerView;
    private Button mAddButton;

    private List<PersonModel> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNameText = (EditText) findViewById(R.id.editTextFirstName);
        mLastNameText = (EditText) findViewById(R.id.editTextLastName);
        mAddButton = (Button) findViewById(R.id.add_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonModel person = new PersonModel(mFirstNameText.getText().toString(), mLastNameText.getText().toString());
                persons.add(person);
            }
        });
    }

    private class PersonHolder extends RecyclerView.ViewHolder {

        private TextView firstNameView;
        private TextView lastNameView;
        private TextView dateView;
        private TextView idView;

        public PersonHolder(View personView) {
            super(personView);

            firstNameView = (TextView)
        }


    }


}
