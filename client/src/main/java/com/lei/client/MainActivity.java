package com.lei.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lei.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected EditText edt1;
    protected EditText edt2;
    protected Button btnAdd;
    protected EditText edt3;
    private int num1;
    private int num2;
    protected IMyAidlInterface iMyAidlInterface;
    protected ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        edt3 = (EditText) findViewById(R.id.edt3);

        Intent intent = new Intent();
        intent.setAction("com.lei.aidl.AIDLService");
        intent.setPackage("com.lei.aidl");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                num1 = Integer.parseInt(edt1.getText().toString().trim());
                num2 = Integer.parseInt(edt2.getText().toString().trim());
                try {
                    int result = iMyAidlInterface.add(num1, num2);
                    edt3.setText(result + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.d("client", "onClick: ");
                break;
            default:
                break;
        }
    }
}
