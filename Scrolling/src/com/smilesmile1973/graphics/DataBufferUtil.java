package com.smilesmile1973.graphics;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class DataBufferUtil implements IPixelArray {
	private DataBuffer dataBuffer;
	private int[] table;
	private int width;
	private int backgroundColor;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private int height;

	public DataBufferUtil(DataBuffer dataBuffer,int width,int height,int color) {
		this.dataBuffer = dataBuffer;
		table = ((DataBufferInt) dataBuffer).getData();
		this.width = width;
		this.height = height;
		this.setBackgroundColor(color);
		this.clear();
	}

	public synchronized void copyToDataBuffer(int[] array) {
		System.arraycopy(array, 0, table, 0, array.length);
	}
	
	public synchronized void copyToDataBuffer(int[] array,int transparentColor) {
		for (int i = 0; i < getTable().length;i++){
			if (array[i] != transparentColor){
				getTable()[i] = array[i];
			}
		}
	}

	public void setPixel(int x, int y, int color) {
		if ((color & 0xff000000) != 0) {
			table[x + y * getWidth()] = color;
		}
	}

	@Override
	public void fillRectangleOfPixel(int x, int y, int width, int height, int[] arrayOfPixels) {
		int c = 0;
		for (int iy = y; iy < y + height; iy++) {
			for (int ix = x; ix < x + width; ix++) {
				setPixel(ix, iy, arrayOfPixels[c]);
				c++;
			}
		}
	}

	@Override
	public int getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public int[] getColumn(int x) {
		int[] results = new int[getHeight()];
		for (int i = 0; i < getHeight(); i++) {
			results[i] = getPixel(x, i);
		}
		return results;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getPixel(int x, int y) {
		return table[x + y * getWidth()];
	};

	@Override
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

	@Override
	public int[] getRow(int y) {
		int[] results = new int[getWidth()];
		for (int i = 0; i < getWidth(); i++) {
			results[i] = getPixel(i, y);
		}
		return results;
	}

	@Override
	public int[] getTable() {
		return table;
	}

	@Override
	public void setBackgroundColor(int color) {
		this.backgroundColor = color;

	}

	@Override
	public void setTable(int[] table) {
		this.table = table;

	}

	@Override
	public void setTransparentForColor(int color) {
		for (int i = 0; i < getTable().length; i++) {
			int rgb = getTable()[i] & 0x00ffffff;
			if (rgb == color) {
				getTable()[i] = color;
			}
		}
	}
	
	public void clear(){
		for (int i = 0; i < getTable().length;i++){
			getTable()[i] = getBackgroundColor();
		}
	}
}
