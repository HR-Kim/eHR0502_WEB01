package kr.co.ehr.file.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.ehr.cmn.HUtil;

@Controller
public class FileController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private static final String UPLOAD_ROOT = "D:\\HR_FILE";
	private static final String VIEW_NAME   = "file/file";
	//http://localhost:8080/ehr/file/uploadfileview.do
	@RequestMapping(value="file/uploadfileview.do")
	public String uploadFileView() {
		LOG.debug("===============================");
		LOG.debug("=@Controller uploadFileView=");
		LOG.debug("===============================");
		return VIEW_NAME;
	}
	
	
	//http://localhost:8080/ehr/file/uploadfileview.do
	@RequestMapping(value="file/do_save.do")
	public String do_save(MultipartHttpServletRequest mReg,Model model) {
		LOG.debug("===============================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("===============================");
		String workDiv =  mReg.getParameter("work_div");
		LOG.debug("===============================");
		LOG.debug("=@Controller workDiv="+workDiv);
		LOG.debug("===============================");
		if(null == workDiv || "".equals(workDiv)) {
			LOG.debug("=@Controller workDiv="+workDiv);
			throw new IllegalArgumentException("작업 구분은 반듯이 입력 해야 합니다.");
		}
		LOG.debug("=@Controller workDiv="+workDiv);
		Iterator<String> files = mReg.getFileNames();
		
		while(files.hasNext()) {
			String uploadFileName = files.next();
			String orgFileName     = "";
			String saveFileName    = "";
			long   fileSize        = 0L;
			
			MultipartFile mFile = mReg.getFile(uploadFileName);
			orgFileName = mFile.getOriginalFilename();
			
			if(mFile.isEmpty())continue;
			
			LOG.debug("===============================");
			LOG.debug("=@Controller uploadFileName="+uploadFileName);
			LOG.debug("=@Controller orgFileName="+orgFileName);
			LOG.debug("===============================");
			String parentDir = HUtil.yearMonthDir(UPLOAD_ROOT);
			LOG.debug("=@Controller parentDir="+parentDir);
			//Dir생성
			File upDir = new File(parentDir);
			LOG.debug("=@Controller upDir.isDirectory()="+upDir.isDirectory());
			if(upDir.isDirectory()==false) {
				
				boolean flag = upDir.mkdirs();
			}
					
			
			File orgFile = new File(parentDir,orgFileName);
			String newFileName = orgFile.getAbsolutePath();
			if(orgFile.exists()) {
				newFileName = HUtil.rename(orgFile);
				
			}
			LOG.debug("=@Controller orgFile.exists()="+newFileName);
			try {
				mFile.transferTo(new File(newFileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return VIEW_NAME;
	}
	
	
}
