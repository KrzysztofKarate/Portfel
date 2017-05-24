package a.portfel;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anna on 2017-05-17.
 */

public class EventLogAdapter extends ArrayAdapter<EventLog> {

    Activity context;
    List<EventLog> mLogs;

    /*
    adapter generuje liste rzeczy ktore maja sie znajdowac w ListView. Tutaj generujemy liste
    wszystkich aktywnosci
    */

    EventLogAdapter(Activity context, List<EventLog> mLogs){
        super(context, R.layout.table_row, mLogs);

        this.context = context;
        this.mLogs = mLogs;
    }

    //deklaruje z klasy abstrakcyjnej klase statyczna
    private static class ViewHolder {
        private TextView logName;
        private TextView logDate;
        private TextView logAmmount;
    }


    //generuje mi kazdy wiersz w listView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.table_row, null, true);

        ViewHolder holder = new ViewHolder();

        holder.logName = (TextView) view.findViewById(R.id.text_name);
        holder.logAmmount = (TextView) view.findViewById(R.id.text_ammount);
        holder.logDate = (TextView) view.findViewById(R.id.text_date);

        EventLog mLog = mLogs.get(position);

        holder.logName.setText(mLog.getTytulem());
        holder.logAmmount.setText(mLog.getKwota() + " zł");
        holder.logDate.setText(mLog.getData());

        return view;
    }

}
