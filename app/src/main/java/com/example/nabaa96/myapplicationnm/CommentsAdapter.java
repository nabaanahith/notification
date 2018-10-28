package com.example.nabaa96.myapplicationnm;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.like.OnLikeListener;

import com.example.nabaa96.myapplicationnm.callbacks.OnLikeCommentListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class CommentsAdapter extends ArrayAdapter<Comment> {


    private OnLikeCommentListener onLikeCommentListener;

    public CommentsAdapter(Context context, ArrayList<Comment> comments) {

        super(context, 0, comments);
    }

    popup c = new popup();

    boolean f = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Comment c1 = getItem(position);
        View convertView2;

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_popup, parent, false);
        convertView2 = LayoutInflater.from(getContext()).inflate(R.layout.comments1, parent, false);
       // final com.like.LikeButton img;
        final  ImageView img;
        img = convertView2.findViewById(R.id.img);
        // TextView name;
        TextView dtime;
        TextView desc;
        TextView fname;
        desc = convertView2.findViewById(R.id.disdesc);
        fname = convertView2.findViewById(R.id.disname);

        dtime = convertView2.findViewById(R.id.distime);


        //   name= convertView.findViewById(R.id.disname);
        View v = convertView2.findViewById(R.id.ret);
        desc.setText(c1.getDesc());
        fname.setText(c1.getName());
        dtime.setText((CharSequence) c1.getTime());
        final String y = c1.getId();
        //  name.setText(c1.getName());
        final DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference().child("commentes");


      /*  img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.oo(mDatabase,y,c1.getDesc(),c1.getName(),"1",c1.getTime());
//
            }
        });*/


       /* img.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
               img.setEnabled(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                img.setEnabled(false);
            }
        });*/

   img.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           // img.setUnlikeDrawable(new BitmapDrawable(getResources(), new IconicsDrawable(this, CommunityMaterial.Icon.cmd_emoticon).colorRes(android.R.color.darker_gray).sizeDp(25).toBitmap()));
          // img.setEnabled(true);

           //   Context context = null;
           // img.setImageDrawable(getContext().getDrawable(R.drawable.ic_like11));
           if (onLikeCommentListener!=null)
               onLikeCommentListener.onLikeComment(c1,position);
       }});









        return v;








    }


    public void setOnLikeCommentListener(OnLikeCommentListener onLikeCommentListener) {
        this.onLikeCommentListener = onLikeCommentListener;
    }







}
