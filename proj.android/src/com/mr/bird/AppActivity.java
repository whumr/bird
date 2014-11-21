/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.
 
http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package com.mr.bird;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.spot.SpotManager;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;

public class AppActivity extends Cocos2dxActivity {
	
	private static AppActivity app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		app = this;
		
		AdManager.getInstance(this).init("bfe235ac86875adc", "32e80d2579252174", false);  
        
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(  
                FrameLayout.LayoutParams.MATCH_PARENT,  
                FrameLayout.LayoutParams.WRAP_CONTENT);  
        // 设置广告条的悬浮位置   
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
        // 实例化广告条   
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);  
		adView.setAdListener(new AdViewListener() {
			@Override
			public void onSwitchedAd(AdView adView) {
				// 切换广告并展示
				System.out.println("onSwitchedAd");
			}

			@Override
			public void onReceivedAd(AdView adView) {
				// 请求广告成功
				System.out.println("onReceivedAd");
			}

			@Override
			public void onFailedToReceivedAd(AdView adView) {
				// 请求广告失败
				System.out.println("onFailedToReceivedAd");
			}
		});
		// 调用Activity的addContentView函数   
        this.addContentView(adView, layoutParams); 
        
        SpotManager.getInstance(this).loadSpotAds();  
//      设置展示超时时间，加载超时则不展示广告，默认0，代表不设置超时时间   
        SpotManager.getInstance(this).setSpotTimeout(10000);// 设置5秒   
        SpotManager.getInstance(this).setShowInterval(20);// 设置20秒的显示时间间隔      
	}
	
	public static Object getActivity(){
		return app;
	}
	
	public void showAds(){  
	    System.out.println("show ads");  
	    SpotManager.getInstance(this).showSpotAds(this);  
	}  
	  
	public void missionads(){  
	    SpotManager.getInstance(this).disMiss(true);  
	}  
	
	@Override  
	protected void onStop(){  
	    SpotManager.getInstance(this).disMiss(true);  
	    super.onStop();   
	}  
	
	@Override
	protected void onDestroy() {
		SpotManager.getInstance(this).unregisterSceenReceiver();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		System.out.println("onBackPressed。。。");
	    // 如果有需要，可以点击后退关闭插屏广告（可选）。
	    if (!SpotManager.getInstance(this).disMiss(true)) {
	        super.onBackPressed();
	    }
	}
}