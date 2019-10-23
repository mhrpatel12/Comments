package com.mihir.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mihir.comments.swipe.SwipeDismissLayout;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    private float density;
    private RecyclerView mRecyclerView;
    private List<String> dataList;
    protected SwipeDismissLayout mSwipeDismissLayout;
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        density = getResources().getDisplayMetrics().density;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        setTitle(TAG);

        mSwipeDismissLayout = (SwipeDismissLayout) findViewById(R.id.swipeLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            dataList.add("Comment " + i);
        }
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (IsRecyclerViewAtTop()) {
                    mSwipeDismissLayout.setDirectionMode(SwipeDismissLayout.FROM_TOP);
                    //your recycler view reached Top do some thing
                } else if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mSwipeDismissLayout.setDirectionMode(SwipeDismissLayout.FROM_BOTTOM);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private boolean IsRecyclerViewAtTop() {
        if (mRecyclerView.getChildCount() == 0)
            return true;
        return mRecyclerView.getChildAt(0).getTop() == 0;
    }

    private RecyclerView.Adapter<ViewHolder> adapter = new RecyclerView.Adapter<ViewHolder>() {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(CommentsActivity.this);
            textView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (40 * density)));
            textView.setGravity(Gravity.CENTER);
            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    };

}
