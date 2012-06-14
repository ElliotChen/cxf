package com.sforce.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileUtilsTest {
	@Test
	public void test() throws Exception {
		File source = new File("/Users/elliot/gitrepo/cxf/src/test/resources/req01.txt");
		System.out.println(source.length());
		FileInputStream fis = new FileInputStream(source);
		BufferedInputStream bis = new BufferedInputStream(fis);
		FileOutputStream fos = new FileOutputStream("/Users/elliot/Desktop/fos.txt");
		
		FileChannel fc = fis.getChannel();
		ByteBuffer bb = ByteBuffer.allocate(1024);
		byte[] bs = null;
		int length = 0;
		while ((length = fc.read(bb)) >= 0) {
			//bb.flip();
			//bb.
			
			System.out.println(length);
			fos.write(bs);
			bb.clear();
		}
		/*
		int end = 1;
		byte[] bs = new byte[1024];
		while (end > 0) {
			
			if (end > 0) {
				fos.write(bs);
				System.out.println(end + ":"+new String(bs));
			}
		}
		*/
		
		fis.close();
		fos.close();
	}
}
