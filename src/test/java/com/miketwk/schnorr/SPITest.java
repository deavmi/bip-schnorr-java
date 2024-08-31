package com.miketwk.schnorr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import org.junit.jupiter.api.Test;

/**
 * Test suite for the signature
 * SPI implementation
 * 
 * @see SchnorrSignature
 * @author Tristan Brice Velloza Kildaire (deavmi)
 */
public class SPITest
{
	private static final SchnorrProvider provider = new SchnorrProvider();
	
	@Test
	public void signatureVerify_good() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
	{
//		"1,0000000000000000000000000000000000000000000000000000000000000001,0279BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798,0000000000000000000000000000000000000000000000000000000000000000,787A848E71043D280C50470E8E1532B2DD5D20EE912A45DBDD2BD1DFBF187EF67031A98831859DC34DFFEEDDA86831842CCD0079E1F92AF177F7F22CC1DCED05,TRUE,";
		String pubkey = "0279BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798";
		String sig = "787A848E71043D280C50470E8E1532B2DD5D20EE912A45DBDD2BD1DFBF187EF67031A98831859DC34DFFEEDDA86831842CCD0079E1F92AF177F7F22CC1DCED05";
		String msg = "0000000000000000000000000000000000000000000000000000000000000000";
		
		PublicKey pub = new SchnorrKeyPub(pubkey);
		
		Signature s = Signature.getInstance("Schnorr", provider);
		
		// The key we will verify with
		s.initVerify(pub);
		
		// Place message in
		s.update(Schnorr.hexStringToByteArray(msg));
		
		// Now use the signature to test whether
		// the message was signed by the given
		// public key
		assertTrue(s.verify(Schnorr.hexStringToByteArray(sig)));
	}
}
