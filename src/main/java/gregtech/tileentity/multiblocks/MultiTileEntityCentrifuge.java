package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregapi.util.WD;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCentrifuge extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing)-1, tY = yCoord, tZ = getOffsetZN(mFacing)-1;
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18100, getMultiTileEntityRegistryID(), 6, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18100, getMultiTileEntityRegistryID(), 7, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , 18100, getMultiTileEntityRegistryID(), 8, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18100, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18100, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, 18100, getMultiTileEntityRegistryID(), 5, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, 18100, getMultiTileEntityRegistryID(), 1, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, 18100, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, 18100, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , 18100, getMultiTileEntityRegistryID(), 1, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , 18100, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , 18100, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, 18100, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+1, 18100, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, 18100, getMultiTileEntityRegistryID(), 5, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, 18100, getMultiTileEntityRegistryID(), 6, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, 18100, getMultiTileEntityRegistryID(), 7, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, 18100, getMultiTileEntityRegistryID(), 8, MultiTileEntityMultiBlockPart.NO_ENERGY_IN	)) tSuccess = F;
			
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.centrifuge.1", "3x3x2 of Centrifuge Parts");
		LH.add("gt.tooltip.multiblock.centrifuge.2", "Main Block centered on Side-Bottom and facing outwards");
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN		+ LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE	+ LH.get("gt.tooltip.multiblock.centrifuge.1"));
		aList.add(Chat.WHITE	+ LH.get("gt.tooltip.multiblock.centrifuge.2"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}
	
	@Override
	public void updateAdjacentToggleableEnergySources() {
		DelegatorTileEntity<TileEntity> tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord-1, getOffsetZN(mFacing), SIDE_TOP, F);
		if (tDelegator.mTileEntity instanceof ITileEntitySwitchableOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
			((ITileEntitySwitchableOnOff)tDelegator.mTileEntity).setStateOnOff(getStateOnOff());
		}
	}
	
	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_BOTTOM);
	}
	
	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(SIDE_BOTTOM);
	}
	
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.centrifuge";}
}