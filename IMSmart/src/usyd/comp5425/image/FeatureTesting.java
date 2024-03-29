/*
 * FeatureTesting.java
 *
 *
 *  Copyright (C) 2007 COMP5425 Multimedia Storage, Retrieval and Delivery
 *  The School of Information Technology
 *  The University of Sydney
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package usyd.comp5425.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import usyd.comp5425.util.ImageFileFilter;

/**
 *
 * @author Yuezhong Zhang  SID:305275631
 */
public class FeatureTesting {
    
    /** Creates a new instance of FeatureTesting */
    public FeatureTesting() {
    }
    public static void testGlobalColor(){
        FeatureModule module = FeatureModuleFactory.getInstance().getFeatureModule("Local_Color_Histogram");
        File file = new File("C:/corel10ext/corel10ext/antiques");
        File files [] = file.listFiles( new ImageFileFilter());
        
        File sample = new File("C:/corel10ext/corel10ext/antiques/435001.jpg");
        
        BufferedImage image = readImage(sample);
        if(image !=null){
            Vector<Double> sampleV = module.getFeatureVector(image);
            for(File f : files){
                Vector<Double> d =  module.getFeatureVector(readImage(f));
                double diff = module.compareFeatureVector(sampleV,d);
                if(diff < module.getThreshold())
                    System.out.println(f.getName() +"=" + diff);
            }
        }
        
    }
    
    public static void testColorMoment(){
        File sample = new File("C:/corel10ext/corel10ext/car_rare/29032.jpg");
        File sample2 = new File("C:/corel10ext/corel10ext/car_rare/29033.jpg");
        ColorMomentFeatureModule module = new ColorMomentFeatureModule();
        Vector<Double> d = module.getFeatureVector(readImage(sample));
        System.out.println(d);
       Vector<Double>  dd = module.getFeatureVector(readImage(sample2));
       System.out.println(dd);
       double diff = module.compareFeatureVector(d,dd);
       System.out.println("dff=" + diff);
        
    }
    public static void main(String[] args) {
        //testGlobalColor();
        testColorMoment();
    }
    public static BufferedImage readImage(File file){
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
