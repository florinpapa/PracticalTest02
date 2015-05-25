package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.webkit.WebView;

public class ClientThread extends Thread {
	Socket sock = null;
	String url;
	WebView web;
	
	public ClientThread(String addr, int port, String url, WebView w) {
		try {
			sock = new Socket(addr, port);
			this.url = url;
			web = w;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		if (sock != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter pw = new PrintWriter(sock.getOutputStream());
				
				// read URL
				pw.println(url);
				
				String result = "";
				String curr = br.readLine();
				while (curr != null) {
					result += curr + "\n";
					curr = br.readLine();
				}
				
				final String res = result;
				web.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						web.loadData(res, "text/html", "UTF-8");
					}
				});
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
