package com.horen.horenbase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseActivity;
import com.horen.horenbase.bean.DetailBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

public class DetailActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private DetailAdapter adapter;

    public static void startAction(Context context, String url) {
        Intent intent = new Intent();
        intent.setClass(context, DetailActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new DetailAdapter(R.layout.item, new ArrayList<DetailBean.ZhuboBean>());
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        getData();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DetailBean.ZhuboBean zhuboBean = DetailActivity.this.adapter.getData().get(position);
                VideoActivity.startAction(mContext, zhuboBean.getAddress(), UniCodeUtils.unicodeToString(zhuboBean.getTitle()));
            }
        });
    }


    private void getData() {
        mRxManager.add(Api.getDefult().getDetailList(getIntent().getStringExtra("url"))
                .compose(RxSchedulers.<DetailBean>io_main())
                .subscribeWith(new BaseObserver<DetailBean>(mContext, false) {
                    @Override
                    protected void _onNext(DetailBean bean) {
                        adapter.setNewData(bean.getZhubo());
                        refresh.finishRefresh();
                    }

                    @Override
                    protected void _onError(String message) {
                        refresh.finishRefresh();
                    }
                }));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }
}
