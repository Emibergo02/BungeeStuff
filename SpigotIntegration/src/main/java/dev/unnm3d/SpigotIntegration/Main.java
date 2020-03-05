package dev.unnm3d.SpigotIntegration;



import org.bukkit.plugin.java.JavaPlugin;

import dev.unnm3d.SpigotIntegration.ManagePmessage.InPluginMessage;




/*
 * Github links for apis:
 * - https://github.com/leonardosnt/BungeeChannelApi
 * 
 */
public class Main extends JavaPlugin{

	@Override
	public void onEnable() {

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new InPluginMessage());
        this.getCommand("getservers").setExecutor(new CommandSwitch(this));
        this.getCommand("sendp").setExecutor(new CommandSwitch(this));
        this.getCommand("test").setExecutor(new CommandSwitch(this));
	}

    





}