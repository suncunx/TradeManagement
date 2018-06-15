package com.architecture.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.architecture.R;
import com.architecture.model.AdapterBean;
import com.architecture.view.DropDownMenu;
import com.architecture.view.RadioFlow;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;
import java.util.List;

import skin.support.widget.SkinCompatTextView;

import static android.graphics.Color.BLACK;
import static com.blankj.utilcode.util.SizeUtils.dp2px;
import static com.tamic.novate.Novate.TAG;

/**
 * Created by Stephen Sun on 2017/5/8 0008.
 * Email:1243357168@qq.com
 */

public class BindUtil {
    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String url) {
        //        .error(R.mipmap.load_failed)
        if (!StringUtils.isEmpty(url)) {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    @BindingAdapter({"image", "camera"})
    public static void loadImageWithCamera(ImageView imageView, String url, @IdRes int resId) {
        //        .error(R.mipmap.load_failed)
        if (!StringUtils.isEmpty(url)) {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(resId).into(imageView);
        }
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, @IdRes int resId) {
        Glide.with(imageView.getContext()).load(resId).into(imageView);
    }

    //这里必须是Drawable，因为在app:placeHolder中使用不了@mipmap
    @BindingAdapter({"image", "placeHolder"})
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder) {
        //        .error(R.mipmap.load_failed)
        if (url != null && url.length() > 0) {
            File file = new File(url);
            if (file.exists())
                Glide.with(imageView.getContext()).load(file).placeholder(placeHolder).into(imageView);
        }

        Glide.with(imageView.getContext()).load(url).placeholder(placeHolder).into(imageView);
    }

    @BindingAdapter({"image", "placeHolder", "error"})
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder, Drawable error) {
        //        .error(R.mipmap.load_failed)
        Glide.with(imageView.getContext()).load(url).placeholder(placeHolder).error(error).into(imageView);
    }

    @BindingAdapter({"layoutManager", "itemDecoration", "adapter"})
    public static void setLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager manager, RecyclerView.ItemDecoration itemDecoration,
                                        RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(manager != null ? manager : new LinearLayoutManager(recyclerView.getContext()));
        if (itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
        if (adapter == null) {
            throw new NullPointerException("the adapter of recyclerView is null");
        }
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"fixSize"})
    public static void setHasFixSize(RecyclerView recyclerView, boolean fixSize) {
        recyclerView.setHasFixedSize(fixSize);
    }

    @BindingAdapter({"background"})
    public static void setTextBackground(TextView textView, int back) {
        if (back != -1) {
            textView.setBackgroundResource(back);
        }
    }

    @BindingAdapter({"background"})
    public static void setViewBackground(View view, int back) {
        if (back != -1) {
            view.setBackgroundResource(back);
        }
    }

    // 设置密码可见性
    @BindingAdapter({"transMethod"})
    public static void setTransMethod(EditText editText, TransformationMethod transMethod) {
        if (transMethod == null) {
            return;
        }
        editText.setTransformationMethod(transMethod);
    }

    // 可设置可不设置
    @BindingAdapter({"adapter", "itemClick", "loadMore"})
    public static void setRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, BaseQuickAdapter.OnItemClickListener itemClickListener,
                                       BaseQuickAdapter.RequestLoadMoreListener loadMoreListener) {

        adapter.setOnItemClickListener(itemClickListener);
        adapter.setOnLoadMoreListener(loadMoreListener, recyclerView);
    }

    @BindingAdapter({"adapterBean"})
    public static void setRecyclerView(RecyclerView recyclerView, AdapterBean adapterBean) {
        if (adapterBean == null) {
            LogUtils.d(recyclerView.getContext().toString());
            throw new NullPointerException("adapterBean is null,have you invoke method loadData or setVariable?");
        }

        if (adapterBean.getLayoutManager() != null) {
            recyclerView.setLayoutManager(adapterBean.getLayoutManager());
        }

        if (adapterBean.getItemDecoration() != null && recyclerView.getAdapter() == null) {
            Log.d(TAG, "setRecyclerView: adapterBean.getItemDecoration() != null && recyclerView.getAdapter() != null");
            recyclerView.addItemDecoration(adapterBean.getItemDecoration());
        }

        if (adapterBean.getAdapter() == null) {
            throw new NullPointerException("RecyclerView.Adapter in adapterBean is null");
        }
        recyclerView.setAdapter(adapterBean.getAdapter());
    }

    @BindingAdapter({"adapterBeanNull"})
    public static void setRecyclerViewNull(RecyclerView recyclerView, AdapterBean adapterBean) {
        if (adapterBean == null) {
            return;
        }

        if (adapterBean.getLayoutManager() != null) {
            recyclerView.setLayoutManager(adapterBean.getLayoutManager());
        }

        if (adapterBean.getItemDecoration() != null && recyclerView.getAdapter() == null) {
            Log.d(TAG, "setRecyclerViewNull: adapterBean.getItemDecoration() != null && recyclerView.getAdapter() != null");
            recyclerView.addItemDecoration(adapterBean.getItemDecoration());
        }

        if (adapterBean.getAdapter() == null) {
            return;
        }
        recyclerView.setAdapter(adapterBean.getAdapter());
    }

    @BindingAdapter({"viewPager"})
    public static void setViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, false);
    }

    @BindingAdapter({"viewPagerAutoRefresh"})
    public static void setViewPagerAutoRefresh(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter({"fragmentPagerAdapter"})
    public static void setFragmentPagerAdapter(ViewPager viewPager, FragmentPagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingAdapter({"cursor", "menuItem", "suggestions", "queryTextListen", "searchVewListen"})
    public static void setSearchView(MaterialSearchView searchView, int cursor, MenuItem menuItem,
                                     String[] suggestions,
                                     MaterialSearchView.OnQueryTextListener queryTextListener,
                                     MaterialSearchView.SearchViewListener searchViewListener) {
        searchView.setCursorDrawable(cursor);
        if (menuItem != null) {
            searchView.setMenuItem(menuItem);
        }
        searchView.setSuggestions(suggestions);
        searchView.setSubmitOnClick(true);
        searchView.setOnQueryTextListener(queryTextListener);
        searchView.setOnSearchViewListener(searchViewListener);
    }

    @BindingAdapter({"query"})
    public static void setSearchView(MaterialSearchView searchView, String query) {
        if (!StringUtils.isEmpty(query)) {
            LogUtils.d("setQuery");
            searchView.setQuery(query, true);
        }
    }

    @BindingAdapter({"close"})
    public static void setSearchView(MaterialSearchView searchView, boolean close) {
        if (close && searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
    }

    @BindingAdapter({"show"})
    public static void setSearchViewShow(MaterialSearchView searchView, boolean show) {
        if (show && !searchView.isSearchOpen()) {
            searchView.showSearch();
        }
    }

    @BindingAdapter({"names", "checkChange"})
    public static void setRadioGroup(RadioFlow radioFlow, String[] names, RadioFlow.OnCheckedChangeListener listener) {
        for (String name : names) {
            //TODO setStyle
            RadioButton button = new RadioButton(radioFlow.getContext());
            LinearLayout.LayoutParams blp = LinearLayoutUtil.W_W;
            blp.setMargins(38, 2, 38, 8);
            button.setLayoutParams(blp);
            button.setHeight(dp2px(34));
            button.setButtonDrawable(R.color.transparent);
            button.setBackgroundResource(R.drawable.button_radio_default);
            button.setText(name);

            button.setTextColor(BLACK);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            radioFlow.addView(button);
        }
        radioFlow.setOnCheckedChangeListener(listener);
    }

    @BindingAdapter({"clickListener"})
    public static void setClickListener(View view, View.OnClickListener clickListener) {
        if (clickListener != null) {
            view.setOnClickListener(clickListener);
        }
    }

    @BindingAdapter({"url"})
    public static void setWebView(WebView webView, String url) {
        webView.loadUrl(url);
    }

    @BindingAdapter({"font"})
    public static void setWebView(WebView webView, int textSize) {
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        switch (textSize) {
            case 1:
                settings.setTextZoom(75);
                break;
            case 2:
                settings.setTextZoom(100);
                break;
            case 3:
                settings.setTextZoom(125);
                break;
            case 4:
                settings.setTextZoom(150);
                break;
        }
    }

    @BindingAdapter({"tabTexts", "popupViews", "contentView"})
    public static void setDropDownMenu(DropDownMenu downMenu, List<String> tabTexts, List<View> popupViews, View contentView) {
        downMenu.setDropDownMenu(tabTexts, popupViews, contentView);
    }

    @BindingAdapter({"tabText"})
    public static void setDropDownMenu(DropDownMenu downMenu, String tabText) {
        downMenu.setTabText(tabText);
    }

    //    @BindingAdapter({"position"})
    //    public static void setDropDownMenu(DropDownMenu downMenu, int position) {
    //        downMenu.setCurrentPosition(position);
    //    }

    @BindingAdapter({"dropMenuClose"})
    public static void setDropDownMenu(DropDownMenu downMenu, boolean close) {
        if (downMenu.isShowing() && close) {
            downMenu.closeMenu();
        }
    }

    @BindingAdapter({"rentPopupChecked"})
    public static void setTextView(TextView textView, boolean checked) {
        Context context = textView.getContext();
        if (checked) {
            //            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
            ((SkinCompatTextView) textView).setTextAppearance(textView.getContext(), R.style.SkinCompatTextAppearance);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.drop_down_checked), null);
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @BindingAdapter({"rentPopupCheckedSimple"})
    public static void setTextViewCheckSimple(TextView textView, boolean checked) {
        Context context = textView.getContext();
        if (checked) {
            ((SkinCompatTextView) textView).setTextAppearance(textView.getContext(), R.style.SkinCompatTextAppearance);
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
        }
    }

    //    @BindingAdapter({"bind:image"})
    //    public static void loadImage(Context context, ImageView imageView, String url) {
    //        Glide.with(context).load(url).into(imageView);
    //    }
    //    @BindingAdapter({"bind:image"})
    //    public static void loadImage(Fragment fragment, ImageView imageView, String url) {
    //        Glide.with(fragment).load(url).into(imageView);
    //    }
    //    @BindingAdapter({"bind:image"})
    //    public static void loadImage(android.app.Fragment fragment, ImageView imageView, String url) {
    //        Glide.with(fragment).load(url).into(imageView);
    //    }
    //    @BindingAdapter({"bind:image"})
    //    public static void loadImage(FragmentActivity activity, ImageView imageView, String url) {
    //        Glide.with(activity).load(url).into(imageView);
    //    }
}
