package com.jorider.truecaller.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jorider.truecaller.assignment.assignments.ManageAssignments;
import com.jorider.truecaller.assignment.exceptions.MyException;
import com.jorider.truecaller.assignment.listeners.ListenerFirstRequest;
import com.jorider.truecaller.assignment.listeners.ListenerSecondRequest;
import com.jorider.truecaller.assignment.listeners.ListenerThirdRequest;
import com.jorider.truecaller.assignment.model.AppRequestError;
import com.jorider.truecaller.assignment.model.Truecaller10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerEvery10thCharacterRequest;
import com.jorider.truecaller.assignment.requests.BaseRequest;
import com.jorider.truecaller.assignment.requests.FirstRequest;
import com.jorider.truecaller.assignment.requests.SecondRequest;
import com.jorider.truecaller.assignment.requests.ThirdRequest;

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

        private Context context = null;

        private Button requestButton = null;

        private TextView firstText = null;
        private TextView secondText = null;
        private TextView thirdText = null;

        private ProgressBar firstProgressBar, secondProgressBar, thirdProgressBar = null;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            context = getActivity();

            requestButton = (Button) rootView.findViewById(R.id.request_button);
            requestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firstRequest();
                    secondRequest();
                    thirdRequest();
                }
            });

            firstText = (TextView) rootView.findViewById(R.id.first_request);
            secondText = (TextView) rootView.findViewById(R.id.second_request);
            thirdText = (TextView) rootView.findViewById(R.id.third_request);

            firstProgressBar = (ProgressBar) rootView.findViewById(R.id.first_progressBar);
            secondProgressBar = (ProgressBar) rootView.findViewById(R.id.second_progressBar);
            thirdProgressBar = (ProgressBar) rootView.findViewById(R.id.third_progressBar);

            return rootView;
        }


        /**
         * First request
         */
        private void firstRequest() {
            firstProgressBar.setVisibility(View.VISIBLE);
            Log.e(TAG, "BEGINS FIRST REQUEST->" + System.currentTimeMillis());
            new FirstRequest(new ListenerFirstRequest() {
                @Override
                public void onResultOK(String result) {
                    firstProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "FINISHES FIRST REQUEST->" + System.currentTimeMillis());
                    manageFirstResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    firstProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.VOLLEY);
        }

        /**
         * Second request
         */
        private void secondRequest() {
            secondProgressBar.setVisibility(View.VISIBLE);
            Log.e(TAG, "BEGINS SECOND REQUEST->" + System.currentTimeMillis());
            new SecondRequest(new ListenerSecondRequest() {
                @Override
                public void onResultOK(String result) {
                    secondProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "FINISHES SECOND REQUEST->" + System.currentTimeMillis());
                    manageSecondResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    secondProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.VOLLEY);
        }

        /**
         * Third request
         */
        private void thirdRequest() {
            thirdProgressBar.setVisibility(View.VISIBLE);
            Log.e(TAG, "BEGINS THIRD REQUEST->" + System.currentTimeMillis());
            new ThirdRequest(new ListenerThirdRequest() {
                @Override
                public void onResultOK(String result) {
                    thirdProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    thirdProgressBar.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.VOLLEY);
        }

        /**
         * Manage the first response
         *
         * @param response
         */
        public void manageFirstResponse(String response) {
            try {
                Truecaller10thCharacterRequest truecallerChar = ManageAssignments.manageFirstResponse(response);
                firstText.setText("" + truecallerChar.getResponse());
            } catch (MyException e) {
                Toast.makeText(context, "Error managing first response " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * Manage the second response
         *
         * @param response
         */
        public void manageSecondResponse(String response) {
            try {
                TruecallerEvery10thCharacterRequest truecallerChar = ManageAssignments.manageSecondResponse(response);
                secondText.setText("" + truecallerChar.getResponse());
            } catch (MyException e) {
                Toast.makeText(context, "Error managing first response " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        public void manageThirdResponse(String response) {

        }
    }
}
