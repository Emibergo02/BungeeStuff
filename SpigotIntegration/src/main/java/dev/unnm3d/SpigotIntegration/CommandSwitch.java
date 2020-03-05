package dev.unnm3d.SpigotIntegration;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;



public class CommandSwitch implements CommandExecutor {

	private final Main plugin;
	public CommandSwitch(Main main) {
		// TODO Auto-generated constructor stub
		this.plugin = main;
	}
	

	public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
		
		if(command.getName().contains("getserver")) {
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			  out.writeUTF("GetServers");
			  

			  // If you don't care about the player
			  // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
			  // Else, specify them
			  
			  
			  ((PluginMessageRecipient) Bukkit.getServer().getOnlinePlayers().toArray()[0]).sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
		}
		if(command.getName().contains("sendp")) {
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			Player p= Bukkit.getPlayer(args[0]);
			//if(p!=null) {
			String server=args[1];
			  out.writeUTF("Connect");
			  out.writeUTF(server);
			  // If you don't care about the player
			  // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
			  // Else, specify them

			  p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
			//}else sender.sendMessage("player not found. maybe the wrong name...");
			  
		}
		

		
		return false;
	}

	

}
