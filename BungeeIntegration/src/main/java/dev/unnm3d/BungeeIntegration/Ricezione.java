package dev.unnm3d.BungeeIntegration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Ricezione implements Listener {

	@EventHandler
    public void onPluginMessage(PluginMessageEvent e) throws IOException {
 /*       if (e.getTag().equalsIgnoreCase("BungeeCord")) {
        	((ProxiedPlayer)e.getSender()).sendMessage(new TextComponent(e.getData().toString()));;
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            
                String action = in.readUTF(); // channel we delivered
                
                if(action.equals("get")){
                    ServerInfo server = Main.getProxyInstance().getPlayer(e.getReceiver().toString()).getServer().getInfo();
                    String input = in.readUTF(); // the inputstring
                    if(input.equals("nickname")){
                        //sendToBukkit(channel, Main.nicks.get(e.getReceiver().toString()), server);
                    } else {
                        //sendToBukkit(channel, Main.points.get(e.getReceiver().toString()).toString(), server);
                    }
              
                }
                
      
        }*/
        if(e.getSender() instanceof ProxiedPlayer) {
        //if(AdminCommands.play!=null) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
        Main.instance.getProxy().broadcast(new TextComponent("PM detected -> Tag: "+e.getTag()+" Playername: "+((ProxiedPlayer)e.getSender()).getName()/*+" Channel: "+ in.readUTF()*/));
        //}
        }
    }


    public void sendToBukkit(String channel, String message, ServerInfo server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(channel);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.sendData(Main.channel, stream.toByteArray());
        
    }

    public void sendToPlayer(String channel, String message, ProxiedPlayer player) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(channel);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendData(Main.channel, stream.toByteArray());

    }
    
}
