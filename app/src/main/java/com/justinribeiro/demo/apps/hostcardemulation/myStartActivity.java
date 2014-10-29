package com.justinribeiro.demo.apps.hostcardemulation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class myStartActivity extends ActionBarActivity {
    private static final String TAG = "JDR HostCardEmulation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        // Android Studio + app:theme = angry for me at moment
        // just set the text white really quick
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        Button setNdef = (Button) findViewById(R.id.set_ndef_button);
        setNdef.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               //
               // Technically, if this is past our byte limit,
               // it will cause issues.
               //
               // TODO: add validation
               //
               TextView getNdefString = (TextView) findViewById(R.id.ndef_text);
               String test = getNdefString.getText().toString();

               Intent intent = new Intent(view.getContext(), myHostApduService.class);
               intent.putExtra("ndefMessage", test);
               startService(intent);
           }
        }
       );
    }
}
