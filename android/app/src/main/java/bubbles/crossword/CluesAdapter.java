package bubbles.crossword;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rachel Fang on 10/24/2015.
 */
public class CluesAdapter extends BaseAdapter {

    Context context;
    ArrayList<Clue> clues;

    public CluesAdapter(Context context, CrosswordData crosswordData) {
       this.context = context;
       setCrosswordData(crosswordData);
    }

    @Override
    public int getCount() {
        return clues.size();
    }

    @Override
    public Object getItem(int position) {
        return clues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;

        if (convertView != null) {
            view = (TextView) convertView;
        } else {
            view = (TextView) LayoutInflater.from(context)
                    .inflate(R.layout.clue_listitem, parent, false);
        }

        // bind data
        Clue clue = clues.get(position);
        if (clue.isLabel) {
            view.setText(clue.clueText);
            view.setTypeface(null, Typeface.BOLD);
        } else {
            view.setText(clue.displayNumber + ". " + clue.clueText);
            view.setTypeface(null, Typeface.NORMAL);
        }

        //general UI stuff because I don't wanna see any more layout files
        view.setPadding(16, 16, 16, 16);
        view.setTextSize(16);

        return view;
    }

    public void setCrosswordData(CrosswordData crosswordData) {
        clues = new ArrayList<>();
        char[] directions = crosswordData.getDirections();
        ArrayList<String> clueTexts = crosswordData.getClues();
        int clueTextIndex = 0;
        int clueNumber = 1;

        // setup across clues
        clues.add(new Clue("Across"));
        for (int i = 0; i < directions.length; i++) {
            char d = directions[i];
            if (d == 'b' || d == 'a') {
                clues.add(new Clue(clueNumber, true, clueTexts.get(clueTextIndex), i));
                clueNumber++;
                clueTextIndex++;
            } else if (d == 'd') {
                clueNumber++;
            }
        }

        // setup down clues
        clues.add(new Clue("Down"));
        clueNumber = 0;
        for (int i = 0; i < directions.length; i++) {
            char d = directions[i];
            if (d == 'b' || d == 'd') {
                clues.add(new Clue(clueNumber, false, clueTexts.get(clueTextIndex), i));
                clueNumber++;
                clueTextIndex++;
            } else if (d == 'a') {
                clueNumber++;
            }
        }
    }

    private class Clue {
        public int displayNumber;
        public boolean isAccrossClue;
        public String clueText;
        public int squareNumber;
        public boolean isLabel;

        public Clue(int d, boolean a, String t, int s) {
            displayNumber = d;
            isAccrossClue = a;
            clueText = t;
            squareNumber = s;
            isLabel = false;
        }

        public Clue(String label) {
            clueText = label;
            isLabel = true;
        }
    }
}
