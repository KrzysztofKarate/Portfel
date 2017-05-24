package a.portfel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class add_event extends AppCompatActivity {

    private EditText name_txt;
    private String date_txt;
    private EditText amount_txt;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = firebaseDatabase.getReference("logi");
    private DatabaseReference sRef = firebaseDatabase.getReference("stanKonta");

    static double stanKonta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name_txt = (EditText) findViewById(R.id.act_name);  //czy dobrze? super jest
        amount_txt= (EditText) findViewById(R.id.amount);  //czy dobrze? ok

        sRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    stanKonta = dataSnapshot.getValue(Double.class);
                }catch (Exception e){
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //poprzez add button dodajemy aktywność finansową
        Button addbtn1 = (Button) findViewById(R.id.adda_button1);
        addbtn1.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                // zczytywanie wartosci do nowego obiektu
                double kwota = Float.parseFloat(amount_txt.getText().toString());
                String tytul = name_txt.getText().toString();
                date_txt = Calendar.getInstance().getTime().toString();

                //
                EventLog mLog = new EventLog(kwota, date_txt, tytul);

                mRef.child(mLog.getData()).setValue(mLog);
                sRef.setValue(stanKonta + mLog.getKwota());
            }
        });
    }
}
