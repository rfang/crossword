package bubbles.crossword;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Rachel Fang on 10/25/2015.
 */
public class PuzzleGridSquare extends FrameLayout {
    public static final int HIGHLIGHT_NONE = 0xFFFFFFFF;
    public static final int HIGHLIGHT_WORD = 0xFFCDD200;
    public static final int HIGHLIGHT_CHAR = 0xEF9A9A00;

    private TextView squareLetter;
    private TextView squareNumber;
    private int highlight;
    private boolean isBlack;

    public PuzzleGridSquare(Context context) {
        super(context);
    }

    public PuzzleGridSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PuzzleGridSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        inflate(getContext(), R.layout.puzzle_grid_square, this);
        squareLetter = (TextView) findViewById(R.id.square_letter);
        squareNumber = (TextView) findViewById(R.id.square_number);
        highlight = HIGHLIGHT_NONE;
        isBlack = false;
    }

    public int highlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
        setBackgroundColor(highlight);
    }

    public void setSquareNumber(String text) {
        if (text != null) {
            squareNumber.setText(text);
            squareNumber.setVisibility(View.VISIBLE);
        } else {
            squareNumber.setVisibility(View.GONE);
        }
    }

    public void setSquareLetter(String text) {
        squareLetter.setText(text);
    }

     public void setIsBlack() {
         isBlack = true;
         setBackgroundColor(Color.BLACK);
     }

    public boolean isBlack() {
        return isBlack;
    }
}
