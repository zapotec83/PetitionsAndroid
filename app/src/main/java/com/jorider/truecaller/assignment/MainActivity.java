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

            return rootView;
        }


        /**
         * First request
         */
        private void firstRequest() {
            new FirstRequest(new ListenerFirstRequest() {
                @Override
                public void onResultOK(String result) {
                    manageFirstResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.VOLLEY);
        }

        /**
         * Second request
         */
        private void secondRequest() {
            new SecondRequest(new ListenerSecondRequest() {
                @Override
                public void onResultOK(String result) {
                    manageSecondResponse(result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
                    Log.e(TAG, "" + error.getHttpCode() + "->" + error.getMsg());
                }
            }).run(BaseRequest.VOLLEY);
        }

        /**
         * Third request
         */
        private void thirdRequest() {
            new ThirdRequest(new ListenerThirdRequest() {
                @Override
                public void onResultOK(String result) {
                    Log.e(TAG, result);
                }

                @Override
                public void onErrorRequest(AppRequestError error) {
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
