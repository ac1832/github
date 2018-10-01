package testForJava;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashMap;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;




public class Test {

	public static void main(String[] args) throws Exception {
//		 String json="{\"name\":\"刘德华\",\"age\":35}";
		String json = "{\"code\":200,\"msg\":\"success\"}";
		JSONObject jso = JSON.parseObject(json);// json字符串转换成jsonobject对象
		System.out.println(jso.toString());
//		System.out.println(signByMD5("busi_code=100401&err_code=&err_msg=&mer_no=761100000091919&mer_order_no=TestHfpayUcMO1809121555003316&order_amount=5110&order_no=1809120000000053004155539485&order_time=2018-09-12 15:55:39&pay_amount=5110&pay_time=2018-09-12 15:56:05&reserver=hfpaynew&status=SUCCESS&","588A028E963BC9BE4C9BF598990FA3B4"));
//		String password = signByMD5("busi_code=100401&err_code=&err_msg=&mer_no=761100000091919&mer_order_no=TestHfpayUcMO1809121555003316&order_amount=5110&order_no=1809120000000053004155539485&order_time=2018-09-12 15:55:39&pay_amount=5110&pay_time=2018-09-12 15:56:05&reserver=hfpaynew&status=SUCCESS&key=","588A028E963BC9BE4C9BF598990FA3B4");
//		
//		System.out.println((password));//.equalsIgnoreCase("dcd36ecdb5a8be3db61fae534a450233"));
//		
//		String payload = "{\"orderNo\":\"kf00228458\",\"customNo\":\"TestDbpayAaLI1809131237005028\",\"resultMsg\":\"成功\",\"bankCode\":\"ICBC\",\"userCode\":\"CC800195\",\"money\":1.100,\"currency\":\"RMB\",\"status\":1,\"orderType\":0,\"sign\":\"dcd36ecdb5a8be3db61fae534a450233\"}";
//		JSONObject.parseObject(payload, DepositCheckItem.class);
//		System.out.println(JSONObject.toJSON(payload));
//		DBpayCheckItem item = JSONObject.parseObject((payload), DBpayCheckItem.class);
//		System.out.println(item.getSign());
//		DBpayCheckItem item = JSONObject.parseObject(("Status=SUCCESS&Merchants=10156&Type=202&OrderNum=TestWeipayBaLI1809211500004776&Amount=11.00&sign=624C8623A6A119F352D7220F4B47A7CA&Member=erryaa5698"), DBpayCheckItem.class);
//		System.out.println(item.getMoney());
//		WEIpayCheckItem item = JSONObject.parseObject((payload), WEIpayCheckItem.class);
//		item.setParamMap(DataParse.getReqParameterMap(req));
//		BigDecimal b1 = new BigDecimal("100");
//		System.out.println(b1.divide(new BigDecimal(100)).toString());
	}
	
	public static String signByMD5(String sourceData,String key) throws Exception {
		String data = sourceData+key ;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] sign = md5.digest(data.getBytes("UTF-8"));

		return Bytes2HexString(sign).toUpperCase();
	}
	/**
	 * 将byte数组转成十六进制的字符串
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String Bytes2HexString(byte[] b) {
		StringBuffer ret = new StringBuffer(b.length);
		String hex = "";
		for (int i = 0; i < b.length; i++) {
			hex = Integer.toHexString(b[i] & 0xFF);

			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret.append(hex.toUpperCase());
		}
		return ret.toString();
	}
//	public static <T> T getDataObject(HttpServletRequest request ,Class<T> cls){
//		try {
//			T obj = cls.newInstance();
//			Enumeration<String> en = request.getParameterNames();
//			Method[] meth = cls.getMethods();
//			HashMap<String,Method> methTemp = new HashMap<String,Method>();
//			for ( Method m : meth ) methTemp.put(m.getName().trim().toUpperCase(), m);
//			
//			while(en.hasMoreElements())
//			{
//				String paramName=en.nextElement();
//				String value=request.getParameter(paramName);
//				String methodName = "SET"+paramName.toUpperCase();
//				
//				if(methTemp.get(methodName)!=null){
//					methTemp.get(methodName).invoke( obj , value);
//				}
//			}		
//			
//			return obj;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//    	return null;
//    } 
	 

}
