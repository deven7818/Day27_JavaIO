package com.emppayrolljavaio;

import java.io.File;

public class FileOperation {

	/**
	 * Method to deleteFile
	 * @param contentToDelete - content of file
	 * @return - delete file
	 */
	public static boolean deletFile(File contentToDelete) {
		File[] content  = contentToDelete.listFiles();
		
		/**
		 * if content is not null then delete the file
		 */
		if(content != null) {
			for(File file : content) {
				deletFile(file);
			}
		}
		return contentToDelete.delete();
	}
}
