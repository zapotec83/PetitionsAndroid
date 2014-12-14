package com.jorider.truecaller.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.CallbackFirstRequest;
import com.jorider.truecaller.assignment.listeners.CallbackSecondRequest;
import com.jorider.truecaller.assignment.listeners.CallbackThirdRequest;
import com.jorider.truecaller.assignment.model.Truecaller10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerEvery10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerWordCounterRequest;
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
    public static class PlaceholderFragment extends Fragment implements CallbackFirstRequest, CallbackSecondRequest, CallbackThirdRequest {

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
            new Requests().runFirst(this);
        }

        /**
         * Second request
         */
        private void secondRequest() {
            secondProgressBar.setVisibility(View.VISIBLE);
            secondText.setVisibility(View.GONE);
            new Requests().runSecond(this);
        }

        /**
         * Third request
         */
        private void thirdRequest() {
            thirdProgressBar.setVisibility(View.VISIBLE);
            thirdText.setVisibility(View.GONE);
            new Requests().runThird(this);

        }

        @Override
        public void onFirstResult(Truecaller10thCharacterRequest result) {

            firstText.setVisibility(View.VISIBLE);
            firstProgressBar.setVisibility(View.GONE);

            if (result.getError() == null) {
                firstText.setText(Html.fromHtml("The character at the " + Constants.CHARACTER_POSITION + " position is " + "<b>" + result.getResponse() + "</b>"));
            } else {
                Toast.makeText(context, "ERROR FIRST REQUEST!" + result.getError().getHttpCode() + result.getError().getMsg(), Toast.LENGTH_SHORT).show();
                firstText.setText("");
            }
        }

        @Override
        public void onSecondResult(TruecallerEvery10thCharacterRequest result) {

            secondText.setVisibility(View.VISIBLE);
            secondProgressBar.setVisibility(View.GONE);

            if (result.getError() == null) {
                secondText.setText("RESULT-> \n" + result.getResponse());
            } else {
                Toast.makeText(context, "ERROR SECOND REQUEST!" + result.getError().getHttpCode() + result.getError().getMsg(), Toast.LENGTH_SHORT).show();
                secondText.setText("");
            }
        }

        @Override
        public void onThirdResult(TruecallerWordCounterRequest result) {
            thirdText.setVisibility(View.VISIBLE);
            thirdProgressBar.setVisibility(View.GONE);

            if (result.getError() == null) {
                thirdText.setText(Html.fromHtml("The word " + TruecallerWordCounterRequest.WORD_REPEATED + " is repeated ->" + "<b>" +  result.getKeyWordRepeated() + "</b>" + " times"));
            } else {
                Toast.makeText(context, "ERROR THIRD REQUEST!" + result.getError().getHttpCode() + result.getError().getMsg(), Toast.LENGTH_SHORT).show();
                thirdText.setText("");
            }
        }
    }
}
