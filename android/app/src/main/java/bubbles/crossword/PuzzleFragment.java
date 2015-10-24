package bubbles.crossword;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rachel Fang on 10/18/2015.
 */
public class PuzzleFragment extends Fragment {

    private CrosswordData crosswordData;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra(PuzzleListFragment.EXTRA_PUZZLE_ID);
        // TODO: actually get stuff from the web server here
        crosswordData = CrosswordData.buildSampleData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TableLayout tableLayout = (TableLayout) inflater.inflate(R.layout.puzzle_fragment, container, false);
        ArrayList<View> squares = getPuzzleSquares();
        int position = 0;
        for (int i = 0; i < crosswordData.getHeight(); i++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int j = 0; j < crosswordData.getWidth(); j++) {
                tableRow.addView(squares.get(position));
                position ++;
            }
            tableLayout.addView(tableRow);
        }
        return tableLayout;
    }

    private ArrayList<View> getPuzzleSquares() {
        char[] fill = crosswordData.getFill().toCharArray();
        char[] solution = crosswordData.getSolution().toCharArray();
        char[] directions = crosswordData.getDirections();

        ArrayList<View> views = new ArrayList<View>();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        int numberCount = 1;
        int size = crosswordData.getHeight() * crosswordData.getWidth();
        for (int i = 0; i < size; i++) {
            View view = inflater.inflate(R.layout.puzzle_grid_square, null, false);
            TextView number = (TextView) view.findViewById(R.id.square_number);
            TextView letter = (TextView) view.findViewById(R.id.square_letter);

            if (fill[i] == '.') {
                letter.setBackgroundColor(Color.BLACK);
            } else {
                letter.setBackgroundColor(Color.WHITE);
            }

            if (directions[i] != '.' && directions[i] != '-') {
                number.setText(""+numberCount);
                numberCount++;
                number.setVisibility(View.VISIBLE);
            }

            //TODO: stop showing solution once we have actual fill data
            letter.setText(""+solution[i]);

            views.add(view);
        }

        return views;
    }
}
