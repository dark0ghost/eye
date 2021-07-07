package org.dark0ghost.ai

import org.bytedeco.opencv.global.opencv_imgcodecs.imread


class Ai {
   
   private fun loadImage(fileName: String){
     val src = imread(fileName)
   }
}