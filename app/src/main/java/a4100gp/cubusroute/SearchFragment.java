//package a4100gp.cubusroute;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.ListFragment;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.miguelcatalan.materialsearchview.MaterialSearchView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class SearchFragment extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
//    List<String> mAllValues;
//    private ArrayAdapter<String> mAdapter;
//    private Context mContext;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getActivity();
//        setHasOptionsMenu(true);
//        populateList();
//    }
//
//    @Override
//    public void onListItemClick(ListView listView, View v, int position, long id) {
//        String item = (String) listView.getAdapter().getItem(position);
//        if (getActivity() instanceof OnItem1SelectedListener) {
//            ((OnItem1SelectedListener) getActivity()).OnItem1SelectedListener(item);
//        }
//        getFragmentManager().popBackStack();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View layout = inflater.inflate(R.layout.fragment_search, container, false);
//        ListView listView = (ListView) layout.findViewById(android.R.id.list);
//        TextView emptyTextView = (TextView) layout.findViewById(android.R.id.empty);
//        listView.setEmptyView(emptyTextView);
//        return layout;
//
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//        searchView.setQueryHint("Search");
//        super.onCreateOptionsMenu(menu, inflater);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return true;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        if (newText == null || newText.trim().isEmpty()) {
//            resetSearch();
//            return false;
//        }
//
//        List<String> filteredValues = new ArrayList<String>(mAllValues);
//        for (String value : mAllValues) {
//            if (!value.toLowerCase().contains(newText.toLowerCase())) {
//                filteredValues.remove(value);
//            }
//        }
//        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, filteredValues);
//        setListAdapter(mAdapter);
//        return false;
//    }
//
//
//    public void resetSearch() {
//        mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mAllValues);
//        setListAdapter(mAdapter);
//    }
//
//    @Override
//    public boolean onMenuItemActionExpand(MenuItem item) {
//        return true;
//    }
//
//    @Override
//    public boolean onMenuItemActionCollapse(MenuItem item) {
//        return true;
//    }
//
//    public interface OnItem1SelectedListener {
//        void OnItem1SelectedListener(String item);
//    }
//
//    private void populateList() {
//        mAllValues = new ArrayList<>();
//        mAllValues.add("1A");
//        mAllValues.add("1B");
//        mAllValues.add("2");
//        mAllValues.add("3");
//        mAllValues.add("4");
//        mAllValues.add("5");
//        mAllValues.add("6");
//        mAllValues.add("7");
//        mAllValues.add("8");
//    }
//}
////    MaterialSearchView searchView;
////
////    public SearchFragment() {
////        // Required empty public constructor
////    }
////
////
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////
////        View view = inflater.inflate(R.layout.fragment_search, container, false);
////        super.onCreate(savedInstanceState);
////        //setContentView(R.layout.fragment_search);
////        Toolbar searchbar = (Toolbar)view.findViewById(R.id.searchbar);
////        ((AppCompatActivity)getActivity()).setSupportActionBar(searchbar);
////        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Search the Bus Route");
////        searchbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
////
////        searchView = view.findViewById(R.id.search_view);
////        setHasOptionsMenu(true);
////        return view;
////    }
////
////    @Override
////    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        inflater.inflate(R.menu.menu_items, menu);
////        super.onCreateOptionsMenu(menu,inflater);
////        //MeunItem item = menu.findItem(R.id.action_search);
////    }
//
//
