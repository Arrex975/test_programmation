package com.smilesmile1973.graphics;

import java.awt.image.BufferedImage;
import java.util.TreeMap;

public class MetalFonts {
	private int width = 32;
	private int height = 32;
	private int interLine = 3;
	private TreeMap<String, int[]> fontsMap = new TreeMap<String, int[]>();

	public MetalFonts() {
		try {
			BufferedImage bufferedImage = BufferedImageUtils.loadBufferedImage("resources/MetalFonts.png");
			PixelArray pixelArray = BufferedImageUtils.convertToPixelArray(bufferedImage, true);
			pixelArray.setTransparentForColor(0x000000);
			int c = 0;
			int d = 0;
			fontsMap.put("A", pixelArray.getRect(c,d, width, height));
			fontsMap.put("B",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("C",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("D",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("E",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("F",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("G",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("H",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("I",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("J",pixelArray.getRect(c+=32,d,width,height));
			d = d + height;
			c = 0;
			fontsMap.put("K",pixelArray.getRect(c,d,width,height));
			fontsMap.put("L",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("M",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("N",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("O",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("P",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("Q",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("R",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("S",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("T",pixelArray.getRect(c+=32,d,width,height));
			d = d + height;
			c = 0;
			fontsMap.put("U",pixelArray.getRect(c,d,width,height));
			fontsMap.put("V",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("W",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("X",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("Y",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("Z",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("1",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("2",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("3",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("4",pixelArray.getRect(c+=32,d,width,height));
			d = d + height;
			c = 0;
			fontsMap.put("5",pixelArray.getRect(c,d,width,height));
			fontsMap.put("6",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("7",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("8",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("9",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("0",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put(" ",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("SPACE2",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put("!",pixelArray.getRect(c+=32,d,width,height));
			fontsMap.put(".",pixelArray.getRect(c+=32,d,width,height));
			d = d + height;
			c = 0;
			fontsMap.put(",", pixelArray.getRect(c, d, width, height));
			fontsMap.put("(", pixelArray.getRect(c+=32, d, width, height));
			fontsMap.put(")", pixelArray.getRect(c+=32, d, width, height));
			fontsMap.put("'", pixelArray.getRect(c+=32, d, width, height));
		} catch (Exception e) {
		}
	}
	
	public void write(int x,int y,String string,IPixelArray out){
		int tmpX = x;
		int[] tmp = null;
		for (int i = 0; i < string.length();i++){
			char tmpChar = string.charAt(i);
			if(tmpChar == '\n'){
				y = y + height + interLine;
				x = tmpX;
			} else {
				tmp = fontsMap.get(String.valueOf(tmpChar));
				if (tmp != null){
					out.fillRectangleOfPixel(x, y,width,height,tmp);
				}
				x = x + width;
			}	
		}
	}
}
