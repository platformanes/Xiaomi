package us.sanguo.ane.xiaomi;

import us.sanguo.ane.xiaomi.context.IABCont;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class Xiaomi implements FREExtension{
public static final String TAG = "us.sanguo.ane.xiaomi.Xiaomi";

	@Override
	public FREContext createContext(String $type)
	{
		//if(ANEContext.IAB.toString().equals($type)) return new IABCont();
		return new IABCont();
	}

	@Override
	public void initialize()
	{
		Log.i(TAG, "Xiaomi initialize");
	}

	@Override
	public void dispose()
	{
		Log.i(TAG, "Xiaomi dispose");
	}
}