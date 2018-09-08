package gregtech.tileentity.panels;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPanelConcrete extends MultiTileEntityPanelColored {
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(Textures.BlockIcons.CONCRETE, DYES[mColor]);
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.panel.concrete";}
}