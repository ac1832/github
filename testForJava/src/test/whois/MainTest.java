package test.whois;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.net.whois.WhoisClient;

import com.alibaba.fastjson.JSON;

public class MainTest {
	static ArrayList<String> mappingDetail = new ArrayList<String>();
	// whois domain list : http://www.nirsoft.net/whois_servers_list.html

	public static void main(String[] args) throws Exception {

		// String domainNameToCheck = "101wonder.com";

		// performWhoisQuery("ph.godaddy.com", 43, domainNameToCheck);
		// performWhoisQuery("whois.cnnic.net.cn", 43, domainNameToCheck);
		// getWhoisData(domainNameToCheck);

		// System.out.println(JSON.toJSONString(performWhois( domainNameToCheck )));
		
		System.out.println(InetAddress.getByName("www.falows.com"));
		
		// System.out.println( InetAddress.getByName("NS2.737BUY.COM") );
		//
		// whois.internic.net com,net,edu
		// whois.cnnic.net.cn cn
		// whois.nic.top top
		// testReadyTxt();
		
		System.out.println("done");
		
	}

	// public static void testReadyTxt() throws FileNotFoundException {
	// // txt檔
	// FileReader fr = new FileReader("D:\\workExcel\\toReady.txt");
	//
	// BufferedReader br = new BufferedReader(fr);
	// try {
	// while (br.ready()) {
	// mappingDetail.add(br.readLine());
	// System.out.println( InetAddress.getByName(br.readLine()) );
	// }
	// fr.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static void performWhoisQuery(String host, int port, String query) throws Exception {
		System.out.println("**** Performing whois query for '" + query + "' at " + host + ":" + port);

		Socket socket = new Socket(host, port);

		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(query);

		String line = "";
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

		isr.close();
		in.close();
		out.close();
		socket.close();
	}

	private static String[] registerMatchs = { "Registrar: ", "Sponsoring Registrar: ", "Registrar WHOIS Server: " };
	private static String[] dateMatchs = { "Registry Expiry Date: ", "Expiration Time: " };
	private static String[] nameMatchs = { "Name Server: " };
	private static int WHOIS_POST = 43;

	private static HashMap<String, String> whoisServers = new HashMap<String, String>();
	static {
		whoisServers.put("com", "whois.internic.net");
		whoisServers.put("net", "whois.internic.net");
		whoisServers.put("edu", "whois.internic.net");
		whoisServers.put("cn", "whois.cnnic.net.cn");
		whoisServers.put("top", "whois.nic.top");
		whoisServers.put("pw", "whois.nic.pw");
		whoisServers.put("org", "whois.pir.org");
	}

	public static HashMap<String, String> performWhois(String domain) throws Exception {
		String hostKey = domain.substring(domain.lastIndexOf(".") + 1).toLowerCase();
		String host = whoisServers.get(hostKey);

		System.out.println("**** Performing whois query for '" + domain + "' at " + host + ":" + WHOIS_POST);

		Socket socket = new Socket(host, WHOIS_POST);

		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(domain);

		HashMap<String, String> valueMap = new HashMap<String, String>();
		String line = "";
		while ((line = in.readLine()) != null) {
			System.out.println(line);

			for (String match : registerMatchs) {
				if (line.indexOf(match) >= 0) {
					valueMap.put("register", line.substring(line.indexOf(match) + match.length()));
				}
			}

			for (String match : dateMatchs) {
				if (line.indexOf(match) >= 0) {
					valueMap.put("expiryDate", line.substring(line.indexOf(match) + match.length()));
				}
			}

			for (String match : nameMatchs) {
				if (line.indexOf(match) >= 0) {
					String val = line.substring(line.indexOf(match) + match.length());

					if (valueMap.get("nameServer") != null) {
						val = valueMap.get("nameServer") + "|" + val;
					}

					valueMap.put("nameServer", val);
				}
			}
		}

		isr.close();
		in.close();
		out.close();
		socket.close();

		return valueMap;
	}

	public static void getWhoisData(String domain) {
		StringBuilder sb = new StringBuilder("");
		WhoisClient wic = new WhoisClient();
		try {
			wic.connect(WhoisClient.DEFAULT_HOST);
			String whoisData1 = wic.query("=" + domain);
			sb.append(whoisData1);
			wic.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
	}
}
