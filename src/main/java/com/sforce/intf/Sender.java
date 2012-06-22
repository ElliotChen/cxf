package com.sforce.intf;

import java.io.File;
import java.util.List;

import com.sforce.intf.impl.InitConfig;

public interface Sender {
	boolean send();
	void init(InitConfig config);
}
