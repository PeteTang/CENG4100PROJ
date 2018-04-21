package a4100gp.cubusroute;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pui on 4/10/2018.
 */

public class CustomListAdapter extends ArrayAdapter<String> {
    //private final Activity context2;
    Context context;
    String[] busname;
    String[] location;

    public CustomListAdapter(Context context,  String[] busname,String[] location) {
        super(context, R.layout.mylist, busname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.busname = busname;
        this.location = location;

    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);

        txtTitle.setText(busname[position]);

        //extratxt.setText("Description "+itemname[position]);
        return rowView;

    }
}


//}