package com.sforce.intf;

import java.io.File;
import java.util.List;

import com.sforce.to.InitConfig;

public interface Receiver {
	boolean receive();
	List<File> getResult();
	void init(InitConfig config);
}
