package a.portfel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class add_event extends AppCompatActivity {

    private EditText name_txt;
    private String date_txt;
    private EditText amount_txt;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = firebaseDatabase.getReference("logi");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name_txt = (EditText) findViewById(R.id.act_name);  //czy dobrze? super jest
        amount_txt= (EditText) findViewById(R.id.amount);  //czy dobrze? ok

        //poprzez add button dodajemy aktywność finansową
        Button addbtn1 = (Button) findViewById(R.id.adda_button1);
        addbtn1.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){

                // zczytywanie wartosci do nowego obiektu
                double kwota = Float.parseFloat(amount_txt.getText().toString());
                String tytul = name_txt.getText().toString();
                date_txt = Calendar.getInstance().getTime().toString();

                //
                finan_activ mLog = new finan_activ(kwota, date_txt, tytul);

                mRef.child(mLog.getData()).setValue(mLog);
            }
        });
    }
}
