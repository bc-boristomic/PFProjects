package ba.bitcamp.android.imagequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageQuiz extends AppCompatActivity {

    private static final String TAG = "Activity logged";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
    private ImageView mQuestionImageView;

    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_one, R.drawable.dog, true),
            new Question(R.string.question_two, R.drawable.bird, false),
            new Question(R.string.question_three, R.drawable.elephant, true),
            new Question(R.string.question_four, R.drawable.bear, true),
            new Question(R.string.question_five, R.drawable.cat, false)
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestions[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        int image = mQuestions[mCurrentIndex].getImageResId();
        mQuestionImageView.setImageResource(image);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerTrue = mQuestions[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(userPressedTrue == answerTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "app created");
        setContentView(R.layout.activity_image_quiz);

        mQuestionImageView = (ImageView) findViewById(R.id.image_view);
        mQuestionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex -= 1;
                if (mCurrentIndex < 0) {
                    mCurrentIndex = mQuestions.length - 1;
                }
                updateQuestion();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }


}
