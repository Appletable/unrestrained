package com.example.viewpager_test;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class Itemadapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<NewsItem> mDatas;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public Itemadapter(Context context, List<NewsItem> mDatas) {
		// TODO Auto-generated constructor stub
		
		this.mDatas = mDatas;
		mInflater = LayoutInflater.from(context);
		
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.icon_tx)
				.showImageForEmptyUri(R.drawable.icon_tx)
				.showImageOnFail(R.drawable.icon_tx).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20))
				.displayer(new FadeInBitmapDisplayer(300)).build();
		
	}

	public void addAll(List<NewsItem> mDatas) {
		this.mDatas.addAll(mDatas);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mDatas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (arg1 == null) {
			arg1 = mInflater.inflate(R.layout.activity_item, null);

			holder = new ViewHolder();
			holder.mProfile = (ImageView) arg1.findViewById(R.id.iv_profile);
			holder.mNickname = (TextView) arg1.findViewById(R.id.tv_nickname);
			holder.mTitle = (TextView) arg1.findViewById(R.id.title);
			holder.mLabel = (TextView) arg1.findViewById(R.id.tv_labeltext);
			holder.mContext = (TextView) arg1.findViewById(R.id.tv_context);
			holder.mTime = (TextView) arg1.findViewById(R.id.tv_time);
			holder.mXumunum = (TextView) arg1.findViewById(R.id.tv_xumonum);
			holder.mZannum = (TextView) arg1.findViewById(R.id.tv_zannum);

			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}

		NewsItem newsItem = mDatas.get(arg0);
		holder.mNickname.setText(newsItem.getNickname());
		holder.mTitle.setText(newsItem.getTitle());
		holder.mContext.setText(newsItem.getContext());
		holder.mLabel.setText(newsItem.getLabeltext());
		holder.mTime.setText(newsItem.getTime());
		holder.mXumunum.setText(newsItem.getXumonum());
		holder.mZannum.setText(newsItem.getZannum());

		if (newsItem.getImgLink() != null) {
			holder.mProfile.setVisibility(View.VISIBLE);
			Log.d("adapter2", "newsItem.getImgLink() = " + newsItem.getImgLink());
			imageLoader.displayImage(newsItem.getImgLink(), holder.mProfile,
					options);
		} else {
			holder.mProfile.setVisibility(View.GONE);
		}

		return arg1;
	}

	private final class ViewHolder {
		ImageView mProfile;
		TextView mNickname;
		TextView mTitle;
		TextView mLabel;
		TextView mContext;
		TextView mTime;
		TextView mXumunum;
		TextView mZannum;

	}

}
