package com.smilesmile1973.graphics.fonts.writer;

import com.smilesmile1973.graphics.IPixelArray;
import com.smilesmile1973.graphics.fonts.BitmapFont;

public class FontWriterUtil {

	public static void writeHorizontally(IPixelArray out, String text, BitmapFont font, int posX, int posY) {

	}

	public static void writeVertically(IPixelArray out, String text, BitmapFont font, int x, int y) {
		final int tmpX = x;
		int[] tmp = null;
		for (int i = 0; i < text.length(); i++) {
			final char tmpChar = text.charAt(i);
			if (tmpChar == '\n') {
				y = y + font.getHeight() + font.getInterLine();
				x = tmpX;
			} else {
				tmp = font.getFontsMap().get(String.valueOf(tmpChar));
				if (x + font.getWidth() > -1 && x <= out.getWidth() && y + font.getHeight() > -1
						&& y <= out.getHeight()) {
					if (tmp != null) {
						out.fillRectangleOfPixel(x, y, font.getWidth(), font.getHeight(), tmp);
					} else {
						out.fillRectangleOfPixel(x, y, font.getWidth(), font.getHeight(),
								font.getFontsMap().get(String.valueOf(" ")));
					}
				}
				x = x + font.getWidth();
			}
		}
	}
}