package LabelingStudy.nctu.minuku_2.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import LabelingStudy.nctu.minuku.logger.Log;
import LabelingStudy.nctu.minuku.manager.TripManager;
import LabelingStudy.nctu.minuku_2.MyDBHelper;
import LabelingStudy.nctu.minuku_2.R;

import static LabelingStudy.nctu.minuku_2.controller.home.duration;
import static LabelingStudy.nctu.minuku_2.controller.home.recordflag;
import static LabelingStudy.nctu.minuku_2.controller.home.result;
import static LabelingStudy.nctu.minuku_2.controller.timer_move.TrafficFlag;

//import edu.ohio.minuku_2.R;

public class Timeline extends AppCompatActivity {

    public static ArrayList<String> myTimeDataset = new ArrayList<>();
    public static ArrayList<String> myActivityDataset = new ArrayList<>();
    public static ArrayList<String> myTrafficDataset = new ArrayList<>();
    String TAG="Timeline";
    Context mContext;




    private RecyclerView listview;
    private int Trip_size;

    ArrayList<String> mlocationDataRecords;

    public Timeline(){}
    public Timeline(Context mContext){
        this.mContext = mContext;
    }

    View item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");

        initTime();
    }

    public void initTime(){

        String current_task = mContext.getResources().getString(R.string.current_task);

        if(current_task.equals("PART")) {
            setDataListItems();
            MyAdapter myAdapter = new MyAdapter(myTimeDataset, myActivityDataset,myTrafficDataset);
            RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
            //initialize RecyclerView
//            final View vitem = LayoutInflater.from(Timeline.this).inflate(R.layout.item_dialog, null);
//            item  = vitem;
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mList.setLayoutManager(layoutManager);
            mList.setAdapter(myAdapter);
        }else{
            ArrayList<String> locationDataRecords = null;

            listview = (RecyclerView) findViewById(R.id.list_view);
//            listview.setEmptyView(findViewById(R.id.emptyView));

            try{

                Log.d(TAG,"ListRecordAsyncTask");

//            locationDataRecords = new ListRecordAsyncTask().execute(mReviewMode).get();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    locationDataRecords = new ListRecordAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                else
                    locationDataRecords = new ListRecordAsyncTask().execute().get();

                setDataListItems();
                MyAdapter myAdapter = new MyAdapter(locationDataRecords, myActivityDataset,myTrafficDataset);
                RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mList.setLayoutManager(layoutManager);
                mList.setAdapter(myAdapter);

                Log.d(TAG,"locationDataRecords = new ListRecordAsyncTask().execute().get();");

                mlocationDataRecords = locationDataRecords;

            }catch(InterruptedException e) {
                Log.d(TAG,"InterruptedException");
                e.printStackTrace();
            } catch (ExecutionException e) {
                Log.d(TAG,"ExecutionException");
                e.printStackTrace();
            }
        }

    }

    public void initTime(View v){


        String current_task = v.getResources().getString(R.string.current_task);

        if(current_task.equals("PART")) {
            setDataListItems();
            MyAdapter myAdapter = new MyAdapter(myTimeDataset, myActivityDataset,myTrafficDataset);
            RecyclerView mList = (RecyclerView) v.findViewById(R.id.list_view);
            //i
            // nitialize RecyclerView
//            final View vitem = LayoutInflater.from(Timeline.this).inflate(R.layout.item_dialog, null);
//            item  = vitem;
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mList.setLayoutManager(layoutManager);
            mList.setAdapter(myAdapter);
        }else{
            ArrayList<String> locationDataRecords = null;

            listview = (RecyclerView) v.findViewById(R.id.list_view);
//            listview.setEmptyView(v.findViewById(R.id.emptyView));

            try{

                Log.d(TAG,"ListRecordAsyncTask");

//            locationDataRecords = new ListRecordAsyncTask().execute(mReviewMode).get();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    locationDataRecords = new ListRecordAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
                else
                    locationDataRecords = new ListRecordAsyncTask().execute().get();

                setDataListItems();
                MyAdapter myAdapter = new MyAdapter(locationDataRecords, myActivityDataset, myTrafficDataset);
                RecyclerView mList = (RecyclerView) v.findViewById(R.id.list_view);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mList.setLayoutManager(layoutManager);
                mList.setAdapter(myAdapter);

                Log.d(TAG,"locationDataRecords = new ListRecordAsyncTask().execute().get();");

                mlocationDataRecords = locationDataRecords;

            }catch(InterruptedException e) {
                Log.d(TAG,"InterruptedException");
                e.printStackTrace();
            } catch (ExecutionException e) {
                Log.d(TAG,"ExecutionException");
                e.printStackTrace();
            }

            /*OhioListAdapter ohioListAdapter = new OhioListAdapter(
                    this,
                    R.id.recording_list,
                    mlocationDataRecords
            );*/

//            listview.setAdapter();



           /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                    startAnnotateActivity(position);

                }
            });*/

        }

    }

    private void setDataListItems(){

        Log.d(TAG, "recordflag: " + recordflag);
        if(recordflag){
            myTimeDataset.add(result);
            myActivityDataset.add(duration);
            myTrafficDataset.add(TrafficFlag);
            //SQLite
            // Init
            MyDBHelper mHelper = new MyDBHelper(mContext);
            SQLiteDatabase mDB = null;
            // Insert by raw SQL
            mDB = mHelper.getWritableDatabase();
            String sql = "INSERT INTO Minuku (_Data) VALUES (\'" + result + "\')";
            mDB.execSQL(sql);
            Cursor cursor = mDB.rawQuery("SELECT _ID, _Data FROM Minuku", null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Log.e("SQLiteDBTestingActivity","_id = "+cursor.getInt(0));
                Log.e("SQLiteDBTestingActivity","_Data = "+cursor.getString(1));
                cursor.moveToNext();
            }
            cursor.close();
            mDB.close();

            recordflag=false;
        }

    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mTime, mActivity, mTraffic;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView time, duration;
            public ImageView traffic;
            public ViewHolder(View v) {
                super(v);
                time = (TextView) v.findViewById(R.id.tv_time);
                duration = (TextView) v.findViewById(R.id.tv_duration);
                traffic = (ImageView) v.findViewById(R.id.iv_traffic);
            }
        }

        public MyAdapter(List<String> timedata, List<String> activitydata, List<String> trafficdata) {
            mTime = timedata;
            mActivity = activitydata;
            mTraffic = trafficdata;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_card_view, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

//            item =

            holder.time.setText(mTime.get(position));
            holder.duration.setText(mActivity.get(position));

            if(mTraffic.get(position).equals("walk")){
                holder.traffic.setImageResource(R.drawable.walk);
            }else if(mTraffic.get(position).equals("bike")){
                holder.traffic.setImageResource(R.drawable.bike);
            }else if(mTraffic.get(position).equals("car")){
                holder.traffic.setImageResource(R.drawable.car);
            }else if(mTraffic.get(position).equals("site")){
                holder.traffic.setImageResource(R.drawable.if_94_171453);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* new AlertDialog.Builder(mContext)
                            .setTitle("請確認您的活動")
                            .setView(item)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    EditText editText = (EditText) item.findViewById(R.id.siteOrMove);
                                    String siteOrMove = editText.getText().toString();
                                    if(TextUtils.isEmpty(siteOrMove)){
                                        Toast.makeText(getApplicationContext(), "不可輸入空值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        MyAdapter.this.notifyItemChanged(position, siteOrMove);
                                    }
                                }
                            })
                            .show();*/

                }
            });
        }

        @Override
        public int getItemCount() {
            return mTime.size();
        }
    }

    private class ListRecordAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

        private ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            Log.d(TAG,"onPreExecute");
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            Log.d(TAG, "listRecordAsyncTask going to list recording");

            ArrayList<String> locationDataRecords = new ArrayList<String>();

            try {

                locationDataRecords = TripManager.getTripDatafromSQLite();
//                locationDataRecords = TripManager.getInstance().getTripDatafromSQLite();
//                Trip_size = TripManager.getInstance().getSessionidForTripSize();
                Trip_size = TripManager.getInstance().getTrip_size();

//                Log.d(TAG,"locationDataRecords(0) : " + locationDataRecords.get(0));
//                Log.d(TAG,"locationDataRecords(max) : " + locationDataRecords.get(locationDataRecords.size()-1));

//                if(locationDataRecords.isEmpty())
//                    ;

                Log.d(TAG,"try locationDataRecords");
            }catch (Exception e) {
                locationDataRecords = new ArrayList<String>();
                Log.d(TAG,"Exception");
                e.printStackTrace();
            }
//            return locationDataRecords;

            return locationDataRecords;

        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(ArrayList<String> result) {

            super.onPostExecute(result);

        }

    }



}
