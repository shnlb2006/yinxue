package com.yinxue.computer.util;

import java.io.File;
import java.io.IOException;

import com.yinxue.computer.constant.ErrorMessage;
import com.yinxue.computer.constant.Files;

public class FileUtil {
	
	private FileUtil() {
		throw new Error(ErrorMessage.NO_REFLECT);
	}
	
	/**
	 * 创建固定路径的文件夹
	 * @see Files
	 * @throws IOException
	 */
	public static void createdFiles() throws IOException {
		for (Files f : Files.values()) {
			File file = new File(f.getPath());
			if (!file.exists()) {
				file.mkdirs();
				System.out.println("新增文件夹："+file.getAbsolutePath());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		createdFiles();
	}
	
}
