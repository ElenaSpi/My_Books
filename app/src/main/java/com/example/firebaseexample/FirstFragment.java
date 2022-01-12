package com.example.firebaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firebaseexample.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstFragment extends Fragment {

    EditText etName, etAuthor;
    Button btnUpload, btnViewAll,profile;

    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference postsRef = database.getReference("books");
    String userID;
    public static long num;
    public static long countItems;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        etName = (EditText) view.findViewById(R.id.etName);
        etAuthor = (EditText) view.findViewById(R.id.etAuthor);

        btnUpload = (Button) view.findViewById(R.id.btnUpload);
        btnViewAll = (Button) view.findViewById(R.id.btnView);
        profile = (Button) view.findViewById(R.id.btnView2);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = mAuth.getUid();

        view.findViewById(R.id.btnView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String name = etName.getText().toString();
                    String author = etAuthor.getText().toString();

                    if (name.isEmpty() || author.isEmpty()) {
                        return;
                    }
                    count();
                    uploadData(name, author);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_profile2);
            }
        });
    }

    private long count() {
        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countItems = dataSnapshot.getChildrenCount();
                Log.d("TAG", "countItems= " + countItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return countItems;
    }




    private void uploadData(String name, String author) {
        long items = count();
            postsRef.child("book" + items).setValue(new Post(mAuth.getUid(), name, author))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                                etName.setText("");
                                etAuthor.setText("");
                            }
                            else {
                                Toast.makeText(getActivity(), "Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
}