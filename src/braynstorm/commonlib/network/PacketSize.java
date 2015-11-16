package braynstorm.commonlib.network;

import braynstorm.commonlib.math.Vector3f;

public class PacketSize {
	public static final int HEADER_SIZE = Short.BYTES * 2;
    public static final short PING = (short) Long.BYTES; 
    public static final short ENTITY_MOTION_UPDATE = (short) Vector3f.BYTES * 3 + 1;
}
