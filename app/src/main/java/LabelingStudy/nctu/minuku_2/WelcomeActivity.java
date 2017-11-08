package LabelingStudy.nctu.minuku_2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import LabelingStudy.nctu.minuku.config.Constants;
import LabelingStudy.nctu.minuku.logger.Log;
import LabelingStudy.nctu.minuku_2.controller.timer_move;
import LabelingStudy.nctu.minuku_2.service.BackgroundService;
import LabelingStudy.nctu.minuku_2.service.CheckpointAndReminderService;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    public final int REQUEST_ID_MULTIPLE_PERMISSIONS=1;

    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        go = (Button)findViewById(R.id.btn_go);
        go.setOnClickListener(doClick);

        startService(new Intent(getBaseContext(), BackgroundService.class));
//        startService(new Intent(getBaseContext(), ExpSampleMethodService.class));
        startService(new Intent(getBaseContext(), CheckpointAndReminderService.class));

//        EventBus.getDefault().register(this);

        int sdk_int = Build.VERSION.SDK_INT;
        if(sdk_int>=23) {
            checkAndRequestPermissions();
        }else{
            startServiceWork();
        }
    }

    private Button.OnClickListener doClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            String current_task = getResources().getString(R.string.current_task);

            if(current_task.equals("PART")) {

                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, timer_move.class);
                startActivity(intent);

            }else{

                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

            }


        }
    };

    private void checkAndRequestPermissions() {

        Log.e(TAG,"checkingAndRequestingPermissions");

        int permissionReadExternalStorage = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWriteExternalStorage = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int permissionFineLocation = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCoarseLocation = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionStatus= ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);

        List<String> listPermissionsNeeded = new ArrayList<>();


        if (permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionWriteExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (permissionFineLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionCoarseLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_PHONE_STATE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
        }else{
            startServiceWork();
        }

    }

    public void startServiceWork(){

        getDeviceid();

        //Use service to catch user's log, GPS, activity;
        //TODO Bootcomplete 復原
        //** remember to check this is for what?
/*
        if (!CheckFamiliarOrNotService.isServiceRunning()){
            android.util.Log.d("MainActivity", "[test service running]  going start the probe service isServiceRunning:" + CheckFamiliarOrNotService.isServiceRunning());
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CheckFamiliarOrNotService.class);

            startService(intent);
        }
*/
    }

    public void getDeviceid(){

        TelephonyManager mngr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        int permissionStatus= ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);
        if(permissionStatus==PackageManager.PERMISSION_GRANTED){
            Constants.DEVICE_ID = mngr.getDeviceId();

            Log.e(TAG,"DEVICE_ID"+Constants.DEVICE_ID+" : "+mngr.getDeviceId());

            /*if(projName.equals("Ohio")) {
               device_id=(TextView)findViewById(R.id.deviceid);
               device_id.setText("ID = " + Constants.DEVICE_ID);

            }*/

        }
    }
}
