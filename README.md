bip-schnorr-java
================

#### _Now with *Maven*!_

[![Java CI with Maven](https://github.com/deavmi/bip-schnorr-java/actions/workflows/maven.yml/badge.svg)](https://github.com/deavmi/bip-schnorr-java/actions/workflows/maven.yml)

Bitcoin Cash(BCH) will add Schnorr signatures on the mainnet on May 15 2019, replacing the Elliptic Curve Digital Signature Algorithm (ECDSA), which is the legacy signature protocol of the original bitcoin. 

This project is the exact java port of Schnorr signature from python code at:
https://github.com/sipa/bips/tree/bip-schnorr/bip-schnorr

This project is a fork of MikeTW's work which can be found at: https://github.com/miketwk/bip-schnorr-java

## Todo

Some of the things being worked on:

- [x] Maven integration
    * This is so that it can be used as a dependency and via JitPack.io
- [x] Java crypto integration
    * This is to integrate this into the Java Crypto Framework so that it
    can be used via there.
    * There are several requirements for this, as shown below:
    - [x] `SignatureSpi` implementation
    - [x] `PublicKey` implementation
    - [x] `PrivateKey` implementation
    - [ ] `KeyFactorySpi` implementation
      * **Help wanted!** - it would be nice if someone could help
      with an algorithmn for _generating_ key-pairs
- [x] Automated testing

## Usage

Firstly add the JitPack repository:

```xml
<repositories>
   <repository>
    <id>jitpack.io</id>
    <url>https://www.jitpack.io</url>
   </repository>
</repositories>
```

Now add the dependency (**note**, currently the master branch builds are your best bet):

```xml
<dependency>
   <groupId>com.github.deavmi</groupId>
   <artifactId>bip-schnorr-java</artifactId>
   <version>0.0.2</version>
</dependency>
```
