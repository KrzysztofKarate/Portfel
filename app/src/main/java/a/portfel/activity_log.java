package a.portfel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_log extends AppCompatActivity {

    ListView logList;
    TextView txtTitle;
    FirebaseDatabase database = FirebaseDatabase.getInstance(); //deklarujemy firebase
    DatabaseReference myRef = database.getReference("logi"); //tworzymy referencje do katalogu "logi"

    ArrayList<EventLog> eventLogArrayList = new ArrayList<>(); //lista do przetwarzania danych

    ArrayAdapter adapter;   //adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logList = (ListView) findViewById(R.id.log_list); //deklaracja Listy wyświetlania

        myRef.addValueEventListener(new ValueEventListener() { //dodajemy nasłuchiwacz zmian
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { // pobieramy wszystkie dane od nowa gdy zostanie wprowadzona zmiana
                eventLogArrayList.clear();  //czyścimy listę w przypadku nastąpienia zmiany w bazie by nie dublowało elementów

                for (DataSnapshot productsSnapshot : dataSnapshot.getChildren()){ //w petli pobieramy dane
                    EventLog mLog = productsSnapshot.getValue(EventLog.class); //tworzymi pojedyncze logi
                    eventLogArrayList.add(mLog); // dodajemy logi do listy
                }

                // ponownie generuje listView
                adapter = new EventLogAdapter(activity_log.this, eventLogArrayList);
                logList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }

}
