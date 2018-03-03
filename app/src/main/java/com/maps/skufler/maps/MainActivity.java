package com.maps.skufler.maps;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment mapFragment = new MapFragment();
            Fragment dataFragment = new DataFragment();

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.add(R.id.map, mapFragment);
            ft.add(R.id.data, dataFragment);

            ft.commit();
        }
    }

    public static class MapFragment extends Fragment implements OnMapReadyCallback {

        MapView mMapView;
        private GoogleMap _googleMap;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.map_fragment, container, false);

            mMapView = rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume(); // needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rootView;
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            _googleMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);
            _googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            _googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }

    public static class DataFragment extends Fragment {

        TextView textView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.data_fragment, container, false);

            textView = rootView.findViewById(R.id.textView);

            // ...

            return rootView;
        }
    }
}
