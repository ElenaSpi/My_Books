package com.example.firebaseexample.adapter;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseexample.R;
import com.example.firebaseexample.SecondFragment;
import com.example.firebaseexample.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> posts;
    private List<String> keys;
    String TAG = "PostAdapter";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference postRef = database.getReference("books");


    public PostAdapter(List<Post> posts, List<String> keys) {
        this.posts = posts;
        this.keys = keys;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, null, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position) {
        holder.bindData(this.posts.get(position));
        Log.i(TAG, "Item with position " + position);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKey(position);
                deleteItem(position);
            }
        });
    }

    public void updateData(List<Post> posts, List<String> keys) {
        this.posts = posts;
        this.keys = keys;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public void deleteItem(int position){
        String key = keys.get(position);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("books");
        ref.child(key).removeValue();
    }

    public String getKey(int position) {
        String key = keys.get(position);
        return key;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvAuthor;
        private final Button deleteButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            deleteButton = (Button) itemView.findViewById(R.id.btnDelete);
        }
        public void bindData(final Post post) {
            tvName.setText(post.getName());
            tvAuthor.setText(post.getAuthor());
        }
    }
}