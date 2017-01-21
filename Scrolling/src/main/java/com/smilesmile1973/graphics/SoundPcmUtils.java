package com.smilesmile1973.graphics;

import com.smilesmile1973.Complex;
import com.smilesmile1973.FFT;

public class SoundPcmUtils {

	private static int[][] resultsTransform = new int[2][1];
	public static int[][] transform(int[] data, int length) {
		//int numberOfChannels = 2;
		if (resultsTransform[0].length != length / 2){
			resultsTransform = new int[2][length/2];
		}
		int c = 0;
		for (int i = 0; i < length/2; i++) {
			resultsTransform[0][i]=data[c];
			resultsTransform[1][i]=data[c++];
			c++;
		}
		return resultsTransform;
	}

	private static int[] resultsInterpolate = new int[1];
	public static int[] interpolate(int numberOfBand, int[] data) {
		if (resultsInterpolate.length != numberOfBand){
			resultsInterpolate = new int[numberOfBand];
		}
		final int set = data.length / numberOfBand;
		double tmp = 0;
		int c = 0;
		for (int i = 0; i < set * numberOfBand; i++) {
			tmp = tmp + data[i];
			if ((i > 0) && (i % set == 0)) {
				resultsInterpolate[c] = (int) Math.round(tmp / set);
				c++;
				tmp = 0;
			}
		}
		return resultsInterpolate;
	}

	public static int[] rescale(int heightMax, int maxValue, int[] data) {
		final int[] result = new int[data.length];
		final int factor = (int) Math.round((double) maxValue / (double) heightMax);
		double tmp = 0;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i] / factor;
			result[i] = (int) Math.round(tmp);
		}
		return result;
	}


	public static Complex[] getFFTfromRawPCM(int[] data) {
		Complex[] complexResults = null;
		final Complex[] complexData = new Complex[data.length];
		//convert between 0 and 1
		for (int i = 0; i < data.length; i++) {
			complexData[i] = new Complex((double)data[i]/(double)Short.MAX_VALUE, 0);
		}
		complexResults = FFT.fft(complexData);
		return complexResults;
	}

	public static double[] convertComplexToAmplitude(Complex[] complex,int sampleRate){
		final double[] results = new double[complex.length/2-1];
		for(int i = 0; i < complex.length/2-1;i++){
			results[i] = Math.sqrt(complex[i].re()*complex[i].re() + complex[i].im() * complex[i].im());
		}
		return results;
	}

	public static double getFrequencyByIndex(int index,int length,int sampleFrequency){
		double result = 0;
		result = (index * sampleFrequency)/length;
		return result;
	}

	public static int getIndexByFrequency(double frequency,int length,int sampleFrequency){
		final double index = frequency * length / sampleFrequency;
		return (int) index;
	}


	public static int nearestPowerOf2(final int a) {
		int b = 1;
		while (b < a) {
			b = b << 1;
		}
		return b;
	}

	public static synchronized void displayTable(Complex[] complex,int length,int sampleFrequency){
		final double[] amplitudes = convertComplexToAmplitude(complex, sampleFrequency);
		System.out.println("===========================================");
		for (int i = 0; i < amplitudes.length;i++){
			final double frequency = getFrequencyByIndex(i,complex.length,sampleFrequency);
			System.out.println(String.format("%f", frequency)+";"+String.format("%f",amplitudes[i]));
		}
		System.out.println("===========================================");
	}

	public static int[] getIndexForRangeOfFrequence(int startFrq,int endFrq,int numberOfBands,int length,int sampleFrequency){
		final int step = (endFrq-startFrq)/numberOfBands;
		final int[] results = new int[numberOfBands];
		for (int i = 0; i < numberOfBands; i ++){
			results[i] = getIndexByFrequency(startFrq + i * step, length, sampleFrequency);
		}
		return results;
	}

	private static double[] resultsAmplitudes = new double[1];
	public static double[] getAmplitudes(double[] fftResults,int[] indexes){
		if (resultsAmplitudes.length != indexes.length){
			resultsAmplitudes = new double[indexes.length];
		}
		for (int i = 0; i < resultsAmplitudes.length;i++){
			double tmp = 0;
			if (i+1 < indexes.length){
				for (int j=indexes[i];j < indexes[i+1];j++){
					tmp = tmp + fftResults[j];
				}
				tmp = tmp / (indexes[i+1]-indexes[i]);
			}
			resultsAmplitudes[i] = fftResults[indexes[i]];
		}
		return resultsAmplitudes;
	}

	public static int[] lead0(int[] data){
		int[] results;
		if (nearestPowerOf2(data.length) != data.length){
			results = new int[nearestPowerOf2(data.length)];
			System.arraycopy(data, 0, results, 0, data.length);
			for (int i = data.length; i < results.length;i++){
				results[i] = 0;
			}
		}else {
			results = data;
		}
		return results;
	}

}
