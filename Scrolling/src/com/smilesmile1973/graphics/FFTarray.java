package com.smilesmile1973.graphics;

import com.smilesmile1973.Complex;

public class FFTarray extends AbstractPixelArray {
	private int posX = 0;

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	private int posY = 0;
	private int numberOfBars = 20;
	private int sampleFrequency = 48000;

	public FFTarray() {
		setBackgroundColor(0);
		setHeight(200);
		setWidth(300);
		setTable(new int[getHeight() * getWidth()]);
		clear();
	}

	public synchronized void processSoundData(int[] soundData, int lengthSound) {
		int height = 0;
		Complex[] fftComplex = SoundPcmUtils.getFFTfromRawPCM(SoundPcmUtils.lead0(soundData));
		double[] amplitudesD = SoundPcmUtils.convertComplexToAmplitude(fftComplex, sampleFrequency);
		int[] indexes = SoundPcmUtils.getIndexForRangeOfFrequence(80, 1080, numberOfBars, lengthSound, sampleFrequency);
		double[] amplitudes = SoundPcmUtils.getAmplitudes(amplitudesD, indexes);
		if (amplitudes != null) {
			for (int i = 0; i < amplitudes.length; i++) {
				if (amplitudes[i] * 4 > 200) {
					height = 200;
				} else {
					height = (int) (amplitudes[i] * 4);
				}
				drawRect(i * 15,getHeight()-height, 15, height, 0xffff0000);
			}
		}
	}

	public void drawRect(int x, int y, int width, int height, int color) {
		for (int j = y; j < y + height; j++) {
			for (int i = x; i < x + width; i++) {
				setPixel(i, j, color);
			}
		}
	}
	
	public void write(IPixelArray out){
		out.fillRectangleOfPixel(getPosX(), getPosY(), getWidth(), getHeight(), getTable());
	}

}
