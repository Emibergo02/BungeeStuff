package dev.unnm3d.BungeeIntegration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin{

	public static String channel="bungee:output";
	public static Plugin instance;
	@Override
	public void onEnable() {
		instance=this;
		getProxy().getPluginManager().registerCommand(this, new Commands());
		getProxy().getPluginManager().registerCommand(this, new AdminCommands());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new Ricezione());
		ProxyServer.getInstance().registerChannel(channel);
		try {
			initializePermissions("bungeeintegration.base", "default");
			//addAdmin("pincopallino");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void initializePermissions(String permission,String group) throws IOException {
		
		Configuration conf = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.instance.getProxy().getPluginsFolder().getParentFile(), "config.yml"));
		List<String> lista =conf.getStringList("permissions."+group);
		if(!conf.getStringList("permissions."+group).contains(permission)) {
		lista.add(permission);
		conf.set("permissions."+group, (Object)lista);
		}
		ConfigurationProvider.getProvider(YamlConfiguration.class).save(conf, new File(Main.instance.getProxy().getPluginsFolder().getParentFile(), "config.yml"));
	}
	public static void addAdmin(String name) throws IOException {
		Configuration conf = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.instance.getProxy().getPluginsFolder().getParentFile(), "config.yml"));
		List<String> lista=new ArrayList<String>();
		lista.add("admin");
		lista.add("default");
		conf.set("groups."+name, (Object)lista);
		
		ConfigurationProvider.getProvider(YamlConfiguration.class).save(conf, new File(Main.instance.getProxy().getPluginsFolder().getParentFile(), "config.yml"));
	}

	public static ProxyServer getProxyInstance() {
		return ProxyServer.getInstance();
	}
	public boolean registerChannel(String channelname) {
		
		getProxyInstance().registerChannel(channelname);
		if(getProxyInstance().getChannels().contains(channelname))return true;
		else return false;
	}
	public boolean unregisterChannel(String channelname) {
		
		getProxyInstance().unregisterChannel(channelname);
		if(!getProxyInstance().getChannels().contains(channelname))return true;
		else return false;
	}
}
