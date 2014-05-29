package com.util;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class MyUploadTool {
	public static final String DEFAULTPICTUREPATH ="upload/images/newsnewspicture/Lighthouse.png";
	 public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
	 // targetW，targetH分别表示目标长和宽
	 int type = source.getType();
	 BufferedImage target = null;
	 double sx = (double) targetW / source.getWidth();
	 double sy = (double) targetH / source.getHeight();
	 //这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
	 //则将下面的if else语句注释即可
	 if(sx>sy)
	 {
	 sx = sy;
	 targetW = (int)(sx * source.getWidth());
	 }else{
	 sy = sx;
	 targetH = (int)(sy * source.getHeight());
	 }
	 if (type == BufferedImage.TYPE_CUSTOM) { //handmade
	 ColorModel cm = source.getColorModel();
	 WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
	 boolean alphaPremultiplied = cm.isAlphaPremultiplied();
	 target = new BufferedImage(cm, raster, alphaPremultiplied, null);
	 } else
	 target = new BufferedImage(targetW, targetH, type);
	 Graphics2D g = target.createGraphics();
	 //smoother than exlax:
	 g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
	 g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
	 g.dispose();
	 return target;
	 }
	 public static void saveYnuStoryImage (File fromFile,String saveToFileStr,String imgType)
	        throws Exception {
		 BufferedImage srcImage;
		 File saveFile=new File(saveToFileStr);
		 srcImage = ImageIO.read(fromFile);
		 //srcImage = resize(srcImage, srcImage.getWidth(), srcImage.getHeight());

		 ImageIO.write(srcImage, imgType, saveFile);
	 }
	 public static void saveMiniImage (File fromFile,String saveToFileStr,int width,int hight,String imgType)
			 throws Exception {
		 BufferedImage srcImage;
		 File saveFile=new File(saveToFileStr);
		 srcImage = ImageIO.read(fromFile);
		 if(width > 0 || hight > 0)
		 {
			 srcImage = resize(srcImage, width, hight);
		 }
		 ImageIO.write(srcImage, imgType, saveFile);
	 }
	 public static void saveMiniImage (String fromFileStr,String saveToFileStr,int width,int hight,String imgType)
	 throws Exception {
	 BufferedImage srcImage;
	 File saveFile=new File(saveToFileStr);
	 File fromFile=new File(fromFileStr);
	 srcImage = ImageIO.read(fromFile);
	 if(width > 0 || hight > 0)
	 {
	 srcImage = resize(srcImage, width, hight);
	 }
	 ImageIO.write(srcImage, imgType, saveFile);
	
	 }
	 public static void saveMiniImage (String fromFileStr,int width,int hight,String imgType)
			 throws Exception{
		 saveMiniImage (fromFileStr,toMiniPath(fromFileStr),width,hight,imgType);
	 }
	 public static String toMiniPath (String path){
		 return path.substring(0,path.indexOf("."))+"mini"+path.substring(path.indexOf("."));
	 };//
	 public static File toFile (String path){
		 return new File(path);
	 }
	 public static String toRealPath (String path){
		 return Sys.BASEPATH + path;
		 //return ServletActionContext.getRequest().getRealPath("/" + path);
	 }
	 public static void saveImage(File fromFile,String destPath,String imgType) throws Exception{
		 String realDestPath = toRealPath (destPath);
		 System.out.println(realDestPath);
		 try {
			/*is = new FileInputStream(fromFile);
			destFile = toFile(destPath);
			os = new FileOutputStream(destFile);
			byte[] buffer = new byte[102400];
			int length = 0;
			while (-1 != (length = is.read(buffer))) {
				os.write(buffer, 0, length);
			}*/
			saveMiniImage(fromFile.toString(),realDestPath,960,720,imgType);
			saveMiniImage(realDestPath,50,50,imgType);
		} catch (Exception e) {
			deleteImage(destPath);
			throw(e);
		} finally{
			/*if(is!=null){
				is.close();
				is=null;
			}
			if(os!=null){
				os.close();
				os=null;
			}*/
		}
	 }
	 public static void deleteImage(String path) throws Exception{
		 try {
			File destFile = toFile(toRealPath (path));
			 if(destFile.exists()){
				 destFile.delete();
			 }
			 destFile = toFile(toMiniPath(toRealPath (path)));
			 if(destFile.exists()){
				 destFile.delete();
			 }
		} catch (Exception e) {
			throw(e);
		}
	 }
	 public static void deleteFile(String path) throws Exception{
		 try {
			 File destFile = toFile(toRealPath (path));
			 if(destFile.exists()){
				 destFile.delete();
			 }
		 } catch (Exception e) {
			 throw(e);
		 }
	 }
	 public static FileInputStream getInputStreamByPath(String path) throws FileNotFoundException{
		 return new FileInputStream(Sys.BASEPATH+path);
	 }
	 public static void saveFile(File fromFile,String destPath) throws Exception{
		 String realDestPath = toRealPath (destPath);
		 InputStream is = null;
		 OutputStream os = null;
		 File destFile=null;
		 try {
			is = new FileInputStream(fromFile);
			destFile = new File(realDestPath);
			os = new FileOutputStream(destFile);
			byte[] buffer = new byte[102400];
			int length = 0;
			while (-1 != (length = is.read(buffer))) {
				os.write(buffer, 0, length);
			}
		} catch (Exception e) {
			//deleteImage(destPath);
			throw(e);
		} finally{
			if(is!=null){
				is.close();
				is=null;
			}
			if(os!=null){
				os.close();
				os=null;
			}
		}
	 }
	 public static void saveVideo(File fromFile,String destPath) throws Exception{
		 String realDestPath = Sys.VIDEOBASEPATH+destPath;
		 InputStream is = null;
		 OutputStream os = null;
		 File destFile=null;
		 try {
			 is = new FileInputStream(fromFile);
			 destFile = new File(realDestPath);
			 os = new FileOutputStream(destFile);
			 byte[] buffer = new byte[102400];
			 int length = 0;
			 while (-1 != (length = is.read(buffer))) {
				 os.write(buffer, 0, length);
			 }
		 } catch (Exception e) {
			 //deleteImage(destPath);
			 throw(e);
		 } finally{
			 if(is!=null){
				 is.close();
				 is=null;
			 }
			 if(os!=null){
				 os.close();
				 os=null;
			 }
		 }
	 }
}