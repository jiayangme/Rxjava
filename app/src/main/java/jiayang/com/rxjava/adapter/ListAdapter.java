package jiayang.com.rxjava.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jiayang.com.rxjava.R;
import jiayang.com.rxjava.model.ZhuangbiImage;

/**
 * Created by xiangkai on 2016/11/29.
 */
public class ListAdapter extends RecyclerView.Adapter {
    private List<ZhuangbiImage> datas;
    private Context context;

    public ListAdapter(Context context, List<ZhuangbiImage> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //RecyclerView.Adapter 不用再设置 tag
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ZhuangbiImage image = datas.get(position);
        Glide.with(context).load(image.getImage_url()).into(viewHolder.imageIv);
        viewHolder.descriptionTv.setText(image.getDescription());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<ZhuangbiImage> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
