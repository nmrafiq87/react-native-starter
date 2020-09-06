package com.reactnativestarter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

    // use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView.setHasFixedSize(true);

    // use a linear layout manager
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
            LinearLayoutManager.VERTICAL);
    recyclerView.addItemDecoration(dividerItemDecoration);

    List<Contact> contacts = new ArrayList<>();
    contacts.add(new Contact("1", "Rafiq", 33));
    contacts.add(new Contact("2","Mani",22));
    contacts.add(new Contact("3", "Ravi", 32));
    contacts.add(new Contact("4", "Sam", 21));
    // specify an adapter (see also next example)
    mAdapter = new ContactAdapter(contacts, this);
    recyclerView.setAdapter(mAdapter);
  }
}
