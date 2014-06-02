package com.core.validate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class RandImgCreater {

    private static final String CODELIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQLSTUVWXUZ";//0123456789
    // private static final String CODELIST = "ABCDEFGHIJ";
    private HttpServletResponse response = null;
    private static final int HEIGHT = 20;
    private static final int FONT_NUMBER = 4;
    private int width = 0;
    private int iNum = 0;
    private String codeList = "";
    boolean drawBgFlag = false;
    private int rBg = 0;
    private int gBg = 0;
    private int bBg = 0;

    public RandImgCreater(HttpServletResponse response) {
        this.response = response;
        this.width = 13 * FONT_NUMBER + 12;
        this.iNum = FONT_NUMBER;
        this.codeList = CODELIST;
    }

    public RandImgCreater(HttpServletResponse response, int iMunm,
            String codelist) {
        this.response = response;
        this.width = 13 * FONT_NUMBER + 12;
        this.iNum = FONT_NUMBER;
        this.codeList = CODELIST;
    }

    public String createRandImg() throws IOException {
        BufferedImage image = new BufferedImage(width, HEIGHT,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        Random random = new Random();
        if (drawBgFlag) {
            g.setColor(new Color(rBg, gBg, bBg));
            g.fillRect(0, 0, width, HEIGHT);
        } else {
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, width, HEIGHT);
            for (int i = 0; i < 155; i++) {
                g.setColor(getRandColor(140, 200));
                int x = random.nextInt(width);
                int y = random.nextInt(HEIGHT);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, xl + x, yl + y);
            }
        }
        g.setFont(new Font("Tims New Roman", Font.PLAIN, 18));
        String sRand = "";
        for (int i = 0; i < iNum; i++) {
            int rand = random.nextInt(codeList.length());
            String strRand = codeList.substring(rand, rand + 1);
            sRand += strRand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(strRand, 13 * i + 16, 16);
        }
        g.dispose();

//		BufferedInputStream bis=new BufferedInputStream(image);
        ServletOutputStream outStream = response.getOutputStream();
        ImageIO.write(image, "JPEG", outStream);
        outStream.flush();
        outStream.close();
        outStream = null;
//			OutputStream outStream=response.getOutputStream();

//			this.response.flushBuffer();
        // new File("C:\\HelloImage.jpeg"));
        // response.getOutputStream());


        return sRand;
    }

    public void setBgColor(int r, int g, int b) {
        drawBgFlag = true;
        this.rBg = r;
        this.gBg = g;
        this.bBg = b;
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}