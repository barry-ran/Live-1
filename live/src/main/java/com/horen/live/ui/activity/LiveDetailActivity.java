package com.horen.live.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.base.app.CCName;
import com.horen.base.net.NetManager;
import com.horen.base.rx.BaseObserver;
import com.horen.base.rx.RxSchedulers;
import com.horen.base.ui.BaseActivity;
import com.horen.base.util.SnackbarUtils;
import com.horen.base.util.UniCodeUtils;
import com.horen.domain.live.Live2Detail;
import com.horen.domain.live.LiveDetail;
import com.horen.domain.live.LivePlatform;
import com.horen.live.R;
import com.horen.live.adapter.DetailAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class LiveDetailActivity extends BaseActivity implements OnRefreshListener, View.OnClickListener {

    private SmartRefreshLayout refresh;
    private RecyclerView recyclerView;
    private Toolbar toolBar;
    private AppCompatImageView ivRight;
    private DetailAdapter adapter;
    private String title;
    private String url;
    private String imageUrl;
    private LivePlatform platform;

    private int type;

    public static void startAction(Context context, String url, String title, String imageUrl, int type) {
        Intent intent = new Intent();
        intent.setClass(context, LiveDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.live_activity_live_detail;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        refresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        ivRight = (AppCompatImageView) findViewById(R.id.iv_right);
        ivRight.setOnClickListener(this);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        type = getIntent().getIntExtra("type", CCName.LIVE_1);
        ivRight.setVisibility(View.VISIBLE);
        initToolbar(toolBar, false);
        toolBar.setTitle(title);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapter = new DetailAdapter(R.layout.live_item, new ArrayList<LiveDetail.ZhuboBean>());
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        getData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (title.contains("欧美")) {
                    VideoActivity.startAction(mContext, LiveDetailActivity.this.adapter.getItem(position).getAddress(),
                            UniCodeUtils.unicodeToString(LiveDetailActivity.this.adapter.getItem(position).getTitle()),
                            UniCodeUtils.replaceHttpUrl(LiveDetailActivity.this.adapter.getItem(position).getImg()));
                } else {
                    RoomActivity.startAction(mContext,
                            (ArrayList<LiveDetail.ZhuboBean>) LiveDetailActivity.this.adapter.getData(), null, position);
                }
            }
        });

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                // 复制播放地址
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(LiveDetailActivity.this.adapter.getItem(position).getAddress());
                showShortToast("复制成功");
                return false;
            }
        });

        platform = LitePal.where("url=?", url)
                .findFirst(LivePlatform.class);
        checkCollectState(platform);
    }

    private void getData() {
        if (type == CCName.LIVE_1) {
            mRxManager.add(NetManager.getInstance().getLiveService().getDetailList(url)
                    .compose(RxSchedulers.<LiveDetail>io_main())
                    .subscribeWith(new BaseObserver<LiveDetail>(mContext, true) {
                        @Override
                        protected void _onNext(LiveDetail bean) {
                            adapter.setNewData(bean.getZhubo());
                            refresh.finishRefresh();
                        }

                        @Override
                        protected void _onError(String message) {
                            refresh.finishRefresh();
                        }
                    }));
        } else if (type == CCName.LIVE_2) {
            mRxManager.add(NetManager.getInstance().getLiveService().getLive2Detail(getLiveList(url))
                    .compose(RxSchedulers.<Live2Detail>io_main())
                    .subscribeWith(new BaseObserver<Live2Detail>(mContext, true) {
                        @Override
                        protected void _onNext(Live2Detail bean) {
                            adapter.setNewData(bean.getData().toList());
                            refresh.finishRefresh();
                        }

                        @Override
                        protected void _onError(String message) {
                            refresh.finishRefresh();
                        }
                    }));
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }

    private void checkCollectState(LivePlatform platform) {
        if (platform == null) {
            ivRight.setImageResource(R.drawable.icon_un_collect);
        } else {
            ivRight.setImageResource(R.drawable.icon_collect);
        }
    }

    @Override
    public void onClick(View view) {
        if (platform == null) { // 没有收藏过
            platform = new LivePlatform.Builder()
                    .setUrl(url)
                    .setImageUrl(imageUrl)
                    .setName(title)
                    .builder();
            if (platform.save()) SnackbarUtils.show(this, getString(R.string.collect_success));
        } else { // 已经收藏，删除收藏
            platform.delete();
            platform = null;
            SnackbarUtils.show(this, getString(R.string.collect_cancle));
        }
        checkCollectState(platform);
    }

    public static RequestBody getLiveList(String name) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
    }
}
