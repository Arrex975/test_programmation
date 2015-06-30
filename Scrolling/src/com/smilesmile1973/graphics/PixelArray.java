package com.smilesmile1973.graphics;

public class PixelArray extends AbstractPixelArray {
	
	public PixelArray(int width, int height, int backgroundColor) {
		setBackgroundColor(backgroundColor);
		setWidth(width);
		setHeight(height);
		setTable(new int[width * height]);
		clear();
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
		System.arraycopy(getTable(), 0, up, 0, up.length);
		System.arraycopy(getTable(), up.length, tmpUp, 0, tmpUp.length);
		System.arraycopy(tmpUp, 0, getTable(), 0, tmpUp.length);
		System.arraycopy(up, 0, getTable(), tmpUp.length, up.length);
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
		System.arraycopy(getTable(), tmpDown.length, down, 0, down.length);
		System.arraycopy(getTable(), 0, tmpDown, 0, tmpDown.length);
		System.arraycopy(down, 0, getTable(), 0, down.length);
		System.arraycopy(tmpDown, 0, getTable(), down.length, tmpDown.length);
	}

	int[] right = new int[1];
	int[] tmpRight = new int[1];

	public void scrollRight(int number) {
		if (right.length != number) {
			right = new int[number];
		}
		if (getTable().length != number) {
			tmpRight = new int[getTable().length - number];
		}
		int tmpLength = getWidth() - number;
		int rowNumber = 0;
		int width = getWidth();
		for (int y = 0; y < getHeight(); y++) {
			rowNumber = y * getWidth();
			System.arraycopy(getTable(), rowNumber + width - number, right, 0, number);
			System.arraycopy(getTable(), rowNumber, tmpRight, 0, tmpLength);
			System.arraycopy(right, 0, getTable(), rowNumber, number);
			System.arraycopy(tmpRight, 0, getTable(), rowNumber + number, tmpLength);
		}
	}

	int[] left = new int[1];
	int[] tmpLeft = new int[1];

	public void scrollLeft(int number) {
		if (left == null || left.length != number) {
			left = new int[number];
		}
		if (tmpLeft == null || tmpLeft.length != (getTable().length - number)) {
			tmpLeft = new int[getTable().length - number];
		}
		int tmpLength = getWidth() - number;
		int rowNumber = 0;
		int width = getWidth();
		for (int y = 0; y < getHeight(); y++) {
			rowNumber = y * getWidth();
			System.arraycopy(getTable(), rowNumber, left, 0, number);
			System.arraycopy(getTable(), rowNumber + number, tmpLeft, 0, tmpLength);
			System.arraycopy(left, 0, getTable(), rowNumber + width - number, number);
			System.arraycopy(tmpLeft, 0, getTable(), rowNumber, tmpLength);
		}
	}

	public void drawRect(int x, int y, int width, int height, int color) {
		for (int j = y; j < y + height; j++) {
			for (int i = x; i < x + width; i++) {
				setPixel(i, j, color);
			}
		}
	}
}
