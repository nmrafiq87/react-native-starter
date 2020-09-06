package com.reactnativestarter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    public List<Contact> mDataset;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(Context context,View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.contact_text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ContactAdapter(List<Contact> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        MyViewHolder vh = new MyViewHolder(context,v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).name);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SampleReactActivity.class);
                Contact contact = mDataset.get(position);
                intent.putExtra("id", contact.id);
                SharedPreferences sharedPref = context.getSharedPreferences(PreferenceModule.COLOUR_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                Gson gson = new Gson();
                String json = gson.toJson(contact);
                editor.putString(contact.id, json);
                editor.commit();
                context.startActivity(intent);

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
