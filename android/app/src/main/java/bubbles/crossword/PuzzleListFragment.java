package bubbles.crossword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rachel Fang on 10/17/2015.
 */
public class PuzzleListFragment extends Fragment{

    private static String EXTRA_PUZZLE_ID = "extra_puzzle_id";
    //TODO: unFake this.
    private static String FAKE_PUZZLE_ID = "puzzle_one";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.puzzle_list_fragment, container, false);
        View puzzleOne = view.findViewById(R.id.puzzle_one);
        puzzleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PuzzleActivity.class);
                intent.putExtra(EXTRA_PUZZLE_ID, FAKE_PUZZLE_ID);
                startActivity(intent);
            }
        });

        return view;
    }
}
