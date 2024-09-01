package com.miketwk.schnorr.misc;

import java.util.List;

/**
 * Utilities
 * 
 * @author Tristan Brice Velloza Kildaire (deavmi)
 */
public class Utils
{
	/**
	 * Converts a {@link List} of bytes into
	 * a byte array
	 * 
	 * @param bytes the input list
	 * @return a byte array
	 */
	public static byte[] fromListToBytes(final List<Byte> bytes)
	{
		byte[] b = new byte[bytes.size()];
		
		int i = 0;
		for(byte c: bytes)
		{
			b[i] = c;
			i++;
		}
		
		return b;
	}
}