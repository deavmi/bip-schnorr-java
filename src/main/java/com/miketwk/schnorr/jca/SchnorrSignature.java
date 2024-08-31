package com.miketwk.schnorr.jca;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.util.ArrayList;
import java.util.List;

import com.miketwk.schnorr.core.Schnorr;
import com.miketwk.schnorr.misc.Utils;

/**
 * Schnorr signature SPI implementation
 * 
 * This class makes use of the routines
 * found in {@link Schnorr} - all of
 * the hardwork is there
 * 
 * @see SignatureSpi
 * @author Tristan Brice Velloza Kildaire (deavmi)
 */
public final class SchnorrSignature extends SignatureSpi
{
	private SchnorrKeyPriv priv;
	private SchnorrKeyPub pub;
	
	private List<Byte> bytes;
	
	private void freshInit()
	{
		this.bytes = new ArrayList<Byte>();
		this.priv = null;
		this.pub = null;
	}
	
	private boolean isInit()
	{
		return this.bytes != null;
	}
	
	@Override
	protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException
	{
		freshInit();
		
		if(!(publicKey instanceof SchnorrKeyPub))
		{
			throw new InvalidKeyException("Only SchnorrKeyPub is supported as public keys");
		}
		
		this.pub = (SchnorrKeyPub)publicKey;
	}

	@Override
	protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException
	{
		freshInit();
		
		if(!(privateKey instanceof SchnorrKeyPriv))
		{
			throw new InvalidKeyException("Only SchnorrKeyPriv is supported as private keys");
		}
		
		this.priv = (SchnorrKeyPriv)privateKey;
	}
	
	@Override
	protected void engineUpdate(byte b) throws SignatureException
	{
		if(!isInit())
		{
			throw new SignatureException("The engine has not yet initialized");
		}
		
		this.bytes.add(b);
	}

	@Override
	protected void engineUpdate(byte[] b, int off, int len) throws SignatureException
	{
		if(!isInit())
		{
			throw new SignatureException("The engine has not yet initialized");
		}
		
		if(b == null)
		{
			throw new SignatureException(new NullPointerException("Array is null"));
		}
		
		System.out.println("input len: "+b.length);
		System.out.println("offset: "+off);
		System.out.println("len: "+len);
		// [1,2,3]
		if(off < b.length && off+len <= b.length)
		{
			int start = off;
			int endExcl = off+len;
			
			for(int idx = start; idx < endExcl; idx++)
			{
				engineUpdate(b[idx]);
			}
		}
		else
		{
			throw new SignatureException("Invalid boundries");
		}
		
	}

	@Override
	protected byte[] engineSign() throws SignatureException
	{
		if(!isInit())
		{
			throw new SignatureException("The engine has not yet initialized");
		}
		else if(this.priv == null)
		{
			throw new SignatureException("The engine has not been initialized with a private key");
		}
		
		return Schnorr.schnorr_sign(Utils.fromListToBytes(this.bytes), this.priv.bigBoy());
	}

	@Override
	protected boolean engineVerify(byte[] sigBytes) throws SignatureException
	{
		if(!isInit())
		{
			throw new SignatureException("The engine has not yet initialized");
		}
		else if(this.pub == null)
		{
			throw new SignatureException("The engine has not been initialized with a public key");
		}
		
		return Schnorr.schnorr_verify
		(
			Utils.fromListToBytes(this.bytes),
			this.pub.keyBytes(),
			sigBytes
		);
	}

	@Override
	protected void engineSetParameter(String param, Object value) throws InvalidParameterException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object engineGetParameter(String param) throws InvalidParameterException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}