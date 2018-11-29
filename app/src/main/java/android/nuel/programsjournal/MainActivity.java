package android.nuel.programsjournal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);


        layoutManager = new GridLayoutManager(this,3);

        recycler.setLayoutManager(layoutManager);

        List<String>input = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            input.add("Test" + i);
        }

        adapter = new MyAdapter(input);

        recycler.setAdapter(adapter);
    }


    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public List<String> values;

            public class ViewHolder extends RecyclerView.ViewHolder{
                public TextView eventTitle;
                public TextView location;
                public View layout;

                public ViewHolder(View v){
                    super(v);
                    layout  = v;
                    eventTitle = v.findViewById(R.id.title);
                    location = v.findViewById(R.id.location);
                }

            }
            public void add(int position, String item){
                values.add(position, item);
                notifyItemInserted(position);
            }
            public void remove(int positon){
                values.remove(positon);
                notifyItemRemoved(positon);
            }
            public MyAdapter(List<String> myDataSet){
                values = myDataSet;
            }

            @Override
            public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View v = inflater.inflate(R.layout.row, parent, false);

                ViewHolder vh = new ViewHolder(v);
                return vh;
            }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
            public int getItemCount(){
                return values.size();
            }

    }
}
