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

```

## Things this doesn't do
It's not nearly as strict to the spec as it should be, but for simple testing it works well.