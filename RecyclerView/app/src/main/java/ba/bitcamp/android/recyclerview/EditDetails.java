package ba.bitcamp.android.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class EditDetails extends AppCompatActivity {

    private UUID value;
    private EditText mPersonFirstName;
    private EditText mPersonLastName;
    private Button mSavePersonDetails;
    private PersonModel person;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        mPersonFirstName = (EditText) findViewById(R.id.editTextFirstName);
        mPersonLastName = (EditText) findViewById(R.id.editTextLastName);
        mSavePersonDetails = (Button) findViewById(R.id.save_button);

        person = (PersonModel) getIntent().getExtras().getSerializable(MainActivity.KEY_INTENT);
        Persons list = Persons.get();
        position = list.findPosition(person);

        mPersonFirstName.setText(person.getFirstName());
        mPersonLastName.setText(person.getLastName());

        mSavePersonDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Persons.get().getPersons().get(position).setFirstName(mPersonFirstName.getText().toString());
                Persons.get().getPersons().get(position).setLastName(mPersonLastName.getText().toString());
            }
        });




    }
}
