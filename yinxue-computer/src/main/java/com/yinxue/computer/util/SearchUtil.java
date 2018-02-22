package com.yinxue.computer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.yinxue.computer.constant.ThreadConstant;


public class SearchUtil {
	private static int count = 1;
	private static ThreadPoolExecutor executor;
	private static File file;
	private static FileWriter fw;
	private static BufferedWriter bw;
	private static String nowTxt = "log" + DateUtil.getCurrentTime().toString() + ".txt";

	static {
		file = new File("E:", "log4.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor = new ThreadPoolExecutor(ThreadConstant.CORE_POOL_SIZE, 
				ThreadConstant.MAXIMNUM_POOL_SIZE, 
				ThreadConstant.KEEP_ALIVE_TIME, 
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	private SearchUtil() {
		throw new Error("no refect");
	}

	public static void main(String[] args) throws IOException {
		File file = new File("G:\\");
		long now = System.currentTimeMillis();
		findFiles(file);
		try {
			// findFilesWithThread(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long now2 = System.currentTimeMillis();
		System.out.println(now2 - now);
	}


	/**
	 * 链表+递归查找文件 C盘查找大概需要15min,D盘5min
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void findFiles(File file) throws IOException {

		// 查找隐藏文件
		if (file.isHidden()) {
			file = file.getAbsoluteFile();
		}
		LinkedList<File> list = new LinkedList<>();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 系统卷files==null 防止空指针异常
			if (null != files) {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						list.add(file2);
						continue;
					}
					// 写入到txt文件中
					String ss = count++ +":"+file2.getName() + ":" + file.length()+"\r\n";
					LogUtil.writeTxt(ss);
					//bw.write(ss);
					//bw.flush();
					System.out.print(ss);
				}
			}
		}

		while (!list.isEmpty()) {
			File file2 = list.removeFirst();
			if (file2.isDirectory()) {
				findFiles(file2);
			}
		}
		
//		bw.flush();
//		bw.close();
//		fw.close();
	}

	/**
	 * 多线程执行检索 链表+递归查找文件 @param file @throws Exception @throws
	 */
	public static void findFilesWithThread(File file) throws Exception {
		// 查找隐藏文件
		if (file.isHidden()) {
			file = file.getAbsoluteFile();
		}
		LinkedList<Future<String>> list = new LinkedList<>();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 系统卷files==null 防止空指针异常
			if (null != files) {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						// 线程数量=文件夹数量
						Future<String> f = executor.submit(new FindFileRun(file2));
						list.add(f);
					}
					System.out.println(file2.getName() + ":" + file.length());
				}
			}
		}
		StringBuilder ss = new StringBuilder(100);
		if (!list.isEmpty()) {
			// 阻塞运行
			for (int i = 0; i < list.size(); i++) {
				ss.append(list.get(i).get());
				if (i != list.size() - 1) {
					ss.append(".");
				}
			}
			System.out.println("完成的文件夹：" + ss.toString());
		}
	}

	private static class FindFileRun implements Callable<String> {

		private File file;

		public FindFileRun(File file) {
			this.file = file;
		}

		@Override
		public String call() throws Exception {
			findFiles(file);
			return Thread.currentThread().getName();
		}
	}
}
