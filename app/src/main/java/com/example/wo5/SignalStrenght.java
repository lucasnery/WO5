package com.example.wo5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.util.List;
import java.util.TimerTask;

public class SignalStrenght extends AppCompatActivity {

    private TelephonyManager tm;
    private static final int PERMISSION_ACCESS_FINE_LOCATION = 1;
    private String LOCATION_PROVIDER = LocationManager.NETWORK_PROVIDER;
    private LocationManager mlocationManager;
    private LocationListener mlocationListener;
    private long MIN_TIME = 5000;
    private float MIN_DISTANCE = 10;
    private final int COARSE_LOCATION_REQUEST_CODE = 102;
    private String TAG = "SignalStrenght";
    private List<CellInfo> cellInfoList;
    private Button ss_button_ThpT;
    private GraphView graph1;
    private LineGraphSeries<DataPoint> mSeries;
    private BarGraphSeries<DataPoint> barGraphSeries;
    private double graph2LastXValue = 0d;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signal_strenght);

        ss_button_ThpT = findViewById(R.id.ss_button_ThpT);

        graph1 = findViewById(R.id.graph1);

        mSeries = new LineGraphSeries<>();
        graph1.addSeries(mSeries);
        graph1.getViewport().setXAxisBoundsManual(true);
        graph1.getViewport().setMinX(0);
        graph1.getViewport().setMaxX(80);
        graph1.getViewport().setScrollable(true);


        // custom label formatter to show currency "EUR"
        graph1.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return "|  -" + super.formatLabel(value, isValueX);
                }
            }
        });




        getCurrentLocation();

        SignalStrengthsListener ss = new SignalStrengthsListener();
        ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).listen(ss, SignalStrengthsListener.LISTEN_SIGNAL_STRENGTHS);

        ss_button_ThpT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpeedTestTaskDl().execute();


             }
        });
    }

    protected class SignalStrengthsListener extends PhoneStateListener{
    @SuppressLint("MissingPermission")
    @Override
        public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

            String time = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(System.currentTimeMillis());
            String date = DateFormat.getDateInstance(DateFormat.SHORT).format(System.currentTimeMillis());

            cellInfoList = tm.getAllCellInfo();


            //Log.d(TAG, String.valueOf(tm.getAllCellInfo()));
            for(CellInfo cellInfo : cellInfoList){
                //Information about LTE network
                if(cellInfo instanceof CellInfoLte){
                    //Cell registered is server
                    if(cellInfo.isRegistered()){
                        //Cell Identity
                        int cellPci = ((CellInfoLte) cellInfo).getCellIdentity().getPci();
                        int ci = ((CellInfoLte) cellInfo).getCellIdentity().getCi();
                        double enobebId = ci / 256;
                        int tac = ((CellInfoLte) cellInfo).getCellIdentity().getTac();
                        int earfcn = ((CellInfoLte) cellInfo).getCellIdentity().getEarfcn();
                        String operatorLong = (String) ((CellInfoLte) cellInfo).getCellIdentity().getOperatorAlphaLong();
                        String operatorAlphaShort = (String) ((CellInfoLte) cellInfo).getCellIdentity().getOperatorAlphaShort();
                        String mcc = ((CellInfoLte) cellInfo).getCellIdentity().getMccString();
                        String mnc = ((CellInfoLte) cellInfo).getCellIdentity().getMncString();
                        String detail = ((CellInfoLte) cellInfo).getCellIdentity().toString();
                        //Cell Signal Strength
                        int rsrp = ((CellInfoLte) cellInfo).getCellSignalStrength().getRsrp();
                        //Add data to graphic
                        double rsrpD = rsrp;
                        graph2LastXValue += 1d;
                        //graph1.getViewport().setScrollable(true);
                        mSeries.appendData(new DataPoint(graph2LastXValue,rsrpD),true,200);
                        graph1.setTitle("teste");
                        int rsrq = ((CellInfoLte) cellInfo).getCellSignalStrength().getRsrq();
                        int snr = ((CellInfoLte) cellInfo).getCellSignalStrength().getRssnr();
                        int ta = ((CellInfoLte) cellInfo).getCellSignalStrength().getTimingAdvance();
                        int asu = ((CellInfoLte) cellInfo).getCellSignalStrength().getAsuLevel();
                        // 3 - SIGNAL_STRENGTH_GOOD
                        // 4 - SIGNAL_STRENGTH_GREAT
                        // 2 - SIGNAL_STRENGTH_MODERATE
                        // 0 - SIGNAL_STRENGTH_NONE_OR_UNKNOWN
                        // 1 - SIGNAL_STRENGTH_POOR
                        int cqi = ((CellInfoLte) cellInfo).getCellSignalStrength().getCqi();
                        int dbm = ((CellInfoLte) cellInfo).getCellSignalStrength().getDbm();
                        int level = ((CellInfoLte) cellInfo).getCellSignalStrength().getLevel();
                        int rssi = ((CellInfoLte) cellInfo).getCellSignalStrength().getRssi();
                        Log.d(TAG,"Server Cell " + date + " " + time + " " + cellPci + " " + ci + " " + enobebId + " " + tac +
                                " " + earfcn + " " +
                                operatorAlphaShort + " " + mcc + " " + mnc +  " " + rsrp + " " +
                                rsrq + " " + snr  + " " + ta + " " + asu + " " + cqi + " " + dbm + " " + rssi);
                    //Cell no registered is neighbor
                    }else{
                        int cellPci = ((CellInfoLte) cellInfo).getCellIdentity().getPci();
                        int ci = ((CellInfoLte) cellInfo).getCellIdentity().getCi();
                        double enobebId = ci / 256;
                        int tac = ((CellInfoLte) cellInfo).getCellIdentity().getTac();
                        int earfcn = ((CellInfoLte) cellInfo).getCellIdentity().getEarfcn();
                        String operatorLong = (String) ((CellInfoLte) cellInfo).getCellIdentity().getOperatorAlphaLong();
                        String operatorAlphaShort = (String) ((CellInfoLte) cellInfo).getCellIdentity().getOperatorAlphaShort();
                        String mcc = ((CellInfoLte) cellInfo).getCellIdentity().getMccString();
                        String mnc = ((CellInfoLte) cellInfo).getCellIdentity().getMncString();
                        String detail = ((CellInfoLte) cellInfo).getCellIdentity().toString();
                        //Cell Signal Strength
                        int rsrp = ((CellInfoLte) cellInfo).getCellSignalStrength().getRsrp();
                        int rsrq = ((CellInfoLte) cellInfo).getCellSignalStrength().getRsrq();
                        int snr = ((CellInfoLte) cellInfo).getCellSignalStrength().getRssnr();
                        int ta = ((CellInfoLte) cellInfo).getCellSignalStrength().getTimingAdvance();
                        int asu = ((CellInfoLte) cellInfo).getCellSignalStrength().getAsuLevel();
                        int cqi = ((CellInfoLte) cellInfo).getCellSignalStrength().getCqi();
                        int dbm = ((CellInfoLte) cellInfo).getCellSignalStrength().getDbm();
                        int level = ((CellInfoLte) cellInfo).getCellSignalStrength().getLevel();
                        int rssi = ((CellInfoLte) cellInfo).getCellSignalStrength().getRssi();
                        Log.d(TAG,"Neighbor Cell " + date + " " + time + " " + cellPci + " " + earfcn + " " +
                                rsrp + " " +  rsrq + " " + asu + " " + rssi);
                    }

                }
                if(cellInfo instanceof CellInfoWcdma){

                }
                if(cellInfo instanceof CellInfoGsm){

                }

            }
        }
    }

    private void requestFineCoarseLocation(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_ACCESS_FINE_LOCATION);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_ACCESS_FINE_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,
                        "ACCESS FINE LOCATION OK",
                        Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,
                        "ACCESS FINE LOCATION NOK",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        mlocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mlocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());
                String altitude = String.valueOf(location.getAltitude());
                Log.d("Position", "Latitude: " + latitude + " Longitude: " + longitude + " Altitude: " + altitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        mlocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mlocationListener);
    }
}
