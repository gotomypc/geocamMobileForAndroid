package gov.nasa.arc.geocam.geocam;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.BaseAdapter;

public class FireIconActivity extends Activity {
	public static final String LOG_TAG = GeoCamMobile.DEBUG_ID;
	
	public static final String EXTRA_ID = "gov.nasa.arc.geocam.geocam.extra.ID";
	public static final String EXTRA_TAG = "gov.nasa.arc.geocam.geocam.extra.TAG";
	
	private IconAdapter mIconAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Window and view properties
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.fire_icon);
		
		mIconAdapter = new IconAdapter(this);
		
		GridView grid = (GridView) findViewById(R.id.fire_icon_grid);
		grid.setAdapter(mIconAdapter);
		
		grid.setOnItemClickListener(new GridView.OnItemClickListener() {

			public void onItemClick(AdapterView<? >parent, View view, int position,
					long id) {
				
				Intent data = new Intent();
				data.putExtra(EXTRA_ID, (Integer) mIconAdapter.getItem(position));
				data.putExtra(EXTRA_TAG, mIconAdapter.getItemTag(position));
				
				FireIconActivity.this.setResult(RESULT_OK, data);
				
				FireIconActivity.this.finish();
			}
			
		});
	}

	public class IconAdapter extends BaseAdapter {
		private Context mContext;
				
		public IconAdapter(Context c) {
			mContext = c;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView image_view;
			
			if (convertView == null) {
				image_view = new ImageView(mContext);
			
				image_view.setBackgroundResource(R.drawable.fire_bg);
				image_view.setAdjustViewBounds(false);
				image_view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
				image_view.setLayoutParams(new GridView.LayoutParams(60, 60));
				image_view.setPadding(8, 8, 8, 8);
			} else {
				image_view = (ImageView) convertView;
			}
			
			image_view.setImageResource(mIconIds[position]);
			return image_view;
		}
		
		public int getCount() {
			return mIconIds.length;
		}
		
		public String getItemTag(int position) {
			return mIconTags[position];
		}
		
		public String getItemTagFromId(int id) {
			for (int i = 0; i < mIconTags.length; ++i)
				if (mIconIds[i] == id)
					return mIconTags[i];
			
			return null;
		}
		
		public Object getItem(int position) {
			return mIconIds[position];
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		private String[] mIconTags = {
				"default",
				"warning",
				"aerialhazard",
				"aerialignition",
				"droppoint",
				"relativelysafe",
				"damaged",
				"unsafe",
				"hazmat",
				"watersource",
				"fireorigin",
				"hotspot",
				"spotfire",
				"downlink",
				"repeater",
				"safetyzone",
				"stagingarea",
				"helispot",
				"base",
				"commandpost"	
		};
		private Integer[] mIconIds = {
				R.drawable.fire_icon_default,	
				R.drawable.fire_icon_warning,
				R.drawable.fire_icon_aerialhazard,
				R.drawable.fire_icon_aerialignition,
				R.drawable.fire_icon_droppoint,
				R.drawable.fire_icon_relativelysafe,
				R.drawable.fire_icon_damaged,
				R.drawable.fire_icon_unsafe,
				R.drawable.fire_icon_hazmat,
				R.drawable.fire_icon_watersource,
				R.drawable.fire_icon_fireorigin,
				R.drawable.fire_icon_hotspot,
				R.drawable.fire_icon_spotfire,
				R.drawable.fire_icon_downlink,
				R.drawable.fire_icon_repeater,
				R.drawable.fire_icon_safetyzone,
				R.drawable.fire_icon_stagingarea,
				R.drawable.fire_icon_helispot,
				R.drawable.fire_icon_base,
				R.drawable.fire_icon_commandpost	
		};
	}
}