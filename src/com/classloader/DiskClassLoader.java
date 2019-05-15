package com.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DiskClassLoader extends ClassLoader {
	
	private String mDirPath;
	
	public DiskClassLoader(String path) {
		super();
		this.mDirPath = path;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class clazz = null;
		byte[] bytes = loadClassBytes(name);
		if (bytes == null) {
			throw new ClassNotFoundException();
		} else {
			clazz = defineClass(name ,bytes, 0, bytes.length);
		}
		return clazz;
	}

	private byte[] loadClassBytes(String name) {
		String fileName = getFileName(name);
		File file = new File(mDirPath, fileName);
		InputStream in = null;
		ByteArrayOutputStream out = null;
		byte[] bytes = null;
		try {
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] arr = new byte[1024];
			int len;
			while ((len = in.read(arr)) != -1) {
				out.write(arr, 0, len);
			}
			bytes = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	private String getFileName(String name) {
		int index = name.lastIndexOf(".");
		if (index < 0) {
			return name + ".class";
		}
		return name.substring(index + 1) + ".class";
	}

}
