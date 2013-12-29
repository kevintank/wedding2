package com.jungbu.wedding_02;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
 

/*
 * 카톡 래퍼 클래스 
 */
public class KaKaoFriend {

    Context context;

    private static KaKaoFriend m_objInstance = null;
	
	public KaKaoFriend(Context context){
		
		this.context = context;         
    	 
	}
		
	public static KaKaoFriend getInstance(Context context)
    {
    	if(m_objInstance == null) {
			m_objInstance = new KaKaoFriend(context);
		}
        return m_objInstance;
    }
	
	
	/**
	 * 
	 * @param subject  제목
	 * @param id       보드 아이디
	 * @param key      보드 키
	 * @param Title    타이틀
	 */
	public void friendKaKao() {
		
		String strMessage = context.getResources().getString(R.string.kakao_name); //표시내용
		String strURL = "http://link.kakao.com";
		String strAppVer = "1.0";
		String strAppName;
		String strPackagName;
		
		//strMessage = strMessage + "[" + Title + "]" + subject;                   // 메시지 + [게시판명] + 제목
		strAppName = context.getResources().getString(R.string.app_name);
		strPackagName = context.getPackageName();
	    // String BoardID=id; 
		//String BoardKey=key;
		//String Subject=subject;
	     
		
		try {
			
			ArrayList<Map<String, String>> arrMetaInfo = new ArrayList<Map<String, String>>();

			Map<String, String> metaInfoAndroid = new Hashtable<String, String>(1);
			metaInfoAndroid.put("os", "android");
			metaInfoAndroid.put("devicetype", "phone");

			//안드로이드 마켓  
			metaInfoAndroid.put("installurl","market://details?id="+ strPackagName);        //없을때 마켓 이동 
				 
			//티스토어 
			//metaInfoAndroid.put("installurl","tstore://PRODUCT_VIEW/"+ strTstory + "/0");
			 
		    //메타데이타에 게시판 ID, 게시글 ID, 게시글 제목 3개를 보낸다.
		    //데이타를 보낸다. 앞이 스키마 뒤가 호스트
			//metaInfoAndroid.put("executeurl",  "MyungPum://host?str1="+BoardID+"&str2="+BoardKey+"&str3="+Subject);  //설치돼있다면 앱실행 

			arrMetaInfo.add(metaInfoAndroid);

			// If application is support ios platform.
			Map<String, String> metaInfoIOS = new Hashtable<String, String>(1);
			metaInfoIOS.put("os", "ios");
			metaInfoIOS.put("devicetype", "phone");
			metaInfoIOS.put("installurl","pass");  // 아이폰은 웹 주소만 링크 이므로 설치가 필요 없어 pass 문자열 기입 
			metaInfoIOS.put("executeurl", "http://marred.mireene.com/");  //이곳에 링크를 써준다.
			arrMetaInfo.add(metaInfoIOS);
			
			if (KakaoLink.getLink(context).isAvailableIntent()) {
				KakaoLink.getLink(context).openKakaoAppLink((Activity)context, strURL, strMessage, strPackagName, strAppVer, strAppName, "UTF-8", arrMetaInfo); 
			} else {
				Toast.makeText(context, "카카오톡이 설치되어있지 않습니다.", Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param subject  제목
	 * @param id       보드 아이디
	 * @param key      보드 키
	 * @param Title    타이틀
	 */
	public void friendKaKao(String subject, String id, String key, String Title) {
		
		String strMessage = context.getResources().getString(R.string.kakao_name); //표시내용
		String strURL = "http://link.kakao.com";
		String strAppVer = "1.0";
		String strAppName;
		String strPackagName;
		
		strMessage = strMessage + "[" + Title + "]" + subject;                   // 메시지 + [게시판명] + 제목
		strAppName = context.getResources().getString(R.string.app_name);
		strPackagName = context.getPackageName();
	    String BoardID=id; 
		String BoardKey=key;
		String Subject=subject;
	     
		
		try {
			
			ArrayList<Map<String, String>> arrMetaInfo = new ArrayList<Map<String, String>>();

			Map<String, String> metaInfoAndroid = new Hashtable<String, String>(1);
			metaInfoAndroid.put("os", "android");
			metaInfoAndroid.put("devicetype", "phone");

			//안드로이드 마켓  
			metaInfoAndroid.put("installurl","market://details?id="+ strPackagName);        //없을때 마켓 이동 
				 
			//티스토어 
			//metaInfoAndroid.put("installurl","tstore://PRODUCT_VIEW/"+ strTstory + "/0");
			 
		    //메타데이타에 게시판 ID, 게시글 ID, 게시글 제목 3개를 보낸다.
		    //데이타를 보낸다. 앞이 스키마 뒤가 호스트
			metaInfoAndroid.put("executeurl",  "MyungPum://host?str1="+BoardID+"&str2="+BoardKey+"&str3="+Subject);  //설치돼있다면 앱실행 

			arrMetaInfo.add(metaInfoAndroid);

			// If application is support ios platform.
			Map<String, String> metaInfoIOS = new Hashtable<String, String>(1);
			metaInfoIOS.put("os", "ios");
			metaInfoIOS.put("devicetype", "phone");
			metaInfoIOS.put("installurl","http://itunes.apple.com/kr/app/id654664525?mt=8");
			metaInfoIOS.put("executeurl", "myungpum2://" + BoardID + "//" + BoardKey + "//" + Subject);
			arrMetaInfo.add(metaInfoIOS);
			
			if (KakaoLink.getLink(context).isAvailableIntent()) {
				KakaoLink.getLink(context).openKakaoAppLink((Activity)context, strURL, strMessage, strPackagName, strAppVer, strAppName, "UTF-8", arrMetaInfo); 
			} else {
				Toast.makeText(context, "카카오톡이 설치되어있지 않습니다.", Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
