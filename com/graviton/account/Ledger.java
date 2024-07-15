package com.graviton.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ledger {

	private final Map<String, List<String>> ledgerLog = new HashMap<String, List<String>>();

	void postLog(String userName, String log) {
		synchronized (userName) {
			if (!ledgerLog.containsKey(userName)) {
				ledgerLog.put(userName, new ArrayList<String>());
			}
		}
		ledgerLog.get(userName).add(log);
	}

	public List<String> getLogs(String userName) {
		return ledgerLog.get(userName);
	}

}
