package fr.univtln.mcg.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bruno on 31/10/15.
 */
public class QueryParameter {

    private Map mParameters = null;

    private QueryParameter(String mName,Object mValue){
        this.mParameters = new HashMap();
        this.mParameters.put(mName, mValue);
    }
    public static QueryParameter with(String mName,Object mValue){
        return new QueryParameter(mName, mValue);
    }
    public QueryParameter and(String mName,Object mValue){
        this.mParameters.put(mName, mValue);
        return this;
    }
    public Map parameters(){
        return this.mParameters;
    }
}