package com.smilesmile1973.graphics;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class DataBufferUtil extends AbstractPixelArray {
	public DataBufferUtil(DataBuffer dataBuffer, int width, int height, int color) {
		setTable(((DataBufferInt) dataBuffer).getData());
		setWidth(width);
		setHeight(height);
		setBackgroundColor(color);
		this.clear();
	}

	public synchronized void copyToDataBuffer(int[] array) {
		System.arraycopy(array, 0, getTable(), 0, array.length);
	}

	public synchronized void copyToDataBuffer(int[] array, int transparentColor) {
		for (int i = 0; i < getTable().length; i++) {
			if (array[i] != transparentColor) {
				getTable()[i] = array[i];
			}
		}
	}
}
