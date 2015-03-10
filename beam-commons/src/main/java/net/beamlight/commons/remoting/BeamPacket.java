package net.beamlight.commons.remoting;

import net.beamlight.commons.util.ByteArrayUtils;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class BeamPacket {
    
    public static final byte[] DELIMITER = new byte[] { '\r', '\n' };
    
    private static final int HEADER_LENGTH = 4 + 1 + 4;
    private static final int DELIMITER_LENGTH = DELIMITER.length;
    
    private int id = -1;
    private byte type;
    private int length;
    private byte[] data;
    
    public BeamPacket(int id, byte type, byte[] data) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.length = HEADER_LENGTH + data.length + DELIMITER_LENGTH;
    }
    
    public int getId() {
        return id;
    }
    
    public byte getType() {
        return type;
    }
    
    public byte[] getData() {
        return data;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getDataLength() {
        return data.length;
    }
    
    public int getDelimiterLength() {
        return DELIMITER_LENGTH;
    }
    
    @Override
    public String toString() {
        return "[id=" + getId() + ", type=" + getType() + ", data=" + ByteArrayUtils.prettyPrint(getData()) + "]";
    }

}
