package net.archeryc.nestedscrolldemo.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.archeryc.nestedscrolldemo.R;

public class ScrollViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        recyclerView = (RecyclerView) findViewById(R.id.rv_hot);
        recyclerView.setVisibility(View.GONE);
    }
}
