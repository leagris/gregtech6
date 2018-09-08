package gregapi.compat.buildcraft;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import gregapi.code.TagData;
import gregapi.data.MD;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import net.minecraft.tileentity.TileEntity;

public class TriggerBC_Energy_Capacity_Empty extends TriggerBC {
	public final TagData mEnergyType;
	
	public TriggerBC_Energy_Capacity_Empty(TagData aEnergyType) {
		super(MD.GAPI.mID, aEnergyType.mName.toLowerCase()+".capacity.empty", "Is Empty (" + aEnergyType.getLocalisedNameShort() + ")");
		mEnergyType = aEnergyType;
	}
	
	@Override
	public boolean isActive(TileEntity aTarget, byte aSideOfTileEntity, IStatementContainer aSource, IStatementParameter[] aParameters) {
		return ((ITileEntityEnergyDataCapacitor)aTarget).getEnergyStored(mEnergyType, aSideOfTileEntity) <= 0;
	}
	
	@Override
	public boolean isApplicable(TileEntity aTarget, byte aSideOfTileEntity) {
		return aTarget instanceof ITileEntityEnergyDataCapacitor && ((ITileEntityEnergyDataCapacitor)aTarget).isEnergyCapacitorType(mEnergyType, aSideOfTileEntity);
	}
}