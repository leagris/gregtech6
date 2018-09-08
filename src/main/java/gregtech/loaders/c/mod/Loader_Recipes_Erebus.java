package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;

import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.IOreDictListenerEvent;
import gregapi.oredict.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Erebus implements Runnable {
	@Override
	public void run() {if (MD.ERE.mLoaded) {OUT.println("GT_Mod: Doing Erebus Recipes.");
		RM.add_smelting(ST.make(BlocksGT.Diggables, 1, 0), IL.ERE_Mud_Brick.get(1));
		
		RM.generify(IL.BoP_Mud_Brick.get(1), IL.ERE_Mud_Brick.get(1));
		RM.generify(IL.ERE_Mud_Brick.get(1), IL.BoP_Mud_Brick.get(1));
		
		RM.Mortar		.addRecipe1(T, 16, 16		, ST.make(MD.ERE, "fireBloom"			, 1, W), OM.dust(MT.Blaze, U9));
		RM.Mortar		.addRecipe1(T, 16, 16		, ST.make(MD.ERE, "materials"			, 1,56), OM.dust(MT.Jade, U9));
		RM.Mortar		.addRecipe1(T, 16, 16		, ST.make(MD.ERE, "materials"			, 1, 0), IL.Dye_Bonemeal.get(1));
		RM.Mortar		.addRecipe1(T, 16, 16		, ST.make(MD.ERE, "materials"			, 1, 2), IL.Dye_Bonemeal.get(1));
		RM.Mortar		.addRecipe1(T, 16,128		, ST.make(MD.ERE, "materials"			, 1,16), IL.Dye_Bonemeal.get(8));
		
		RM.Crusher		.addRecipe1(T, 16, 64		, ST.make(MD.ERE, "oreEncrustedDiamond"	, 1, 0), ST.make(MD.ERE, "encrustedDiamond", 2*MT.Diamond.mOreMultiplier*MT.Diamond.mOreProcessingMultiplier, 0), OM.dust(MT.VolcanicAsh));
		RM.Hammer		.addRecipe1(T, 16, 16,  7500, ST.make(MD.ERE, "oreEncrustedDiamond"	, 1, 0), ST.make(MD.ERE, "encrustedDiamond", 2*MT.Diamond.mOreMultiplier*MT.Diamond.mOreProcessingMultiplier, 0));
		
		RM.Crusher		.addRecipe1(T, 16, 64		, ST.make(MD.ERE, "umberstone"			, 1, 1), OP.rockGt.mat(MT.Umber, 4));
		RM.Hammer		.addRecipe1(T, 16, 16,  7000, ST.make(MD.ERE, "umberstone"			, 1, 1), OP.rockGt.mat(MT.Umber, 4));
		
		RM.Crusher		.addRecipe1(T, 16, 64		, ST.make(MD.ERE, "volcanicRock"		, 1, 0), OM.dust(MT.VolcanicAsh));
		RM.Hammer		.addRecipe1(T, 16, 16		, ST.make(MD.ERE, "volcanicRock"		, 1, 0), OM.dust(MT.VolcanicAsh));
		RM.Shredder		.addRecipe1(T, 16, 96		, ST.make(MD.ERE, "volcanicRock"		, 1, 0), OM.dust(MT.VolcanicAsh));
		
		RM.Sharpening	.addRecipe1(T, 16, 16		, ST.make(MD.ERE, "encrustedDiamond"	, 1, W), ST.make(Items.diamond, 1, 0), OM.dust(MT.VolcanicAsh, U2));
		
		RM.pack(rockGt.mat(MT.Umber		, 4), ST.make(MD.ERE, "umberstone", 1, 1));
		RM.pack(rockGt.mat(MT.Gneiss	, 4), ST.make(MD.ERE, "gneiss", 1, 0));
		
		RM.pack(ST.make(Items.string, 9, 0), 9, ST.make(MD.ERE, "blockSilk", 1, 0));
		RM.unpack(ST.make(MD.ERE, "blockSilk", 1, 0), ST.make(Items.string, 9, 0));
		
		CR.shaped(ST.make(MD.ERE, "umberstone"	, 1, 1), CR.DEF_NAC, "XX", "XX", 'X', rockGt.dat(MT.Umber));
		CR.shaped(ST.make(MD.ERE, "gneiss"		, 1, 0), CR.DEF_NAC, "XX", "XX", 'X', rockGt.dat(MT.Gneiss));
		
		
		CR.remout(MD.ERE, "mirbrick");
		if (IL.BoP_Mud_Brick.exists())
		CR.shaped(ST.make(MD.ERE, "mirbrick"	, 1, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', IL.BoP_Mud_Brick, 'Y', "itemClay");
		CR.shaped(ST.make(MD.ERE, "mirbrick"	, 1, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', IL.ERE_Mud_Brick, 'Y', "itemClay");
		if (IL.BoP_Mud_Bricks.exists())
		CR.shaped(ST.make(MD.ERE, "mirbrick"	, 4, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', IL.BoP_Mud_Bricks, 'Y', "blockClay");
		CR.shaped(ST.make(MD.ERE, "mirbrick"	, 4, 0), CR.DEF_NAC_MIR, "XY", "YX", 'X', ST.make(MD.ERE, "mudBricks", 1, 0), 'Y', "blockClay");
		
		
		CR.remout(IL.ERE_Spray_Repellant.get(1));
		
		RM.Canner		.addRecipe2(T, 16,144,	ST.make(MD.ERE, "materials"		, 1,29), IL.Spray_Empty.get(9), IL.ERE_Spray_Repellant.get(9));
		
		for (FluidStack tFluid : new FluidStack[] {FL.Water.make(250), FL.DistW.make(250)})
		RM.Mixer		.addRecipe1(T, 16, 16, IL.ERE_Herbicide.get(1), tFluid, FL.Potion_Poison_2.make(250), ZL_IS);
		
		RM.Squeezer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "weepingBlue"	, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.ERE, "materials", 2,26));
		RM.Juicer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "weepingBlue"	, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.ERE, "materials", 2,26));
	    RM.ic2_extractor(						ST.make(MD.ERE, "weepingBlue"	, 1, 0), ST.make(MD.ERE, "materials", 2,26));
	    
		RM.Squeezer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "waterFlower"	, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.Juicer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "waterFlower"	, 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 2, DYE_INDEX_Pink));
	    RM.ic2_extractor(						ST.make(MD.ERE, "waterFlower"	, 1, 0), ST.make(Items.dye, 3, DYE_INDEX_Pink));
	    
		RM.Squeezer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "pricklyPair"	, 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.Juicer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "pricklyPair"	, 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
	    RM.ic2_extractor(						ST.make(MD.ERE, "pricklyPair"	, 1, W), IL.Dye_Cactus.get(2));
	    
		RM.Squeezer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "materials"		, 1,22), NF, FL.Potion_Poison_2.make(750), ZL_IS);
		RM.Juicer		.addRecipe1(T, 16, 16,	ST.make(MD.ERE, "materials"		, 1,22), NF, FL.Potion_Poison_2.make(750), ZL_IS);
		
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.Honey		.make(1000), ST.make(MD.ERE, "bucketHoney"		, 1, 0), ST.make(Items.bucket		, 1, 0), F), F, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.HoneyBoP	.make(1000), ST.make(MD.ERE, "bucketHoney"		, 1, 0), ST.make(Items.bucket		, 1, 0), F), F, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.HoneyGrC	.make(1000), ST.make(MD.ERE, "bucketHoney"		, 1, 0), ST.make(Items.bucket		, 1, 0), F), F, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.Honey		.make(1000), ST.make(MD.ERE, "bambucketHoney"	, 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.HoneyBoP	.make(1000), ST.make(MD.ERE, "bambucketHoney"	, 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.HoneyGrC	.make(1000), ST.make(MD.ERE, "bambucketHoney"	, 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.Milk		.make(1000), ST.make(MD.ERE, "bambucketMilk"	, 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		UT.Fluids.setFluidContainerData(new FluidContainerData(FL.MilkGrC	.make(1000), ST.make(MD.ERE, "bambucketMilk"	, 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("stoneGneiss", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Crusher	.addRecipe1(T, 16, 64		, aEvent.mStack, OP.rockGt.mat(MT.Gneiss, 4));
			RM.Hammer	.addRecipe1(T, 16, 64,  7000, aEvent.mStack, OP.rockGt.mat(MT.Gneiss, 4));
		}});
		}};
	}}
}