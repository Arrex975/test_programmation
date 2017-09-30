package com.smilesmile1973.graphics;

import java.awt.image.BufferedImage;
import java.util.TreeMap;

public class XenonFonts {
	private final int width = 16;
	private final int height = 23;
	private final TreeMap<String, int[]> fontsMap = new TreeMap<String, int[]>();
	private int posX;
	private int posY;
	private int interLine = 3;

	private String textToDisplay;

	public XenonFonts() {
		try {
			final BufferedImage bufferedImage = BufferedImageUtils.loadBufferedImage("/xenon2.bmp");
			final PixelArray pixelArray = BufferedImageUtils.convertToPixelArray(bufferedImage, true);
			pixelArray.setTransparentForColor(0x000000);
			int c = 0;
			fontsMap.put(" ", pixelArray.getRect(c, 0, width, height));
			fontsMap.put(".", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("0", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("1", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("2", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("3", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("4", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("5", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("6", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("7", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("8", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("9", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put(":", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("<", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put(">", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("A", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("B", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("C", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("D", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("E", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("F", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("G", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("H", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("I", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("J", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("K", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("L", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("M", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("N", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("O", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("P", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("Q", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("R", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("S", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("T", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("U", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("V", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("W", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("X", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("Y", pixelArray.getRect(c += width, 0, width, height));
			fontsMap.put("Z", pixelArray.getRect(c += width, 0, width, height));
		} catch (final Exception e) {
		}
	}

	public int getHeightOfText() {
		int result = 0;
		for (int i = 0; i < getTextToDisplay().length(); i++) {
			final char tmpChar = getTextToDisplay().charAt(i);
			if (tmpChar == '\n') {
				result = result + height + interLine;
			}
		}
		return result;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public String getTextToDisplay() {
		return textToDisplay;
	}

	public void moveDown(int speed) {
		setPosXPosy(posX, posY + speed);
	}

	public void moveUp(int speed) {
		setPosXPosy(posX, posY - speed);
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosXPosy(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setTextToDisplay(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

	public void write(IPixelArray out) {
		int x = posX;
		int y = posY;
		final int tmpX = x;
		int[] tmp = null;
		for (int i = 0; i < getTextToDisplay().length(); i++) {
			final char tmpChar = getTextToDisplay().charAt(i);
			if (tmpChar == '\n') {
				y = y + height + interLine;
				x = tmpX;
			} else {
				tmp = fontsMap.get(String.valueOf(tmpChar));
				if (tmp != null) {
					out.fillRectangleOfPixel(x, y, width, height, tmp);
				}
				x = x + width;
			}
		}
	}
}
