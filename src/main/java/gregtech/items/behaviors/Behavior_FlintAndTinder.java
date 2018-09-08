package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.GT_Mod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class Behavior_FlintAndTinder extends AbstractBehaviorDefault {
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer != null && SIDES_VALID[aSide] && !(aPlayer instanceof FakePlayer) && WD.obstructed(aWorld, aX, aY, aZ, aSide)) return F;
		List<String> tChatReturn = new ArrayListNoNulls();
		long tDamage = 10000;
		if (RNGSUS.nextInt(100)<GT_Mod.gregtechproxy.mFlintChance) tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 1, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer != null && aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		UT.Entities.sendchat(aPlayer, tChatReturn, F);
		if (aWorld.isRemote) return F;
		if (aPlayer == null || !UT.Entities.hasInfiniteItems(aPlayer)) ((MultiItemTool)aItem).doDamage(aStack, UT.Code.units(Math.max(10000, tDamage), 10000, 100, T), aPlayer);
		UT.Sounds.send(aWorld, SFX.MC_IGNITE, 1.0F, 1.0F, aX, aY, aZ);
		return T;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		return T;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aPlayer.worldObj.isRemote) return F;
		if (aEntity instanceof EntityCreeper) {
			if (!UT.Entities.hasInfiniteItems(aPlayer)) ((MultiItemTool)aItem).doDamage(aStack, 100, aPlayer);
			UT.Sounds.send(aPlayer.worldObj, SFX.MC_IGNITE, 1.0F, 1.0F, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
			((EntityCreeper)aEntity).func_146079_cb();
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.flintandtinder", "Ignites a Fire with a Chance of ");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.flintandtinder") + GT_Mod.gregtechproxy.mFlintChance + "%");
		return aList;
	}
}