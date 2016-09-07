package net.archeryc.nestedscrolldemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 一句话功能简述
 * 功能详细描述
 *
 * @author 杨晨 on 2016/9/6 09:44
 * @e-mail 247067345@qq.com
 * @see [相关类/方法](可选)
 */

public class ImageAdapter extends RecyclerView.Adapter{
    private Context mContext;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder= (ItemViewHolder) holder;
        viewHolder.sdvItem.setImageURI("http://7xpp4m.com1.z0.glb.clouddn.com/20150722110848_zjBZ5.jpeg");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdvItem;
        public ItemViewHolder(View itemView) {
            super(itemView);
            sdvItem = (SimpleDraweeView) itemView.findViewById(R.id.sdv_item);
        }
    }

}
