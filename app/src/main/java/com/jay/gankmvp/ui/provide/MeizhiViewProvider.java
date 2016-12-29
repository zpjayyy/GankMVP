package com.jay.gankmvp.ui.provide;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jay.gankmvp.R;
import com.jay.gankmvp.data.entity.Meizhi;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by jay on 16/12/29.
 */
public class MeizhiViewProvider
        extends ItemViewProvider<Meizhi, MeizhiViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_meizi, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Meizhi meizi) {
        Uri uri = Uri.parse(meizi.url);
        holder.mImageMeizhi.setImageURI(uri);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_meizhi)
        SimpleDraweeView mImageMeizhi;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}