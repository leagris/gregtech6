package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetFoodValues;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetItemUseAction;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetMaxItemUseDuration;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEaten;
import gregapi.item.IItemRottable;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.tank.TileEntityBase09FluidContainerSmall;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityThermos extends TileEntityBase09FluidContainerSmall implements ITileEntityFunnelAccessible, IMTE_GetFoodValues, IMTE_OnEaten, IMTE_GetItemUseAction, IMTE_GetMaxItemUseDuration, IItemRottable {
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return SIDES_TOP[aSide] && isFluidAllowed(aFluid) ? mTank.fill(aFluid, aDoFill) : 0;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return SIDES_HORIZONTAL[aSide] || aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/thermos/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/thermos/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/thermos/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/thermos/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/thermos/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/thermos/overlay/side"),
	};
	
	@Override public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]); return T;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]);}
	
	@Override public ItemStack getRotten(ItemStack aStack) {return aStack;}
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return aStack;}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 4];}
	
	@Override public boolean canDrinkFromSide(byte aSide) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.thermos.can";}
}