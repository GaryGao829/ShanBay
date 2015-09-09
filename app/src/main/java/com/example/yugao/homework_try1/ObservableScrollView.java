package com.example.yugao.homework_try1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by yugao on 15/9/8.
 */
public class ObservableScrollView extends ScrollView{
    private ScrollViewListener scrollViewListener = null;
    TextView textView;
    //ArrayList<String> StringArrayList;


    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
