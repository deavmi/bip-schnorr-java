package com.miketwk.schnorr;

import java.util.List;

public class Utils
{
	public static byte[] fromListToBYtes(final List<Byte> bytes)
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