package dev.unnm3d.BungeeIntegration;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdminCommands extends Command {

	public AdminCommands(){
		super("bungeeadmin","bungeeintegration.admin","ba");
		// TODO Auto-generated constructor stub
	}
	public static ProxiedPlayer play;
	@Override
	public void execute(CommandSender sender, String[] args) {
		try {
		if(args[0].equalsIgnoreCase("connect")) {
			
		if(args.length > 2) {
		connect(Main.instance.getProxy().getPlayer(args[2]),Main.instance.getProxy().getServerInfo(args[1]));
		}else {
			if(sender instanceof ProxiedPlayer) {
			connect((ProxiedPlayer) sender,Main.instance.getProxy().getServerInfo(args[1]));
			}
		}

		}
		if(args[0].equalsIgnoreCase("serverstat")) {
			TextComponent text=new TextComponent(ChatColor.GOLD+"STATISTICS:\n");
			for(Map.Entry<String, ServerInfo> entry : Main.instance.getProxy().getServers().entrySet()) {
			
				text.addExtra(entry.getKey()+":\n"
						+ "Player count: "+entry.getValue().getPlayers().size()+"\n");
			    try {
			        Socket s = new Socket();
			        s.connect(entry.getValue().getAddress(),1000);
			         // ONLINE
			         s.close();
			         text.addExtra(ChatColor.GREEN+"The server is online\n");
			      } catch (UnknownHostException e) {
			        text.addExtra(ChatColor.DARK_RED+"The server is offline\n");
			      } catch (IOException e) {
			    	  text.addExtra(ChatColor.DARK_RED+"The server is offline\n");
			      }
				
				BaseComponent extra=new TextComponent("Join Server");
				extra.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bungee connect "+entry.getKey()+" "+((ProxiedPlayer) sender).getName()));
				extra.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to join").color(ChatColor.AQUA).create()));
				extra.setColor(ChatColor.RED);
				text.addExtra(extra);
				text.addExtra("\n"+ChatColor.GRAY+"------------------"+ChatColor.WHITE+"\n");
				
				
				
			}
			text.addExtra(ChatColor.YELLOW+"OPENED CHANNELS (for developers):\n"+ChatColor.BLUE);
			Collection<String> coll=Main.instance.getProxy().getChannels();
			
			
			for(Iterator<String> i=coll.iterator();i.hasNext();) {
				text.addExtra(i.next());
				if(!i.hasNext()) break;
				text.addExtra(" , ");
				
			}
			
			sender.sendMessage(text);
		}
		if(args[0].equalsIgnoreCase("togglepluginmsg")) {
			
			if(sender instanceof ProxiedPlayer) {
			if(play==null || !((ProxiedPlayer)sender).getDisplayName().equals(play.getDisplayName())) {
				play=(ProxiedPlayer) sender;
				play.sendMessage(new TextComponent("toggled true"));
			}else play=null;
			}
		}
	}catch(NullPointerException e){		
		sender.sendMessage(new TextComponent("you typed something wrong or an error occurred"));
	}
	}
	private void connect(ProxiedPlayer p,ServerInfo server) {
		p.connect(server);
	}
}
