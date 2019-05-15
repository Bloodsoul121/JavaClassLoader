package com.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassMain {
	
	public static void main(String[] args) {
		File file = new File("");
		String dir = file.getAbsolutePath() + "/lib";
		
		DiskClassLoader diskClassLoader = new DiskClassLoader(dir);
		try {
			Class<?> clazz = diskClassLoader.loadClass("com.classloader.Hello");
			if (clazz != null) {
				Object object = clazz.newInstance();
				System.out.println(object.getClass().getClassLoader());
				
				Method method = clazz.getMethod("sayHello");
				method.invoke(object);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印如下：
	 * com.classloader.DiskClassLoader@5c647e05
	 * hello
	 */

}
