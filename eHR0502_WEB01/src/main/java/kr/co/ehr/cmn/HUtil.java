package kr.co.ehr.cmn;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HUtil {
	
	/**
	 * 년도별/월별 폴더 동적 생성.
	 * @param basePath
	 * @return
	 */
	public static String yearMonthDir(String basePath) {
		return basePath+File.separator+formatDate("yyyy")+File.separator+formatDate("MM");
	}
	/**
	 * 포멧 Date
	 * @param format
	 * @return
	 */
	public static String formatDate(String format) {
		if (null == format || format.equals(""))
			format = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * File Rename util
	 * RUNNING.txt --> RUNNING1.txt
	 * @param f
	 * @return
	 */
	public static String rename(File f) {
		if (!f.exists()) {
			return f.getAbsolutePath();
		}
		String name = f.getName();
		String body = null;
		String ext = null;

		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot); // includes "."
		} else {
			body = name;
			ext = "";
		}

		int count = 0;
		while (f.exists() && count < 99999) {
			count++;
			String newName = body + count + ext;
			f = new File(f.getParent(), newName);
		}

		return f.getAbsolutePath();
	}


}
