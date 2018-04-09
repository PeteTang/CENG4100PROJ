package a4100gp.cubusroute;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float bearing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        LatLng MTR = new LatLng(22.4148789, 114.2104344);
        googleMap.addMarker(new MarkerOptions().position(MTR)
                .title("MTR Station"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(MTR));

        LatLng SHHO = new LatLng(22.417850, 114.210382);
        googleMap.addMarker(new MarkerOptions().position(SHHO)
                .title("SHHO"));

        LatLng SRR = new LatLng(22.419865, 114.207020);
        googleMap.addMarker(new MarkerOptions().position(SRR)
                .title("SRR"));

        LatLng AdminBuilding = new LatLng(22.418818, 114.205284);
        googleMap.addMarker(new MarkerOptions().position(AdminBuilding)
                .title("AdminBuilding"));

        LatLng SHHO_back = new LatLng(22.418033, 114.209908);
        googleMap.addMarker(new MarkerOptions().position(SHHO_back)
                .title("SHHO_back"));
    }
}