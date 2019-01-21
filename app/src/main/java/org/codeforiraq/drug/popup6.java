package org.codeforiraq.drug;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.codeforiraq.drug.callbacks.OnLikeCommentListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class popup6 extends AppCompatActivity implements OnLikeCommentListener {
    private static final String TAG = "popup";
    DatabaseReference mDatabase;
    DatabaseReference mDatabaselike;
    ArrayList<Comment> arraycom;
    ArrayList<Comment> arraycom2;
    ImageView img;
    boolean like = false;
    int c;
    int cc;
    int count = 0;
    String flag;
    String key;
    String key2;
    FirebaseUser mauth;
    Comment comment;
    ListView commentl;
    ImageView b;
    EditText ed;
    int likee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabaselike = FirebaseDatabase.getInstance().getReference();
        ed = findViewById(R.id.editText);
        img = findViewById(R.id.img);
        flag = " ";
        likee = 0;
        c = 0;
        mauth = FirebaseAuth.getInstance().getCurrentUser();

        cc = -1;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("commentes6");
        mDatabaselike = FirebaseDatabase.getInstance().getReference().child("likes6"); // hon ghalat ok aha ana lm astamlah asasn ok
        final String auth = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        commentl = findViewById(R.id.listtt);

        arraycom = new ArrayList<>();
        arraycom2 = new ArrayList<>();

        key2 = mDatabaselike.child("likes2").push().getKey();
        comment = new Comment();


        mDatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Log.i("popup", "onDataChange: res des: " + postSnapshot.child("desc").getValue().toString() + "s :");

                    //   String describtion=postSnapshot.child("desc").getValue().toString();

                    String s = postSnapshot.child("accept").getValue().toString();
                    // String s= itm.accept("describtion");
                    if (s.equals("1")) {


                        Log.i("popup", "onDataChange: res : " + postSnapshot.child("accept").getValue().toString() + "s :" + s);

                        if (c >= cc)

                        {

                            Comment c11 = postSnapshot.getValue(Comment.class);
                            c11.setId(postSnapshot.getKey());

                            arraycom.add(c11);
                            c++;
                        } else {
                            c++;
                        }
                    }
                }
                CommentsAdapter adapter = new CommentsAdapter(getBaseContext(), arraycom);
                adapter.setOnLikeCommentListener(popup6.this);
                adapter.setCurrentUserUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                commentl.setAdapter(adapter);
                cc = arraycom.size();
                c = 0;


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }



    public void sen(View view) {
        final String[] desc = new String[1];
        final String[] name = new String[1];


        FirebaseAuth mauth;
        mauth = FirebaseAuth.getInstance();

        final String[] u = new String[1];

        final DatabaseReference mDatabase;
        final DatabaseReference mDatabase2;
        mauth = FirebaseAuth.getInstance();
        mDatabase2 = FirebaseDatabase.getInstance().getReference("users");
        mDatabase = FirebaseDatabase.getInstance().getReference("commentes6");
        FirebaseUser user = mauth.getCurrentUser();
        final String[] mn = {" "};
        if (mauth.getCurrentUser() != null) {
            final String uid = user.getUid();
            mDatabase2.child(uid + "/name").addValueEventListener(new ValueEventListener() {

                //
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mn[0] = dataSnapshot.getValue(String.class);
                    Log.i(TAG, "onDataChange: " + dataSnapshot.toString());
                    Log.i(TAG, "onDataChange: name :" + mn[0]);
                    Log.i(TAG, "onDataChange: Uid : " + uid);
                    int yy;
                    desc[0] = ed.getText().toString();
                    name[0] = mn[0];
                    Calendar c = Calendar.getInstance();
                    String time = DateFormat.getDateTimeInstance().format(c.getTime());
                    key = mDatabase.child("commentes6").push().getKey();

                    //  yy=oo(key,desc,name,time,yy);
                    comment = new Comment(desc[0], name[0], "0", time, 0);
                    comment.setLikedUsers(new ArrayList<String>());
                    comment.setId(key);

                    mDatabase.child(key).setValue(comment);
                    ed.setText(" ");

                    Toast.makeText(popup6.this, " your comment will appear when admin approve.",
                            Toast.LENGTH_SHORT).show();
//dash itm=new dash();
                    // String s= itm.accept(comment.getDesc());
                    u[0] = mn[0];


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }


    public void oo(final String kk, final String desc, final String name, String acc, final String time) {

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String ss = postSnapshot.getKey().toString();


                    if (ss.equals(kk)) {

                        count = postSnapshot.child("like").getValue(Integer.class);

                        count++;
                        Comment mm = new Comment(desc, name, "1", time, count);
                        mDatabase.child(kk).setValue(mm);
                        mm.setId(kk);
                        //comment.setLike(count);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onLikeComment(Comment comment, int position) {

        // TODO: 24/10/18  increment likes count
// hooon tamam !! ?yes ok;):d
        // TODO: 24/10/18  update likes of commnet on firebase
        List<String> likedUsers = comment.getLikedUsers();
        if (likedUsers == null)
            likedUsers = new ArrayList<>(); // because in first time no one like this comment so list is null ok ok

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (!likedUsers.contains(uid)) // check again if the user is not in the list ok ok
            likedUsers.add(uid);


        HashMap<String, Object> childrens = new HashMap<>();

        childrens.put("like6", comment.like++);
        // hon name faqt test ok ok
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd 'at' hh:mm:ss");

        childrens.put("lastUpdate", dateFormat.format(new Date()));

        childrens.put("likedUsers", likedUsers);

        mDatabase.child(comment.id).updateChildren(childrens).addOnSuccessListener(new OnSuccessListener<Void>() {


            @Override
            public void onSuccess(Void aVoid) {

                Log.i(TAG, "onSuccess: update likes success");
                // tamam hala
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: update likes failed  error : ", e);
            }
        });


    }
}