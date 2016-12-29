package com.grability.elio.grabilitytest.domain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.grability.elio.grabilitytest.R;

/**
 * Created by elio on 12/28/16.
 */

public class FragmentUtils {
    public static void setFragmentContent(FragmentActivity context, Fragment fragment) {
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_content, fragment);
        fragmentTransaction.commit();
    }
}
