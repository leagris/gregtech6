package gregapi.network.packets.ids;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import gregapi.block.IBlockSyncData.IBlockSyncDataAndIDs;
import gregapi.network.INetworkHandler;
import gregapi.network.packets.PacketCoordinates;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 * 
 * Transmits the extended Data of a Block.
 */
public class PacketSyncDataByteArrayAndIDs extends PacketCoordinates {
	public byte[] mData;
	public short mID1 = 0, mID2 = 0;
	
	public PacketSyncDataByteArrayAndIDs(int aDecoderType) {
		super(aDecoderType);
	}
	
	/** aData is limited to length = 256 */
	public PacketSyncDataByteArrayAndIDs(int aX, int aY, int aZ, short aID1, short aID2, byte... aData) {
		super(aX, aY, aZ);
		mData = aData;
		mID1 = aID1;
		mID2 = aID2;
	}
	/** aData is limited to length = 256 */
	public PacketSyncDataByteArrayAndIDs(ChunkCoordinates aCoords, short aID1, short aID2, byte... aData) {
		super(aCoords);
		mData = aData;
		mID1 = aID1;
		mID2 = aID2;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -48;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeShort(mID1);
		aData.writeShort(mID2);
		aData.writeByte(mData.length);
		aData.write(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		short tID1 = aData.readShort(), tID2 = aData.readShort();
		byte[] tData = new byte[UT.Code.unsignB(aData.readByte())];
		aData.readFully(tData);
		return new PacketSyncDataByteArrayAndIDs(aX, aY, aZ, tID1, tID2, tData);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncDataAndIDs) ((IBlockSyncDataAndIDs)tBlock).receiveDataByteArray(aWorld, mX, mY, mZ, mData, aNetworkHandler, mID1, mID2);
		}
	}
}