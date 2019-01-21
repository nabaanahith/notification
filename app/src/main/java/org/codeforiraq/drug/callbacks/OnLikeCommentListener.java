package org.codeforiraq.drug.callbacks;

import org.codeforiraq.drug.Comment;

public interface OnLikeCommentListener {
    /**
     * @param comment : comment item
     * @param position : position of comment in list
     */
    void onLikeComment(Comment comment,int position);
}
