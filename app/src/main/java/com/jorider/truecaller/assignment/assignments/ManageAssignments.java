package com.jorider.truecaller.assignment.assignments;

import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.exceptions.MyException;
import com.jorider.truecaller.assignment.model.Truecaller10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerEvery10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerWordCounterRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorge on 13/12/14.
 */
public class ManageAssignments {

    /**
     * Method to give the response to the
     *
     * @param content
     * @return
     * @throws MyException
     */
    public static Truecaller10thCharacterRequest manageFirstResponse(String content) throws MyException {

        char characterToReturn = 'a';

        if (content != null && content.length() > Constants.CHARACTER_POSITION) {
            characterToReturn = content.charAt(Constants.CHARACTER_POSITION);
        } else {
            throw new MyException("No enough data from content!");
        }

        Truecaller10thCharacterRequest truecaller10thChar = new Truecaller10thCharacterRequest();
        truecaller10thChar.setResponse(characterToReturn);
        return truecaller10thChar;
    }

    /**
     * @param content
     * @return
     * @throws MyException
     */
    public static TruecallerEvery10thCharacterRequest manageSecondResponse(String content) throws MyException {

        TruecallerEvery10thCharacterRequest response = new TruecallerEvery10thCharacterRequest();
        StringBuffer buffer = new StringBuffer();

        if (content != null && content.length() > Constants.CHARACTER_POSITION) {
            for (int i = Constants.CHARACTER_POSITION; i < content.length(); i = i + Constants.CHARACTER_POSITION) {
                buffer.append(content.charAt(i));
            }
            response.setResponse(buffer.toString());

        } else {
            throw new MyException("No enough data from content!");
        }
        return response;
    }

    /**
     * Method to manage the third assignment
     *
     * @param content
     * @return
     * @throws MyException
     */
    public static TruecallerWordCounterRequest manageThirdResponse(String content) throws MyException {
        TruecallerWordCounterRequest truecallerRequest = new TruecallerWordCounterRequest();

        Map<String, Integer> wordList = new HashMap<String, Integer>();

        if (content != null) {
            String[] listOfWords = content.split(" ");
            if(listOfWords != null && listOfWords.length > 0) {
                for(int i = 0; i<listOfWords.length; i++) {
                    String word = listOfWords[i].trim();
                    if(wordList.containsKey(word)) {
                        int repeated = wordList.get(word);
                        wordList.put(word, repeated + 1);
                    } else {
                        wordList.put(word, 1);
                    }
                }
            }

        } else {
            throw new MyException("Content == null");
        }

        truecallerRequest.setHashMap(wordList);

        return truecallerRequest;
    }
}
