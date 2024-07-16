package com.graviton.process;

import java.util.List;

import com.graviton.account.AccountManager;
import com.graviton.packages.PackageManager;
import com.graviton.process.ProcessStatus.PROCESS_STATUS;
import com.graviton.services.ServiceContext;
import com.graviton.services.ServiceManager;
import com.graviton.utils.Constants;

public class BootUpProcess implements IProcess {

	private final PackageManager packageManager;
	private final ServiceManager serviceManager;
	private final AccountManager accountManager;

	public BootUpProcess(PackageManager packageManager, ServiceManager serviceManager, AccountManager accountManager) {
		this.packageManager = packageManager;
		this.serviceManager = serviceManager;
		this.accountManager = accountManager;
	}

	private final FileReaderProcess fileReaderProcess = new FileReaderProcess();
	private final FileWriterProcess fileWriterProcess = new FileWriterProcess();

	@Override
	public ProcessStatus doProcess(ProcessContext processContext) {

		ProcessStatus processStatus = null;

		processContext.putData(Constants.FILE_PATH,
				processContext.getData(Constants.PRICE_INFO_FILE_PATH, String.class));
		ProcessStatus priceInfoReaderprocessStatus = fileReaderProcess.doProcess(processContext);
		List<String> priceInfo = (List<String>) priceInfoReaderprocessStatus.getResultObj();

		processContext.putData(Constants.FILE_PATH,
				processContext.getData(Constants.PURCHASE_INFO_FILE_PATH, String.class));
		ProcessStatus purchaseInfoReaderprocessStatus = fileReaderProcess.doProcess(processContext);
		List<String> purchaseInfo = (List<String>) purchaseInfoReaderprocessStatus.getResultObj();

		processContext.putData(Constants.FILE_PATH,
				processContext.getData(Constants.USAGE_INFO_FILE_PATH, String.class));
		ProcessStatus usageInfoReaderprocessStatus = fileReaderProcess.doProcess(processContext);
		List<String> usageInfo = (List<String>) usageInfoReaderprocessStatus.getResultObj();

		for (String priceInfoString : priceInfo) {
			String[] priceInfoStringSplit = priceInfoString.split("\\|");
			if (priceInfoStringSplit[0].equals(Constants.PACKAGE)) {
				packageManager.createPackage(priceInfoStringSplit[1]);
			} else if (priceInfoStringSplit[0].equals(Constants.SERVICE)) {
				serviceManager.createService(priceInfoStringSplit[1]);
			}
		}

		for (String purchaseInfoString : purchaseInfo) {
			String[] purchaseInfoStringSplit = purchaseInfoString.split("\\|");
			String userName = purchaseInfoStringSplit[0];
			String packageName = purchaseInfoStringSplit[1];
			accountManager.purchasePackageForUser(userName, packageManager.getPackage(packageName));
		}

		for (String usageInfoString : usageInfo) {
			String[] usageInfoStringSplit = usageInfoString.split("\\|");
			ServiceContext serviceContext = new ServiceContext();
			accountManager.takeServiceForUser(usageInfoStringSplit[0],
					serviceManager.getService(usageInfoStringSplit[1]), serviceContext);
		}

		processContext.putData(Constants.LOGS, accountManager.getLedger());
		processContext.putData(Constants.FILE_PATH,
				"/Users/rravindranathan/eclipse-workspace-1/Graviton/src/output.txt");
		fileWriterProcess.doProcess(processContext);

		processStatus = new ProcessStatus(PROCESS_STATUS.SUCCESS, "Booted Up");

		return processStatus;

	}

}
