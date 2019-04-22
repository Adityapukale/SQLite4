package com.example.expt1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Insert i;
    EditText name;
    EditText password;
    Button signUp,display;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i=new Insert(this);
        name=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        display = findViewById(R.id.button2);

        signUp=findViewById(R.id.button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean n= i.insertDate(name.getText().toString(),Integer.parseInt(password.getText().toString()));
                if(n==true){
                    Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs=i.Display();
                if(cs.getCount()==0){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    return;
                }
                
            StringBuffer stringBuffer=new StringBuffer();
                while(cs.moveToNext()){
                stringBuffer.append("Password:"+cs.getString(0)+"\n");
                stringBuffer.append("Name :"+cs.getString(1)+"\n");
            }
            text = stringBuffer.toString();
            Intent myIntent = new Intent(getBaseContext(),DisplayActivity.class);
            myIntent.putExtra("Text", text);
            startActivity(myIntent);
        }
    });

    }
}
