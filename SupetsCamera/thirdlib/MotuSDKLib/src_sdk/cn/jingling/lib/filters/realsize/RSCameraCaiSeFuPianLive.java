package cn.jingling.lib.filters.realsize;

import android.content.Context;
import cn.jingling.lib.filters.CMTProcessor;
import cn.jingling.lib.filters.Curve;
import cn.jingling.lib.filters.ImageProcessUtils;

public class RSCameraCaiSeFuPianLive extends RSLineFilter {

	private Curve mCurve;
	
	@Override
	public boolean apply(Context cx, String inPath, String outPath, int[] args) {
		mCurve = new Curve(cx, "curves/live_caisefupian.dat");
		return super.apply(cx, inPath, outPath, args);
	}
	
	@Override
	protected void applyLine(Context cx, int[] pixels, int line, int height) {
		// TODO Auto-generated method stub
		int w = pixels.length;
		//
		ImageProcessUtils.saturationPs(pixels, w, 1, -15);
		//
		CMTProcessor.curveEffect(pixels, mCurve.getCurveRed(), mCurve.getCurveGreen(),
				mCurve.getCurveBlue(), w, 1);

	}

	@Override
	protected void releaseLayers() {

	}

}
