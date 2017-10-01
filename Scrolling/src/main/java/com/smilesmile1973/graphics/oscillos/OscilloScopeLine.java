package com.smilesmile1973.graphics.oscillos;

public class OscilloScopeLine extends OscilloscopeArray {

	public OscilloScopeLine(int width, int height, int backgroundColor) {
		super(width, height, backgroundColor);
	}

	@Override
	public synchronized void drawOscillo(int[] points) {
		{
			drawLine(getOriginX(), getOriginY(), getOriginX() + getWidth(), getOriginY(), 0xffffffff);
			if (points != null) {
				setSpace(getWidth() / points.length);
				int x = getOriginX();
				int y = getOriginY();
				for (int i = 0; i < points.length; i++) {
					final int x2 = getOriginX() + getSpace() * i;
					final int y2 = getOriginY() + (points[i] / getScaleFactor());
					if (i == 0) {
						setPixel(x, y, 0xffffffff);
					} else {
						drawLine(x, y, x2, y2, 0xffffffff);
					}
					x = x2;
					y = y2;
				}
			}
		}

	}

}
