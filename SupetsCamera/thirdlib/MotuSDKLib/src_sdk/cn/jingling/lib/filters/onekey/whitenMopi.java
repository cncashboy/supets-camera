package cn.jingling.lib.filters.onekey;

import android.content.Context;
import android.graphics.Bitmap;
import cn.jingling.lib.filters.Curve;
import cn.jingling.lib.filters.OneKeyFilter;
import cn.jingling.lib.filters.SmoothSkinProcessor;
import cn.jingling.lib.filters.detection.FaceDetection;
import cn.jingling.lib.filters.detection.FaceDetectorResults;

public class whitenMopi extends OneKeyFilter {

	@Override
	public Bitmap apply(Context cx, Bitmap bm) {
		this.statisticEvent();
//		Point left = new Point();
//		Point right = new Point();
//		Point mouth = new Point();
		int w = bm.getWidth();
		int h = bm.getHeight();
		int[] pixels = new int[w * h];
		bm.getPixels(pixels, 0, w, 0, 0, w, h);
		
//		int sigma = 5;
//		int radius = 5;
//		int delta  = 10;
//		SmoothSkinProcessor.Bilateral(pixels, w, h, delta, radius, sigma);
		
		Curve c;
		c = new Curve(cx, "curves/Mopi.amp");
		FaceDetectorResults hn = FaceDetection.detect(bm);
		if(hn == null || hn.humans.length <= 0)
		{
			SmoothSkinProcessor.buffingTemplate(pixels, w, h, 10, 1);	
			SmoothSkinProcessor.faceBuffing(pixels, w, h, c.getCurveRed(),
					c.getCurveGreen(), c.getCurveBlue());
			SmoothSkinProcessor.releaseSource();
		}
		else
		{
			int leftx = hn.humans[0].leftEye.x;
			int lefty = hn.humans[0].leftEye.y;
			int rightx = hn.humans[0].rightEye.x;
			int righty = hn.humans[0].rightEye.y;
//			SmoothSkinProcessor.whiten(pixels, w, h, 1, lefty, leftx, righty, rightx);
			SmoothSkinProcessor.buffingTemplate(pixels, w, h, 10, 1);	
			SmoothSkinProcessor.faceBuffing(pixels, w, h, c.getCurveRed(),
					c.getCurveGreen(), c.getCurveBlue());
			SmoothSkinProcessor.releaseSource();
		}

		bm.setPixels(pixels, 0, w, 0, 0, w, h);
		
		return bm;
	}

}
