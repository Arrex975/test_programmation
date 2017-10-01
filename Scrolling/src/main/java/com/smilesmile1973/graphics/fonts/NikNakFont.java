package com.smilesmile1973.graphics.fonts;

import java.awt.image.BufferedImage;

import com.smilesmile1973.graphics.BufferedImageUtils;
import com.smilesmile1973.graphics.PixelArray;

public class NikNakFont extends BitmapFont {
	@Override
	protected void buildMap() {
		try {
			final BufferedImage bufferedImage = BufferedImageUtils.loadBufferedImage("/fonts/niknak_48x48.png");
			final PixelArray pixelArray = BufferedImageUtils.convertToPixelArray(bufferedImage, true);
			pixelArray.setTransparentForColor(0x000000);
			int w = 0;
			int h = 0;
			setWidth(48);
			setHeight(48);
			getFontsMap().put("A", pixelArray.getRect(w, 0, getWidth(), getHeight()));
			getFontsMap().put("B", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("C", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("D", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("E", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("F", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("G", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put("H", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("I", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("J", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("K", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("L", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("M", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put("N", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("O", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("P", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("Q", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("R", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("S", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put("T", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("U", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("V", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("W", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("X", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("Y", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put("Z", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("0", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("1", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("2", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("3", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("4", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put("5", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("6", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("7", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("8", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("9", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("!", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put(".", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("&", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put(",", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("(", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put(")", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

			h = h + getHeight();
			w = 0;
			getFontsMap().put("-", pixelArray.getRect(w, h, getWidth(), getHeight()));
			getFontsMap().put("'", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("?", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put(":", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put(" ", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));
			getFontsMap().put("ñ", pixelArray.getRect(w += getWidth(), h, getWidth(), getHeight()));

		} catch (final Exception e) {
		}

	}
}