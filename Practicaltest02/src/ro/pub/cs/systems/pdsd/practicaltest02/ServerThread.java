package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class ServerThread extends Thread {
	ServerSocket servSock = null;
	boolean finished = false;
	
	public ServerThread(int port) {
		try {
			servSock = new ServerSocket(port);
			Log.e("PDSD", "Started server successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (!finished) {
			try {
				Socket socket = servSock.accept();
				CommunicationThread commThread = new CommunicationThread(socket);
				commThread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
