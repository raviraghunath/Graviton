package com.graviton.process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.graviton.process.ProcessStatus.PROCESS_STATUS;

public class FileReaderProcess implements IProcess {

	@Override
	public ProcessStatus doProcess(ProcessContext processContext) {
		List<String> content = null;
		BufferedReader reader = null;
		ProcessStatus processStatus = null;

		try {
			String filePath = processContext.getData(Constants.FILE_PATH, String.class);
			if (null != filePath) {
				content = new ArrayList<String>();
				reader = new BufferedReader(new FileReader(filePath));
				String line;

				while ((line = reader.readLine()) != null) {
					content.add(line);
				}
				processStatus = new ProcessStatus(PROCESS_STATUS.SUCCESS, content);
			} else {
				processStatus = new ProcessStatus(PROCESS_STATUS.FAILURE, "File is certainly invalid");
			}
		} catch (IOException e) {
			processStatus = new ProcessStatus(PROCESS_STATUS.FAILURE,
					"An error occurred while reading the file: " + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println("An error occurred while closing the file: " + e.getMessage());
				}
			}
		}
		return processStatus;
	}

}
