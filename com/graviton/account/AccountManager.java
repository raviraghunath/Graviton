package com.graviton.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.graviton.services.Service;

public class AccountManager {

	private final Ledger ledger;

	public AccountManager(Ledger ledger) {
		this.ledger = ledger;
	}

	private final Map<String, Integer> credits = new HashMap<String, Integer>();

	public boolean purchasePackageForUser(String userName, com.graviton.packages.Package package1) {
		synchronized (userName) {
			ledger.postLog(userName, userName + " has purchased " + package1.getPackageName() + ", which has "
					+ package1.getPackageCredits() + " credit(s), by paying $" + package1.getPackagePrice());
			if (credits.containsKey(userName)) {
				credits.put(userName, credits.get(userName) + package1.getPackageCredits());
			} else {
				credits.put(userName, package1.getPackageCredits());
			}

		}
		return true;
	}

	public boolean takeServiceForUser(String userName, Service service) {
		if (null == service) {
			ledger.postLog(userName, "Requested for Service that doesn't exists");
			return false;
		}
		synchronized (userName) {
			boolean canTake = canTakeServiceForUser(userName, service);
			if (canTake) {
				ledger.postLog(userName, userName + " has used " + service.getServiceName() + " service, which is "
						+ service.getServicePrice() + " credit(s) ");
				credits.put(userName, credits.get(userName) - service.getServicePrice());
			} else {
				ledger.postLog(userName, "Not sufficient credits for Service : " + service.getServiceName());
				return false;
			}
		}
		return true;
	}

	private boolean canTakeServiceForUser(String userName, Service service) {
		if (credits.containsKey(userName)) {
			int credit = getBalanceForUser(userName);
			return credit >= service.getServicePrice();
		}
		return false;
	}

	public int getBalanceForUser(String userName) {
		return credits.containsKey(userName) ? credits.get(userName) : -1;
	}

	public String getLedger() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Entry<String, Integer> entry : credits.entrySet()) {
			List<String> logs = ledger.getLogs(entry.getKey());
			stringBuilder.append(entry.getKey() + "\n");
			for (String log : logs) {
				stringBuilder.append("\t" + log + "\n");
			}
			stringBuilder.append("Remaining Balance : " + getBalanceForUser(entry.getKey()) + " credits\n");
		}
		return stringBuilder.toString();

	}

}
