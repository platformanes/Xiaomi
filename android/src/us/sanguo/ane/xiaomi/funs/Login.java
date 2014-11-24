package us.sanguo.ane.xiaomi.funs;

import us.sanguo.ane.xiaomi.context.IABCont;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
import com.xiaomi.gamecenter.sdk.OnLoginProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;

public class Login extends IABFunctionBase implements OnLoginProcessListener {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------login-------");
		try
		{
			// 调用SDK执行登陆操作
			MiCommplatform.getInstance().miLogin( getActivity(), Login.this );
		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
	}

	@Override
	public void finishLoginProcess( int arg0, MiAccountInfo arg1 )
	{
		if ( MiErrorCode.MI_XIAOMI_PAYMENT_SUCCESS == arg0 )
		{
			dispatch("{\"code\":\"30000\", \"name\":\""+ arg1.getNikename() +"\", \"uid\":\""+ arg1.getUid() +"\", \"sessionid\":\""+ arg1.getSessionId() +"\"}", getTag());
		}
		else if ( MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_ACTION_EXECUTED == arg0 )
		{
			dispatch("{\"code\":\"70000\"}", getTag());
		}
		else
		{
			dispatch("{\"code\":\"40000\"}", getTag());
		}
	}

	@Override
	public String getTag() {
		return IABCont.FUNS.LOGIN.toString();
	}
}
