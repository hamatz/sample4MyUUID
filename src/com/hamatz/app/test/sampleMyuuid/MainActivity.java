package com.hamatz.app.test.sampleMyuuid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private final int REQUEST_CODE = 123;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final String alertMessage = getText(R.string.alert_message).toString();
        
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 Intent it = new Intent("com.hamatz.app.myuuid.ACTION_GET_UUID");
            	 try{
            		 startActivityForResult(it, REQUEST_CODE);
            	 }catch (android.content.ActivityNotFoundException e){
            		 // MyUUIDがインストールされてなかった場合の処理
            		 Toast.makeText(getApplicationContext(), alertMessage, Toast.LENGTH_SHORT).show();
            		 
            		 Uri uri = Uri.parse("market://details?id=com.hamatz.app.myuuid");
            		 Intent it2 = new Intent();
            		 it2.setAction(Intent.ACTION_VIEW);
            		 it2.setData(uri);
            		 startActivity(it2);
            	 }
            }
        });
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        final String message_deny = getText(R.string.msg1).toString();
        final String message_none = getText(R.string.msg2).toString();
        final String message_ok1 = getText(R.string.msg3).toString();
        final String message_ok2 = getText(R.string.msg4).toString();
        
      if (requestCode == REQUEST_CODE)
      {
        if (resultCode != -1)
        {
          int i = data.getIntExtra("flag", 1);
          TextView text = (TextView) findViewById(R.id.text);
          if (i != 0)
            text.setText(message_deny);
          else
            text.setText(message_none);
        }
        else
        {
          String uuid = data.getStringExtra("UUID");
          TextView text = (TextView) findViewById(R.id.text);
          text.setText(message_ok1 + " " + uuid + " " + message_ok2);
        }
      }
    }
}