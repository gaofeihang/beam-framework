package net.beamlight.commons.frame;

import net.beamlight.commons.util.ByteArrayUtils;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class BeamPacket {
    
    private byte type;
    private byte[] data;
    
    public BeamPacket(byte type, byte[] data) {
        this.type = type;
        this.data = data;
    }
    
    public byte getType() {
        return type;
    }
    
    public void setType(byte type) {
        this.type = type;
    }
    
    public byte[] getData() {
        return data;
    }
    
    public void setData(byte[] data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "[type=" + getType() + ", data=" + ByteArrayUtils.prettyPrint(getData()) + "]";
    }

}
