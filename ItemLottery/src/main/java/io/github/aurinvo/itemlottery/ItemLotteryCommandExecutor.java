package io.github.aurinvo.itemlottery;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemLotteryCommandExecutor implements CommandExecutor {
	private final ItemLottery plugin;

	public ItemLotteryCommandExecutor(ItemLottery plugin) {
		this.plugin = plugin;                                                                //Store the plugin in situations where you need it.
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] s) {
		if (sender instanceof Player) {                                                         //Checks to make sure send is a Player.
			String[] args=s;  
			if (cmd.getName().equalsIgnoreCase("setlotteryitem")) {                             //If player typed...
				if (args.length != 2) {
					sender.sendMessage("Usage: /setlotteryitem [ITEM] [AMOUNT]");
					return false;
				} 

				plugin.setInfo(0, args[0]);                                                     //Sets what item to show when asked.
				plugin.setInfo(1, args[1]);                                                     //Sets the amount of the item to show when asked.

				args[0]=args[0].toUpperCase();
				args[0]=args[0].replace(' ', '_');                                              //Proper format for Materials.

				plugin.setStack(new ItemStack(Material.getMaterial(args[0].toString())));       //Sets the item to give.
				plugin.setStackNum(Integer.parseInt(args[1].toString()));                       //Sets the amount of the item to give.

				return true;
			} 
			else if (cmd.getName().equalsIgnoreCase("setlotterychance")) { 
				if (args.length != 1) {
					sender.sendMessage("Usage: /setlotterychace [CHANCE]");
					return false;
				} 
				plugin.setChance(Integer.parseInt(args[0].toString()));
				return true;
			}
			else if (cmd.getName().equalsIgnoreCase("lotteryinfo")) {
				sender.sendMessage("Current prize: " + plugin.getInfo(1) + " " + plugin.getInfo(0) + ". Odds: " + plugin.getInfo(1) + "%.");
				return true;
			}
			return false; 
		} else {
			sender.sendMessage("You must be a player!");
			return false;
		}

	}
}
