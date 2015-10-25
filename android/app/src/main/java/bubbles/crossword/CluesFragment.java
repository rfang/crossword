package bubbles.crossword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Rachel Fang on 10/24/2015.
 */
public class CluesFragment extends Fragment {

    private String id;
    private CrosswordData crosswordData;
    private ListAdapter adapter;

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
        ListView listView = (ListView) inflater.inflate(R.layout.clues_fragment, container, false);
        listView.setAdapter(new CluesAdapter(getActivity(), crosswordData));
        return listView;
    }
}
