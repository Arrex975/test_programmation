package com.smilesmile1973.graphics;

import java.awt.image.BufferedImage;

public class XenonFonts extends BitmapFont {
	public XenonFonts() {
		try {
			final BufferedImage bufferedImage = BufferedImageUtils.loadBufferedImage("/xenon2.bmp");
			final PixelArray pixelArray = BufferedImageUtils.convertToPixelArray(bufferedImage, true);
			pixelArray.setTransparentForColor(0x000000);
			int c = 0;
			getFontsMap().put(" ", pixelArray.getRect(c, 0, width, height));
			getFontsMap().put(".", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("0", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("1", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("2", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("3", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("4", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("5", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("6", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("7", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("8", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("9", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put(":", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("<", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put(">", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("A", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("B", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("C", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("D", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("E", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("F", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("G", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("H", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("I", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("J", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("K", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("L", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("M", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("N", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("O", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("P", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("Q", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("R", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("S", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("T", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("U", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("V", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("W", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("X", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("Y", pixelArray.getRect(c += width, 0, width, height));
			getFontsMap().put("Z", pixelArray.getRect(c += width, 0, width, height));
		} catch (final Exception e) {
		}
	}
}
