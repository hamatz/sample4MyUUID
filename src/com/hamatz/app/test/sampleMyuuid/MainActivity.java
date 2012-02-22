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
	private final int REQUEST_CODE2 = 357;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final String alertMessage = getText(R.string.alert_message).toString();
        
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 Intent it = new Intent("com.hamatz.app.myuuid.ACTION_GET_UUID");
            	 it.setClassName("com.hamatz.app.myuuid","com.hamatz.app.myuuid.IPCActivity");
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
        
        findViewById(R.id.check_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 Intent it3 = new Intent("com.hamatz.app.myuuid.ACTION_GET_SYNCANCHOR");
            	 it3.setClassName("com.hamatz.app.myuuid","com.hamatz.app.myuuid.IPCActivity");
            	 try{
            		 startActivityForResult(it3, REQUEST_CODE2);
            	 }catch (android.content.ActivityNotFoundException e){
            		 // MyUUIDがインストールされてなかった場合の処理
            		 Toast.makeText(getApplicationContext(), alertMessage, Toast.LENGTH_SHORT).show();
            		 
            		 Uri uri = Uri.parse("market://details?id=com.hamatz.app.myuuid");
            		 Intent it4 = new Intent();
            		 it4.setAction(Intent.ACTION_VIEW);
            		 it4.setData(uri);
            		 startActivity(it4);
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
        final String message_ok3 = getText(R.string.msg5).toString();
        final String message_ok4 = getText(R.string.msg6).toString();
        
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
      if (requestCode == REQUEST_CODE2)
      {
    	  if (resultCode == -1){
    	  // 返り値がnullでも特にケアしていない点に注意
    		  String anchor = data.getStringExtra("Anchor");
    		  TextView text = (TextView) findViewById(R.id.text);
    		  text.setText(message_ok3 + " " + anchor + " " + message_ok4);
    	  }
      }
    }
}