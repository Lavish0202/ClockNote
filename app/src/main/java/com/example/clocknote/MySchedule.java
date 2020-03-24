package com.example.clocknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySchedule extends AppCompatActivity {
    private RecyclerView recyclerView;
   private List<Items> scheduleitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scheduleitems=new ArrayList<>();
        for(int i=0;i<10;i++){
            Items item=new Items();
            item.setEventname("Event"+i);
            item.setEventdate("This is date for user"+i);
            scheduleitems.add(item);
        }
        recyclerView.setAdapter(new itemAdapter(scheduleitems));
    }
    class itemAdapter extends RecyclerView.Adapter<itemviewholder>{
        private List<Items> mitems;
        public itemAdapter(List<Items> items) {
            super();
            this.mitems=items;
        }

        @NonNull
        @Override
        public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new itemviewholder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull itemviewholder holder, int position) {
            holder.bind(this.mitems.get(position));
        }

        @Override
        public int getItemCount() {
            return this.mitems.size();
        }
    }
    class itemviewholder extends RecyclerView.ViewHolder{
        private TextView evname,totime,fromtime,evdate;
        public itemviewholder(ViewGroup container) {
            super(LayoutInflater.from(MySchedule.this).inflate(R.layout.schedule_items,container,false));
            totime=(TextView) findViewById(R.id.displayto);
            evname=(TextView) findViewById(R.id.displayev);
            fromtime=(TextView) findViewById(R.id.displayfrom);
            evdate=(TextView) findViewById(R.id.displaydate);
        }
        public void bind(Items item){
            evname.setText(item.getEventname());
            evdate.setText(item.getEventdate());
        }
    }
}
class Items{
private  String eventname,eventdate,totime,fromtime;

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }
}

