package io.github.aurinvo.itemlottery;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemLotteryHandListener implements Listener {
	private final ItemLottery plugin;

	public ItemLotteryHandListener(ItemLottery p) {
		plugin=p;
	}

	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){

		Player p = event.getPlayer();

		if (p.getItemInHand().getType() == Material.PAPER){                                       //Checks is held item is paper.
			if (p.getItemInHand().getItemMeta().hasLore()) {                                      //Checks if it has lore.
				if(p.getItemInHand().getItemMeta().getLore().get(0).equals("Lottery Ticket")){    //Checks is it is a Lottery Ticket.
					p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);                 //decrements amount of tickets by 1.
					if (getRandomBool()) {
						p.getInventory().addItem(plugin.getStack());
						p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You won the lottery! Congratulations!");
					}
					else {
						p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You didn't win. Better luck next time!");
					}
				}
			}
		}

	}

	public boolean getRandomBool() {
		int randNum=(int) Math.ceil(Math.random()*100);  //random number from 1-100
		if (randNum>=plugin.getChance()) {                           //player wins
			return true;
		}
		else {                                          //player loses
			return false;
		}
	}
}
