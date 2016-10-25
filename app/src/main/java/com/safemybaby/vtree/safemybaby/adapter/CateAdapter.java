package com.safemybaby.vtree.safemybaby.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.safemybaby.vtree.safemybaby.R;
import com.safemybaby.vtree.safemybaby.model.Category;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by Kill on 10/6/2016.
 */

public class CateAdapter extends BaseRecyclerAdapter<Category> {

    Context context;
    ItemClickListener itemClickListener;
    public CateAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener ;
    }

    @Override
    protected int getLayoutItem() {
        return R.layout.item_cate_handbook;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutItem(), parent, false);
        return new ViewHolderItem(view);
    }

    public class ViewHolderItem extends ViewHolder {
        @BindView(R.id.imgCateHandbook)
        ImageView imgCateHandbook;

        @BindView(R.id.holder_cate)
        LinearLayout itemLayout;

        public ViewHolderItem(View itemView) {
            super(itemView);
        }

        @Override
        public void populate(Category item, final int position) {
            super.populate(item, position);
            Picasso.with(context).load(item.getImg())
                    .error(R.mipmap.ic_launcher)
                    .into(imgCateHandbook);
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.clickItemListtener(v, position);
                }
            });
        }
    }
}
