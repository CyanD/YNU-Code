<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.json.*" %>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper" %>
<%@ page import="com.util.Sys,com.util.MyUploadTool" %>
<%

//String savePath = pageContext.getServletContext().getRealPath("/") + "attached/";
String savePath = request.getSession().getServletContext().getRealPath("/") + "attached/";

//String saveUrl  = request.getContextPath() + "/attached/";
String saveUrl  = request.getContextPath() + "/download/download!showImage?path=/attached/";

HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("image", "gif,jpg,jpeg,png,bmp");
extMap.put("flash", "swf,flv");
extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

long maxSize = 1000000;

response.setContentType("text/html; charset=UTF-8");

if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError("请选择文件。"));
	return;
}
//检查目录
File uploadDir = new File(savePath);
if(!uploadDir.isDirectory()){
	out.println(getError("上传目录不存在。"));
	return;
}
//检查目录写权限
if(!uploadDir.canWrite()){
	out.println(getError("上传目录没有写权限。"));
	return;
}

String dirName = request.getParameter("dir");
if (dirName == null) {
	dirName = "image";
}
if(!extMap.containsKey(dirName)){
	out.println(getError("目录名不正确。"));
	return;
}
//创建文件夹
savePath += dirName + "/";
saveUrl += dirName + "/";
File saveDirFile = new File(savePath);
if (!saveDirFile.exists()) {
	saveDirFile.mkdirs();
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//String ymd = sdf.format(new Date());
//savePath += ymd + "/";
//saveUrl += ymd + "/";
File dirFile = new File(savePath);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}

MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
String fileName = wrapper.getFileNames("imgFile")[0];//imgFile,imgFile,imgFile
File file = wrapper.getFiles("imgFile")[0];
String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
    out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
    return;

}

if (file.length() > maxSize) {
        out.println(getError("上传文件大小超过限制。"));
        return;

} 

SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
String beforeImgName = df.format(new Date()) + "_" + new Random().nextInt(1000);
String newImgName = beforeImgName + "." + fileExt;
String newMiniImgName = beforeImgName + "mini." + fileExt;
byte[] buffer = new byte[1024];

File realPathFile = new File(Sys.BASEPATH + Sys.KEPICTUREPATH);
if (!realPathFile.exists()) {
	realPathFile.mkdirs();
}

//FileOutputStream fos_proom = new FileOutputStream(savePath +"/" + newImgName);
FileOutputStream fos_interface = new FileOutputStream(new File(Sys.BASEPATH + Sys.KEPICTUREPATH + newImgName));

InputStream in = new FileInputStream(file);
try {
        int num = 0;
        while ((num = in.read(buffer)) > 0) {
                //fos_proom.write(buffer, 0, num);
                fos_interface.write(buffer, 0, num);
        }

} catch (Exception e) {
        e.printStackTrace(System.err);

} finally {
        in.close();
        //fos_proom.close();
        fos_interface.close();
}

MyUploadTool.saveMiniImage(Sys.BASEPATH + Sys.KEPICTUREPATH + newImgName, Sys.KEMINIPICTUREWIDTH, Sys.KEMINIPICTUREHIGHT, fileExt);

JSONObject obj = new JSONObject();

obj.put("error", 0);
obj.put("url", saveUrl +"/" + newMiniImgName);
out.println(obj.toJSONString());
%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toJSONString();
}
%>