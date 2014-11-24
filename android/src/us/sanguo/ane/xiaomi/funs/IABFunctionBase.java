package us.sanguo.ane.xiaomi.funs;

import us.sanguo.ane.xiaomi.context.IABCont;
import android.app.Activity;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public abstract class IABFunctionBase implements FREFunction
{
	protected FREContext _context;
	
	/**
	 * 返回IABCont类型的上下文
	 * @return
	 */
	protected IABCont getContext()
	{
		return (IABCont) _context;
	}
	
	protected Activity getActivity()
	{
		return _context.getActivity();
	}
	
	public void dispatch(String $code)
	{
		dispatch($code, getTag());
	}
	
	public void dispatch(String $code, String $level)
	{
		try
		{
			Log.d(getTag(), "dispatch code:"+$code+",level:" +$level);
			if($code == null)
			{
				$code = "Unknown Error:code is null.";
			}
			if($level == null)
			{
				$level = "Unknown Error:level is null.";
			}
			_context.dispatchStatusEventAsync($code, $level);
		}
		catch(Exception $e)
		{
    		Log.d(getTag(), "dispatchStatusEventAsync error:" + $e.toString());
		}
	}
	
	@Override
	abstract public FREObject call(FREContext $context, FREObject[] $args);
	
	abstract public String getTag();
}
