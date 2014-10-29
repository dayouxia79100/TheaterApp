package com.ming.dayouxia.theaterapp.xmlwriter;

import java.util.ArrayList;

/**
 * Created by dayouxia on 10/29/14.
 */
public class Writer {

    public static void Write(String locationToSaveFile){
        //XML.writeSample();
        ArrayList<String> images = new ArrayList<String>();
        images.add("dog.jpg");
        images.add("wine.jpg");
        images.add("octocat.png");
        ArrayList<String> imgAttr = new ArrayList<String>();
        imgAttr.add("SmoothingFuser");
        imgAttr.add("BestQualityFuser");
        imgAttr.add("BestQualityFuser");
        //final String connectionXML ="/Users/harrylew/AndroidstudioProjects/XMLWriter/connection.xml";
        //final String sensorXML ="/Users/harrylew/AndroidstudioProjects/XMLWriter/sensors.xml";
        TrackingDataXmlWriter.headerFooter(imgAttr,locationToSaveFile,images);
    }
}

