package com.justinribeiro.demo.apps.hostcardemulation;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;

/**
 * Created by justin.ribeiro on 10/27/2014.
 */
public class myHostApduService extends HostApduService {

    private static final String TAG = "JDR HostApduService";

    //
    // We use the default AID from the HCE Android documentation
    // https://developer.android.com/guide/topics/connectivity/nfc/hce.html
    //
    // Ala... <aid-filter android:name="F0394148148100" />
    //
    private static final byte[] APDU_SELECT = {
            (byte)0x00, // CLA	- Class - Class of instruction
            (byte)0xA4, // INS	- Instruction - Instruction code
            (byte)0x04, // P1	- Parameter 1 - Instruction parameter 1
            (byte)0x00, // P2	- Parameter 2 - Instruction parameter 2
            (byte)0x07, // Lc field	- Number of bytes present in the data field of the command
            (byte)0xF0, (byte)0x39, (byte)0x41, (byte)0x48, (byte)0x14, (byte)0x81, (byte)0x00,
            (byte)0x00  // Le field	- Maximum number of bytes expected in the data field of the response to the command
    };

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {

        Log.i(TAG, "commandApdu = " + Arrays.toString(commandApdu));

        if (Arrays.equals(APDU_SELECT, commandApdu)) {
            Log.i(TAG, "processCommandApdu(): Our Application Selected!");
            return "Hello Chromebook NFC!".getBytes();
        } else {
            Log.i(TAG, "processCommandApdu(): Our Application Received!");
            return "Hello From Android!".getBytes();
        }
    }

    @Override
    public void onDeactivated(int reason) {
        Log.i(TAG, "onDeactivated() Fired! Reason: " + reason);
    }
}
