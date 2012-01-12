import android.util.Log;

/**
 * ログ管理クラス
 * @author Kenji
 *
 */
public class Logger {
	
	/** LogCatのタグ名 */
	private static final String APP_NAME = "ApplicationName";
	
	/**
	 * デバッグレベルでログを残す
	 * @param message
	 */
	public static final void d(String message){
		Log.d(APP_NAME, getFileName() +" - "+ message);
	}
	
	public static final void d(String message, Throwable e){
		Log.d(APP_NAME, getFileName() +" - " + message);
		printThrowable(e);
		if (e.getCause() != null){
			printDebugThrowable(e.getCause());
		}
	}
	
	/**
	 * インフォレベルでログを残す。
	 * @param message
	 */
	public static final void i(String message){
		Log.i(APP_NAME, getFileName() +" - "+ message);
	}
	
	/**
	 * エラーレベルでログを残す。
	 * @param message
	 */
	public static final void e(String message){
		Log.e(APP_NAME, getFileName() +" - "+ message);
	}
	
	/**
	 * ワーニングレベルでログを残す。
	 * @param message
	 */
	public static final void w(String message){
		Log.w(APP_NAME, getFileName() +" - "+ message);
	}
	
	/**
	 * ファイル名と行番号を取得
	 * 
	 * コールスタックで2階層上のファイル名と行番号を取得する。
	 * @return
	 */
	private static String getFileName(){
		Exception e = new Exception();
		StackTraceElement[] elements = e.getStackTrace();
		String ret = "";
		if (elements.length > 2){
			ret = "("+elements[2].getFileName();
			ret += ":";
			ret += elements[2].getLineNumber()+")";
		}
		return ret;
	}
	
	/**
	 * エラーレベルで例外のスタックトレースとともにログを残す
	 * @param message
	 * @param e
	 */
	public static final void e(String message, Throwable e){
		Log.e(APP_NAME, getFileName() +" - "+message);
		printThrowable(e);
		if (e.getCause() != null){
			printThrowable(e.getCause());
		}
	}
	
	/**
	 * 例外のスタックトレースをログに出力する 
	 * @param e
	 */
	private static final void printDebugThrowable(Throwable e){
		Log.d(APP_NAME,e.getClass().getName()+": "+e.getMessage());
		for (StackTraceElement element : e.getStackTrace()){
			Log.d(APP_NAME, "  at "+element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+":"+element.getLineNumber()+")");
		}
	}
	
	/**
	 * 例外のスタックトレースをログに出力する 
	 * @param e
	 */
	private static final void printThrowable(Throwable e){
		Log.e(APP_NAME,e.getClass().getName()+": "+e.getMessage());
		for (StackTraceElement element : e.getStackTrace()){
			Log.e(APP_NAME, "  at "+element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+":"+element.getLineNumber()+")");
		}
	}
}
