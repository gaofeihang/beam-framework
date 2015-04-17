package net.beamlight.remoting;

import java.nio.ByteBuffer;

import net.beamlight.commons.util.ByteArrayUtils;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class BeamPacket {
    
    public static final short MAGIC = (short) 0xBEAF;
    public static final int LENGTH_FIELD_OFFSET = 2 + 8 + 1 + 1;
    public static final int LENGTH_FIELD_LENGTH = 4;
    public static final int BASE_LENGTH = LENGTH_FIELD_OFFSET + LENGTH_FIELD_LENGTH;
    
    private long id = -1;
    private byte cmd;
    private byte codec;
    private int length;
    private byte[] data;
    
    private int timeout = Integer.MAX_VALUE;
    
    public BeamPacket(long id, byte cmd, byte codec, byte[] data) {
        this.id = id;
        this.cmd = cmd;
        this.codec = codec;
        this.data = data;
        this.length = data.length;
    }
    
    public long getId() {
        return id;
    }
    
    public byte getCmd() {
        return cmd;
    }
    
    public byte getCodec() {
        return codec;
    }
    
    public int getLength() {
        return length;
    }
    
    public byte[] getData() {
        return data;
    }
    
    public int getPacketLength() {
        return BASE_LENGTH + length;
    }
    
    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketLength());
        
        buffer.putShort(MAGIC);
        buffer.putLong(id);
        buffer.put(cmd);
        buffer.put(codec);
        buffer.putInt(length);
        buffer.put(data);
        
        return buffer.array();
    }
    
    public static BeamPacket parseFrom(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        
        buffer.getShort();
        long id = buffer.getLong();
        byte cmd = buffer.get();
        byte codec = buffer.get();
        int length = buffer.getInt();
        
        byte[] data = new byte[length];
        buffer.get(data);
        
        return new BeamPacket(id, cmd, codec, data);
    }
    
    public void setTimeout(int timeout) {
        if (timeout > 0) {
            this.timeout = timeout;
        }
    }
    
    public int getTimeout() {
        return timeout;
    }
    
    @Override
    public String toString() {
        return "[id=" + id + 
                ", cmd=" + cmd + 
                ", codec=" + codec + 
                ", data=" + ByteArrayUtils.prettyPrint(data) + "]";
    }

}
