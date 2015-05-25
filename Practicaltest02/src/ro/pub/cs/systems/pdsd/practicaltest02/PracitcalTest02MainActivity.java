package ro.pub.cs.systems.pdsd.practicaltest02;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class PracitcalTest02MainActivity extends Activity {
	ServerThread serverThread = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pracitcal_test02_main);
        
        Button servButton = (Button) findViewById(R.id.button1);
        servButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText serverPortEditText = (EditText) findViewById(R.id.editText2);
				String servport = serverPortEditText.getText().toString();
				if (servport == null || servport.isEmpty()) {
					return;
				}
				
				serverThread = new ServerThread(Integer.parseInt(servport));
				serverThread.start();
			}
		});
        
        Button clientButton = (Button) findViewById(R.id.button2);
        clientButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText addrText = (EditText) findViewById(R.id.editText6);
				String addr = addrText.getText().toString();
				
				EditText portText = (EditText) findViewById(R.id.editText4);
				int port1 = Integer.parseInt(portText.getText().toString());
				
				EditText urlText = (EditText) findViewById(R.id.editText5);
				String url = urlText.getText().toString();
				
				WebView w = (WebView) findViewById(R.id.webView1);
				ClientThread cli = new ClientThread(addr, port1, url, w);
				cli.start();
 			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pracitcal_test02_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
