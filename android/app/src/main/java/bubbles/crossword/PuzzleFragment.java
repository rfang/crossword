package bubbles.crossword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View rootView = inflater.inflate(R.layout.puzzle_fragment, container, false);
        return rootView;
    }
}
