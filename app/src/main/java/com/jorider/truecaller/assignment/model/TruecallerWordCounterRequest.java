package com.jorider.truecaller.assignment.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorge
 */
public class TruecallerWordCounterRequest {

    public static final String WORD_REPEATED = "mobile";

    private Map<String, Integer> hashMap = null;
    private TruecallerRequestError error = null;

    public Map<String, Integer> getHashMap() {
        return hashMap;
    }

    public TruecallerRequestError getError() {
        return error;
    }

    public void setError(TruecallerRequestError error) {
        this.error = error;
    }


    public void setHashMap(Map<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    public int getKeyWordRepeated() {
        if(getHashMap() != null && getHashMap().containsKey(WORD_REPEATED)) {
            return getHashMap().get(WORD_REPEATED);
        } else {
            return 0;
        }
    }
}
