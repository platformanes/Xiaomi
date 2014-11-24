package us.sanguo.ane.xiaomi.context;

import java.util.HashMap;
import java.util.Map;

import us.sanguo.ane.xiaomi.funs.Init;
import us.sanguo.ane.xiaomi.funs.Login;
import us.sanguo.ane.xiaomi.funs.Payment;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class IABCont extends FREContext{
	public static final String TAG = "us.sanguo.ane.xiaomi.context.IABCont";

	/**
	 * 记录所有支持的FREFunction
	 */
	public static enum FUNS {
		INIT, LOGIN, PAYMENT
	};

	static private IABCont _instance;

	static public IABCont getInstance() {
		return _instance;
	}

	@Override
	public void dispose() {
		String __info = "dispose";
		Log.d(TAG, __info);
		dispatchStatusEventAsync(__info, getTag());
	}

	private void dispatch(String $code, String $level) {
		Log.d($code, $level);
		dispatchStatusEventAsync($code, $level);
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		_instance = this;
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		functions.put(FUNS.INIT.toString(), new Init());
		functions.put(FUNS.LOGIN.toString(), new Login());
		functions.put(FUNS.PAYMENT.toString(), new Payment());
		return functions;
	}

	public void destroy() {
		dispatch(TAG, "Destroying helper.");
	}

	public String getTag() {
		return TAG;
	}
}
