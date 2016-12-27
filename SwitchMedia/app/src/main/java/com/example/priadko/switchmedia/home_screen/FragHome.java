package com.example.priadko.switchmedia.home_screen;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.priadko.switchmedia.FragBaseAbstract;
import com.example.priadko.switchmedia.R;
import com.example.priadko.switchmedia.display_items.adapter_horizontal.AdapterRecViewHorizontal;
import com.example.priadko.switchmedia.display_items.adapter_vertical.AdapterRecViewVertical;
import com.example.priadko.switchmedia.home_screen.presenter.FragHomePresenterImpl;
import com.example.priadko.switchmedia.home_screen.presenter.IFragHomePresenter;
import com.example.priadko.switchmedia.utils.recycler_view.ItemDecorLinVertical;
import com.squareup.picasso.Picasso;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHome extends FragBaseAbstract implements IFragHomeView {

    private IFragHomePresenter presenter;
    private RecyclerView recyclerView;
    private AdapterRecViewVertical adapterRecViewVertical;
    private FrameLayout layoutLoading;
    private SwipeRefreshLayout refreshLayout;
    private RelativeLayout layoutDetails;
    private Picasso picasso;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return id of rootLayout
     */
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home;
    }

    /**
     * Initialize your views here
     *
     * @param rootView parent view of the fragment
     */
    @Override
    protected void prepareViews(View rootView) {
        initRefresh(rootView);
        initLayoutLoading(rootView);
        initRecView(rootView);
        initPicasso();
        initDetails(rootView);
        presenter = new FragHomePresenterImpl();
    }

    private void initRefresh(View rootView) {
        refreshLayout = ((SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh));
        //noinspection deprecation
        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                presenter.loadData();
            }
        });
    }

    private void initLayoutLoading(View rootView) {
        layoutLoading = ((FrameLayout) rootView.findViewById(R.id.loading));
    }

    private void initRecView(View rootView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        int margin = getResources().getDimensionPixelOffset(R.dimen.margin_item_main);
        ItemDecorLinVertical decorator = new ItemDecorLinVertical(margin, margin, 0, margin, true, true);
        recyclerView = ((RecyclerView) rootView.findViewById(R.id.recView));
        recyclerView.addItemDecoration(decorator);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initPicasso() {
        picasso = Picasso.with(getContext());
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);
    }

    private void initDetails(View rootView) {
        layoutDetails = ((RelativeLayout) rootView.findViewById(R.id.layout_details));
        layoutDetails.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clickedOkDetailScreen();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ACTIONS
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onResume() {
        super.onResume();
        presenter.bind(this);
    }

    @Override
    public void onStop() {
        presenter.unBind();
        super.onStop();
    }

    private void showLoading(boolean show) {
        layoutLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showHideDetails(boolean show) {
        layoutDetails.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Data from server loaded or updated
     */
    @Override
    public void dataLoaded(String[][] data) {
        if (adapterRecViewVertical == null) {
            adapterRecViewVertical = new AdapterRecViewVertical(data, itemListener);
        } else {
            adapterRecViewVertical.setData(data, itemListener);
        }
        recyclerView.setAdapter(adapterRecViewVertical);
        showLoading(false);
        refreshLayout.setRefreshing(false);
    }

    /**
     * Loading started
     */
    @Override
    public void loadingStarted() {
        showLoading(true);
    }

    @Override
    public void showDetailScreen() {
        showHideDetails(true);
    }

    @Override
    public void hideDetailScreen() {
        showHideDetails(false);
    }

    @Override
    public void setDetailScreenTitle(String title) {
        ((TextView) layoutDetails.findViewById(R.id.textView_title)).setText(title);
    }

    @Override
    public void setDetailScreenImage(String imageUrl) {
        picasso.load(imageUrl)
                .fit()
                .error(R.drawable.ic_error_outline_white_24dp)
                .into(((ImageView) layoutDetails.findViewById(R.id.imageView_poster)));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  LISTENERS
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private AdapterRecViewHorizontal.ItemListener itemListener = new AdapterRecViewHorizontal.ItemListener() {
        @Override
        public void itemClicked(String title, String url) {
            presenter.itemClicked(title, url);
        }
    };
}
