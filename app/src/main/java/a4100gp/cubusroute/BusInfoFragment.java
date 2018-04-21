package a4100gp.cubusroute;


import android.app.ListFragment;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusInfoFragment extends ListFragment {

    private Integer[] imgid={
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4
    };
    public BusInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Integer[] infoitem = {};
        View view = inflater.inflate(R.layout.fragment_bus_info,container,false);

        ImageView imageView = (ImageView) view.findViewById(R.id.info);


        return inflater.inflate(R.layout.fragment_bus_info, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

}
