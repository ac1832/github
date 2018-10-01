package testForJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class test3 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://www.lnptyn.cn/pay/getPay");
        try {
            HttpResponse response = client.execute(request);
            System.out.println(response.getStatusLine());

        } catch (Exception e) {
            e.printStackTrace();

        }

	}

}
