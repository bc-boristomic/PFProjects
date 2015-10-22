package ba.bitcamp.android.recyclerview;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "Saved list of persons";

    private EditText mFirstNameText;
    private EditText mLastNameText;
    private Button mAddButton;
    private RecyclerView mRecyclerView;
    private PersonAdapter mPersonAdapter;
    private Persons persons = Persons.get();

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
                String name = mFirstNameText.getText().toString();
                String surname = mLastNameText.getText().toString();
                if (name.length() < 2 || surname.length() < 2) {
                    Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                } else {
                    PersonModel person = new PersonModel(name, surname);
                    mFirstNameText.setText("");
                    mFirstNameText.requestFocus();
                    mLastNameText.setText("");
                    persons.addPerson(person);
                    mPersonAdapter.notifyDataSetChanged();
                }
            }
        });

        if (savedInstanceState != null) {
            persons = savedInstanceState.getParcelable(KEY_INDEX);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_layout_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        updateUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(KEY_INDEX, persons);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            persons = savedInstanceState.getParcelable(KEY_INDEX);
        }
    }

    public void updateUI() {
        List<PersonModel> personsList = persons.getPersons();
        mPersonAdapter = new PersonAdapter(personsList);
        mRecyclerView.setAdapter(mPersonAdapter);
    }

    private class PersonHolder extends RecyclerView.ViewHolder {

        private PersonModel mPerson;

        private TextView firstNameView;
        private TextView lastNameView;
        private TextView dateView;
        private TextView idView;
        private Button removePersonButton;

        public PersonHolder(View personView) {
            super(personView);

            idView = (TextView) personView.findViewById(R.id.person_id);
            firstNameView = (TextView) personView.findViewById(R.id.person_firstName);
            lastNameView = (TextView) personView.findViewById(R.id.person_lastName);
            dateView = (TextView) personView.findViewById(R.id.person_date);
            removePersonButton = (Button) personView.findViewById(R.id.remove_person);
            removePersonButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    persons.removePerson(mPerson);
                    updateUI();
                }
            });
        }

        public void bindPerson(PersonModel person) {
            mPerson = person;
            idView.setText(person.getId());
            firstNameView.setText(person.getFirstName());
            lastNameView.setText(person.getLastName());
            dateView.setText(person.getDateAdded());
        }
    }

    private class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {

        private List<PersonModel> mPersons;

        public PersonAdapter(List<PersonModel> persons) {
            mPersons = persons;
        }

        @Override
        public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.person_layout, parent, false);
            return new PersonHolder(view);
        }

        @Override
        public void onBindViewHolder(PersonHolder holder, int position) {
            PersonModel person = mPersons.get(position);
            holder.bindPerson(person);
        }

        @Override
        public int getItemCount() {
            return mPersons.size();
        }

    }


}
