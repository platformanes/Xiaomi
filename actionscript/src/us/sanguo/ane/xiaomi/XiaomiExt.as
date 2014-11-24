package us.sanguo.ane.xiaomi
{

import flash.external.ExtensionContext;
import flash.system.Capabilities;

import us.sanguo.ane.xiaomi.funs.XiaomiCont;

/**
 * 
 * @author zm
 * 创建日期：2014-11-10
 */
public class XiaomiExt
{
	/**
	 * 定义本地插件的ID
	 */	
	public static const EXT_ID:String = 'us.sanguo.ane.xiaomi';
	
	protected static var _cont:XiaomiCont= null;
	
	/**
	 * 获取当前插件
	 */
	public static function get iab():XiaomiCont
	{
		if(!_cont)
		{
			checkSuppored();
			_cont = new XiaomiCont(ExtensionContext.createExtensionContext(EXT_ID, ""));
		}
		return _cont;
	}
	
	protected static function get isSupported() : Boolean
	{
		return (Capabilities.os.indexOf("Linux") >= 0);
	}
	
	private static function checkSuppored():void
	{
		if(!isSupported) throw new TypeError('The native extension is not supported on this device!');
	}
}
}