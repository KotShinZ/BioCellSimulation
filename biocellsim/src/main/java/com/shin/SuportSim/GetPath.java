package com.shin.SuportSim;

import com.shin.Baselib.DataClass.vector.Vector3Int;

public class GetPath {
	static String macPath = "/Users/shin/Library/CloudStorage/OneDrive-公立大学法人大阪/研究/中野研究室/課題/kadai1.txt";
	static String winPath = "E:\\OneDrive - 公立大学法人大阪\\研究\\中野研究室\\課題\\SimMain\\SimMain\\Out\\2\\2_datas_3D_1000\\molecules_";
	static String linuxPath = "./Out/3/area_100_150t_production/areas_";

	public static String Get(String preName, Vector3Int size, int time, String name) {
		return preName + "_" + String.valueOf(size.x()) + "_" + String.valueOf(size.y()) + "_"
				+ String.valueOf(size.z())
				+ "_"
				+ String.valueOf(time) + "t/" + name;
		/*
		 * // ")[0].toLowerCase());
		 * switch (System.getProperty("os.name").split(" ")[0].toLowerCase()) {
		 * case "windows":
		 * return winPath;
		 * case "mac":
		 * return macPath;
		 * case "linux":
		 * return linuxPath;
		 * 
		 * default:
		 * return linuxPath;
		 * }
		 */
	}

	public static String Get(String preName, Vector3Int size, int time) {
		return preName + String.valueOf(size.x()) + "_" + String.valueOf(size.y()) + "_" + String.valueOf(size.z())
				+ "_"
				+ String.valueOf(time) + "t";

		/*
		 * System.out.println(System.getProperty("os.name").split("
		 * // ")[0].toLowerCase());
		 * switch (System.getProperty("os.name").split(" ")[0].toLowerCase()) {
		 * case "windows":
		 * return winPath;
		 * case "mac":
		 * return macPath;
		 * case "linux":
		 * return linuxPath;
		 * 
		 * default:
		 * return linuxPath;
		 * }
		 */
	}
}
