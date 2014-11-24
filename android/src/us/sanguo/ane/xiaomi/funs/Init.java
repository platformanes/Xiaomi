package us.sanguo.ane.xiaomi.funs;

import us.sanguo.ane.xiaomi.context.IABCont;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.entry.MiAppInfo;
import com.xiaomi.gamecenter.sdk.entry.ScreenOrientation;

public class Init extends IABFunctionBase {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------init-------");
		try
		{
			String appId = $args[0].getAsString();
			String appKey = $args[1].getAsString();
			
			/**SDK初始化*/
			MiAppInfo appInfo = new MiAppInfo();
			appInfo.setAppId( appId );
			appInfo.setAppKey( appKey );
			appInfo.setOrientation( ScreenOrientation.vertical ); //横竖屏
			MiCommplatform.Init( getActivity(), appInfo );

		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
	}
	

	@Override
	public String getTag() {
		return IABCont.FUNS.INIT.toString();
	}
}
