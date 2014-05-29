package com.core.validate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateImgae {
	public static void main(String args[]) {
		BufferedImage image = new BufferedImage(80, 25,
				BufferedImage.TYPE_INT_RGB); // java.awt.image.BufferedImage
		Graphics g = image.getGraphics(); // java.awt.Graphics;
		g.setColor(new Color(255, 255, 255)); // java.awt.Color;
		g.fillRect(0, 0, 80, 25);
		g.setColor(new Color(0, 0, 0));
		g.drawString("HelloImage", 6, 16);
		//g.draw3DRect(20, 12, 20, 40, false) ; //画立体图
		g.dispose();
		try {
			ImageIO.write(image, "jpeg", new File("C:\\HelloImage.jpeg")); // java.io.File;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
