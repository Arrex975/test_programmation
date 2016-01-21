package com.smilesmile1973.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class BufferedImageUtils {
	public static BufferedImage convertToARGB(BufferedImage src) {
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(),
				dest.getColorModel().getColorSpace(), null);
		cco.filter(src, dest);
		System.out.println("Number of bands by pixel :" + dest.getRaster().getNumBands());
		System.out.println("Number of data elements  :" + dest.getRaster().getNumDataElements());
		System.out.println("Transfer type raster     :" + dest.getRaster().getTransferType());
		System.out.println("Transfer type dataBuffer :" + dest.getRaster().getDataBuffer().getDataType());
		System.out.println("Transfer type colorModel :" + dest.getColorModel().getTransferType());
		System.out.println("Number of bits by pixel  :" + dest.getColorModel().getPixelSize());
		System.out.println("alpha premultiplied      :" + dest.getColorModel().isAlphaPremultiplied());
		System.out.println("isCompatibleRaster       :" + dest.getColorModel().isCompatibleRaster(dest.getRaster()));
		System.out.println("Color space type         :" + dest.getColorModel().getColorSpace().getType());
		System.out.println("Sample model             :" + dest.getSampleModel().getClass());
		System.out.println("Color model class        :" + dest.getColorModel().getClass());
		return dest;
	}

	public static BufferedImage loadBufferedImage(String image) {
		BufferedImage result = null;
		try {
			InputStream in = BufferedImageUtils.class.getResourceAsStream(image);
			BufferedImage bufferedImage = ImageIO.read(in);
			result = convertToARGB(bufferedImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static PixelArray convertToPixelArray(BufferedImage image, boolean clone) {
		PixelArray result = null;
		result = new PixelArray(image.getWidth(), image.getHeight(), 0);
		if (!clone) {
			result.setTable(((DataBufferInt) image.getRaster().getDataBuffer()).getData());
		} else {
			System.arraycopy(((DataBufferInt) image.getRaster().getDataBuffer()).getData(), 0, result.getTable(), 0,
					result.getTable().length);
		}
		return result;
	}
}
