import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Blurring {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("image.jpg"));
        WritableRaster raster = image.getRaster();

        int width = raster.getWidth();
        int height = raster.getHeight();

        final int blurMatrixSize = 5;
        final double[][] blurMatrix = new double[blurMatrixSize][blurMatrixSize];
        //заполняем массив размытия
        double blurMatrixValues = 1.0 / (blurMatrixSize * blurMatrixSize);
        for (int i = 0; i < blurMatrixSize; i++) {
            for (int j = 0; j < blurMatrixSize; j++) {
                blurMatrix[i][j] = blurMatrixValues;
            }
        }

        Pixel[][] originalPixels = new Pixel[width][height];
        final int colorsCountInRgb = 3;
        int[] tmpPixel = new int[colorsCountInRgb];

        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                raster.getPixel(i, j, tmpPixel);
                originalPixels[i][j] = new Pixel(tmpPixel);
            }
        }

        int shift = blurMatrix.length / 2;
        for (int j = shift; j < height - shift; ++j) {
            for (int i = shift; i < width - shift; ++i) {
                tmpPixel = getRGB(originalPixels, i, j, blurMatrix);
                raster.setPixel(i, j, tmpPixel);
            }
        }
        ImageIO.write(image, "png", new File("out.png"));
    }

    private static int[] getRGB(Pixel[][] pixels, int x, int y, double[][] blurMatrix) {
        final int colorsCountInRgb = 3;
        int[] tmpPixel = new int[colorsCountInRgb];
        int shift = blurMatrix.length / 2;
        for (int k = 0; k < colorsCountInRgb; k++) {
            double tmp = 0;
            for (int i = -shift; i <= shift; i++) {
                for (int j = -shift; j <= shift; j++) {
                    tmp += pixels[x + i][y + j].getRgbArray()[k] * blurMatrix[i + shift][j + shift];
                }
            }
            tmpPixel[k] = sat((int) tmp);
        }
        return tmpPixel;
    }

    private static int sat(int x) {
        int maxRgb = 255;
        int minRgb = 0;
        if (x > maxRgb) {
            return maxRgb;
        } else if (x < minRgb) {
            return minRgb;
        } else {
            return x;
        }
    }
}

class Pixel {
    private int[] rgbArray;

    public Pixel(int[] rgbArray) {
        this.rgbArray = Arrays.copyOf(rgbArray, rgbArray.length);
    }

    public int[] getRgbArray() {
        return Arrays.copyOf(rgbArray, rgbArray.length);
    }
}

