package com.ddj.dudujia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.ddj.dudujia.R;
import com.ddj.dudujia.adapter.ProvinceAdapter;
import com.ddj.dudujia.base.BaseActivity;
import com.ddj.dudujia.bean.ProvinceEntity;
import com.ddj.dudujia.view.PinnedHeaderListView;
import com.ddj.dudujia.view.SlideBar;
import com.ddj.dudujia.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hanshaobo on 2016/11/22.
 */

public class ProvinceActivity extends BaseActivity {
    @BindView(R.id.headListView)
    PinnedHeaderListView headListView;
    @BindView(R.id.tvHeader)
    TextView tvHeader;
    @BindView(R.id.tvLetter)
    TextView tvLetter;
    @BindView(R.id.slideBar)
    SlideBar slideBar;
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.etSearch)
    EditText etSearch;
    private ArrayList<ProvinceEntity.Province> list;
    private ArrayList<ProvinceEntity.Province> filterList;
    private ProvinceAdapter mAdapter;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_province);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initView() {
//        titleView.setBackVisible(true);
//        titleView.setSplitLineInvisible(true);

        titleView.setFocusable(true);
        titleView.setFocusableInTouchMode(true);
        titleView.requestFocus();
    }

    private void initData() {
        filterList = new ArrayList<>();
        slideBar.setTextView(tvLetter);
        getProvince();

        headListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProvinceEntity.Province bean = filterList.get(position);
                String name = bean.name;
                Intent intent = getIntent();
                intent.putExtra("region", name);
                setResult(RESULT_OK, intent);
                myFinish();
            }
        });
    }


    private void initListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterList.clear();
                String str = s.toString();
                for (int i = 0; i < list.size(); i++) {
                    ProvinceEntity.Province province = list.get(i);
                    if (!province.letter.equals("热门城市")) {
                        if (province.name.contains(str) || province.province.contains(str) || province.letter.contains(str.toUpperCase()) || province.simple.contains(str.toUpperCase())) {
                            filterList.add(province);
                        }
                    }

                }
                mAdapter.notifyDataSetChanged();
            }
        });

//        headListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                isFirst = false;
//                if (scrollState == SCROLL_STATE_IDLE) {
//                    RxjavaUtil.doInUIThreadDelay(new UITask<Object>() {
//                        @Override
//                        public void doInUIThread() {
//                            slideBar.setVisibility(View.INVISIBLE);
//                        }
//                    }, 3, TimeUnit.SECONDS);
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                LogUtil.d(TAG, "onScroll" + firstVisibleItem);
//                if (isFirst) {
//                    slideBar.setVisibility(View.INVISIBLE);
//                }else{
//                    slideBar.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
    }


    private void getProvince() {
        String str = readLocalJson(this, "province.json");
        Gson gson = new GsonBuilder().create();
        ProvinceEntity provinceEntity = gson.fromJson(str, ProvinceEntity.class);

        setData(provinceEntity);
    }

    public String readLocalJson(Context context, String fileName) {
        String jsonString = "";
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultString;
    }

    private void setData(ProvinceEntity provinceEntity) {
        //sort
        list = provinceEntity.province;
        Collections.sort(list, comparator);
        filterList.addAll(list);
        for (int i = 0; i < 3; i++) {
            ProvinceEntity.Province province = filterList.get(filterList.size() - 1);
            filterList.remove(filterList.size() - 1);
            filterList.add(0, province);
        }

        //adapter
        mAdapter = new ProvinceAdapter(this, filterList);
        headListView.setAdapter(mAdapter);
        //sliderbar
        slideBar.setOnTouchLetterChangeListenner(new SlideBar.OnTouchLetterChangeListenner() {
            @Override
            public void onTouchLetterChange(MotionEvent event, String s) {
                tvLetter.setText(s);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        tvLetter.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        tvLetter.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        tvLetter.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
                if (mAdapter != null) {
//                    int position = mAdapter.getPositionForSection(s.charAt(0)); // 这个array就是传给自定义Adapter的
                    int position = mAdapter.getPositionForSection(s);
                    headListView.setSelection(position);
                }
            }
        });
    }

    Comparator<ProvinceEntity.Province> comparator = new Comparator<ProvinceEntity.Province>() {
        @Override
        public int compare(ProvinceEntity.Province o1, ProvinceEntity.Province o2) {
            return o1.letter.compareTo(o2.letter);

        }
    };
}
