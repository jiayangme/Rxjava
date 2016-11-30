package jiayang.com.rxjava.model;

import java.util.List;

/**
 * Created by xiangkai on 2016/11/29.
 */

public class BeautyResult {
    private boolean error;
    private List<BeautyImage> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<BeautyImage> getResults() {
        return results;
    }

    public void setResults(List<BeautyImage> results) {
        this.results = results;
    }
}
