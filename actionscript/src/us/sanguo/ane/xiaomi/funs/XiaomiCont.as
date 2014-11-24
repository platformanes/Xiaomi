package us.sanguo.ane.xiaomi.funs
{
import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;

import us.sanguo.ane.xiaomi.enum.XiaomiFunction;

/**
 * 提供Android中Xiaomi的所有功能
 * @author zm
 * 创建日期：2014-11-10
 */
public class XiaomiCont extends EventDispatcher
{
	protected var _extension:ExtensionContext;
	
	public function XiaomiCont($context:ExtensionContext)
	{
		_extension = $context;
		if(!_extension) throw new TypeError('必须提供ExtensionContext实例！');
		_extension.addEventListener(StatusEvent.STATUS, handler_status);
	}
	
	protected function handler_status($evt:StatusEvent):void
	{
		this.dispatchEvent($evt);
	}
	
	public function init(
		appId:String,
		appKey:String):String{
		
		return _extension.call(XiaomiFunction.INIT,
			appId,
			appKey) as String;
	} 
	
	public function login():String{
		return _extension.call(XiaomiFunction.LOGIN) as String;
	}
	
	/**
	 * 显示购买
	 * @param amount 产品价格
	 * @param orderID 订单号
	 */		
	public function payment(amount:String, orderID:String):String{
		return _extension.call(XiaomiFunction.PAYMENT,
			amount,
			orderID
		)as String;
	}
	
	public function dispose():void
	{
		if(_extension)
		{
			_extension.removeEventListener(StatusEvent.STATUS, handler_status);
			_extension.dispose();
			_extension = null;
		}
	}
}
}