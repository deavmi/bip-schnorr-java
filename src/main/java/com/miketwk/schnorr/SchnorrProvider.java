package com.miketwk.schnorr;

import java.security.Provider;
import java.security.SignatureSpi;

/**
 * A Schnorr provider interface
 * which provides the implementations
 * of signature classes, like our
 * {@link SchnorrSignature}
 * 
 * @see SignatureSpi
 * @see SchnorrSignature
 * @author Tristan Brice Velloza Kildaire (deavmi)
 */
public class SchnorrProvider extends Provider
{
//	protected SchnorrProvider(String name, String version, String info)
	public SchnorrProvider()
	{
		 // TODO: What should this be set to?
		 // TODO: What should this be set to?
		super("SchnorrProvider", "", "");

		// Add our custom SignatureSpi
		put("Signature.Schnorr", SchnorrSignature.class.getName());
	}
}