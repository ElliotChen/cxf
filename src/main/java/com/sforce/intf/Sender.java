package com.sforce.intf;

import java.io.File;
import java.util.List;

public interface Sender {
	boolean send();
	boolean initSource(List<File> source);
}
