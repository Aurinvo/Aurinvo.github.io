package io.github.aurinvo.itemlottery;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemLottery extends JavaPlugin {
	private int chance;               //chance to win (1%-100%)

	private int stackNum=0;          //How many of the won item you won
	private ItemStack stack=new ItemStack(Material.WOOD, stackNum);       //what you win, upon win.
	private String[] lotteryInfo={"WOOD","0"};

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new ItemLotteryHandListener(this), this);

		this.getCommand("setlotteryitem").setExecutor(new ItemLotteryCommandExecutor(this));
		this.getCommand("setlotterychance").setExecutor(new ItemLotteryCommandExecutor(this));
		this.getCommand("lotteryinfo").setExecutor(new ItemLotteryCommandExecutor(this));

	}

	@Override
	public void onDisable() {
		//getLogger().info("onDisable has been invoked!"); //generic test, remove later

	}

	public int getChance() {
		return chance;
	}

	public void setChance(int i) {
		chance=i;
	}

	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack s) {
		stack=s;
	}

	public int getStackNum() {
		return stackNum;
	}

	public void setStackNum(int i) {
		stackNum=i;
	}

	public String getInfo(int e) {
		return lotteryInfo[e];
	}

	public void setInfo(int e, String s) {
		lotteryInfo[e]=s;
	}

}
