package com.simacoders.morteza.customrecyclerview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<classData> list = new ArrayList<>();
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        final MyAdapter adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);

        final EditText text = findViewById(R.id.editText);

        ImageView btnP1 = findViewById(R.id.imageViewP1);
        btnP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classData data = new classData();
                data.type = 1;
                data.text = text.getText().toString();
                DateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String time = formatter.format(new Date());
                data.time = time;
                adapter.add(data);
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
        });

        ImageView btnP2 = findViewById(R.id.imageViewP2);
        btnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classData data = new classData();
                data.type = 2;
                data.text = text.getText().toString();
                DateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String time = formatter.format(new Date());
                data.time = time;
                adapter.add(data);
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
        });



    }

    public class classData{
        public int type;
        public String text;
        public String time;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        ArrayList<classData> myDataset;
        int currentPosition;

        public MyAdapter(ArrayList<classData> myDataset) {
            this.myDataset = myDataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if(viewType == 1){
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_itemp1, parent, false);
            }else {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_itemp2, parent, false);
            }
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            classData data = myDataset.get(position);
            holder.textViewText.setText(data.text);
            holder.textViewTime.setText(data.time);
        }

        @Override
        public int getItemViewType(int position) {
            return myDataset.get(position).type;
        }

        @Override
        public int getItemCount() {
            return myDataset.size();
        }

        public void add(classData data){
            myDataset.add(data);
            notifyItemInserted(getItemCount()-1);
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView textViewText;
            public TextView textViewTime;
            public ViewHolder(View itemView) {
                super(itemView);
                textViewText = itemView.findViewById(R.id.textViewText);
                textViewTime = itemView.findViewById(R.id.textViewTime);
            }
        }
    }

}
