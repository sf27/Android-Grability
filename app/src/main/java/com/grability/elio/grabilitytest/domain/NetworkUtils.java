package com.grability.elio.grabilitytest.domain;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by elio on 12/26/16.
 */

public class NetworkUtils {

    public static boolean haveNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE
        );
        // test for connection
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected();
    }
}
