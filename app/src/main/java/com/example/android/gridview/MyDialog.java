package com.example.android.gridview;

import android.content.Intent;
import android.view.Menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        Intent intent = getIntent();
        if(intent != null){
            int imageId = intent.getIntExtra("countryImage", R.drawable.andorra_texture);
            String countryName = intent.getStringExtra("countryName");
            ImageView myImage = (ImageView) findViewById(R.id.myDialogImage);
            myImage.setImageResource(imageId);
            TextView myText = (TextView) findViewById(R.id.myDialogText);
            myText.setText("This flag belong to " + countryName);
        }
    }
    public void closeDialog(View v){
        finish();
    }
}
