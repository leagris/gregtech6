package gregapi.item.multiitem.food;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.DrinksGT;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.util.UT;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FoodStatDrink extends FoodStat {
	public final String mFluid;
	
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getFluid(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getName(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		super(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aAction, NI, aAlwaysEdible || DRINKS_ALWAYS_DRINKABLE || MD.ENVM.mLoaded, aInvisibleParticles, aIsRotten, T, aPotionEffects);
		mFluid = aFluid;
		if (UT.Code.stringValid(mFluid)) {
			LH.add("gt.drink." + mFluid, aToolTip);
			DrinksGT.REGISTER.put(mFluid, this);
		}
	}
	
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getFluid(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getName(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		super(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, NI, aAlwaysEdible || DRINKS_ALWAYS_DRINKABLE || MD.ENVM.mLoaded, aInvisibleParticles, aIsRotten, T, aPotionEffects);
		mFluid = aFluid;
		if (UT.Code.stringValid(mFluid)) {
			LH.add("gt.drink." + mFluid, aToolTip);
			DrinksGT.REGISTER.put(mFluid, this);
		}
	}
	
	@Override
	public void addAdditionalToolTips(Item aItem, List aList, ItemStack aStack, boolean aF3_H) {
		String tTooltip = LH.get("gt.drink." + mFluid, "");
		if (UT.Code.stringValid(tTooltip)) aList.add(tTooltip);
		super.addAdditionalToolTips(aItem, aList, aStack, aF3_H);
	}
}