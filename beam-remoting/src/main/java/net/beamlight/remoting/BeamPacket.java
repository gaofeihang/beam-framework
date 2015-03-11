package net.beamlight.remoting;

import java.nio.ByteBuffer;

import net.beamlight.commons.util.ByteArrayUtils;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class BeamPacket {
    
    public static final byte[] DELIMITER = new byte[] { '\r', '\n' };
    public static final int LENGTH_FIELD_OFFSET = 4 + 1 + 1 + 1;
    public static final int LENGTH_FIELD_LENGTH = 4;
    public static final int BASE_LENGTH = LENGTH_FIELD_OFFSET + LENGTH_FIELD_LENGTH;
    public static final int DELIMITER_LENGTH = DELIMITER.length;
    
    private int id = -1;
    private byte version;
    private byte cmd;
    private byte codec;
    private int length;
    private byte[] data;
    
    public BeamPacket(int id, byte cmd, byte codec, byte[] data) {
        this.id = id;
        this.version = Protocol.DEFAULT_VERSION;
        this.cmd = cmd;
        this.codec = codec;
        this.data = data;
        this.length = data.length;
    }
    
    public int getId() {
        return id;
    }
    
    public void setVersion(byte version) {
        this.version = version;
    }
    
    public byte getVersion() {
        return version;
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
        
        buffer.putInt(id);
        buffer.put(version);
        buffer.put(cmd);
        buffer.put(codec);
        buffer.putInt(length);
        buffer.put(data);
        
        return buffer.array();
    }
    
    public static BeamPacket parseFrom(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        
        int id = buffer.getInt();
        byte version = buffer.get();
        byte cmd = buffer.get();
        byte codec = buffer.get();
        int length = buffer.getInt();
        
        byte[] data = new byte[length];
        buffer.get(data);
        
        BeamPacket packet = new BeamPacket(id, cmd, codec, data);
        packet.setVersion(version);
        
        return packet;
    }
    
    @Override
    public String toString() {
        return "[id=" + id + 
                ", version=" + version + 
                ", cmd=" + cmd + 
                ", codec=" + codec + 
                ", data=" + ByteArrayUtils.prettyPrint(data) + "]";
    }

}
