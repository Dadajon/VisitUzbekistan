package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerActivity extends FragmentActivity implements /*GoogleMap.OnMarkerClickListener,*/
        OnMapReadyCallback {

    private static final LatLng REGISTAN = new LatLng(39.654799, 66.975715);
    private static final LatLng GUR_E_AMIR = new LatLng(39.648659, 66.969233);
    private static final LatLng SHAH_I_ZINDA = new LatLng(39.663247, 66.987810);
    private static final LatLng BIBI_KHANYM = new LatLng(39.660946, 66.980146);
    private static final LatLng TILLYA_KORI = new LatLng(39.655580, 66.975496);
    private static final LatLng ULUGBEK = new LatLng(39.654658, 66.974900);
    private static final LatLng SHER_DOR = new LatLng(39.654903, 66.976305);
    private static final LatLng AFRASIYAB = new LatLng(39.669356, 66.993367);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap map) {

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_dot_place);

        Marker mRegistan = map.addMarker(new MarkerOptions()
                .position(REGISTAN)
                .icon(icon)
                .title("Registan")
                .snippet("Address: Registan St, Samarkand, Uzbekistan"));

        Marker mGURAMIR = map.addMarker(new MarkerOptions()
                .position(GUR_E_AMIR)
                .icon(icon)
                .title("Gur-e-Amir Mausoleum")
                .snippet("Address: Samarkand, Uzbekistan"));

        Marker mShahizinda = map.addMarker(new MarkerOptions()
                .position(SHAH_I_ZINDA)
                .icon(icon)
                .title("Shah-i-Zinda")
                .snippet("Address: M-37, Samarkand, Uzbekistan"));

        Marker mBibi = map.addMarker(new MarkerOptions()
                .position(BIBI_KHANYM)
                .icon(icon)
                .title("Bibi-Khanym Mosque")
                .snippet("Address: Bibikhonim St, Samarkand, Uzbekistan"));

        Marker mTillya = map.addMarker(new MarkerOptions()
                .position(TILLYA_KORI)
                .icon(icon)
                .title("Tilla-Kari Medressa")
                .snippet("Address: Samarkand, Uzbekistan"));

        Marker mUlugbek = map.addMarker(new MarkerOptions()
                .position(ULUGBEK)
                .icon(icon)
                .title("Ulugbek Medressa")
                .snippet("Address: Samarkand, Uzbekistan"));

        Marker mSherdor = map.addMarker(new MarkerOptions()
                .position(SHER_DOR)
                .icon(icon)
                .title("Sher Dor Medressa")
                .snippet("Address: Samarkand, Uzbekistan"));

        Marker mAfrosiyab = map.addMarker(new MarkerOptions()
                .position(AFRASIYAB)
                .icon(icon)
                .title("Afrosiab Museum")
                .snippet("Address: Tashkent Road, Samarkand, Uzbekistan"));

    }
}








