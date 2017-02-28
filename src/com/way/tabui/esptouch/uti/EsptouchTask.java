package com.way.tabui.esptouch.uti;

import java.util.List;

import android.content.Context;

import com.way.tabui.esptouch.uti.IEsptouchListener;
import com.way.tabui.esptouch.uti.IEsptouchResult;
import com.way.tabui.esptouch.uti.IEsptouchTask;
import com.way.tabui.esptouch.task.EsptouchTaskParameter;
import com.way.tabui.esptouch.task.IEsptouchTaskParameter;
import com.way.tabui.esptouch.task.__EsptouchTask;

public class EsptouchTask implements IEsptouchTask {

	public __EsptouchTask _mEsptouchTask;
	private IEsptouchTaskParameter _mParameter;

	/**
	 * Constructor of EsptouchTask
	 * 
	 * @param apSsid
	 *            the Ap's ssid
	 * @param apBssid
	 *            the Ap's bssid
	 * @param apPassword
	 *            the Ap's password
	 * @param isSsidHidden
	 *            whether the Ap's ssid is hidden
	 * @param context
	 *            the Context of the Application
	 */
	public EsptouchTask(String apSsid, String apBssid, String apPassword,
			boolean isSsidHidden, Context context) {
		_mParameter = new EsptouchTaskParameter();
		_mEsptouchTask = new __EsptouchTask(apSsid, apBssid, apPassword,
				context, _mParameter, isSsidHidden);
	}

	/**
	 * Constructor of EsptouchTask
	 * 
	 * @param apSsid
	 *            the Ap's ssid
	 * @param apBssid
	 *            the Ap's bssid
	 * @param apPassword
	 *            the Ap's password
	 * @param isSsidHidden
	 *            whether the Ap's ssid is hidden
	 * @param timeoutMillisecond
	 *            (it should be >= 15000+6000) millisecond of total timeout
	 * @param context
	 *            the Context of the Application
	 */
	public EsptouchTask(String apSsid, String apBssid, String apPassword,
			boolean isSsidHidden, int timeoutMillisecond, Context context) {
		_mParameter = new EsptouchTaskParameter();
		_mParameter.setWaitUdpTotalMillisecond(timeoutMillisecond);
		_mEsptouchTask = new __EsptouchTask(apSsid, apBssid, apPassword,
				context, _mParameter, isSsidHidden);
	}

	@Override
	public void interrupt() {
		_mEsptouchTask.interrupt();
	}

	@Override
	public IEsptouchResult executeForResult() throws RuntimeException {
		return _mEsptouchTask.executeForResult();
	}

	@Override
	public boolean isCancelled() {
		return _mEsptouchTask.isCancelled();
	}

	@Override
	public List<IEsptouchResult> executeForResults(int expectTaskResultCount)
			throws RuntimeException {
		if (expectTaskResultCount <= 0) {
			expectTaskResultCount = Integer.MAX_VALUE;
		}
		return _mEsptouchTask.executeForResults(expectTaskResultCount);
	}

	@Override
	public void setEsptouchListener(IEsptouchListener esptouchListener) {
		_mEsptouchTask.setEsptouchListener(esptouchListener);
	}
}
