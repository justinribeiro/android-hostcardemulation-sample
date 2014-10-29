# Testing the Android Host Card Emulation of a NFC Forum Type-4 tag

The following program takes a command by command approach to testing our  ISO14443-4 emulated tag. It is an extended version of the existing [libnfc apdu example](http://nfc-tools.org/index.php?title=Libnfc:APDU_example).

## What do I need to run this?

You'll need a couple of things:

1. An NFC Reader. I've used this with the [ACR122U USB NFC Reader](http://www.acs.com.hk/en/products/3/acr122u-usb-nfc-reader) without issue.
2. [Libnfc](http://nfc-tools.org/index.php?title=Libnfc). You'll likely be able to install from your package manager of choice.

## Compile

```
gcc -o apdu_tag_test apdu_tag_test.c -lnfc
```

## Run

```
./apdu_tag_test
```

## Sample Output

```
$ ./apdu_tag_test

Running checks...
./apdu_tag_test uses libnfc 1.7.1
NFC reader: ACS / ACR122U PICC Interface opened
Polling for target...
Target detected! Running command set...

Sending ADPU SELECT...
=> 00 a4 04 00 07 f0 39 41 48 14 81 00 00 
<= 90 00 
Application selected!

Sending CC SELECT...
=> 00 a4 00 0c 02 e1 03 
<= 90 00 
Capability Container selected!

Sending ReadBinary from CC...
=> 00 b0 00 00 0f 
<= 00 0f 20 00 3b 00 34 04 06 e1 04 00 32 00 00 90 00 

Capability Container header:
00 0f 20 00 3b 00 34 04 06 e1 04 00 32 00 00 

Sending NDEF Select...
=> 00 a4 00 0c 02 e1 04 
<= 90 00 

Sending ReadBinary NLEN...
=> 00 b0 00 00 02 
<= 00 13 90 00 

Sending ReadBinary, get NDEF data...
=> 00 b0 00 00 0f 
<= 00 13 d9 01 0c 02 54 e1 04 48 65 6c 6c 6f 20 77 6f 72 6c 64 21 90 00 

Wrapping up, closing session.
```

## Things this doesn't do
It's not nearly as strict to the spec as it should be, but for simple testing it works well.
