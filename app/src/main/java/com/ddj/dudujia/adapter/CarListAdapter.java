package com.ddj.dudujia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddj.dudujia.R;
import com.ddj.dudujia.base.CarListBean;
import com.ddj.dudujia.common.CommonMethod;
import com.ddj.dudujia.utils.ImageUtils;
import com.first.basket.common.CommonMethod1;
import com.first.basket.utils.LogUtils;

import java.util.List;

/**
 * Created by hanshaobo on 24/09/2017.
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder> {
    private Context context;
    private List<CarListBean.CarBean> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private MyViewHolder holder;
    private int layoutPosition;

    public interface OnItemClickListener {
        void onBaoyangItemClick(View view, CarListBean.CarBean data, int position);

        void onWeizhangItemClick(View view, CarListBean.CarBean data, int position);

        void onJianceItemClick(View view, CarListBean.CarBean data, int position);

        void onItemClick(View view, CarListBean.CarBean data, int position);
    }

    public CarListAdapter(Context context, List<CarListBean.CarBean> data) {
        this.context = context;
        this.mDatas = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_recycler_car, null);
        holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CarListBean.CarBean carBean = mDatas.get(position);
        holder.tvPlate.setText(carBean.getLicenseplate());
        holder.tvModel.setText("思域");
        holder.tvVin.setText(carBean.getVinnum());

//        ImageUtils.showImg(context, carBean.getImage(), holder.ivImg);

        holder.itemView.setTag(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前点击的位置
                layoutPosition = holder.getLayoutPosition();
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(holder.itemView, (CarListBean.CarBean) holder.itemView.getTag(), layoutPosition);
            }
        });

        holder.tvBaoyang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onBaoyangItemClick(holder.itemView, (CarListBean.CarBean) holder.itemView.getTag(), layoutPosition);
            }
        });
        holder.tvWeizhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onWeizhangItemClick(holder.itemView, (CarListBean.CarBean) holder.itemView.getTag(), layoutPosition);
            }
        });
        holder.tvJiance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onJianceItemClick(holder.itemView, (CarListBean.CarBean) holder.itemView.getTag(), layoutPosition);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPlate;
        private final TextView tvModel;
        private final TextView tvVin;
        private final TextView tvBaoyang;
        private final TextView tvJiance;
        private final TextView tvWeizhang;
        private final ImageView ivImg;

        MyViewHolder(View itemView) {
            super(itemView);
            tvPlate = itemView.findViewById(R.id.tvPlate);
            tvModel = itemView.findViewById(R.id.tvModel);
            tvVin = itemView.findViewById(R.id.tvVin);
            tvBaoyang = itemView.findViewById(R.id.tvBaoyang);
            tvWeizhang = itemView.findViewById(R.id.tvWeizhang);
            tvJiance = itemView.findViewById(R.id.tvJiance);
            ivImg = itemView.findViewById(R.id.ivImg);
        }
    }
}
