package edu.byu.ViewSwitcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ViewFlipper;

public class ViewSwitcherActivity extends Activity implements OnTouchListener {
	
	private float downXValue;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set main.XML as the layout for this Activity
        setContentView(R.layout.main);

        LinearLayout layMain = (LinearLayout) findViewById(R.id.layout_main);
        layMain.setOnTouchListener((OnTouchListener) this); 
        
        // Add a few countries to the spinner
        Spinner spinnerCountries = (Spinner) findViewById(R.id.spinner_country);
        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    new String[] { "Canada", "USA" });
        spinnerCountries.setAdapter(countryArrayAdapter);
        
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:{
				//store the x value when the user's finger was pressed down 
				downXValue=event.getX();
				break;
			}
			
			case MotionEvent.ACTION_UP:{
				//get the x value when the user released his/her finger
				float currentX=event.getX();
				
				if(downXValue < currentX){
					//get a reference to the viewflipper
					ViewFlipper vf=(ViewFlipper) findViewById(R.id.details);
					//set the animation
					vf.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
					vf.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
					//flip
					vf.showPrevious();
				}
				else if (downXValue > currentX){
					//Get a reference to the viewFlipper
					ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
					//set the animation
					vf.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
					vf.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));

					//flip
					vf.showNext();
				}
				break;
			}
		}
		return true;
	}
}