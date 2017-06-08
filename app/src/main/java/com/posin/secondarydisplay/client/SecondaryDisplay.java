package com.posin.secondarydisplay.client;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.posin.secondarydisplay.client.ISecondaryDisplay;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class SecondaryDisplay {

	public static final String SVR_NAME = "minipos.secondarydisplay";
	private static final String TAG = "SecondaryDisplay";

	private ISecondaryDisplay mDsp = null;

	public SecondaryDisplay() throws Throwable {
		Class<?> cls = Class.forName("android.os.ServiceManager");
		Class<?>[] pa = { String.class };
		Method m = cls.getDeclaredMethod("getService", pa);
		Object o = m.invoke(null, SVR_NAME);

		if(o == null)
			throw new IOException("failed to connect to SecondaryDisplay service.");
		mDsp = ISecondaryDisplay.Stub.asInterface((IBinder)o);

		if(mDsp == null)
			throw new IOException("failed to connect to SecondaryDisplay service.");
	}

	public void send(List<Map<String,String>> cmdList) throws RemoteException {
		String msg = Json.listToString(cmdList);
		//Log.d(TAG, msg);
		mDsp.send(msg);
	}

	public void send(Map<String,String> cmd) throws RemoteException {
		String msg = Json.mapToString(cmd);
		//Log.d(TAG, msg);
		mDsp.send(msg);
	}

	public void execute(Map<String,String> cmd, ISecondaryDisplayCallback callback) throws RemoteException {
		String msg = Json.mapToString(cmd);
		//Log.d(TAG, msg);
		int id = mDsp.execute(msg, callback);
	}

	public void execute(Map<String,String> cmd) throws RemoteException {
		String msg = Json.mapToString(cmd);
		//Log.d(TAG, msg);
		int id = mDsp.execute(msg, new ISecondaryDisplayCallback.Stub() {
			@Override
			public void onStart() throws RemoteException {
				Log.d(TAG, "任务开始执行");
			}

			@Override
			public void onProcess(String data) throws RemoteException {
				Log.d(TAG, "任务返回数据 : " + data);
			}

			@Override
			public void onFinish(String data) throws RemoteException {
				Log.d(TAG, "任务结束");
			}
		});

		Log.d(TAG, "执行任务 id="+id);
	}

//	public void writeFile(String path, long pos, byte[] data) throws RemoteException {
//		mDsp.writeFile(path, pos, data);
//	}
}
