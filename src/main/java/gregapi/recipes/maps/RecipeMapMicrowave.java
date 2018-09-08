package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.tileentity.base.TileEntityBase01Root;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapMicrowave extends RecipeMapNonGTRecipes {
	public RecipeMapMicrowave(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null) return null;
		if (aRecipe != null && aRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return aRecipe;
		ItemStack tOutput = RM.get_smelting(aInputs[0], F, null);
		
		if (ST.equal(aInputs[0], ST.make(Items.book, 1, W))) {
			return new Recipe(F, F, T, new ItemStack[] {ST.amount(1, aInputs[0])}, new ItemStack[] {ST.book("Manual_Microwave")}, null, null, null, null, 32, 4, 0);
		}
		
		// Check Container Item of Input since it is around the Input, then the Input itself, then Container Item of Output and last check the Output itself
		for (ItemStack tStack : new ItemStack[] {ST.container(aInputs[0], T), aInputs[0], ST.container(tOutput, T), tOutput}) if (ST.valid(tStack)) {
			if (ST.equal(tStack, ST.make(Blocks.netherrack, 1, W), T)
			 || ST.equal(tStack, ST.make(Blocks.tnt, 1, W), T)
			 || ST.equal(tStack, ST.make(Items.egg, 1, W), T)
			 || ST.equal(tStack, ST.make(Items.firework_charge, 1, W), T)
			 || ST.equal(tStack, ST.make(Items.fireworks, 1, W), T)
			 || ST.equal(tStack, ST.make(Items.fire_charge, 1, W), T)
			) {
				if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).overcharge(aSize * 4, TD.Energy.EU);
				return null;
			}
			
			OreDictItemData tData = OM.anydata_(tStack);
			
			if (tData != null) {
				if (tData.mMaterial != null) {
					if (tData.mMaterial.mMaterial.contains(TD.Atomic.METAL) || tData.mMaterial.mMaterial.contains(TD.Properties.EXPLOSIVE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).overcharge(aSize * 4, TD.Energy.EU);
						return null;
					}
					if (tData.mMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).setOnFire();
						return null;
					}
				}
				for (OreDictMaterialStack tMaterial : tData.mByProducts) if (tMaterial != null) {
					if (tMaterial.mMaterial.contains(TD.Atomic.METAL) || tMaterial.mMaterial.contains(TD.Properties.EXPLOSIVE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).overcharge(aSize * 4, TD.Energy.EU);
						return null;
					}
					if (tMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).setOnFire();
						return null;
					}
				}
			}
			if (ST.fuel(tStack) > 0) {
				if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).setOnFire();
				return null;
			}
			
		}
		
		return tOutput == null ? null : new Recipe(F, F, T, new ItemStack[] {ST.amount(1, aInputs[0])}, new ItemStack[] {tOutput}, null, null, null, null, 32, 4, 0);
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return RM.get_smelting(aStack, F, null) != null;}
}
