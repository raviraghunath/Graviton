package com.graviton.process;

public interface IProcess {

	ProcessStatus doProcess(ProcessContext processContext);
	
	default String getProcessName() {
		return "";
	}
}
