package com.smilesmile1973.graphics;

import java.text.DecimalFormat;
import java.util.Arrays;

import com.smilesmile1973.Complex;
import com.smilesmile1973.FFT;

public class SoundPcmUtils {
	public static int[][] transform(int[] data, int length) {
		//int numberOfChannels = 2;
		int[][] result = new int[2][length/2];
		int c = 0;
		for (int i = 0; i < length/2; i++) {
			result[0][i]=data[c];
			result[1][i]=data[c++];
			c++;
		}
		return result;
	}

	public static int[] interpolate(int numberOfBand, int[] data) {
		int[] result = new int[numberOfBand];
		int set = data.length / numberOfBand;
		double tmp = 0;
		int c = 0;
		for (int i = 0; i < set * numberOfBand; i++) {
			tmp = tmp + (double) data[i];
			if ((i > 0) && (i % set == 0)) {
				result[c] = (int) Math.round(tmp / (double) set);
				c++;
				tmp = 0;
			}
		}
		return result;
	}

	public static int[] rescale(int heightMax, int maxValue, int[] data) {
		int[] result = new int[data.length];
		double tmp = 0;
		for (int i = 0; i < data.length; i++) {
			tmp = ((double) data[i] / (double) maxValue) * (double) heightMax;
			result[i] = (int) Math.round(tmp);
		}
		return result;
	}

	public static Complex[] getFFTfromRawPCM(int[] data) {
		Complex[] results = null;
		Complex[] complexData = new Complex[data.length];
		//convert between 0 and 1
		for (int i = 0; i < data.length; i++) {
			
			complexData[i] = new Complex((double)data[i]/(double)Short.MAX_VALUE, 0);
		}
		results = FFT.fft(complexData);
		return results;
	}
	
	public static double[] convertComplexToAmplitude(Complex[] complex,int sampleRate){
		double[] results = new double[complex.length/2-1];
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
		double index = frequency * (double) length / (double) sampleFrequency;
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
		double[] amplitudes = convertComplexToAmplitude(complex, sampleFrequency);
		System.out.println("===========================================");
		for (int i = 0; i < amplitudes.length;i++){
			double frequency = getFrequencyByIndex(i,complex.length,sampleFrequency);
			System.out.println(String.format("%f", frequency)+";"+String.format("%f",amplitudes[i]));
		}
		System.out.println("===========================================");
	}
	
	public static int[] getIndexForRangeOfFrequence(int startFrq,int endFrq,int numberOfBands,int length,int sampleFrequency){
		int step = (endFrq-startFrq)/numberOfBands;
		int[] results = new int[numberOfBands];
		for (int i = 0; i < numberOfBands; i ++){
			results[i] = getIndexByFrequency(startFrq + i * step, length, sampleFrequency);
		}
		return results;
	}
	
	public static int[] getAmplitudes(double[] fftResults,int[] indexes){
		int[] results = new int[indexes.length];
		for (int i = 0; i < results.length;i++){
			results[i] = (int) fftResults[indexes[i]];
		}
		return results;
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
