package com.sforce.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sforce.intf.Component;

public class ContextHolder implements ApplicationContextAware {
	public static ApplicationContext context = null;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	public static List<Component> getAllConComponents() {
		Map<String, Component> beans = context.getBeansOfType(Component.class);
		List<Component> result = new ArrayList<Component>();
		for (Entry<String, Component> entry : beans.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}

}
