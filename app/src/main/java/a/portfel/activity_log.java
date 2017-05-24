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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("logi");

    ArrayList<EventLog> eventLogArrayList = new ArrayList<>();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logList = (ListView) findViewById(R.id.log_list);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // pobieramy wszystkie dane od nowa gdy zostanie wprowadzona zmiana
                eventLogArrayList.clear();
                for (DataSnapshot productsSnapshot : dataSnapshot.getChildren()){
                    EventLog mLog = productsSnapshot.getValue(EventLog.class);
                    eventLogArrayList.add(mLog);
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
