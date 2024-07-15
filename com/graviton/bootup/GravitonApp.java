package com.graviton.bootup;

import java.util.List;

import com.graviton.account.AccountManager;
import com.graviton.account.Ledger;
import com.graviton.packages.PackageManager;
import com.graviton.process.BootUpProcess;
import com.graviton.process.Constants;
import com.graviton.process.ProcessContext;
import com.graviton.services.S1;
import com.graviton.services.S2;
import com.graviton.services.S3;
import com.graviton.services.S4;
import com.graviton.services.ServiceFactory;
import com.graviton.services.ServiceManager;

public class GravitonApp {

	private final PackageManager packageManager = new PackageManager();
	private final ServiceFactory serviceFactory = new ServiceFactory(List.of(new S1(), new S2(), new S3(), new S4()));
	private final ServiceManager serviceManager = new ServiceManager(serviceFactory);
	private final Ledger ledger = new Ledger();
	private final AccountManager accountManager = new AccountManager(ledger);

	private final BootUpProcess bootUpProcess = new BootUpProcess(packageManager, serviceManager, accountManager);

	public static void main(String[] args) {

		GravitonApp gravitonApp = new GravitonApp();

		if (args.length != 3) {
			System.out.println(
					"Invalid Input. Need 3 files\n 1. Pricing Information - represents the service pricing and credit packages in format of {PACKAGE/SERVICE}|{NAME}:{CREDITS}:{PRICE} example : PACKAGE|BASIC:100:100 or SERVICE|S1:1 \n"
							+ "2. Purchase Information - represents multiple customers purchasing different/same packages in format of {USER_NAME}|{PACKAGE_NAME} example : PETER|BASIC\n"
							+ "3. Usage Information - represents the usage information of multiple customers in format of {USER_NAME}|{SERVICE_NAME} example : PETER|S1\\n");
			return;
		}

		ProcessContext processContext = new ProcessContext();

		processContext.putData(Constants.PRICE_INFO_FILE_PATH, args[0]);
		processContext.putData(Constants.PURCHASE_INFO_FILE_PATH, args[1]);
		processContext.putData(Constants.USAGE_INFO_FILE_PATH, args[2]);
		gravitonApp.bootUpProcess.doProcess(processContext);

	}

}
