package com.jorider.truecaller.assignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jorider.truecaller.assignment.listeners.ListenerFirstRequest;
import com.jorider.truecaller.assignment.requests.BaseRequest;
import com.jorider.truecaller.assignment.requests.FirstRequest;

/**
 * @author jorge
 */
public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private Button requestButton = null;

        private TextView firstText = null;
        private TextView secondText = null;
        private TextView thirdText = null;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            requestButton = (Button) rootView.findViewById(R.id.request_button);
            requestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generateRequests();
                }
            });

            firstText = (TextView) rootView.findViewById(R.id.first_request);
            secondText = (TextView) rootView.findViewById(R.id.second_request);
            thirdText = (TextView) rootView.findViewById(R.id.third_request);

            return rootView;
        }

        /**
         * Method to generate the three requests
         */
        private void generateRequests() {
            firstRequest();
            secondRequest();
            thirdRequest();
        }

        /**
         * First request
         */
        private void firstRequest() {
            new FirstRequest(BaseRequest.VOLLEY, new ListenerFirstRequest() {
                @Override
                public void onResultOK(String result) {
                    Log.e(TAG, result);
                }

                @Override
                public void onErrorRequest(int httpErrorCode, String msg) {
                    Log.e(TAG, "" + httpErrorCode + "->" + msg);
                }
            }).run();
        }

        /**
         * Second request
         */
        private void secondRequest() {

        }

        /**
         * Third request
         */
        private void thirdRequest() {

        }
    }
}
