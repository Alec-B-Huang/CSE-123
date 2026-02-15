import java.util.*;
import java.awt.*;

public class Mondrian {

    public static void paintBasicMondrian(Color[][] pixels) {
        if (pixels == null || (pixels.length < 300 || pixels[0].length < 300)) {
            throw new IllegalArgumentException();
        }

        int x1 = 0;
        int y1 = 0;
        int x2 = pixels[0].length;
        int y2 = pixels.length;
        Random rand = new Random();

        recurseBasicMondrian(pixels, x1, x2, y1, y2, pixels[0].length, pixels.length, rand);
    }

    public static void paintComplexMondrian(Color[][] pixels) {
        if (pixels == null || pixels.length < 300 || pixels[0].length < 300) {
            throw new IllegalArgumentException();
        }

        int x1 = 0;
        int y1 = 0;
        int x2 = pixels[0].length;
        int y2 = pixels.length;
        Random rand = new Random();
    
        recurseComplexMondrian(pixels, x1, x2, y1, y2, pixels[0].length, pixels.length, rand);
    }

    private static void recurseBasicMondrian(Color[][] pixels, int x1, int x2, int y1, int y2, 
                                                int fullWidth, int fullHeight, Random rand) {
        int regionWidth = x2 - x1;
        int regionHeight = y2 - y1;
        int minSplitWidth = fullWidth / 4;
        int minSplitHeight = fullHeight / 4;     
        
        if (regionWidth >= minSplitWidth && regionHeight >= minSplitHeight && 
                                                regionHeight >= 20 && regionWidth >= 20) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseBasicMondrian(pixels, x1, randomSplitNumX, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, randomSplitNumX, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, x1, randomSplitNumX, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, randomSplitNumX, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionHeight >= minSplitHeight && regionHeight >= 20) {
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseBasicMondrian(pixels, x1, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, x1, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionWidth >= minSplitWidth && regionWidth >= 20) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);

            recurseBasicMondrian(pixels, x1, randomSplitNumX, y1, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseBasicMondrian(pixels, randomSplitNumX, x2, y1, y2, 
                                                        fullWidth, fullHeight, rand);

        } else {
            int randomColorNumber = rand.nextInt(4);
            Color color = null;

            if (randomColorNumber == 0) {
                color = Color.RED;
            } else if (randomColorNumber == 1) {
                color = Color.YELLOW;
            } else if (randomColorNumber == 2) {
                color = Color.CYAN;
            } else {
                color = Color.WHITE;
            }

            fillRegion(pixels, x1, x2, y1, y2, color);
        }
    }

    private static void recurseComplexMondrian(Color[][] pixels, int x1, int x2, int y1, int y2, 
                                                int fullWidth, int fullHeight, Random rand) {
        int regionWidth = x2 - x1;
        int regionHeight = y2 - y1;
        int minSplitWidth = fullWidth / 4;
        int minSplitHeight = fullHeight / 4;   
        int splitOrNot = rand.nextInt(2);
        
        if (regionWidth >= minSplitWidth && regionHeight >= minSplitHeight && 
                                    regionHeight >= 20 && regionWidth >= 20 && splitOrNot == 1) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseComplexMondrian(pixels, x1, randomSplitNumX, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, randomSplitNumX, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, x1, randomSplitNumX, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, randomSplitNumX, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionHeight >= minSplitHeight && regionHeight >= 20 && splitOrNot == 1) {
            int randomSplitNumY = rand.nextInt((y2 - 10) - (y1 + 10) + 1) + (y1 + 10);

            recurseComplexMondrian(pixels, x1, x2, y1, randomSplitNumY, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, x1, x2, randomSplitNumY, y2, 
                                                        fullWidth, fullHeight, rand);

        } else if (regionWidth >= minSplitWidth && regionWidth >= 20 && splitOrNot == 1) {
            int randomSplitNumX = rand.nextInt((x2 - 10) - (x1 + 10) + 1) + (x1 + 10);

            recurseComplexMondrian(pixels, x1, randomSplitNumX, y1, y2, 
                                                        fullWidth, fullHeight, rand);
            recurseComplexMondrian(pixels, randomSplitNumX, x2, y1, y2, 
                                                        fullWidth, fullHeight, rand);

        } else {
            int randomColorNumber = rand.nextInt(4);
            Color color = null;

            if (randomColorNumber == 0) {
                color = Color.RED;
            } else if (randomColorNumber == 1) {
                color = Color.YELLOW;
            } else if (randomColorNumber == 2) {
                color = Color.CYAN;
            } else {
                color = Color.WHITE;
            }

            fillRegion(pixels, x1, x2, y1, y2, color);
        }
    }

    public static void fillRegion(Color[][] pixels, int x1, int x2, int y1, int y2, Color color) {
        for (int i = (y1 + 1); i < (y2 - 1); i++) {
            for (int j = (x1 + 1); j < (x2 - 1); j++) {
                pixels[i][j] = color;
            }
        }
    }    
}