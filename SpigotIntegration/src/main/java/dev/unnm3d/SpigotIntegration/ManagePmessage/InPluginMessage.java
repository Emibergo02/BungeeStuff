package dev.unnm3d.SpigotIntegration.ManagePmessage;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import dev.unnm3d.SpigotIntegration.Main;

public class InPluginMessage implements PluginMessageListener {

	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		// TODO Auto-generated method stub
		if (!channel.equals("BungeeCord")) {
		      return;
		    }
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("GetServers")) {
	      // Use the code sample in the 'Response' sections below to read
	      // the data.
	    	String[] serverList = in.readUTF().split(", ");
	    	for(String s:serverList) {
	    		Bukkit.getConsoleSender().sendMessage(s);
	    	}
	    }
	    if (subchannel.equals("GetPlayerInfo")) {
	    	try {
				sendToBungeeCord(player,"ReturnPlayerInfo",playerMessage(player));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		
	}
	
	private byte[] playerMessage(Player p) throws IOException {
		byte[] end = null;
		//end=p.getDisplayName()+", "+p.isOnline()+", "+p.isOp()+", "+p.getLastDamageCause().getEntityType();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			  out = new ObjectOutputStream(bos);   
			  out.writeObject(p);
			  out.flush();
			  end = bos.toByteArray();
		  
		} finally {
		  try {
		    bos.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		return end;
		
	}
	public void sendToBungeeCord(Player p, String channel, byte[] cs){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF(channel);
            out.write(cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", b.toByteArray());       
    }

}
