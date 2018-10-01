package testForJava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;

public class test2 {
	/** 解密工具 */
	private static Cipher decryptCipher = null;
	
	public static void main(String[] args) throws Exception {
		Calendar cl=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateString = sdf.format(new Date());
		System.out.println(new String(decrypt(hexStr2ByteArr("6E213E65C9E7FA0EF20EE8A670D45996"))));

	}
//	 private static long _TEN_THOUSAND=10000;
//	    public static void main(String[] args) {
//	        long times=1000*_TEN_THOUSAND;
//	        long t1=System.currentTimeMillis();
//	        testSystem(times);
//	        long t2=System.currentTimeMillis();
//	        System.out.println(t2-t1);
//	 
//	        testCalander(times);
//	        long t3=System.currentTimeMillis();
//	        System.out.println(t3-t2);
//	 
//	        testDate(times);
//	        long t4=System.currentTimeMillis();
//	        System.out.println(t4-t3);
//	    }
	 
	    public static void testSystem(long times){//use 188
	        for(int i=0;i<times;i++){
	            long currentTime=System.currentTimeMillis();
	        }
	    }
	 
	    public static void testCalander(long times){//use 6299
	        for(int i=0;i<times;i++){
	            long currentTime=Calendar.getInstance().getTimeInMillis();
	        }
	    }
	 
	    public static void testDate(long times){
	        for(int i=0;i<times;i++){
	            long currentTime=new Date().getTime();
	        }
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
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}
}
