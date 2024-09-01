package com.miketwk.schnorr.jca;

import java.math.BigInteger;
import java.security.PrivateKey;

/**
 * A Schnorr private key
 * 
 * @author Tristan Brice Velloza Kildaire (deavmi)
 */
public final class SchnorrKeyPriv implements PrivateKey
{
	private final BigInteger key;
	
	public SchnorrKeyPriv(final String hex)
	{
		this.key = new BigInteger(hex, 16);
	}
	
	@Override
	public String getAlgorithm()
	{
		// TODO: What should this return?
		return "SCHNORR";
	}

	@Override
	public String getFormat()
	{
		return null;
	}

	@Override
	public byte[] getEncoded()
	{
		return null;
	}
	
	public BigInteger bigBoy()
	{
		return key;
	}
}