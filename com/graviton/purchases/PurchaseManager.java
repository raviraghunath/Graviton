//package com.graviton.purchases;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.graviton.packages.Package;
//import com.graviton.packages.PackageManager;
//
//public class PurchaseManager {
//
//	private final PackageManager packageManager;
//	private static PurchaseManager instance;
//
//	private PurchaseManager() {
//		packageManager = PackageManager.getInstance();
//	}
//
//	public static synchronized PurchaseManager getInstance() {
//		if (instance == null) {
//			instance = new PurchaseManager();
//		}
//		return instance;
//	}
//
//	Map<String, List<Package>> purchases = new HashMap<String, List<Package>>();
//
//	public boolean doPurchasePackageForUser(String userName, String packageName) {
//		synchronized (userName) {
//			packageManager.getPackage(packageName);
//			if(null != packageName) {
//				
//				
//				
//			}
//		}
//		return false;
//	}
//
//}
