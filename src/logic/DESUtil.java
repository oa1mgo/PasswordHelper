package logic;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil {
	/**
	* DES加密算法
	* @param mode   模式： 加密，解密
	* @param data   需要加密的内容
	* @param keyData 密钥 8个字节数组
	* @return     将内容加密后的结果也是byte[]格式的
	*/
	public static byte[] des(int mode,byte[] data,byte[] keyData)
	{
	    byte[] ret = null;
	    //加密的内容存在并且密钥存在且长度为8个字节
	    if (data != null
	        && data.length>0
	        &&keyData!=null
	        && keyData.length==8) {
	      try {
	        Cipher cipher = Cipher.getInstance("DES");
	        DESKeySpec keySpec = new DESKeySpec(keyData);
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey key = keyFactory.generateSecret(keySpec);
	        cipher.init(mode, key);
	        ret = cipher.doFinal(data);
	      } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	      } catch (NoSuchPaddingException e) {
	        e.printStackTrace();
	      } catch (IllegalBlockSizeException e) {
	        e.printStackTrace();
	      } catch (BadPaddingException e) {
	        e.printStackTrace();
	      } catch (InvalidKeySpecException e) {
	        e.printStackTrace();
	      } catch (InvalidKeyException e) {
	        e.printStackTrace();
	      }
	    }
	    return ret;
	}
	//DES 加密
	public static byte[] desEncrypt(byte[] data,byte[] keyData){
	    return des(Cipher.ENCRYPT_MODE,data,keyData);
	}
	//DES 解密
	public static byte[] desDecrypt(byte[] data,byte[] keyData){
	    return des(Cipher.DECRYPT_MODE,data,keyData);
	}

	/**将16进制转换为2进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
	        if (hexStr.length() < 1)
	                return null;
	        byte[] result = new byte[hexStr.length()/2];
	        for (int i = 0;i< hexStr.length()/2; i++) {
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
	                result[i] = (byte) (high * 16 + low);
	        }
	        return result;
	}
	
	/**将2进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buf.length; i++) {
                    String hex = Integer.toHexString(buf[i] & 0xFF);
                    if (hex.length() == 1) {
                            hex = '0' + hex;
                    }
                    sb.append(hex.toUpperCase());
            }
            return sb.toString();
    }
}
