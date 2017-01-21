package com.smilesmile1973.graphics;

import com.smilesmile1973.Complex;

public class FFTarray extends AbstractPixelArray {
	private final int endColor = 0xFFFFFF00;
	private final int endFrequency = 4000;
	private final int[] gradients;
	private final int numberOfBars = 60;
	private int posX = 0;
	private int posY = 0;

	private final int sampleFrequency = 48000;
	private final int startColor = 0xFFFF0000;

	private final int startFrequency = 80;

	public FFTarray() {
		setBackgroundColor(0);
		setHeight(200);
		setWidth(300);
		setTable(new int[getHeight() * getWidth()]);
		gradients = generateGradient();
		clear();
	}

	@Override
	public void clear() {
		final int step = 5;
		for (int i = 0; i < getTable().length; i++) {
			if ((getTable()[i] & 0x00ffffff) == 0) {
				getTable()[i] = 0;
			} else {
				final int[] rgb = getRGB(getTable()[i]);
				for (int j = 0; j < rgb.length; j++) {
					if (rgb[j] > 0) {
						rgb[j] = rgb[j] - rgb[j] / step;
					}
				}
				if (rgb[0] < step){
					if (rgb[1] < step){
						if (rgb[2] < step){
							getTable()[i] = getBackgroundColor();
						}
					}
				}else{
					getTable()[i] = getColor(rgb);
				}
			}
		}
	}
	public void drawRect(int x, int y, int width, int height) {
		for (int j = y; j < y + height; j++) {
			final int color = gradients[j];
			for (int i = x; i < x + width; i++) {
				setPixel(i, j, color);
			}
		}
	}
	public int[] generateGradient() {
		final int[] results = new int[getHeight()];
		final int[] startRGB = getRGB(startColor);
		final int[] endRGB = getRGB(endColor);
		final double stepR = (double) (endRGB[0] - startRGB[0]) / (double) getHeight();
		final double stepG = (double) (endRGB[1] - startRGB[1]) / (double) getHeight();
		final double stepB = (double) (endRGB[2] - startRGB[2]) / (double) getHeight();
		for (int i = 0; i < getHeight(); i++) {
			final int[] tmp = new int[3];
			tmp[0] = (int) (startRGB[0] + i * stepR);
			tmp[1] = (int) (startRGB[1] + i * stepG);
			tmp[2] = (int) (startRGB[2] + i * stepB);
			results[i] = getColor(tmp);
		}
		return results;
	}

	public int getColor(int[] rgb) {
		int result = 0;
		result = result + rgb[2] + (rgb[1] << 8) + (rgb[0] << 16);
		result = result + 0xFF000000;
		return result;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int[] getRGB(int color) {
		final int[] results = new int[3];
		final int red = (color & 0x00ff0000) >>> 16;
		final int green = (color & 0x0000ff00) >>> 8;
		final int blue = color & 0x000000ff;
		results[0] = red;
		results[1] = green;
		results[2] = blue;
		return results;
	}

	public synchronized void processSoundData(int[] soundData, int lengthSound) {
		final int gap = 1;
		int height = 0;
		final Complex[] fftComplex = SoundPcmUtils.getFFTfromRawPCM(SoundPcmUtils.lead0(soundData));
		final double[] amplitudesD = SoundPcmUtils.convertComplexToAmplitude(fftComplex, sampleFrequency);
		final int[] indexes = SoundPcmUtils.getIndexForRangeOfFrequence(startFrequency, endFrequency, numberOfBars, lengthSound, sampleFrequency);
		final double[] amplitudes = SoundPcmUtils.getAmplitudes(amplitudesD, indexes);
		final int widthBar = (getWidth() / numberOfBars);
		if (amplitudes != null) {
			for (int i = 0; i < amplitudes.length; i++) {
				if (amplitudes[i] * 4 > 200) {
					height = 200;
				} else {
					height = (int) (amplitudes[i] * 4);
				}

				drawRect(i * widthBar + gap, getHeight() - height, widthBar - 2 * gap, height);
			}
		}
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void write(IPixelArray out) {
		out.fillRectangleOfPixel(getPosX(), getPosY(), getWidth(), getHeight(), getTable());
	}

}
