package com.justinribeiro.demo.apps.hostcardemulation;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

import java.math.BigInteger;
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
        (byte)0xF0, (byte)0x39, (byte)0x41, (byte)0x48, (byte)0x14, (byte)0x81, (byte)0x00, // Data field - String of bytes sent in the data field of the command
        (byte)0x00  // Le field	- Maximum number of bytes expected in the data field of the response to the command
    };

    //
    // The following definitions are based on the APDU example in libnfc:
    // http://nfc-tools.org/index.php?title=Libnfc:APDU_example
    //
    // The example has been included in this project slightly modified to handle the AID in the
    // Android documentation (the APDU_SELECT defined above)
    //
    private static final byte[] CAPABILITY_CONTAINER = {
            (byte)0x00, // CLA	- Class - Class of instruction
            (byte)0xa4, // INS	- Instruction - Instruction code
            (byte)0x00, // P1	- Parameter 1 - Instruction parameter 1
            (byte)0x0c, // P2	- Parameter 2 - Instruction parameter 2
            (byte)0x02, // Lc field	- Number of bytes present in the data field of the command
            (byte)0xe1,
            (byte)0x03  // Le field	- Maximum number of bytes expected in the data field of the response to the command
    };

    private static final byte[] READ_CAPABILITY_CONTAINER = {
            (byte)0x00, // CLA	- Class - Class of instruction
            (byte)0xb0, // INS	- Instruction - Instruction code
            (byte)0x00, // P1	- Parameter 1 - Instruction parameter 1
            (byte)0x00, // P2	- Parameter 2 - Instruction parameter 2
            (byte)0x0f  // Lc field	- Number of bytes present in the data field of the command
    };

    private static final byte[] READ_CAPABILITY_CONTAINER_RESPONSE = {
            (byte)0x00, // CLA	- Class - Class of instruction
            (byte)0x0F, // INS	- Instruction - Instruction code
            (byte)0x20, // P1	- Parameter 1 - Instruction parameter 1
            (byte)0x00, // P2	- Parameter 2 - Instruction parameter 2
            (byte)0x3B,  // Lc field	- Number of bytes present in the data field of the command
            (byte)0x00, (byte)0x34, (byte)0x04, (byte)0x06, (byte)0xE1, (byte)0x04, (byte)0x00, (byte)0x32, (byte)0x00, (byte)0x00, // Data field - String of bytes sent in the data field of the command
            (byte)0x90, (byte)0x00 // A_OKAY
    };

    private static final byte[] NDEF_SELECT = {
            (byte)0x00, // CLA	- Class - Class of instruction
            (byte)0xa4, // INS	- Instruction - Instruction code
            (byte)0x00, // P1	- Parameter 1 - Instruction parameter 1
            (byte)0x0c, // P2	- Parameter 2 - Instruction parameter 2
            (byte)0x02,  // Lc field	- Number of bytes present in the data field of the command
            (byte)0xE1, (byte)0x04
    };

    private static final byte[] NDEF_READ_BINARY_NLEN = {
            (byte)0x00, // CLA	- Class - Class of instruction
            (byte)0xb0, // INS	- Instruction - Instruction code
            (byte)0x00, // P1	- Parameter 1 - Instruction parameter 1
            (byte)0x00, // P2	- Parameter 2 - Instruction parameter 2
            (byte)0x02  // Lc field	- Number of bytes present in the data field of the command
    };

    private static final byte[] NDEF_READ_BINARY_NLEN_RESPONSE = {
            (byte)0x00,
            (byte)0x0f,
            (byte)0x90, (byte)0x00 // A_OKAY
    };

    private static final byte[] A_OKAY = {
            (byte)0x90,  // SW1	Status byte 1 - Command processing status
            (byte)0x00   // SW2	Status byte 2 - Command processing qualifier
    };

    //
    //  TESTING ONLY - NdefRecord + NDEF_READ_BINARY_NLEN_RESPONSE
    //
    private static final NdefRecord NDEF_URI = new NdefRecord(
            NdefRecord.TNF_WELL_KNOWN,
            NdefRecord.RTD_TEXT,
            null,
            "textValue".getBytes());

    private static final byte[] NDEF_URI_BYTES = NDEF_URI.toByteArray();
    private static final byte[] NDEF_URI_LEN = BigInteger.valueOf(NDEF_URI_BYTES.length).toByteArray();

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {

        Log.i(TAG, "commandApdu = " + Arrays.toString(commandApdu));

        if (Arrays.equals(APDU_SELECT, commandApdu)) {
            Log.i(TAG, "processCommandApdu() | APDU_SELECT triggered");
            return A_OKAY;
        }

        if (Arrays.equals(CAPABILITY_CONTAINER, commandApdu)) {
            Log.i(TAG, "processCommandApdu() | CAPABILITY_CONTAINER triggered");
            return A_OKAY;
        }

        if (Arrays.equals(READ_CAPABILITY_CONTAINER, commandApdu)) {
            Log.i(TAG, "processCommandApdu() | READ_CAPABILITY_CONTAINER triggered");
            return READ_CAPABILITY_CONTAINER_RESPONSE;
        }

        if (Arrays.equals(NDEF_SELECT, commandApdu)) {
            Log.i(TAG, "processCommandApdu() | NDEF_SELECT triggered");
            return A_OKAY;
        }

        if (Arrays.equals(NDEF_READ_BINARY_NLEN, commandApdu)) {
            Log.i(TAG, "processCommandApdu() | NDEF_READ_BINARY_NLEN triggered");
            return A_OKAY;
        }

        //
        // We're doing something outside our scope
        //
        Log.i(TAG, "processCommandApdu(): Our Application Received, but we don't know what to do.");
        return "Can I help you?".getBytes();
    }

    @Override
    public void onDeactivated(int reason) {
        Log.i(TAG, "onDeactivated() Fired! Reason: " + reason);
    }
}
