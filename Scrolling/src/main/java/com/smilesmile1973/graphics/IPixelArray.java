package com.smilesmile1973.graphics;

public interface IPixelArray {
	public void clear();

	public void drawLine(int x1, int y1, int x2, int y2, int color);

	public void fillRectangleOfPixel(int x, int y, int width, int height, int[] arrayOfPixels);

	public int getBackgroundColor();

	public int[] getColumn(int x);

	public int getHeight();

	public int getPixel(int x, int y);

	/**
	 * This method return an array of int containing the data inside a table.
	 * 
	 * @param x
	 *            the upper left corner
	 * @param y
	 *            the upper left corner
	 * @param width
	 *            the width of the rectangle to select
	 * @param height
	 *            the height of the rectangle to select
	 * @return an array of int.
	 */
	public int[] getRect(int x, int y, int width, int height);

	public int[] getRow(int y);

	public int[] getTable();

	public int getWidth();

	public void setBackgroundColor(int color);

	void setHeight(int height);

	public void setPixel(int x, int y, int color);

	public void setTable(int[] table);

	public void setTransparentForColor(int color);

	public void setWidth(int width);
}
