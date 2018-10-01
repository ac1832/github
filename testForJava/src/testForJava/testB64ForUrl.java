package testForJava;

import java.io.UnsupportedEncodingException;


public class testB64ForUrl {

	public static void main(String[] args) throws UnsupportedEncodingException {
			EnDecryption en = new EnDecryption();
			
			String parameter = "fe290ad77a582aedb14c9352fd6d8bfd70a4b59b2dc967170e591f6baa17f75ac4926559a619a3802a51a7059f43d2b805e7a7f1e2f53f49640df63827fdef3c3d5778558a86aabe9092e15b9879e0c2a40f3cb94a8465a8402d3a7d7ab49b2e8b156e9c04dfce70440a0f9b3351f8c3d3281bc12f9dd3d7";
			System.out.println(en.decrypt(parameter));
			
			String url = java.net.URLDecoder.decode(en.decrypt("fe290ad77a582aed9973249801ce2da4cad9b1d56b91b5826757cb1df797f3353a60aee16216e5ca1c14828cf72511b805e7a7f1e2f53f49640df63827fdef3c3d5778558a86aabed545c84722354aed0dc2eb3c985e1eb87d74efbaf6c53e4fb53f58d18c67e90926c4034e628572e3d3281bc12f9dd3d7"), "utf8");
			System.out.println(url);
		

	}

	public static String decryptStr(String password) {
		
		EnDecryption en = null;
		try {
			en = new EnDecryption();
		} catch (Exception e) {
		}
		return en.decrypt(password);
		
	}
	public static String Decode(String str) throws UnsupportedEncodingException {
		
			return java.net.URLDecoder.decode(str, "utf8");
		
	}
}