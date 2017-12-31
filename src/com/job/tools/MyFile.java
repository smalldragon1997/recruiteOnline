// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 19:03:22
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MyFile.java

package com.job.tools;

import java.io.File;
import java.io.FileInputStream;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class MyFile {

	// 将目标文件File复制到目标文件夹中
	public static boolean copyFileToDir(File file, String dir) {
		// 读入 文件
		FileInputStream in_file;
		try {

			in_file = new FileInputStream(file);

			// 转 MultipartFile
			MultipartFile multi = new MockMultipartFile(file.getName(), file.getName(), "text/plain", in_file);

			File filePath = new File(dir);

			// 若存在则删除
			if (filePath.exists()) {
				filePath.delete();
			}
			// 写入文件
			multi.transferTo(filePath);
			// 删除原文件
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void deleteFile(String path){
		File filePath = new File(path);
		// 若存在则删除
		if (filePath.exists()) {
			filePath.delete();
		}
	}
}
