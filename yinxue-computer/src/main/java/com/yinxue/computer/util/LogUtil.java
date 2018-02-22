package com.yinxue.computer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.yinxue.computer.constant.ErrorMessage;
import com.yinxue.computer.constant.Files;

public class LogUtil {
	private static File file;
	private static FileWriter fw;
	private static BufferedWriter bw;
	private static final String NOW_TXT = "log" + DateUtil.getCurrentTime().toString().replaceAll("-", "").replaceAll(":", "").substring(0, 15) + ".txt";
	static {
		file = new File("E:", NOW_TXT);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private LogUtil() {
		throw new Error(ErrorMessage.NO_REFLECT);
	}
	
	public static void writeTxt(String s) throws IOException {
		bw.write(s);
		bw.flush();
	}
	
	public static void closeIO() throws IOException {
		bw.close();
		fw.close();
	}
	
	

}
