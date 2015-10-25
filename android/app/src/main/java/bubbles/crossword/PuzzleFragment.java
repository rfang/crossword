package bubbles.crossword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

/**
 * Created by Rachel Fang on 10/18/2015.
 */
public class PuzzleFragment extends Fragment {

    private CrosswordData crosswordData;
    private String id;
    private ArrayList<PuzzleGridSquare> squares;

    // Highlight tracker;
    private boolean highlightAcross;
    ArrayList<Integer> highlightedSquares;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra(PuzzleListFragment.EXTRA_PUZZLE_ID);
        // TODO: actually get stuff from the web server here
        crosswordData = CrosswordData.buildSampleData();
        squares = crosswordData.getPuzzleSquares(getActivity());
        highlightAcross = true;
        highlightedSquares = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TableLayout tableLayout = (TableLayout) inflater.inflate(R.layout.puzzle_fragment, container, false);
        int position = 0;
        for (int i = 0; i < crosswordData.getHeight(); i++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int j = 0; j < crosswordData.getWidth(); j++) {
                final int squarePosition = position;
                PuzzleGridSquare square = squares.get(position);
                square.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSquareClicked(squarePosition);
                    }
                });
                tableRow.addView(square);
                position ++;
            }
            tableLayout.addView(tableRow);
        }
        return tableLayout;
    }

    public void onSquareClicked(int position) {
        PuzzleGridSquare square = squares.get(position);
        if (square.isBlack()) {
            return;
        }

        switch (square.highlight()) {
            case PuzzleGridSquare.HIGHLIGHT_CHAR:
                // Switch highlighting direction
                highlightAcross = !highlightAcross;
                highlightWordAtPosition(position);
                break;
            case PuzzleGridSquare.HIGHLIGHT_NONE:
                highlightWordAtPosition(position);
                break;
            case PuzzleGridSquare.HIGHLIGHT_WORD:
                highlightSameWordAtPosition(position);
                break;
        }
    }

    private void highlightWordAtPosition(int position) {
        // clear existing coloring
        for (int i: highlightedSquares) {
            squares.get(i).setHighlight(PuzzleGridSquare.HIGHLIGHT_NONE);
        }

        // put new coloring in
        highlightedSquares.clear();
        squares.get(position).setHighlight(PuzzleGridSquare.HIGHLIGHT_CHAR);
        highlightedSquares.add(position);
        int temp;
        int width = crosswordData.getWidth();
        if (highlightAcross) {
            // go left
            temp = position - 1;
            while (temp >= 0 && ((temp + 1) % width) != 0 && !squares.get(temp).isBlack()) {
                squares.get(temp).setHighlight(PuzzleGridSquare.HIGHLIGHT_WORD);
                highlightedSquares.add(temp);
                temp--;
            }

            // go right
            temp = position + 1;
            while (temp < squares.size() && ((temp + 1) % width) != 1 && !squares.get(temp).isBlack()) {
                squares.get(temp).setHighlight(PuzzleGridSquare.HIGHLIGHT_WORD);
                highlightedSquares.add(temp);
                temp++;
            }
        } else {
            // go up
            temp = position - width;
            while (temp >= 0 && !squares.get(temp).isBlack()) {
                squares.get(temp).setHighlight(PuzzleGridSquare.HIGHLIGHT_WORD);
                highlightedSquares.add(temp);
                temp -= width;
            }

            // go down
            temp = position + crosswordData.getWidth();
            while (temp < squares.size() && !squares.get(temp).isBlack()) {
                squares.get(temp).setHighlight(PuzzleGridSquare.HIGHLIGHT_WORD);
                highlightedSquares.add(temp);
                temp += width;
            }

        }
    }

    private void highlightSameWordAtPosition(int position) {
        for (int i : highlightedSquares) {
            if (squares.get(i).highlight() == PuzzleGridSquare.HIGHLIGHT_CHAR) {
                squares.get(i).setHighlight(PuzzleGridSquare.HIGHLIGHT_WORD);
            }
        }

        squares.get(position).setHighlight(PuzzleGridSquare.HIGHLIGHT_CHAR);
    }
}
