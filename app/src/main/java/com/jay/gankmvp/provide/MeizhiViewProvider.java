package com.jay.gankmvp.provide;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jay.gankmvp.R;
import com.jay.gankmvp.data.entity.Gank;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.jay.gankmvp.ui.gank.GankActivity;
import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by jay on 16/12/29.
 */
public class MeizhiViewProvider extends ItemViewProvider<Gank, MeizhiViewProvider.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_meizi, parent, false);
    return new ViewHolder(root);
  }

  @Override protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Gank meizi) {

    holder.gank = meizi;

    Uri uri = Uri.parse(meizi.url);
    holder.mImageMeizhi.setImageURI(uri);

    holder.mTextDesc.setText(meizi.desc);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_meizhi) SimpleDraweeView mImageMeizhi;
    @BindView(R.id.text_desc) TextView mTextDesc;

    private Gank gank;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          v.getContext().startActivity(GankActivity.newIntent(v.getContext(), gank.publishedAt, gank.url));
        }
      });
    }
  }
}