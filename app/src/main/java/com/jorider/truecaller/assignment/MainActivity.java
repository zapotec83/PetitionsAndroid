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
import com.jorider.truecaller.assignment.listeners.ListenerRequests;
import com.jorider.truecaller.assignment.model.AppRequestError;
import com.jorider.truecaller.assignment.model.Truecaller10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerEvery10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerWordCounterRequest;
import com.jorider.truecaller.assignment.requests.BaseRequest;
import com.jorider.truecaller.assignment.requests.Requests;

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
            firstText.setVisibility(View.GONE);
            Log.e(TAG, "BEGINS FIRST REQUEST->" + System.currentTimeMillis());
            new Requests(new ListenerRequests() {
                @Override
                public void onResultOK(String result) {
                    firstText.setVisibility(View.VISIBLE);
                    firstProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "FINISHES FIRST REQUEST->" + System.currentTimeMillis());
                    manageFirstResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    firstText.setVisibility(View.VISIBLE);
                    firstProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.NATIVE);
        }

        /**
         * Second request
         */
        private void secondRequest() {
            secondProgressBar.setVisibility(View.VISIBLE);
            secondText.setVisibility(View.GONE);
            Log.e(TAG, "BEGINS SECOND REQUEST->" + System.currentTimeMillis());
            new Requests(new ListenerRequests() {
                @Override
                public void onResultOK(String result) {
                    secondText.setVisibility(View.VISIBLE);
                    secondProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "FINISHES SECOND REQUEST->" + System.currentTimeMillis());
                    manageSecondResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    secondText.setVisibility(View.VISIBLE);
                    secondProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.NATIVE);
        }

        /**
         * Third request
         */
        private void thirdRequest() {
            thirdProgressBar.setVisibility(View.VISIBLE);
            thirdText.setVisibility(View.GONE);
            Log.e(TAG, "BEGINS THIRDVOLLEY REQUEST->" + System.currentTimeMillis());
            new Requests(new ListenerRequests() {
                @Override
                public void onResultOK(String result) {
                    thirdText.setVisibility(View.VISIBLE);
                    thirdProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "FINISHES THIRD REQUEST->" + System.currentTimeMillis());
                    manageThirdResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    thirdText.setVisibility(View.VISIBLE);
                    thirdProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.NATIVE);
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
                Toast.makeText(context, "Error managing second response " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        public void manageThirdResponse(String response) {
            try {
                TruecallerWordCounterRequest counterRequest = ManageAssignments.manageThirdResponse(response);
                thirdText.setText(TruecallerWordCounterRequest.WORD_REPEATED + " is repeated -> " + counterRequest.getKeyWordRepeated() + " times");
            } catch (MyException e) {
                Toast.makeText(context, "Error managing third response " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
