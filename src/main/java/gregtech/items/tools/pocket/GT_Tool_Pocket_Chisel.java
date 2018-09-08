package gregtech.items.tools.pocket;

import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregtech.items.tools.early.GT_Tool_Chisel;
import net.minecraft.item.ItemStack;

public class GT_Tool_Pocket_Chisel extends GT_Tool_Chisel {
	public final int mSwitchIndex;
	
	public GT_Tool_Pocket_Chisel(int aSwitchIndex) {
		mSwitchIndex = aSwitchIndex;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POCKET_MULTITOOL_CHISEL : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		super.onStatsAddedToTool(aItem, aID);
		aItem.addItemBehavior(aID, new Behavior_Switch_Metadata(mSwitchIndex));
	}
}