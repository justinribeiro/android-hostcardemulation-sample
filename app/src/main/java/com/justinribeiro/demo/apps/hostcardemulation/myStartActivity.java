package com.justinribeiro.demo.apps.hostcardemulation;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.nfc.NfcAdapter;
import android.nfc.cardemulation.CardEmulation;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

public class myStartActivity extends Activity {
    private static final String TAG = "JDR HostCardEmulation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        CardEmulation cardEmulationManager = CardEmulation.getInstance(NfcAdapter.getDefaultAdapter(this));
        ComponentName apduServiceComponent = new ComponentName(getApplicationContext(), myHostApduService.class.getCanonicalName());

        if (!cardEmulationManager.isDefaultServiceForCategory(apduServiceComponent, CardEmulation.CATEGORY_OTHER)) {
            Log.i(TAG, "onCreate: We could request Android to make us the default action for CardEmulation.CATEGORY_OTHER");
        } else {
            Log.i(TAG, "onCreate: We would already be the default action for CardEmulation.CATEGORY_OTHER");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_start, container, false);
            return rootView;
        }
    }
}
