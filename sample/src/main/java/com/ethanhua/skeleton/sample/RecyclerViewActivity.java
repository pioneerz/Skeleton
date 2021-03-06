package com.ethanhua.skeleton.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.ethanhua.skeleton.sample.adapter.NewsAdapter;
import com.ethanhua.skeleton.sample.adapter.PersonAdapter;
import com.ethanhua.skeleton.sample.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanhua on 2017/7/27.
 */

public class RecyclerViewActivity extends AppCompatActivity {


    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_LINEAR = "type_linear";
    public static final String TYPE_GRID = "type_grid";
    private String mType;

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mType = getIntent().getStringExtra(PARAMS_TYPE);
        init();
    }


    private void init() {
        if (TYPE_LINEAR.equals(mType)) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            NewsAdapter adapter = new NewsAdapter(getData());
            final SkeletonScreen skeletonScreen = Skeleton.bind(recyclerView)
                    .adapter(adapter)
                    .count(10)
                    .placeHolder(R.layout.item_skeleton_news)
                    .show();
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    skeletonScreen.hide();
                }
            }, 3000);
            return;
        }
        if (TYPE_GRID.equals(mType)) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            PersonAdapter adapter = new PersonAdapter(getData());
            final SkeletonScreen skeletonScreen = Skeleton.bind(recyclerView)
                    .adapter(adapter)
                    .placeHolder(R.layout.item_skeleton_person)
                    .count(10)
                    .show();
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    skeletonScreen.hide();
                }
            }, 3000);
        }
    }

    public List getData() {
        List datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsModel item1 = new NewsModel();
            item1.imgResID = R.drawable.img1;
            item1.newsContent = "这里是新闻这里是新闻这里是新闻这里是新闻这里是新闻这里是新闻这里是新闻这里是新闻这里是新闻";
            datas.add(item1);
        }
        return datas;
    }
}

