package com.sforce.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CollectionUtils {
	private static final Logger logger = LoggerFactory.getLogger(CollectionUtils.class);
	public static <T> List<List<T>> splitList(List<T> source, int pageSize) {
		List<List<T>> result = new ArrayList<List<T>>();
		int size = source.size();
		for (int i =0; i < size; ) {
			int end = i + pageSize;
			if (end > size) {
				end = size;
			}
			result.add(source.subList(i, end));
			
			i = end;
		}
		
		
		return result;
	}
}
