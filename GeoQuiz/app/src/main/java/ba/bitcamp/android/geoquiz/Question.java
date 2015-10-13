package ba.bitcamp.android.geoquiz;

/**
 * Created by boris.tomic on 08/10/15.
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public void setTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public void setAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
}
