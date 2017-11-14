package LabelingStudy.nctu.minuku_2.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import LabelingStudy.nctu.minuku.config.Constants;
import LabelingStudy.nctu.minuku.streamgenerator.LocationStreamGenerator;
import LabelingStudy.nctu.minuku_2.NearbyPlaces.GetNearbyPlacesData;
import LabelingStudy.nctu.minuku_2.NearbyPlaces.GetUrl;
import LabelingStudy.nctu.minuku_2.R;

public class PlaceSelection extends FragmentActivity implements OnMapReadyCallback {

    private final String TAG = "PlaceSelection";

    private SharedPreferences sharedPrefs;

    MapView mapView;
    Button AddPlace, SecRes, Muf, Third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_selection);

        sharedPrefs = getSharedPreferences(Constants.sharedPrefString, Context.MODE_PRIVATE);

    }

    private Button.OnClickListener onClick = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            final View v = LayoutInflater.from(PlaceSelection.this).inflate(R.layout.addplace, null);

            String sitename = AddPlace.getText().toString();

            timer_site.data.add(sitename);

            Log.d(TAG, " data : "+ timer_site.data);
            Log.d(TAG, " dataSize : "+ timer_site.data.size());

            sharedPrefs.edit().putString("dataContent" + (timer_site.data.size()-1), sitename);// -1 is because of after add it in.
            sharedPrefs.edit().putInt("dataSize", timer_site.data.size());
            sharedPrefs.edit().apply();

            PlaceSelection.this.finish();

            /*if(AddPlace.getText().equals("新增地點")) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlaceSelection.this);
                alertDialog.setTitle("自訂地點");
                alertDialog.setView(v);
                alertDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = (EditText) v.findViewById(R.id.edit_text);
                        String name = editText.getText().toString();
                        if (name.equals("")) {
                            Toast.makeText(PlaceSelection.this, "請輸入地點", Toast.LENGTH_SHORT);
                        }
                        AddPlace.setText("使用\"" + name + "\"為地點");
                    }
                });
                alertDialog.show();
            }else {
                //TODO jump back to home.xml and ready to count the time they staying here;


            }*/
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG,"onResume");

        initPlaceSelection();
    }

    private void initPlaceSelection(){

//        mapView = (MapView) findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(PlaceSelection.this);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.Mapfragment)).getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {

                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                float lat = 0;
                float lng = 0;

                try{
                    lat = LocationStreamGenerator.toCheckFamiliarOrNotLocationDataRecord.getLatitude();
                    lng = LocationStreamGenerator.toCheckFamiliarOrNotLocationDataRecord.getLongitude();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                LatLng latLng = new LatLng(lat, lng);

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
                map.addMarker(new MarkerOptions().position(latLng).title("您的位置"));


                //TODO make the neighbor list
                Object dataTransfer[] = new Object[1];

                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                String url = GetUrl.getUrl(lat,lng);
                dataTransfer[0] = url;

                getNearbyPlacesData.execute(dataTransfer);
            }
        });

        AddPlace = (Button)findViewById(R.id.btn_addplace);
//        SecRes = (Button)findViewById(R.id.btn_secRes);
//        Muf = (Button)findViewById(R.id.btn_muf);
//        Third = (Button)findViewById(R.id.btn_third);


        AddPlace.setOnClickListener(onClick);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        /*
        float lat = 0;
        float lng = 0;

        try{
            lat = LocationStreamGenerator.toCheckFamiliarOrNotLocationDataRecord.getLatitude();
            lng = LocationStreamGenerator.toCheckFamiliarOrNotLocationDataRecord.getLongitude();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        LatLng latLng = new LatLng(lat, lng);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        */
    }

   /* @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }*/

    @Override
    protected void onDestroy() {
//        mapView.onDestroy();
        super.onDestroy();
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }*/
}
