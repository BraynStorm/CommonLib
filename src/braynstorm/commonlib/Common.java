package braynstorm.commonlib;

import java.io.File;
import java.nio.ByteBuffer;

import braynstorm.commonlib.math.Vector3f;

public class Common {
    
    public static final Vector3f Y_AXIS = new Vector3f(0, 1, 0);

    public static void createFolder(String folder){
        File f = new File(folder);
        if(!f.isDirectory()){
            f.mkdirs();
        }
    }
    
    /**
     * 
     * @param packetType MUST BE A SHORT
     * @param length MUST BE A SHORT
     * @return
     */
    public static ByteBuffer createPacket(short packetType, int length){
        return createPacket(packetType, (short) length);
    }
    
    /**
     * Convenience method for createing packetBuffers.
     * @param packetType the packet type (or OPCODE)
     * @param length the length in bytes.
     * @return the allocated buffer with already inserted OPCODE and Length
     */
    public static ByteBuffer createPacket(short packetType, short length){
        return ByteBuffer.allocate(Short.BYTES * 2 + length).putShort(packetType).putShort(length);
    }
    
}
