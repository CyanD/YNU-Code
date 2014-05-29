package com.action;

import java.io.InputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.util.MyUploadTool;
@Component("downloadAction")
@Scope("prototype")
public class DownloadAction extends ActionSupport {
	private InputStream imageStream;
	private InputStream fileStream;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InputStream getFileStream() {
		return fileStream;
	}
	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}
	private String path;
    public InputStream getImageStream() {
		return imageStream;
	}
	public String getPath() {
		return path;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String showImage() throws Exception {
		try {
			if(path==null||"".equals(path.trim())){
				return null;
			}
			this.setImageStream(MyUploadTool.getInputStreamByPath(path));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        return "image";
    }
	public String downloadFile() throws Exception {
		try {
			if(path==null||"".equals(path.trim())){
				return null;
			}
			 this.name = new String(name.getBytes("ISO-8859-1"),"utf-8");
			 this.path = new String(path.getBytes("ISO-8859-1"),"utf-8");
			 this.name = new String(name.getBytes(), "ISO8859-1");
			 this.setFileStream(MyUploadTool.getInputStreamByPath(path));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        return "file";
    }

}
