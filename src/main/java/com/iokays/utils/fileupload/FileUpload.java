package com.iokays.utils.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
	public static String uploadImages(final MultipartFile file, final String tempDir) throws IllegalStateException, IOException {
		File _tempDir = new File(tempDir);
    	if (!_tempDir.exists()) {			//不存在，新建之					
    		_tempDir.mkdirs();
    	}
    	String fileName = file.getOriginalFilename();// 获取文件名称
        String extension = FilenameUtils.getExtension(fileName);	//获取文件后缀
        
        final String _tempName = UUID.randomUUID().toString() + "." + extension;	//临时文件名(UUID)
        final String _tempPath = tempDir + File.separator + _tempName;
       
        file.transferTo(new File(_tempPath));
        
        return _tempName;
	}
}
