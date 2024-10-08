//package org.firstinspires.ftc.teamcode.library.vision;
//
//import com.acmerobotics.dashboard.config.Config;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import org.openftc.easyopencv.OpenCvPipeline;
//
//@Config
//public class SpikeDetect extends OpenCvPipeline {
//    Mat mat = new Mat();
//
//    Scalar lowHSV;
//    Scalar highHSV;
//
//    //HSV Ranges for red and blue
//    //TODO: tune if necessary
//    public SpikeDetect(boolean redAlliance) {
//        //Blue
////        lowHSV = new Scalar(110, 200, 50);
////        highHSV = new Scalar(130, 255, 255);
//        //Red
////        lowHSV = new Scalar(0, 128, 100);
////        highHSV = new Scalar(20, 255, 255);
//        if (redAlliance) {
//            lowHSV = new Scalar(0, 128, 100);
//            highHSV = new Scalar(20, 255, 255);
//        } else {
//            lowHSV = new Scalar(0, 0, 240);
//            highHSV = new Scalar(180, 25, 255);
//        }
//    }
//
//
//    //locations
//    public enum Location {
//        LEFT,
//        MID,
//        RIGHT
//    }
//
//    double leftValue;
//    double midValue;
//    double rightValue;
//
//    //location
//    private Location location;
//
//    //Region of interest coordinates
//    //TODO: change ROI coordinates if necessary
//    public static final Rect LEFT_ROI = new Rect(
//            new Point(0, 0),
//            new Point(400, 720));
//    public static final Rect MID_ROI = new Rect(
//            new Point(440, 0),
//            new Point(840, 720));
//    public static final Rect RIGHT_ROI = new Rect(
//            new Point(880, 0),
//            new Point(1280, 720));
//
//    @Override
//    public Mat processFrame(Mat input) {
//        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
//
//        //creates submatrices
//        Core.inRange(mat, lowHSV, highHSV, mat);
//
//        Mat left = mat.submat(LEFT_ROI);
//        Mat mid = mat.submat(MID_ROI);
//        Mat right = mat.submat(RIGHT_ROI);
//
//        //finds the raw value that fits in HSV range
//        leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
//        midValue = Core.sumElems(mid).val[0] / MID_ROI.area() / 255;
//        rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;
//
//        left.release();
//        mid.release();
//        right.release();
//
//        //stuff to make the gray scale appear on on robot phone
//        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);
//
//        //select location region and draw rectangle on it
//        if (leftValue > midValue && leftValue > rightValue) {
//            location = Location.LEFT;
//            Imgproc.rectangle(mat, LEFT_ROI, new Scalar(255, 0, 0), 5);
//        } else if (midValue > rightValue) {
//            location = Location.MID;
//            Imgproc.rectangle(mat, MID_ROI, new Scalar(255, 0, 0), 5);
//        } else {
//            location = Location.RIGHT;
//            Imgproc.rectangle(mat, RIGHT_ROI, new Scalar(255, 0, 0), 5);
//        }
//
//        return mat;
//    }
//
//    //some methods to get constants and vars
//    public Location getLocation() {
//        return location;
//    }
//
//    public String getLeft() {
//        return Math.round(leftValue * 100) + "%";
//    }
//
//    public String getMid() {
//        return Math.round(midValue * 100) + "%";
//    }
//
//    public String getRight() {
//        return Math.round(rightValue * 100) + "%";
//    }
//}