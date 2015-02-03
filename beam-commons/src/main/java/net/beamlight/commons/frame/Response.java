package net.beamlight.commons.frame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaofeihang
 * @since Feb 3, 2015
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Object result;
    private Integer ec;
    private Map<String, Object> props = new HashMap<String, Object>();
    
    @SuppressWarnings("unchecked")
    public <T> T getResult() {
        return result == null ? null : (T) result;
    }
    
    public void setResult(Object result) {
        this.result = result;
    }
    
    public Integer getEc() {
        return ec;
    }
    
    public void setEc(Integer ec) {
        this.ec = ec;
    }
    
    public Response setProperty(String k, Object v) {
        props.put(k, v);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String k, Class<T> t) {
        Object obj = props.get(k);
        return obj == null ? null : (T) obj;
    }

}
