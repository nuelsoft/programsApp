package android.nuel.programsjournal;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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
//
//  Recycler View Resources
//    recycler is the xml view with id recyclerView
//    layoutManager is the Recycler View Layout Manager
//    adapter feeds the recycler
//
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

//
//  Viewpager Resources
//    .
//    .
//    .

    private static final int pageNumbers = 2;
    private ViewPager newViewPager;
    private PagerAdapter newPagerAdapter;


    // ONCREATE: BEGIN
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler Resources Initializations
        recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(layoutManager);

//        This specifies the number of elements in Recycler View
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        adapter = new MyAdapter(input);
        recycler.setAdapter(adapter);

//        View Pager Resources Initializations
        newViewPager = findViewById(R.id.pager);
        newPagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        newViewPager.setAdapter(newPagerAdapter);

    } // ONCREATE:END

//    RECYCLER ADAPTER CLASS: BEGIN
    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public List<String> values;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView eventTitle;
            public TextView location;
            public View layout;

            public ViewHolder(View v) {
                super(v);
                layout = v;
                eventTitle = v.findViewById(R.id.title);
                location = v.findViewById(R.id.location);
            }

        }

        public void add(int position, String item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        public MyAdapter(List<String> myDataSet) {
            values = myDataSet;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.row, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return values.size();
        }

    }// RECYCLER ADAPTER: END


    @Override
    public void onBackPressed() {

        if (newViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            newViewPager.setCurrentItem(newViewPager.getCurrentItem() - 1);
        }

    }

    //FRAGEMENT ADAPTER: BEGIN

    public static class FragmentAdapter extends FragmentStatePagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new FragmentActivity();
        }

        @Override
        public int getCount() {
            return pageNumbers;
        }
    } //FRAGMENT ADAPTER: END

}

