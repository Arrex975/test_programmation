package com.smilesmile1973.graphics;

public abstract class AbstractPixelArray implements IPixelArray {
	private int[] table = null;
	private int backgroundColor = 0;
	private int width = 0;
	private int height = 0;

	@Override
	public void clear() {
		for (int i = 0; i < getTable().length; i++) {
			getTable()[i] = backgroundColor;
		}
	}

	public synchronized void fillRectangleOfPixel(int x, int y, int width, int height, int[] arrayOfPixels) {
		int c = 0;
		for (int iy = y; iy < y + height; iy++) {
			for (int ix = x; ix < x + width; ix++) {
				setPixel(ix, iy, arrayOfPixels[c]);
				c++;
			}
		}
	}

	public synchronized int getBackgroundColor() {
		return backgroundColor;
	}

	public synchronized int[] getColumn(int x) {
		int[] results = new int[getHeight()];
		for (int i = 0; i < getHeight(); i++) {
			results[i] = getPixel(x, i);
		}
		return results;
	}

	public synchronized int getHeight() {
		return height;
	}

	public synchronized int getPixel(int x, int y) {
		return table[x + y * getWidth()];
	};

	public synchronized int[] getRect(int x, int y, int width, int height) {
		int[] results = new int[width * height];
		int c = 0;
		for (int iy = y; (iy < y + height); iy++) {
			for (int ix = x; ix < (x + width); ix++) {
				results[c++] = getPixel(ix, iy);
			}
		}
		return results;
	}

	public synchronized int[] getRow(int y) {
		int[] results = new int[getWidth()];
		for (int i = 0; i < getWidth(); i++) {
			results[i] = getPixel(i, y);
		}
		return results;
	}

	public synchronized int[] getTable() {
		return table;
	}

	public synchronized int getWidth() {
		return width;
	}

	public synchronized void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * This method will write a pixel at x and y.<br>
	 * If x >= 0 and x < {@link #getWidth()}<br>
	 * If y >= 0 and y < {@link #getHeight()}<br>
	 * 
	 * @param x
	 *            position on the x axis.
	 * @param y
	 *            position on the y axis.
	 * @author marechal
	 */
	public synchronized void setPixel(int x, int y, int color) {
		if ((color & 0xff000000) != 0) {
			if (x < getWidth() && y < getHeight()) {
				if (x >= 0 && y >= 0) {
					table[x + y * getWidth()] = color;
				}
			}
		}
	}

	public synchronized void setTable(int[] table) {
		this.table = table;
	}

	public synchronized void setTransparentForColor(int color) {
		for (int i = 0; i < getTable().length; i++) {
			int rgb = getTable()[i] & 0x00ffffff;
			if (rgb == color) {
				getTable()[i] = color;
			}
		}
	}

	public synchronized void setWidth(int width) {
		this.width = width;
	}

	public synchronized void setHeight(int height) {
		this.height = height;
	}

	public synchronized void drawLine(int x1, int y1, int x2, int y2, int color) {
		int d = 0;

		int dy = Math.abs(y2 - y1);
		int dx = Math.abs(x2 - x1);

		int dy2 = (dy << 1); // slope scaling factors to avoid floating
		int dx2 = (dx << 1); // point

		int ix = x1 < x2 ? 1 : -1; // increment direction
		int iy = y1 < y2 ? 1 : -1;

		if (dy <= dx) {
			for (;;) {
				setPixel(x1, y1, color);
				if (x1 == x2)
					break;
				x1 += ix;
				d += dy2;
				if (d > dx) {
					y1 += iy;
					d -= dx2;
				}
			}
		} else {
			for (;;) {
				setPixel(x1, y1, color);
				if (y1 == y2)
					break;
				y1 += iy;
				d += dx2;
				if (d > dy) {
					x1 += ix;
					d -= dy2;
				}
			}
		}
	}
	
	@Override
	public void setPixel(int x, int y, int z, int color) {
		double xd = (double) x;
		double yd = (double) y;
		double zd = (double) z;
		x = (int) Math.round(xd/(zd+100d));
		y = (int) Math.round(yd/(zd+100d));
		setPixel(x, y, color);
	}
	
	
}
