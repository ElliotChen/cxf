package com.sforce.intf;

import java.io.File;
import java.util.List;

public interface Sender extends Component {
	boolean initSource(List<File> source);
}
