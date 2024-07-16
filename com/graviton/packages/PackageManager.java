package com.graviton.packages;

import java.util.HashMap;
import java.util.Map;

import com.graviton.utils.Constants;

public class PackageManager {

	private final Map<String, Package> packages;

	public PackageManager() {
		packages = new HashMap<String, Package>();
	}

	// Format : {PACKAGE_NAME}:{CREDITS}:{PRICE}
	public Package createPackage(String packageInfo) {
		String[] packageInfoSplit = packageInfo.split(Constants.COLON);

		Package package1 = new Package() {

			@Override
			public String getPackageName() {
				return packageInfoSplit[0];
			}

			@Override
			public int getPackageCredits() {
				return Integer.parseInt(packageInfoSplit[1]);
			}

			@Override
			public int getPackagePrice() {
				return Integer.parseInt(packageInfoSplit[2]);
			}
		};

		packages.put(packageInfoSplit[0], package1);
		return package1;
	}

	public Package getPackage(String packageName) {
		return packages.get(packageName);
	}

}
