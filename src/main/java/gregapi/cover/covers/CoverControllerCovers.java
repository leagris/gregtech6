package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerCovers extends AbstractCoverAttachment {
	@Override
	public void onCoverRemove(byte aCoverSide, CoverData aData, Entity aPlayer) {
		super.onCoverRemove(aCoverSide, aData, aPlayer);
		aData.setStopped(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
		aData.setStopped(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public void onTickPre(byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide) aData.setStopped(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public boolean onCoverClickedRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		byte tSide = UT.Code.getSideWrenching(aSideClicked, aHitX, aHitY, aHitZ);
		if (tSide == aCoverSide || aSideClicked != aCoverSide) return F;
		if (aData.mBehaviours[tSide] != null) return aData.mBehaviours[tSide].onCoverClickedRight(tSide, aData, aPlayer, aSideClicked, aHitX, aHitY, aHitZ);
		return F;
	}
	
	@Override
	public boolean onCoverClickedLeft(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		byte tSide = UT.Code.getSideWrenching(aSideClicked, aHitX, aHitY, aHitZ);
		if (tSide == aCoverSide || aSideClicked != aCoverSide) return F;
		if (aData.mBehaviours[tSide] != null) return aData.mBehaviours[tSide].onCoverClickedLeft(tSide, aData, aPlayer, aSideClicked, aHitX, aHitY, aHitZ);
		return F;
	}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		byte tSide = UT.Code.getSideWrenching(aSideClicked, aHitX, aHitY, aHitZ);
		if (tSide == aCoverSide || aSideClicked != aCoverSide) {
			if (aTool.equals(TOOL_screwdriver)) {
				aData.value(aCoverSide, (short)(aData.mValues[aCoverSide] ^ B[0]));
				if (aChatReturn != null) aChatReturn.add((aData.mValues[aCoverSide] & B[0]) != 0 ? "Covers work when Input is OFF" : "Covers work when Input is ON");
				aData.setStopped(getStateOnOff(aCoverSide, aData));
				return 1000;
			}
			if (aTool.equals(TOOL_magnifyingglass)) {
				if (aChatReturn != null) aChatReturn.add((aData.mValues[aCoverSide] & B[0]) != 0 ? "Covers work when Input is OFF" : "Covers work when Input is ON");
				return 1;
			}
		}
		if (aData.mBehaviours[tSide] != null) return aData.mBehaviours[tSide].onToolClick(tSide, aData, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
		return 0;
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return aCoverSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/coverswitch/base"), sTextureForeground = BlockTextureDefault.get("machines/covers/coverswitch/circuit");
	
	public boolean getStateOnOff(byte aCoverSide, CoverData aData) {
		return UT.Code.bind1(aData.mTileEntity.getRedstoneIncoming(aCoverSide)) == (aData.mValues[aCoverSide] & B[0]);
	}
}