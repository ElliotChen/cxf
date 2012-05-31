package com.sforce.intf;

import java.io.File;
import java.util.List;

public interface Receiver extends Component  {
	List<File> getResult();
}
