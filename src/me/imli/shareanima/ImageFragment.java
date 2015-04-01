package me.imli.shareanima;

import me.imli.shareanima.blur.BlurView;
import me.imli.shareanima.view.ScaleImageView;
import me.imli.shareanima.view.ScaleImageView.ImageViewListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ImageFragment extends Fragment{
	
	private ScaleImageView mScaleImageView;
	private RelativeLayout rootView;
	private BlurView blurView;
	
	private boolean isClose;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_image, null);
		rootView.setVisibility(View.INVISIBLE);
		blurView = (BlurView) rootView.findViewById(R.id.fragment_image_blurview);
		
		mScaleImageView = (ScaleImageView) rootView.findViewById(R.id.fragment_image_scaleimageview);
		mScaleImageView.setBlurView(blurView);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		isClose = false;
		mScaleImageView.setImageViewListener(new ImageViewListener() {
			
			@Override
			public void onSingleTap() {
				isClose = true;
				mScaleImageView.startCloseScaleAnimation();
			}
			
			@Override
			public void onScaleEnd() {
				if(isClose){
					mScaleImageView.setImageDrawable(null);
					rootView.setVisibility(View.GONE);
					((MainActivity)getActivity()).showImageFragment(null, false);
					getActivity().supportInvalidateOptionsMenu();
					isClose = false;
				}else{
					mScaleImageView.setTopCrop(false);
					mScaleImageView.initAttacher();
				}
			}
		});
	}
	
	
	public void startScaleAnimation(ImageView smallImageView){
		rootView.setVisibility(View.VISIBLE);
		blurView.drawBlurOnce();
		mScaleImageView.startScaleAnimation(smallImageView);
		getActivity().supportInvalidateOptionsMenu();
	}
	
	
	public boolean canBack(){
		return rootView.getVisibility() == View.VISIBLE;
	}
	
	public void goBack(){
		if(!isClose){
			isClose = true;
			mScaleImageView.startCloseScaleAnimation();
		}
	}
	

}
