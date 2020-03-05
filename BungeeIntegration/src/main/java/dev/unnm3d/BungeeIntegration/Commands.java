package dev.unnm3d.BungeeIntegration;






import java.util.Map;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;


import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class Commands extends Command {

	public Commands(){
		super("bungee","bungeeintegration.base","b");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length==0)return;
		
		try {
		Plugin bungee=Main.instance;

		if(args[0].equalsIgnoreCase("tell")||args[0].equalsIgnoreCase("msg")) {
			//comando messaggio
			bungee.getProxy().getPlayer(args[1]).sendMessage(new TextComponent(ChatColor.LIGHT_PURPLE+sender.getName()
									+ ChatColor.WHITE+" from server "+ChatColor.GOLD+getServer((ProxiedPlayer) sender)+ChatColor.WHITE+": "+accodaDa(args,2)));
		}

		if(args[0].equalsIgnoreCase("whereis")) {
			sender.sendMessage(new TextComponent(args[1]+" is in the server "+getServer(Main.getProxyInstance().getPlayer(args[1]))));;
		}
		}catch(NullPointerException e){		
			sender.sendMessage(new TextComponent("you typed something wrong or an error occurred"));
		}
	}



	public static String getServer(ProxiedPlayer p) {
		// da Player trova nome server
		Map<String,ServerInfo> lel=Main.instance.getProxy().getServers();
		// ciclo per ogni nome e server
		for(Map.Entry<String, ServerInfo> entry : lel.entrySet()) {
		    String key = entry.getKey();
		    ServerInfo server = entry.getValue();
		    // se nel server c'è il Player returna nomeserver
		    if(server.getPlayers().contains(p)) return key;
		}
		return "not found";
	}

//	private void connect(ProxiedPlayer p,ServerInfo server) {
//			p.connect(server);
//		}
	
	private String accodaDa(String[] stringhe,int da) {
		
		String stringa = new String();
		for(int i=da;i<stringhe.length;i++) {
			
			stringa=stringa+stringhe[i]+" ";
		}
		
		return stringa;
	}


}
