package com.smilesmile1973.graphics;

public interface IPixelArray {
	public void fillRectangleOfPixel(int x, int y,int width,int height, int[] arrayOfPixels);
	public int getBackgroundColor();
	public int[] getColumn(int x);
	public int getHeight();
	public int getPixel(int x, int y);
	public int[] getRect(int x,int y,int width,int height);
	public int[] getRow(int y);
	public int[] getTable();
	public int getWidth();
	public void setBackgroundColor(int color);
	public void setPixel(int x, int y, int color);
	public void setTable(int[] table);
	public void setTransparentForColor(int color);
	public void setWidth(int width);
	public void clear();
	void setHeight(int height);
	public void drawLine(int x1, int y1, int x2, int y2, int color);
	//3d
	public void setPixel(int x,int y, int z, int color);
	
}
