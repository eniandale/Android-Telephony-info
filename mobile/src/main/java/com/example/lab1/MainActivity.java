package com.example.lab1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.telephony.gsm.GsmCellLocation;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.Manifest.permission;

import android.support.v7.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private TextView alltext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alltext  = (TextView)findViewById(R.id.text1);
        if(checkPermission()){
            realMainActivity();
        }
        else {
            requestPermission();
        }

        //final TextView text1 = (TextView)findViewById(R.id.text1);
       // text1.setText("\n Call state: \t" + convertCallStateToString(manage.getCallState()) +
        //"\n Network type: \t" + convertNetworkTypeToString(manage.getNetworkType()));

    }


    private void realMainActivity() {

        TelephonyManager manage = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);


        alltext.append("\nCallState:\t" +
                convertCallStateToString(manage.getCallState()));
        alltext.append("\nDevice ID:\t" +
                manage.getDeviceId());
        alltext.append("\nDevice Software Version:\t" +
                manage.getDeviceSoftwareVersion());
        alltext.append("\nLine1 Number:\t" +
                manage.getLine1Number());
        alltext.append("\nNetwork Type:\t" +
                convertNetworkTypeToString(manage.getNetworkType()));
        alltext.append("\nNetwork Country ISO:\t" +
                manage.getNetworkCountryIso());
        alltext.append("\nNetwork Operator:\t" +
                manage.getNetworkOperator());
        alltext.append("\nNetwork Operator Name:\t" +
                manage.getNetworkOperatorName());
        alltext.append("\nPhone Type:\t" +
                convertPhoneTypeToString(manage.getPhoneType()));
        alltext.append("\nData Activity:\t" +
                convertDataActivityToString(manage.getDataActivity()));
        alltext.append("\nData State:\t" +
                convertDataStateToString(manage.getDataState()));
        alltext.append("\nSubscriber ID:\t" +
                manage.getSubscriberId());
        alltext.append("\nVoice Mail Alpha Tag:\t" +
                manage.getVoiceMailAlphaTag());
        alltext.append("\nVoice Mail Number:\t" +
                manage.getVoiceMailNumber());
        alltext.append("\nIcc Card:\t" +
                manage.hasIccCard());
        alltext.append("\nNetwork Roaming:\t" +
                manage.isNetworkRoaming());
        GsmCellLocation gsmCell =
                (GsmCellLocation)manage.getCellLocation();
        if (gsmCell != null) {
            alltext.append("\nGSM Cell Location:");
            alltext.append("\n\tCID:\t" + gsmCell.getCid());
            alltext.append("\n\tLAC:\t" + gsmCell.getLac());
        }

    }





    private String convertCallStateToString(int callState) {
        switch(callState){
            case TelephonyManager.CALL_STATE_IDLE: return "IDLE";
            case TelephonyManager.CALL_STATE_OFFHOOK: return "OFFHOCK";
            case TelephonyManager.CALL_STATE_RINGING: return "RINGING";
            default: return "NOT DEFINED";
        }
    }

    private  String convertNetworkTypeToString(int networkType){
        switch (networkType){
            case TelephonyManager.NETWORK_TYPE_1xRTT: return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA: return  "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE: return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EHRPD: return  "EHRPD";
            case TelephonyManager.NETWORK_TYPE_EVDO_0: return "EVDO_0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A: return "EVDO_A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B: return "EVDO_B";
            case TelephonyManager.NETWORK_TYPE_GPRS: return "GPRS";
            case TelephonyManager.NETWORK_TYPE_GSM: return "GSM";
            case TelephonyManager.NETWORK_TYPE_HSDPA: return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA: return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSPAP: return "HSPAP";
            case TelephonyManager.NETWORK_TYPE_HSUPA: return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_IDEN: return "IDEN";
            case TelephonyManager.NETWORK_TYPE_IWLAN: return "IWLAN";
            case TelephonyManager.NETWORK_TYPE_LTE: return "LTE";
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA: return "TD SCDMA";
            case TelephonyManager.NETWORK_TYPE_UMTS: return "UMTS";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN: return "UNKNOWN";
            default: return "NOT DEFINED";
        }
    }

    private String convertDataActivityToString(int dataActivity) {
        switch (dataActivity) {
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "Dormant";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "In";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "In-out";
            case TelephonyManager.DATA_ACTIVITY_NONE:
                return "None";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "Out";
            default:
                return "Not defined";
        }
    }

    private String convertDataStateToString(int dataState) {
        switch (dataState) {
            case TelephonyManager.DATA_CONNECTED:
                return "Data connected";
            case TelephonyManager.DATA_CONNECTING:
                return "Data connecting";
            case TelephonyManager.DATA_DISCONNECTED:
                return "Data suspended";
            case TelephonyManager.DATA_SUSPENDED:
                return "Data suspended";
            default:
                return "Not defined";
        }
    }
    private String convertPhoneTypeToString(int phoneType) {
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_GSM:
                return "GSM";
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.PHONE_TYPE_NONE:
                return "NONE";
            default:
                return "Not defined";
        }
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), permission.READ_PHONE_STATE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), permission.ACCESS_COARSE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{permission.READ_PHONE_STATE, permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean readStateAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean locationAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (readStateAccepted && locationAccepted) {
                        //do the programm code
                        realMainActivity();
                    }

                    else{
                        alltext.setText("I need both permissions");
                    }
                    break;
                }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


}




