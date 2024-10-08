package org.firstinspires.ftc.teamcode.library.vision;

import org.opencv.core.Core;
import org.opencv.core.Point;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class PixelDetect extends OpenCvPipeline {
    private Mat mat = new Mat();
    private Point center = new Point(0, 0);
    private double contourArea = 0;

    //Threshold to determine if there is an object
    //TODO: use contour area threshold instead
    final static double PERCENT_COLOR_THRESHOLD = 0.1;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        //Hue, Saturation, Value ranges
        Scalar lowHSV = new Scalar(0, 0, 172);//change color HSV
        Scalar highHSV = new Scalar(255, 32, 255);//change color HSV

        //creates submatrices
        Core.inRange(mat, lowHSV, highHSV, mat);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

        int largestContourIdx = 0;

        Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        //skips contour calculations if there are no contours
        if (contours.size() == 0) {
            contourArea = 0;
            hierarchy.release();

            //stuff to make the gray scale appear on on robot phone
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

            return mat;
        }

        //finds the largest contour
        for (int contourIdx=0; contourIdx < contours.size(); contourIdx++) {
            if (Imgproc.contourArea(contours.get(contourIdx)) > Imgproc.contourArea(contours.get(largestContourIdx))) {
                largestContourIdx = contourIdx;
            }
        }

        contourArea = Imgproc.contourArea(contours.get(largestContourIdx));

        //gets moments and center from contour
        Moments moments = Imgproc.moments(contours.get(largestContourIdx));
        center = new Point(moments.get_m10() / moments.get_m00(), moments.get_m01() / moments.get_m00());

        hierarchy.release();

        //stuff to make the gray scale appear on on robot phone
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        //drawing features onto the matrix
        Imgproc.drawContours(mat, contours, largestContourIdx, new Scalar(255, 0, 0), 3);
        Imgproc.circle(mat, center, 5, new Scalar(255,0,0), 3);

        return mat;
    }

    public double getContourArea() {
        return contourArea;
    }

    public Point getCenter() {
        return center;
    }
}