package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSubItems;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase10FluidContainerSyncSmall;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCell extends TileEntityBase10FluidContainerSyncSmall implements IMTE_GetSubItems {
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List aList, short aID) {
		return SHOW_HIDDEN_MATERIALS || !mMaterial.mHidden;
	}
	
	public ITexture[] mTextures = new ITexture[3];
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextures[0] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom));
		mTextures[1] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop));
		mTextures[2] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides), BlockTextureFluid.get(mTank), BlockTextureDefault.get(sTextureSides, mRGBa), BlockTextureDefault.get(sOverlaySides));
		return 3;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 0: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[ 6], PX_N[ 5], PX_N[ 5], PX_N[ 6]); return T;
		case 1: box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 5], PX_N[ 6], PX_N[ 5], PX_N[ 5]); return T;
		case 2: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 4], PX_N[ 6]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides		= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/sides"),
	sTextureInsides		= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/insides"),
	sTextureTop			= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/top"),
	sTextureBottom		= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/colored/bottom"),
	sOverlaySides		= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/sides"),
	sOverlayInsides		= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/insides"),
	sOverlayTop			= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/top"),
	sOverlayBottom		= new Textures.BlockIcons.CustomIcon("machines/tanks/cell/overlay/bottom");
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTextures[FACES_TBS[aSide]];}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 5];}
	
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return aDefault;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.cell";}
}