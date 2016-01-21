package com.nearsoft.nearbooks.gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * GCM listener.
 * Created by epool on 1/19/16.
 */
public class NearbooksGcmListenerService extends GcmListenerService {

    private static final String TAG = NearbooksGcmListenerService.class.getSimpleName();

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
    }

}
