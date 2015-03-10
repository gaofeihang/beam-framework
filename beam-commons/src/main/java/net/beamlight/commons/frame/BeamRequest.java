package net.beamlight.commons.frame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.beamlight.commons.util.JsonUtils;

/**
 * @author gaofeihang
 * @since Feb 3, 2015
 */
public class BeamRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String cmd;
    private Map<String, Object> params = new HashMap<String, Object>();
    
    public BeamRequest() {
    }
    
    public BeamRequest(String cmd) {
        this.cmd = cmd;
    }
    
    public String getCmd() {
        return cmd;
    }
    
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
    
    public BeamRequest setParam(String k, Object v) {
        params.put(k, v);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getParam(String k) {
        return (T) params.get(k);
    }
    
    @Override
    public String toString() {
        return JsonUtils.toJSON(this);
    }

}
