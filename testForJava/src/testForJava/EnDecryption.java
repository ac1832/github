package testForJava;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * encryption and decryption
 * @author Diven
 * 
 */
public class EnDecryption {
	private static String strDefaultKey = "finishjxf2012diven";

	/** 加密工具 */
	private Cipher encryptCipher = null;

	/** 解密工具 */
	private Cipher decryptCipher = null;

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 * 
	 */
	public static String byteArr2HexStr(byte[] arrB){
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 * 
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}



	

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws IllegalBlockSizeException, BadPaddingException{
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws IllegalBlockSizeException, BadPaddingException{
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn){
		try {
			return new String(decrypt(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private Key getKey(byte[] arrBTmp){
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}
	public static void main(String[] args) throws Exception{
		//jdbc:sqlserver://192.168.2.213:1433;DatabaseName=jxf2012
		//String str = "jdbc:sqlserver://192.168.2.213:1433;DatabaseName=jxf2012";
		EnDecryption ed = new EnDecryption();
		System.out.println( ed.encrypt( "10101030" ) );
//		System.out.println( ed.decrypt( "c20c0e8a42b2a87654d13b45ca79d732e5d3532c3e696a23f8b508bda2768d31af7458a00f120602734892779b1486848ffc2d6e0cb1da7b7979c9d1af102324192543c9b1294b350dadf913c234f3f7452ab645b779b4ed" ) );
//		System.out.println( ed.decrypt( "021af5b02b117febc20c0e8a42b2a87654d13b45ca79d732e5d3532c3e696a23f8b508bda2768d31af7458a00f120602734892779b1486848ffc2d6e0cb1da7b7979c9d1af102324192543c9b1294b350dadf913c234f3f7452ab645b779b4ed" ) );
//		
//		System.out.println("25:"+ ed.encrypt( "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGP5ZSNwi7WmNwKBcg1sKSvT0YITx5o6gapCM8Fnjx3sB1u2FSBi+IsKMVD1TQlNJ6VQ4yg9mKTy+GMOPpbiNFCitXD8IBNEDqPJo0Qvu3LEZQ8zYSl1/DF1vWvWxrKVenm1b2pUJOVg4FRkxBGZpei716UYVuJLiwGMQ10ExupwIDAQAB" ) );
//		System.out.println("24:"+ ed.encrypt( "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCc3bHD2U0fv4kXBkNTkbBs03aOZ3jeXpYAPCqhApLcf4EbQ3NuAIZWgK09ZfMO1PqKlDH1W0G0r87Z3n9cgJdGJ7cQ0uXRbQd5q8sPBdYQ+DP1j75EV+r9061P92G0PYUj8qZButoPllAzn0cY6sPcYTwcWGMjcBpjxLVhX3iIcQIDAQAB" ) );
//		                                 //
		                                 
//		System.out.println( ed.decrypt( "9fe0af790bb0a555c77e3c7b70a28e9e1431421778198" ) );
//		System.out.println( ed.encrypt( "kerryaacc" ) );
//		new Date( date )
//		String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format( new java.util.Date( 1430303549950l ) );
//		System.out.println( date );
		
		
		
	}
}
