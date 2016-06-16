package com.example.viewpager_test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	private RadioGroup mRadioGroup;
	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private BaseAdapter mAdapter2;

	private int page = 0;
	private List<NewsItem> mDatas = new ArrayList<NewsItem>();

	private PullToRefreshListView mPullToRefresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mRadioGroup = (RadioGroup) findViewById(R.id.rg_xinmo_jiamo);
		mIndicator = (TabPageIndicator) findViewById(R.id.id_indictor);
		mViewPager = (ViewPager) findViewById(R.id.id_pager);
		mPullToRefresh = (PullToRefreshListView) findViewById(R.id.id_listview);
		
		mPullToRefresh.setMode(Mode.BOTH);
		mViewPager.setVisibility(View.VISIBLE);
		mIndicator.setVisibility(View.VISIBLE);
		mPullToRefresh.setVisibility(View.GONE);
		
		for (int i = 0; i < 5; i++) {
			NewsItem listItem1 = new NewsItem();
			listItem1.setContext("��������������һ���ǳ������Ĵ�ҵ�Ŷӣ�������ī....");
			listItem1.setImgLink("http://avatar.csdn.net/F/6/B/3_tuzongxun.jpg");
			listItem1.setLabeltext("90��");
			listItem1.setNickname("����");
			listItem1.setTime("05-12 10:04");
			listItem1.setTitle("���������е��ǻۣ������е��ǻ�");
			listItem1.setXumonum("����34����ī");
			listItem1.setZannum("41");
			
			mDatas.add(listItem1);

			NewsItem listItem2 = new NewsItem();
			listItem2.setContext("��������������һ����֪����ʲô�ķǳ������Ĵ�ҵ�Ŷ�....");
			listItem2.setImgLink("http://avatar.csdn.net/4/A/7/3_qq_23547831.jpg");
			listItem2.setLabeltext("�ж�����");
			listItem2.setNickname("����");
			listItem2.setTime("05-12 09:08");
			listItem2.setTitle("���������е��ǻۣ������е��ǻ�");
			listItem2.setXumonum("����45����ī");
			listItem2.setZannum("52");
			
			mDatas.add(listItem2);

		}
		
		mAdapter = new TabAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager, 0);
		
		ILoadingLayout startLabels = mPullToRefresh.getLoadingLayoutProxy(true, false);
		startLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		startLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ

		ILoadingLayout endLabels = mPullToRefresh.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("���ظ���...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		endLabels.setReleaseLabel("�ſ����ظ���...");// �����ﵽһ������ʱ����ʾ����ʾ
		
		ListView lv = mPullToRefresh.getRefreshableView();
		lv.setDivider(null);
		mAdapter2 = new Itemadapter(getApplicationContext(), mDatas);
		mPullToRefresh.setAdapter(mAdapter2);
		
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				switch (arg1) {
				case R.id.rb_xinmo:
					mViewPager.setVisibility(View.VISIBLE);
					mIndicator.setVisibility(View.VISIBLE);
					mPullToRefresh.setVisibility(View.GONE);
					
					break;

				case R.id.rb_jiamo:
					
					mIndicator.setVisibility(View.GONE);
					mViewPager.setVisibility(View.GONE);
					mPullToRefresh.setVisibility(View.VISIBLE);
					
					mPullToRefresh.setOnRefreshListener(new OnRefreshListener2<ListView>() {

						@Override
						public void onPullDownToRefresh(
								PullToRefreshBase<ListView> refreshView) {
							// TODO Auto-generated method stub
							new AsyncTask<Void, Void, Void>(){

								@Override
								protected Void doInBackground(Void... arg0) {
									// TODO Auto-generated method stub
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									return null;
								}
								
								protected void onPostExecute(Void result) {
									NewsItem listItem = new NewsItem();
									listItem.setImgLink("https://avatars3.githubusercontent.com/u/11755050?v=3&s=460");
									listItem.setNickname("lolo");
									listItem.setTitle("���������е��ǻۣ������е��ǻ�");
									listItem.setLabeltext("xxx");
									listItem.setContext("����ˢ�³ɹ�����������������");
									listItem.setTime("05-12 10:14");
									listItem.setXumonum("����xx����ī");
									listItem.setZannum("xx");

									mDatas.add(0, listItem);

									mAdapter2.notifyDataSetChanged();
									mPullToRefresh.onRefreshComplete();
								};
								
							}.execute();
						}

						@Override
						public void onPullUpToRefresh(
								PullToRefreshBase<ListView> refreshView) {
							// TODO Auto-generated method stub
							new AsyncTask<Void, Void, Void>() {

								@Override
								protected Void doInBackground(Void... params) {

									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									return null;
								}

								protected void onPostExecute(Void result) {

									page++;

									if (page == 4) {

										mPullToRefresh.onRefreshComplete();
										page--;
									} else {
										for (int i = 0; i < 10; i++) {
											NewsItem listItem = new NewsItem();
											listItem.setImgLink("http://avatar.csdn.net/5/9/9/2_appletable.jpg");
											listItem.setNickname("new");
											listItem.setTitle("���������е��ǻۣ������е��ǻ�");
											listItem.setLabeltext("xxx");
											listItem.setContext("����ˢ�³ɹ�����������������");
											listItem.setTime("05-12 10:14");
											listItem.setXumonum("����xx����ī");
											listItem.setZannum("xx");

											mDatas.add(listItem);
										}

										mAdapter2.notifyDataSetChanged();
										mPullToRefresh.onRefreshComplete();
									}

								};

							}.execute();
						}
					});
					
					break;
				}
			}
		});
		
	}

}
