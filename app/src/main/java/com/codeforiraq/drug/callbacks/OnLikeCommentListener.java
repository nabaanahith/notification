package com.codeforiraq.drug.callbacks;

import com.codeforiraq.drug.Comment;

public interface OnLikeCommentListener {
    /**
     * @param comment : comment item
     * @param position : position of comment in list
     */
    void onLikeComment(Comment comment,int position);
}
