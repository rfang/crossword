package bubbles.crossword;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rachel Fang on 10/18/2015.
 */
public class PuzzleFragment extends Fragment {

    public PuzzleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.puzzle_fragment, container, false);
        return rootView;
    }
}
