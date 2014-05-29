package com.vo.bean;

import com.model.publicservice.YnuStory;
import com.ynu.zjx.PageBean;
import java.io.File;
import java.util.List;

public class YnuStoryVO extends PageBean{
	private Long id;
	private String title;
	private String happenTime;
	private String content;
	private String createTime;
	private String publisher;
    private String ids;
    private String param;
    
    public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	private String headpath;
    private List<File> Filedata;
    private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	public List<File> getFiledata() {
		return Filedata;
	}
	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}
	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}
	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}
	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}
	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}
    public String getHeadpath() {
		return headpath;
	}
	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}
	
	public YnuStory getYnuStory(){
		return new YnuStory(title, happenTime, content, createTime, publisher, headpath);
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
