package org.dark0ghost.ai

import org.bytedeco.opencv.global.opencv_imgcodecs.imread
import org.bytedeco.opencv.opencv_core.Mat


class Ai {
    private fun loadImage(fileName: String): Mat = imread(fileName)

}