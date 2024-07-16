package com.graviton.process;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.graviton.process.ProcessStatus.PROCESS_STATUS;
import com.graviton.utils.Constants;

public class FileWriterProcess implements IProcess {

	@Override
	public ProcessStatus doProcess(ProcessContext processContext) {

		BufferedWriter writer = null;
		ProcessStatus processStatus = null;

		try {
			writer = new BufferedWriter(new FileWriter(processContext.getData(Constants.FILE_PATH, String.class)));
			String logs = processContext.getData(Constants.LOGS, String.class);
			writer.write(logs);
			processStatus = new ProcessStatus(PROCESS_STATUS.SUCCESS, "File created successfully");
		} catch (IOException e) {
			processStatus = new ProcessStatus(PROCESS_STATUS.FAILURE,
					"An error occurred while creating the file: \" + e.getMessage()");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.println("An error occurred while closing the file: " + e.getMessage());
				}
			}
		}
		return processStatus;
	}

}
