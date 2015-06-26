package com.smilesmile1973.graphics;


public class PixelArray implements IPixelArray{
	private int[] table = null;
	private int backgroundColor = 0;
	private int width = 0;
	private int height = 0;

	public PixelArray(int width, int height, int backgroundColor) {
		this.backgroundColor = backgroundColor;
		setWidth(width);
		setHeight(height);
		table = new int[width * height];
		clear();
	}
	
	public void clear(){
		for (int i = 0;i < getTable().length;i++){
			getTable()[i] = backgroundColor;
		}
	}

	public void setPixel(int x, int y, int color) {
		if ((color & 0xff000000) != 0) {
			table[x + y * getWidth()] = color;
		}
	};

	public int getPixel(int x, int y) {
		return table[x + y * getWidth()];
	};

	public int[] getColumn(int x) {
		int[] results = new int[getHeight()];
		for (int i = 0; i < getHeight(); i++) {
			results[i] = getPixel(x, i);
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

	public void fillRectangleOfPixel(int x, int y, int width, int height, int[] arrayOfPixels) {
		int c = 0;
		for (int ix = x; ix < x + width; ix++) {
			for (int iy = y; iy < y + height; iy++) {
				setPixel(ix, iy, arrayOfPixels[c++]);
			}
		}
	}

	int[] up = new int[1];
	int[] tmpUp = new int[1];

	public void scrollUp(int number) {
		if (up.length != getWidth() * number) {
			up = new int[getWidth() * number];
		}
		if (tmpUp.length != getWidth() * (getHeight() - number)) {
			tmpUp = new int[getWidth() * (getHeight() - number)];
		}
		System.arraycopy(table, 0, up, 0, up.length);
		System.arraycopy(table, up.length, tmpUp, 0, tmpUp.length);
		System.arraycopy(tmpUp, 0, table, 0, tmpUp.length);
		System.arraycopy(up, 0, table, tmpUp.length, up.length);
	}

	private int[] down = new int[1];
	private int[] tmpDown = new int[1];

	public void scrollDown(int number) {
		if (down.length != getWidth() * number) {
			down = new int[getWidth() * number];
		}
		if (tmpDown.length != getWidth() * (getHeight() - number)) {
			tmpDown = new int[getWidth() * (getHeight() - number)];
		}
		System.arraycopy(table, tmpDown.length, down, 0, down.length);
		System.arraycopy(table, 0, tmpDown, 0, tmpDown.length);
		System.arraycopy(down, 0, table, 0, down.length);
		System.arraycopy(tmpDown, 0, table, down.length, tmpDown.length);
	}

	int[] right = new int[1];
	int[] tmpRight = new int[1];

	public void scrollRight(int number) {
		if (right.length != number) {
			right = new int[number];
		}
		if (table.length != number) {
			tmpRight = new int[table.length - number];
		}
		int tmpLength = getWidth() - number;
		int rowNumber = 0;
		int width = getWidth();
		for (int y = 0; y < getHeight(); y++) {
			rowNumber = y * getWidth();
			System.arraycopy(table, rowNumber + width - number, right, 0, number);
			System.arraycopy(table, rowNumber, tmpRight, 0, tmpLength);
			System.arraycopy(right, 0, table, rowNumber, number);
			System.arraycopy(tmpRight, 0, table, rowNumber + number, tmpLength);
		}
	}

	int[] left = new int[1];
	int[] tmpLeft = new int[1];

	public void scrollLeft(int number) {
		if (left == null || left.length != number) {
			left = new int[number];
		}
		if (tmpLeft == null || tmpLeft.length != (table.length - number)) {
			tmpLeft = new int[table.length - number];
		}
		int tmpLength = getWidth() - number;
		int rowNumber = 0;
		int width = getWidth();
		for (int y = 0; y < getHeight(); y++) {
			rowNumber = y * getWidth();
			System.arraycopy(table, rowNumber, left, 0, number);
			System.arraycopy(table, rowNumber + number, tmpLeft, 0, tmpLength);
			System.arraycopy(left, 0, table, rowNumber + width - number, number);
			System.arraycopy(tmpLeft, 0, table, rowNumber, tmpLength);
		}
	}

	public int[] getTable() {
		return table;
	}

	public void setTable(int[] table) {
		this.table = table;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void drawRect(int x, int y, int width,int height,int color){
		for (int j = y; j < y + height; j++){
			for (int i = x; i < x + width;i++){
				setPixel(i, j, color);
			}
		}
	}

	public int[] getRect(int x, int y, int width, int height) {
		int[] results = new int[width * height];
		int c = 0;
		for (int ix = x; ix < (x + width); ix++) {
			for (int iy = y; (iy < y + height); iy++) {
				results[c++] = getPixel(ix, iy);
			}
		}
		return results;
	}

	public void setTransparentForColor(int color) {
		for (int i = 0; i < getTable().length; i++) {
			int rgb = getTable()[i] & 0x00ffffff;
			if (rgb == color) {
				getTable()[i] = color;
			}
		}
	}
}
