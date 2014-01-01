package com.jungbu.wedding_02;

 
 

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ViewPager mPager;
	ImageView countImageView = null;  
	private KaKaoFriend mKaKao;
	private static MediaPlayer mp;
	private int status = 0; 
	//생일을 적는다.
	private int mYear = 2014;
	private int mMonth = 2;
	private int mDay = 16;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mPager = (ViewPager)findViewById(R.id.view_pager);
		mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
		
		countImageView = (ImageView)findViewById(R.id.imageView1);
		
		 
	    mPager.setOnPageChangeListener(new OnPageChangeListener() {
		    
			public void onPageSelected(int position) {
		        // Check if this is the page you want.
		    	ImageCountViewReset(position);
		    }
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onPageScrolled(int position, float offset, int offsetPixel) {
			
			}
			
		});
	    
	    
		playBGM();
	}
	
	
	private void playBGM(){
		
		mp = MediaPlayer.create(this, R.raw.bgm);
		mp.setLooping(true);
		mp.start();
	}
	
	 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp.stop();
		mp.release();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		 
		//if(mp.isPlaying())
		            //  mp.pause();
		
	}
	
	@Override
	protected void onUserLeaveHint() {
		// TODO Auto-generated method stub
		super.onUserLeaveHint();
		
		if(status != 1){
		  if(mp.isPlaying())
                mp.pause();
		}
			
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		status = 0;
		if(!mp.isPlaying())
			            mp.start();
		
	}
	
	public void ImageCountViewReset(Integer position)
	{
		 
			if(position == 0)
			{
				countImageView.setImageResource(R.drawable.bg_event_p1);
			}
			else if(position == 1)
			{
				countImageView.setImageResource(R.drawable.bg_event_p2);
			}
			else if(position == 2)
			{
				countImageView.setImageResource(R.drawable.bg_event_p3);	
			}

	 
	}
	
	
	/**
	 * 페이져 뷰에서 버튼 처리
	 */
	private View.OnClickListener mPagerListener = new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	           
	        	 switch (v.getId()) {
			        case R.id.btn_click_photo:
			            // 사진 상세 페이지액티비티 실행 
			        	
			        	status = 1;  //엑티비티 이동 
			        	Intent myIntent = new Intent(getApplicationContext(), DetailActivity.class);      //자극 사진
						startActivity(myIntent);
			            break;
		         
			        case R.id.btn_click_kakao:
			        	
			        	status = 1;        // 다른 어플이 열리며 비활성화 될때 
			        	//카톡 로직 보여 주기
			        	openKaKao();
			        	break;
	        	 }
	        	/*
	        	String text = ((Button)v).getText().toString();
	            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();*/
	        }
	};
	     

	
	private class PagerAdapterClass extends PagerAdapter{

		
		private LayoutInflater mInflater;
		
		public PagerAdapterClass(Context c){
			
			super();
			mInflater = LayoutInflater.from(c);
			
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		   @Override
	        public Object instantiateItem(View pager, int position) {
	            View v = null;
	            if(position==0){
	                v = mInflater.inflate(R.layout.inflate_one, null);
	                v.findViewById(R.id.iv_one).setBackgroundResource(R.drawable.scene1);
	                TextView tv = (TextView)v.findViewById(R.id.cntdown);
	                
	                long d = getDay();
	                tv.setText(String.valueOf(d));
	                
	                
	            }else if(position==1){
	                v = mInflater.inflate(R.layout.inflate_two, null);
	               // v.findViewById(R.id.iv_two).setBackgroundResource(R.drawable.scene2);
	                
	                v.findViewById(R.id.btn_click_photo).setOnClickListener(mPagerListener);
	                v.findViewById(R.id.btn_click_kakao).setOnClickListener(mPagerListener);
	            }else{
	                v = mInflater.inflate(R.layout.inflate_three, null);
	                v.findViewById(R.id.iv_three).setBackgroundResource(R.drawable.mapdown2); 
	                //v.findViewById(R.id.btn_click_3).setOnClickListener(mPagerListener);
	            }
	             
	            ((ViewPager)pager).addView(v, 0);
	             
	            return v; 
	        }

		   
	  @Override
	  public void destroyItem(View pager, int position, Object view) {    
	            ((ViewPager)pager).removeView((View)view);
	  }
   
		   
		@Override
		public boolean isViewFromObject(View pager, Object obj) {
			 return pager == obj; 
		 
		}
		
	}
	
	private long getDay() {

		Calendar thatDay = Calendar.getInstance();
		thatDay.set(Calendar.DAY_OF_MONTH, mDay);
		thatDay.set(Calendar.MONTH, mMonth-1); // month는 0부터 시작 하므로 -1해준다.
		thatDay.set(Calendar.YEAR, mYear);
		Calendar today = Calendar.getInstance();
		long diff = thatDay.getTimeInMillis() - today.getTimeInMillis();

		long seconds = diff / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;

		return days;
		// System.out.print(days + " days, ");
		// System.out.print(hours + " hours, ");
		// System.out.print(minutes + " minutes, ");
		// System.out.print(seconds + " seconds.");
	}
	// 카톡
	public void openKaKao() {

		mKaKao = KaKaoFriend.getInstance(this);
		
		mKaKao.friendKaKao();

	}
	

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
