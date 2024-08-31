package com.miketwk.schnorr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Test suite for the schnorr implementation
 * 
 * @see Schnorr
 * @author michaeltan
 */
public class SchnorrTest
{
	@Test
	public void test() throws FileNotFoundException
	{
		boolean all_passed=true;
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(new File("test-vectors.csv"));
		scanner.nextLine(); //heading
		while(scanner.hasNextLine()) {
			String row=scanner.nextLine();
			int pos=row.indexOf(",");
			
			String index=row.substring(0, pos).trim();
			String seckey=row.substring(pos+1, pos=row.indexOf(",", pos+1)).trim();
			byte[] pubkey=Schnorr.hexStringToByteArray(row.substring(pos+1, pos=row.indexOf(",", pos+1)).trim());
			byte[] msg=Schnorr.hexStringToByteArray(row.substring(pos+1, pos=row.indexOf(",", pos+1)).trim());
			String sig=row.substring(pos+1, pos=row.indexOf(",", pos+1)).trim();
			boolean result="TRUE".equals(row.substring(pos+1, pos=row.indexOf(",", pos+1)).trim());
			String comment=row.indexOf(",", pos+1)==-1 ? "" : row.substring(pos+1, pos=row.indexOf(",", pos+1)).trim();
			
			System.out.println("\nTest vector "+index+":");
			if(!"".equals(seckey)) {
				BigInteger seckeyNum=new BigInteger(seckey,16);
				String sig_actual=Schnorr.bytesToHex(Schnorr.schnorr_sign(msg, seckeyNum));
				if(sig.equals(sig_actual))
					System.out.println(" * Passed signing test.");
				else {
					System.out.println(" * Failed signing test.");
					System.out.println("   Excepted signature:"+ sig);
					System.out.println("   Actual signature:"+ sig_actual);
	                all_passed = false;
				}
			}
			boolean result_actual = Schnorr.schnorr_verify(msg, pubkey, Schnorr.hexStringToByteArray(sig));
			if(result==result_actual)
				System.out.println(" * Passed verification test.");
			else {
				System.out.println(" * Failed verification test.");
				System.out.println("   Excepted verification result:"+ result);
				System.out.println("     Actual verification result:"+ result_actual);
                if(!"".equals(comment))
                	System.out.println("   Comment:"+ comment);
                all_passed = false;
			}
		}
		
	    if(all_passed)
	    {
	    	System.out.println("All test vectors passed.");
	    }
	    else
	    {
	    	System.out.println("Some test vectors failed.");
	    }
	    assertTrue(all_passed);
	}
}