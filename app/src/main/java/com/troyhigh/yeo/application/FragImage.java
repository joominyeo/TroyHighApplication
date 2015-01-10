package com.troyhigh.yeo.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragImage extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;
    ImageView fragImage;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
    public static final String WEB_IMAGE_ID = "webImageID";


	public FragImage() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.frag_image_layout, container,
				false);

		ivIcon = (ImageView) view.findViewById(R.id.frag_icon);
		tvItemName = (TextView) view.findViewById(R.id.frag_text);
        fragImage = (ImageView) view.findViewById(R.id.imageView);

		tvItemName.setText(getArguments().getString(ITEM_NAME));
		ivIcon.setImageDrawable(view.getResources().getDrawable(
				getArguments().getInt(IMAGE_RESOURCE_ID)));
        fragImage.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(WEB_IMAGE_ID)));

		return view;
	}

}
