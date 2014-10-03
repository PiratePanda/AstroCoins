package com.beastmc.astrocraft;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("AstroCoins has been successfuly enabled!");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("AstroCoins has been successfuly disabled");
	}
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		if(!getConfig().contains(p.getName())){
			getConfig().set(p.getName() + ".AstroCoins", 0);
		}//if !getConfig
	}//onJoin

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase("giveac")){
			if(player.hasPermission("astrocoins.giveac") || sender.isOp()){
				if(args.length == 2){
				@SuppressWarnings("deprecation")
				Player tp = player.getServer().getPlayer(args[0]);
				giveAstroCoins(tp, 200);
				}
			
			else if(args.length != 2){
				player.sendMessage("§4" + "Who do you want to give AstroCoins to? (/giveac [player] amountofcoins)");
;			}//!=1
			}
		
		}//commandlabel
		
		if(label.equalsIgnoreCase("ac")){
			String p = sender.getName();
			String amount = getConfig().getString(p + ".AstroCoins");
			player.sendMessage("You have" + " " + amount + " " + "AstroCoins!");
		}//ac
		return false;
	}//giveCommand
	
	
	public void giveAstroCoins(Player p, int i){
		getConfig().set(p.getName() + ".AstroCoins", getConfig().getInt(p.getName() + ".AstroCoins", 0) + i);
		saveConfig(); 
		p.sendMessage("§2§l" + "You have recieved" + " " + i + " " + "AstroCoins!");
	}//giveAstroCoins
	
	public void takeAstroCoins(Player p, int i){
		getConfig().set(p.getName() + ".AstroCoins", getConfig().getInt(p.getName() + ".AstroCoins", 0) - i);
		saveConfig(); 
		p.sendMessage("§4§l" + i + " " + "AstroCoins have been taken from your account!");
	}//takeAstroCoins
	
}//ends class

/*
 * 
 * Created by Pirate Panda.
 * Inspired by an adaptation of BCBros.
 * 
 */
