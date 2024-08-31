package com.miketwk.schnorr;

import java.security.Key;
import java.security.PublicKey;

public class SchnorrKeyPub implements PublicKey
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
		return "SCHNORR-KEY";
	}

	@Override
	public String getFormat()
	{
		// TODO: What should this return?
				return "SCHNORR-KEY";
	}

	@Override
	public byte[] getEncoded()
	{
		// TODO: Pretty sure key-specific is how you get the bytes but screw
		// ... it, making this method do that
		byte[] tmp = new byte[33];
		System.arraycopy(this.kb, 0, tmp, 0, 33);
		return tmp;
	}
}