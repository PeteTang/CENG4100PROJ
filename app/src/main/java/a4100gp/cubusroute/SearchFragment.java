package a4100gp.cubusroute;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcatalan.materialsearchview.MaterialSearchView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_search);


        Toolbar searchbar = (Toolbar)view.findViewById(R.id.searchbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(searchbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Search the Bus Route");
        searchbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        MaterialSearchView searchView = (MaterialSearchView)view.findViewById(R.id.search_view);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_items, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

}
