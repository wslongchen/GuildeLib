package mrpan.android.guildelib;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;

/**
 * @author MrPan
 * 2015-05-14
 */
public class GuideControler {
	private Context context;

	private ViewPager viewPager;

	private List<View> views;

	private GuideViewPagerAdapter pagerAdapter;

	private LinearLayout indicatorGroup;

	private View[] indicators;

	private static final int INDICATOR_WIDTH_FOR_RECT = 40;

	private static final int INDICATOR_HEIGHT_FOR_RECT = 5;

	private static final int INDICATOR_WIDTH_FOR_OVAL = 25;

	private static final int INDICATOR_HEIGHT_FOR_OVAL = 25;

	private int mIndicatorWidth;

	private int mIndicatorHeight;

	private int mIndicatorBgResForSelected;

	private int mIndicatorBgResForUnselected;

	private ShapeType mShapeType;

	public GuideControler(Context context) {
		super();
		this.context = context;
	}

	public void init(int[] imageIds, View view) {
		views = new ArrayList<View>();
		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(imageIds[i]);
			imageView.setScaleType(ScaleType.FIT_XY);
			views.add(imageView);
		}
		views.add(view);
		set();
	}

	private void set() {
		setViewPager();
		setIndicators();
	}

	private void setViewPager() {
		viewPager = (ViewPager) ((Activity) context)
				.findViewById(R.id.viewPager_lib);
		pagerAdapter = new GuideViewPagerAdapter(views);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
	}

	private void setIndicators() {
		setConfigure(mShapeType);
		indicatorGroup = (LinearLayout) ((Activity) context)
				.findViewById(R.id.indicatorGroup_lib);
		indicators = new View[views.size()];
		LayoutParams params = new LayoutParams(mIndicatorWidth,
				mIndicatorHeight);
		params.setMargins(0, 0, 15, 0);
		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new View(context);
			if (i == 0) {
				indicators[i].setBackgroundResource(mIndicatorBgResForSelected);
			} else {
				indicators[i]
						.setBackgroundResource(mIndicatorBgResForUnselected);
			}
			indicators[i].setLayoutParams(params);
			indicatorGroup.addView(indicators[i]);
		}
	}

	private void setConfigure(ShapeType shapeType) {
		if (shapeType != null) {
			if (shapeType == ShapeType.OVAL) {
				mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_OVAL
						: mIndicatorWidth;
				mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_OVAL
						: mIndicatorHeight;
				mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_oval;
				mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_oval;
			} else if (shapeType == ShapeType.RECT) {
				mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_RECT
						: mIndicatorWidth;
				mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_RECT
						: mIndicatorHeight;
				mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_rect;
				mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_rect;
			}
		} else {
			mIndicatorWidth = mIndicatorWidth == 0 ? INDICATOR_WIDTH_FOR_OVAL
					: mIndicatorWidth;
			mIndicatorHeight = mIndicatorHeight == 0 ? INDICATOR_HEIGHT_FOR_OVAL
					: mIndicatorHeight;
			mIndicatorBgResForSelected = R.drawable.shape_indicator_selected_oval;
			mIndicatorBgResForUnselected = R.drawable.shape_indicator_unselected_oval;
		}
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < indicators.length; i++) {
				if (i == arg0) {
					indicators[i]
							.setBackgroundResource(mIndicatorBgResForSelected);
				} else {
					indicators[i]
							.setBackgroundResource(mIndicatorBgResForUnselected);
				}
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	public enum ShapeType {
		RECT, OVAL
	}
}
