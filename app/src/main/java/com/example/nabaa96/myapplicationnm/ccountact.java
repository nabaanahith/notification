package com.example.nabaa96.myapplicationnm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ccountact extends AppCompatActivity {
LinearLayout text;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccountact);
        text=findViewById(R.id.eem);
        final Uri u= Uri.parse("anon@gmail.com");

    text.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Intent.ACTION_SEND);
            n.setData(u);
            String [] s={"naba.nahith@gmail.com "};
            n.putExtra(Intent.EXTRA_EMAIL,s);
            n.setType("message/rfc822");
            startActivity(Intent.createChooser(n,"send feedback"));
        }
    });
    }
}
