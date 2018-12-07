package fr.ul.duckseditor;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import fr.ul.duckseditor.DucksEditor;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		if (!isTaskRoot()) {
			// Android launched another instance of the root activity into an existing task
			//  so just quietly finish and go away, dropping the user back into the activity
			//  at the top of the stack (ie: the last state of this task)
			finish();
			return;
		}
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.a = 8; //codage du canal alpha sur 8 bits
		initialize(new DucksEditor(), config);
	}
}
