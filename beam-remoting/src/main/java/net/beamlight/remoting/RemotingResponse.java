package net.beamlight.remoting;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class RemotingResponse {
    
    private int id;
    private Object model;
    
    public RemotingResponse(int id, Object model) {
        this.id = id;
        this.model = model;
    }
    
    public int getId() {
        return id;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getModel() {
        return (T) model;
    }
    
    @Override
    public String toString() {
        return "[id=" + getId() + ", model=" + getModel() + "]";
    }

}
