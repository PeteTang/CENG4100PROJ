package a4100gp.cubusroute;

        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.support.annotation.NonNull;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.widget.Toast;

        import com.google.android.gms.location.FusedLocationProviderClient;
        import com.google.android.gms.location.LocationServices;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.LatLngBounds;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.PolylineOptions;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.maps.DirectionsApi;
        import com.google.maps.GeoApiContext;
        import com.google.maps.android.PolyUtil;
        import com.google.maps.model.DirectionsResult;
        import com.google.maps.model.TravelMode;

        import java.util.List;
        import java.util.concurrent.TimeUnit;

public class MapFragment extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final int DEFAULT_ZOOM = 15;
    private LatLng destination;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String TAG = MapFragment.class.getSimpleName();
    private Location mLastKnownLocation;
    private boolean mLocationPermissionGranted;
    private int PADDING = 150;
    private final LatLng mDefaultLocation = new LatLng(22.4148789, 114.2104344);

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // in case we might need to receive
//        Intent intent=getIntent();
        mLocationPermissionGranted = false;
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //destination = new LatLng(22.4148789, 114.2104344);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.4148789, 114.2104344), 15));
        LatLng MTR = new LatLng(22.4148789, 114.2104344);

        mMap.addMarker(new MarkerOptions().position(MTR)
                .title("MTR Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MTR));

        LatLng SHHO = new LatLng(22.417850, 114.210382);
        mMap.addMarker(new MarkerOptions().position(SHHO)
                .title("SHHO"));

        LatLng SRR = new LatLng(22.419865, 114.207020);
        mMap.addMarker(new MarkerOptions().position(SRR)
                .title("SRR"));

        LatLng AdminBuilding = new LatLng(22.418818, 114.205284);
        mMap.addMarker(new MarkerOptions().position(AdminBuilding)
                .title("AdminBuilding"));

        LatLng SHHO_back = new LatLng(22.418033, 114.209908);
        mMap.addMarker(new MarkerOptions().position(SHHO_back)
                .title("SHHO_back"));


    }

    //-----------------------------------below is the code i have written before----------------------------------//
    //fix later
//    public void onBackPressed() {
//
//                moveTaskToBack(true);  //If view is in News fragment, exit application
//            }
//
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        Log.d("DEBUG", "HELLOIMHERE");
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    //AIzaSyC2AXWrlitYh3RpL-vX1ULgyHcA-A5_5pw
    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3)
                .setApiKey("AIzaSyC2AXWrlitYh3RpL-vX1ULgyHcA-A5_5pw")
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override

                    public void onComplete(@NonNull Task<Location> task) {
                        Log.d("DEBUG", "HEYIMHERE");
                        if (task.isSuccessful()) {

                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation != null) {
                                double latitude = mLastKnownLocation.getLatitude();
                                double longitude = mLastKnownLocation.getLongitude();

                                try {

                                    LatLng current = new LatLng(latitude, longitude);
                                    com.google.maps.model.LatLng lastKnownLocation2 = new com.google.maps.model.LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                                    com.google.maps.model.LatLng destination2 = new com.google.maps.model.LatLng(destination.latitude, destination.longitude);
                                    DirectionsResult result = DirectionsApi.newRequest(getGeoContext()).mode(TravelMode.DRIVING).origin(lastKnownLocation2).destination(destination2).await();
                                    if (result.routes.length == 0) {
                                        Toast.makeText(MapFragment.this, "No route is found",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
                                        if (decodedPath != null) {
                                            mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
                                            mMap.addMarker(new MarkerOptions().position(current).title("Marker"));
                                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                            builder.include(destination);
                                            builder.include(current);
                                            int i;
                                            for (i = 0; i < decodedPath.size(); i++) {
                                                builder.include(decodedPath.get(i));
                                            }
                                            LatLngBounds bounds = builder.build();
                                            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, PADDING));
                                        }

                                    }

                                } catch (java.lang.InterruptedException e) {
                                    e.printStackTrace();
                                } catch (java.io.IOException e) {
                                    e.printStackTrace();
                                } catch (com.google.maps.errors.ApiException e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
