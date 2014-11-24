package us.sanguo.ane.xiaomi.funs;

import us.sanguo.ane.xiaomi.context.IABCont;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
import com.xiaomi.gamecenter.sdk.OnPayProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiBuyInfo;

public class Payment extends IABFunctionBase implements OnPayProcessListener {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------payment-------");
		try
		{
			String amount = $args[0].getAsString(); 	//金额
			String strOrderId = $args[1].getAsString(); //订单号

			int money = Integer.parseInt( amount );
			
			MiBuyInfo miBuyInfo = new MiBuyInfo();
			miBuyInfo.setCpOrderId( strOrderId );
			miBuyInfo.setCpUserInfo( "xxx" );  //此参数在用户支付成功后会透传给CP的服务器
			miBuyInfo.setAmount( money );	   //必须是大于1的整数, 10代表10米币,即10元人民币(不为空)

			MiCommplatform.getInstance().miUniPay( getActivity(), miBuyInfo, Payment.this );
		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
	}
	
	@Override
	public void finishPayProcess( int arg0 )
	{
		if ( arg0 == MiErrorCode.MI_XIAOMI_PAYMENT_SUCCESS )// 成功  success
		{
			dispatch("10000", getTag());
		}
		else if ( arg0 == MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_CANCEL || arg0 == MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_PAY_CANCEL )// 取消 cancel
		{
			dispatch("20000", getTag());
		}
		else if ( arg0 == MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_PAY_FAILURE )// 失败 fail
		{
			dispatch("30000", getTag());
		}
		else if ( arg0 == MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_ACTION_EXECUTED )
		{
			dispatch("40000", getTag());
		}
		else if( arg0 == MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_LOGIN_FAIL )
		{
			dispatch("50000", getTag());
		}
	}
	
	@Override
	public String getTag() {
		return IABCont.FUNS.PAYMENT.toString();
	}

}