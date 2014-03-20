package com.iokays.utils.fileupload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传操作通用类
 * @author andy
 *
 */
public class FileUpload {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUpload.class);
	/**
	 * 图片保存到临时目录,并输出文件名。
	 * @param file
	 * @param timeInMillis
	 * @param tempPath
	 * @param request
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadImages(final MultipartFile file, final String tempDir) {
		File _tempDir = new File(tempDir);
    	if (!_tempDir.exists()) {			//不存在，新建之					
    		_tempDir.mkdirs();
    	}
    	
        final String _tempName = UUID.randomUUID().toString() + ".jpg";	//临时文件名(UUID)
        final String _tempPath = tempDir + File.separator + _tempName;
       
        try {
			file.transferTo(new File(_tempPath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        LOGGER.debug("_tempName:{}", _tempName);
        return _tempName;
	}
	
	
	public static void copyFileNIO(final File input, final File output) throws IOException {
		output.createNewFile();
		RandomAccessFile inputFile = null;
		try {
			inputFile = new RandomAccessFile(input, "r");
			RandomAccessFile outputFile = null;
			try {
				outputFile = new RandomAccessFile(output, "rw");
				
				final FileChannel inputChannel = inputFile.getChannel();
				final FileChannel outputChannel = outputFile.getChannel();
				
				long pos = 0;
				long size = inputFile.length();
				
				while (size > 0) {
					final long bytes = inputChannel.transferTo(pos, size, outputChannel);
					pos += bytes;
					size -= bytes;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				outputFile.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			inputFile.close();
		}
		
		input.delete();
	}
	
}
