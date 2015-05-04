package net.beamlight.remoting;

import java.nio.ByteBuffer;

import net.beamlight.commons.util.ByteArrayUtils;

/**
 * Created on Mar 9, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class BeamPacket {
    
    public static final short MAGIC = (short) 0xBEAF;
    public static final int LENGTH_FIELD_OFFSET = 2 + 8 + 1 + 1;
    public static final int LENGTH_FIELD_LENGTH = 4;
    public static final int HEADER_LENGTH = LENGTH_FIELD_OFFSET + LENGTH_FIELD_LENGTH;
    
    private long id = -1;
    private byte type;
    private byte codec;
    private int length;
    private byte[] body;
    
    private int timeout = Integer.MAX_VALUE;
    
    public BeamPacket(long id, byte type, byte codec, byte[] body) {
        this.id = id;
        this.type = type;
        this.codec = codec;
        this.body = body;
        this.length = body.length;
    }
    
    public long getId() {
        return id;
    }
    
    public byte getType() {
        return type;
    }
    
    public byte getCodec() {
        return codec;
    }
    
    public int getLength() {
        return length;
    }
    
    public byte[] getBody() {
        return body;
    }
    
    public int getPacketLength() {
        return HEADER_LENGTH + length;
    }
    
    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketLength());
        
        buffer.putShort(MAGIC);
        buffer.putLong(id);
        buffer.put(type);
        buffer.put(codec);
        buffer.putInt(length);
        buffer.put(body);
        
        return buffer.array();
    }
    
    public static BeamPacket parseFrom(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        
        buffer.getShort();
        long id = buffer.getLong();
        byte type = buffer.get();
        byte codec = buffer.get();
        int length = buffer.getInt();
        
        byte[] body = new byte[length];
        buffer.get(body);
        
        return new BeamPacket(id, type, codec, body);
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
                ", type=" + type + 
                ", codec=" + codec + 
                ", body=" + ByteArrayUtils.prettyPrint(body) + "]";
    }

}
