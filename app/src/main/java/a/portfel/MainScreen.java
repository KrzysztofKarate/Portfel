package a.portfel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.google.firebase.FirebaseApp;

public class MainScreen extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //przejscie przyciskiem add button do ekranu dodania akcji
        Button addbtn = (Button) findViewById(R.id.add_button);
        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, add_event.class);
                startActivity(intent);
            }
        });
        //przejscie przyciskiem activity_log button do ekranu z logami
        Button actbtn = (Button) findViewById(R.id.activ_button);
        actbtn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, activity_log.class);
                startActivity(intent);
            }
        });
        //przejscie przyciskiem grapgh button do wykresu stanu konta
        Button gphbtn = (Button) findViewById(R.id.graph_button);
        gphbtn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, graphs.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
