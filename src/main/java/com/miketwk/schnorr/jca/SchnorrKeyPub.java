package com.miketwk.schnorr.jca;

import java.security.PublicKey;

import com.miketwk.schnorr.core.Schnorr;

/**
 * A Schnorr public key
 * 
 * @author Tristan Brice Velloza Kildaire (deavmi)
 */
public final class SchnorrKeyPub implements PublicKey
{
	private byte[] kb;
	
	public SchnorrKeyPub(String hex)
	{
		this(Schnorr.hexStringToByteArray(hex));
	}
	
	public SchnorrKeyPub(final byte[] key)
	{
		if(key == null)
		{
			throw new NullPointerException("Key cannot be null");
		}
		else if(key.length != 33)
		{
			throw new IllegalArgumentException("The key must be 33 bytes long");
		}
		
		this.kb = new byte[33];
		System.arraycopy(key, 0, this.kb, 0, 33);
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
	
	public byte[] keyBytes()
	{
		byte[] tmp = new byte[33];
		System.arraycopy(this.kb, 0, tmp, 0, 33);
		return tmp;
	}
}