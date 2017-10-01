package com.smilesmile1973.graphics.oscillos;

public class OscilloScopePoint extends OscilloscopeArray {

	public OscilloScopePoint(int width, int height, int backgroundColor) {
		super(width, height, backgroundColor);
		super.setNumberOfPoints(300);
	}

	@Override
	public synchronized void drawOscillo(int[] points) {
		drawLine(getOriginX(), getOriginY(), getOriginX() + getWidth(), getOriginY(), 0xffffffff);
		if (points != null) {
			setSpace(getWidth() / points.length);
			int x = 0;
			int y = 0;
			for (int i = 0; i < points.length; i++) {
				x = getOriginX() + getSpace() * i;
				y = getOriginY() + (points[i] / getScaleFactor());
				setPixel(x, y, 0xffffffff);

			}
		}
	}

}
