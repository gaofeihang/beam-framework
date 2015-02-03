package net.beamlight.commons.frame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaofeihang
 * @since Feb 3, 2015
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String action;
    private Map<String, Object> params = new HashMap<String, Object>();
    
    public Request(String action) {
        this.action = action;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public Request setParam(String k, Object v) {
        params.put(k, v);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getParam(String k, Class<T> t) {
        Object obj = params.get(k);
        return obj == null ? null : (T) obj;
    }

}
