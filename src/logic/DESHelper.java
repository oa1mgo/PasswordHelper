package logic;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;


import com.sun.crypto.provider.SunJCE;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class DESHelper {
	public static void init() throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Security.addProvider(new SunJCE ());
		KeyGenerator deskg = null;
		deskg.getInstance("DES");
		Key k = deskg.generateKey();
		
		Cipher c = null;
		c.init(Cipher. ENCRYPT_MODE , k);
		byte[] desdata = "This a DES examplecode".getBytes();
		byte[]desresult =c. doFinal(desdata);
		
	}
	
}
