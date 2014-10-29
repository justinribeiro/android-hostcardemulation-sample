# Android Host Card Emulation of a NFC Forum Type-4 tag

This example emulates a NFC Forum Type-4 tag with an a single NDEF record (RTD_TEXT). The project uses aid-filter F0394148148100 (which is an example that is defined in the Android documentation) for the APDU SELECT command.

## Whoa there what?

The NFC Forum Type 4 Tag Operation Specification 3.0 outlines how to interact with tags. Using Host Card Emulation in Android, we can do something pretty nifty:

1. We can write an application on the card reader side that sends the proper APDU SELECT and associated commands and interact with a "card".
2. That "card" in this case is emulated in our Android application.

## Where can I get the tag operational spec?

There are various copies on the Internet, but the place to go is to sign the specification license and download direct from the [NFC Forum document library](http://members.nfc-forum.org/specs/spec_license/document_form/).

## How do I interact with the Android application through a card reader?

You have to follow the commands in your client application. You can also compile and run the sample in the apdu_exchange_tester directory that is based on the APDU examples from libNFC (for detailed instructions, see the readme in that folder).

## Where can I learn more about Host Card Emulation on Android?

You'll need API 19 (aka Android 4.4) to use HCE. See [Host-based Card Emulation @ developers.android.com](https://developer.android.com/guide/topics/connectivity/nfc/hce.html) for all the details.

## What if I want to interact with or read a Type-4 tag?

This is a different sort of thing and you'll want to look into [ISO-DEP](http://developer.android.com/reference/android/nfc/tech/IsoDep.html).