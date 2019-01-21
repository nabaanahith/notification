package org.codeforiraq.drug;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;




    public class mytv extends android.support.v7.widget.AppCompatTextView {


        public mytv(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Cairo-Light.ttf"));
        }
    }


