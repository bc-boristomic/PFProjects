package ba.bitcamp.android.imagequiz;

/**
 * Created by boris.tomic on 08/10/15.
 */
public class Question {

    private int mTextResId;
    private int mImageResId;
    private boolean mAnswerTrue;

    public Question(int textResId, int imageResId, boolean answer) {
        mTextResId = textResId;
        mImageResId = imageResId;
        mAnswerTrue = answer;
    }

    public void setTextResId(int textResId) {
        this.mTextResId = textResId;
    }

    public void setAnswer(boolean answer) {
        this.mAnswerTrue = answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public void setImageResId(int imageResId) {
        this.mImageResId = imageResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

}
