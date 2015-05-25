package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class CommunicationThread extends Thread {
	Socket sock = null;
	
	public CommunicationThread(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		if (sock != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter pw = new PrintWriter(sock.getOutputStream());
				
				// read URL
				String url = br.readLine();
				Log.e("SERVER", "Got URL " + url);
				if (url.contains("bad")) {
					pw.println("Bad Address");
					pw.flush();
				} else {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(url);
					HttpResponse response = httpClient.execute(httpGet);
					HttpEntity httpGetEntity = response.getEntity();
					if (httpGetEntity != null) {  
					    // do something with the response
						String source = EntityUtils.toString(httpGetEntity);
						pw.println(source);
						pw.flush();
					} 
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
