package com.jay.gankmvp.provide;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jay.gankmvp.R;
import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by jay on 17/1/5.
 */

public class CategoryViewProvider
    extends ItemViewProvider<String, CategoryViewProvider.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_category, parent, false);
    return new ViewHolder(root);
  }

  @Override protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull String s) {
    holder.mTextCategory.setText(s);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_category) TextView mTextCategory;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
