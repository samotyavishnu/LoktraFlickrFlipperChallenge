package com.loktra.apps.collection;

import java.util.List;

import com.loktra.apps.collection.models.FlickrPhoto;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhotoArrayAdapter extends ArrayAdapter<FlickrPhoto> {
	public PhotoArrayAdapter(Context context, List<FlickrPhoto> photoList) {
		super(context, R.layout.photo_item, photoList);
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		FlickrPhoto photo = this.getItem(position);
		final LinearLayout itemView;
		final ImageView ivImage;
		ImageLoader imageLoader = ImageLoader.getInstance();
        if (convertView == null) {
    		LayoutInflater inflator = LayoutInflater.from(getContext());
    		itemView = (LinearLayout) inflator.inflate(R.layout.photo_item, parent, false);
        } else {
            itemView = (LinearLayout) convertView;
        }
        ivImage = (ImageView) itemView.findViewById(R.id.ivPhoto);
        ivImage.setImageResource(android.R.color.transparent); 
        imageLoader.displayImage(photo.getUrl(), ivImage);

		final TextView name = (TextView) itemView.findViewById(R.id.textView);
		name.setText(photo.getName());

		final LinearLayout textViewLinear = (LinearLayout) itemView.findViewById(R.id.linearLayoutForTextView);

		itemView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				FlipAnimation flipAnim = new FlipAnimation(ivImage,name);

				if(ivImage.getVisibility() == View.VISIBLE)
				{
					itemView.startAnimation(flipAnim);
					textViewLinear.setVisibility(View.VISIBLE);
					//ivImage.setVisibility(View.GONE);
				}
				else {
					flipAnim.reverse();
					itemView.startAnimation(flipAnim);
					//textViewLinear.setVisibility(View.GONE);
					//ivImage.setVisibility(View.VISIBLE);
				}
			}
		});

        return itemView;
	}
}
