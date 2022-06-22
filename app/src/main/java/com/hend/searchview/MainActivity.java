package com.hend.searchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.hend.searchview.adapter.CourseAdapter;
import com.hend.searchview.models.CourseModal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // creating variables for
    // our ui components.
    private RecyclerView courseRV;
    private SearchView searchView;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        searchView = findViewById(R.id.searchView);
        searchViewSetup();

        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    private void buildRecyclerView() {
        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();

        // below line is to add data to our array list.
        courseModalArrayList.add(new CourseModal("DSA", "DSA Self Paced Course"));
        courseModalArrayList.add(new CourseModal("JAVA", "JAVA Self Paced Course"));
        courseModalArrayList.add(new CourseModal("C++", "C++ Self Paced Course"));
        courseModalArrayList.add(new CourseModal("Python", "Python Self Paced Course"));
        courseModalArrayList.add(new CourseModal("Fork CPP", "Fork CPP Self Paced Course"));

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModalArrayList, MainActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }
    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<CourseModal> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (CourseModal item : courseModalArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }
    private void searchViewSetup(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
    }
}