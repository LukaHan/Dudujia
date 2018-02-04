package com.ddj.dudujia.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ddj.dudujia.R;
import com.ddj.dudujia.base.BaseActivity;
import com.ddj.dudujia.bean.ProvinceEntity;
import com.ddj.dudujia.view.PinnedHeaderListView;

import java.util.ArrayList;

public class ProvinceAdapter extends BaseAdapter
        implements OnScrollListener, PinnedHeaderListView.PinnedHeaderAdapter {

    // ===========================================================
    // Fields
    // ===========================================================

    private BaseActivity mContext;
    private ArrayList<ProvinceEntity.Province> mData;
    private LayoutInflater mLayoutInflater;
    private StarListener mStarListener;
    private boolean isTitleVisible = true; // 字母标题是否显示


    // ===========================================================
    // Constructors
    // ===========================================================

    public ProvinceAdapter(BaseActivity context, ArrayList<ProvinceEntity.Province> pData) {
        mContext = context;
        mData = pData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setListener(StarListener myStarListener) {
        mStarListener = myStarListener;
    }

    public void setTitleVisible(boolean is_title_visible) {
        isTitleVisible = is_title_visible;
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = initFullNews(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            Object object = convertView.getTag();
            if (object instanceof ViewHolder) {
                viewHolder = (ViewHolder) object;
            }
        }
        final ProvinceEntity.Province itemEntity = (ProvinceEntity.Province) getItem(position);
        viewHolder.tvProvince.setText("(" + itemEntity.province + ")");
        viewHolder.tvSimple.setText(itemEntity.name);


        if (isTitleVisible) {
            if (needTitle(position)) {
                viewHolder.tvTitle.setText(itemEntity.letter + "　");
                viewHolder.tvTitle.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvTitle.setVisibility(View.GONE);
            }
        } else {
            viewHolder.tvTitle.setVisibility(View.GONE);
        }

        return convertView;
    }

    private View initFullNews(ViewHolder viewHolder) {
        View convertView = mLayoutInflater.inflate(R.layout.listitem_province, null);

        viewHolder.tvProvince = (TextView) convertView.findViewById(R.id.tvProvince);
        viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        viewHolder.tvSimple = (TextView) convertView.findViewById(R.id.tvSimple);
        convertView.setTag(viewHolder);
        return convertView;
    }

    @Override
    public int getCount() {
        if (null != mData) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != mData && position < getCount()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (view instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) view).controlPinnedHeader(firstVisibleItem);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }


    @Override
    public int getPinnedHeaderState(int position) {
        if (getCount() == 0 || position < 0) {
            return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_GONE;
        }

        if (isMove(position) == true) {
            return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP;
        }

        return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_VISIBLE;
    }


    @Override
    public void configurePinnedHeader(View headerView, int position, int alpaha) {
        ProvinceEntity.Province itemEntity = (ProvinceEntity.Province) getItem(position);
        String headerValue = itemEntity.letter;

//		Log.e(TAG, "header = " + headerValue);

        if (!TextUtils.isEmpty(headerValue)) {
            TextView headerTextView = (TextView) headerView.findViewById(R.id.tvHeader);
            headerTextView.setText(headerValue);
        }

    }

    // ===========================================================
    // Methods
    // ===========================================================

    private boolean needTitle(int position) {
        if (position == 0) {
            return true;
        }

        if (position < 0) {
            return false;
        }

        ProvinceEntity.Province currentEntity = (ProvinceEntity.Province) getItem(position);
        ProvinceEntity.Province previousEntity = (ProvinceEntity.Province) getItem(position - 1);
        if (null == currentEntity || null == previousEntity) {
            return false;
        }

        String currentTitle = currentEntity.letter;
        String previousTitle = previousEntity.letter;
        if (null == previousTitle || null == currentTitle) {
            return false;
        }

        if (currentTitle.equals(previousTitle)) {
            return false;
        }

        return true;
    }


    private boolean isMove(int position) {
        ProvinceEntity.Province currentEntity = (ProvinceEntity.Province) getItem(position);
        ProvinceEntity.Province nextEntity = (ProvinceEntity.Province) getItem(position + 1);
        if (null == currentEntity || null == nextEntity) {
            return false;
        }

        String currentTitle = currentEntity.letter;
        String nextTitle = nextEntity.letter;
        if (null == currentTitle || null == nextTitle) {
            return false;
        }

        if (!currentTitle.equals(nextTitle)) {
            return true;
        }

        return false;
    }

    private class ViewHolder {
        private TextView tvTitle;
        private TextView tvProvince;
        private TextView tvSimple;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
//    public int getPositionForSection(int section) {
//        for (int i = 0; i < getCount(); i++) {
//            String sortStr = mData.get(i).region;
//            if (!TextUtils.isEmpty(sortStr)) {
//            	char firstChar = sortStr.toUpperCase().charAt(0);
//            	if (firstChar == section) {
//            		return i;
//            	}
//			}
//        }
//
//        return -1;
//    }
    public int getPositionForSection(String section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mData.get(i).letter;
            if (!TextUtils.isEmpty(sortStr)) {
                if (sortStr.equals(section)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public interface StarListener {
        public void careCheck(String member_id, int position);

        public void cancleCareCheck(String member_id, int position);
    }

}
