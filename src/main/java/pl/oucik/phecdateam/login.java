package pl.oucik.phecdateam;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class login implements Listener {

    @EventHandler
    public void onJoin(PreLoginEvent e) throws IOException {
        File config = new File("plugins/config.yml");
        Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
        String sURL = "http://tools.glowingmines.eu/convertor/nick/" + configuration.get("AdminNick");
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        String uuid = rootobj.get("offlinesplitteduuid").getAsString();
        e.getConnection().setUniqueId(UUID.fromString(uuid));
    }

}
