package com.miketwk.schnorr;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.miketwk.schnorr.core.Schnorr;
import com.miketwk.schnorr.jca.SchnorrKeyPriv;
import com.miketwk.schnorr.jca.SchnorrKeyPub;
import com.miketwk.schnorr.jca.SchnorrProvider;
import com.miketwk.schnorr.jca.SchnorrSignature;

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
		PrivateKey priv = new SchnorrKeyPriv("0000000000000000000000000000000000000000000000000000000000000001");
	
		Signature s = Signature.getInstance("Schnorr", provider);
		s.initSign(priv);
		
		// Place message in
		s.update(Schnorr.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"));
		
		// Perform the signing of the
		// message using our private
		// key
		byte[] sig = s.sign();
		assertNotNull(sig);
		System.out.println(Arrays.toString(sig));
		
		
		// ------- Now we verify ------- 
		
//		"1,0000000000000000000000000000000000000000000000000000000000000001,0279BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798,0000000000000000000000000000000000000000000000000000000000000000,787A848E71043D280C50470E8E1532B2DD5D20EE912A45DBDD2BD1DFBF187EF67031A98831859DC34DFFEEDDA86831842CCD0079E1F92AF177F7F22CC1DCED05,TRUE,";
		String pubkey = "0279BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798";
//		String sig = "787A848E71043D280C50470E8E1532B2DD5D20EE912A45DBDD2BD1DFBF187EF67031A98831859DC34DFFEEDDA86831842CCD0079E1F92AF177F7F22CC1DCED05";

		
		PublicKey pub = new SchnorrKeyPub(pubkey);
		s = Signature.getInstance("Schnorr", provider); // TODO: test re-use - is that intended SPI behavor?
		
		// The key we will verify with
		s.initVerify(pub);
		
		// Place message in
		s.update(Schnorr.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"));
		
		// Now use the signature to test whether
		// the message was signed by the given
		// public key
		assertTrue(s.verify(sig));
	}
	
	@Test
	public void signatureVerify_failure() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
	{
		PublicKey pub = new SchnorrKeyPub("03EEFDEA4CDB677750A420FEE807EACF21EB9898AE79B9768766E4FAA04A2D4A34");
		byte[] msg = Schnorr.hexStringToByteArray("4DF3C3F68FCC83B27E9D42C90431A72499F17875C81A599B566C9889B9696703");
		Signature s = Signature.getInstance("Schnorr", provider);
		s.initVerify(pub);
		s.update(msg);
		
		byte[] sig = Schnorr.hexStringToByteArray("00000000000000000000003B78CE563F89A0ED9414F5AA28AD0D96D6795F9C6302A8DC32E64E86A333F20EF56EAC9BA30B7246D6D25E22ADB8C6BE1AEB08D49D");
		
		assertFalse(s.verify(sig));
	}
}
