package cn.jingling.lib.filters.realsize;

import android.content.Context;
import android.graphics.Bitmap;
import cn.jingling.lib.filters.CMTProcessor;
import cn.jingling.lib.filters.Curve;
import cn.jingling.lib.filters.ImageProcessUtils;
import cn.jingling.lib.filters.Layer;

public class RSCameraFoodLive1 extends RSLineFilter {

	private Curve mCurve;
	private Bitmap mLayer;
	
	@Override
	public boolean apply(Context cx, String inPath, String outPath, int[] args) {
		mCurve = new Curve(cx, "curves/live_food1.dat");
		mLayer = Layer.getLayerImage(cx, "layers/live_food1", Layer.Type.NORMAL);
		return super.apply(cx, inPath, outPath, args);
	}
	
	@Override
	protected void applyLine(Context cx, int[] pixels, int line, int height) {
		// TODO Auto-generated method stub
		int w = pixels.length;
		int[] layerPixels;
		
		//
		ImageProcessUtils.saturationPs(pixels, w, 1, 15);
		
		CMTProcessor.curveEffect(pixels, mCurve.getCurveRed(), mCurve.getCurveGreen(),
				mCurve.getCurveBlue(), w, 1);
		//
		layerPixels = getLayerPixels(mLayer, line, height);
		CMTProcessor.rsOverlayAlphaEffect(pixels, layerPixels, w, 1, mLayer.getWidth(), 1, 10);
	}

	@Override
	protected void releaseLayers() {
		mLayer.recycle();	
	}

}
