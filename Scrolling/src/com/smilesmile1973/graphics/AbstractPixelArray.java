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
	
	public void fillRectangleOfPixel(int x, int y, int width, int height, int[] arrayOfPixels) {
		int c = 0;
		for (int iy = y; iy < y + height; iy++) {
			for (int ix = x; ix < x + width; ix++) {
				setPixel(ix, iy, arrayOfPixels[c++]);
			}
		}
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public int[] getColumn(int x) {
		int[] results = new int[getHeight()];
		for (int i = 0; i < getHeight(); i++) {
			results[i] = getPixel(x, i);
		}
		return results;
	}

	public int getHeight() {
		return height;
	}

	public int getPixel(int x, int y) {
		return table[x + y * getWidth()];
	};

	public int[] getRect(int x, int y, int width, int height) {
		int[] results = new int[width * height];
		int c = 0;
		for (int iy = y; (iy < y + height); iy++) {
			for (int ix = x; ix < (x + width); ix++) {
				results[c++] = getPixel(ix, iy);
			}
		}
		return results;
	}

	public int[] getRow(int y) {
		int[] results = new int[getWidth()];
		for (int i = 0; i < getWidth(); i++) {
			results[i] = getPixel(i, y);
		}
		return results;
	}

	public int[] getTable() {
		return table;
	}

	public int getWidth() {
		return width;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * This method will write a pixel at x and y.<br>
	 * If x >= 0 and x < {@link #getWidth()}<br>
	 * If y >= 0 and y < {@link #getHeight()}<br>
	 * @param x position on the x axis.
	 * @param y position on the y axis.
	 * @author marechal
	 */
	public void setPixel(int x, int y, int color) {
		if ((color & 0xff000000) != 0) {
			if (x < getWidth() && y < getHeight()) {
				if (x >= 0 && y >= 0) {
					table[x + y * getWidth()] = color;
				}
			}
		}
	}

	public void setTable(int[] table) {
		this.table = table;
	}

	public void setTransparentForColor(int color) {
		for (int i = 0; i < getTable().length; i++) {
			int rgb = getTable()[i] & 0x00ffffff;
			if (rgb == color) {
				getTable()[i] = color;
			}
		}
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
