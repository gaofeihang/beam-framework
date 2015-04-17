package net.beamlight.commons.frame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.beamlight.commons.util.JsonUtils;

/**
 * Created on Feb 3, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class BeamResponse implements Serializable {

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
    
    public BeamResponse setProperty(String k, Object v) {
        props.put(k, v);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String k) {
        return (T) props.get(k);
    }
    
    @Override
    public String toString() {
        return JsonUtils.toJSON(this);
    }

}
