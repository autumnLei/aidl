package com.lei.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;


public class AIDLService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
       return new IMyAidlInterface.Stub() {
           @Override
           public int add(int num1, int num2) throws RemoteException {
               return num1+num2;
           }
       };
    }
}

