package com.miketwk.schnorr.misc;

import java.util.List;

public class Utils
{
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