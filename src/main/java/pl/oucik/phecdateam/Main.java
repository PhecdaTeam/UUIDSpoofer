package pl.oucik.phecdateam;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Plugin;
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

public class Main extends Plugin {
    File config = new File("plugins/config.yml");
    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new login());
        getLogger().info("[Spoofer] Plugin Enabled!");
        if (!(config.exists())){
            try{
                    config.createNewFile();
                    Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
                    configuration.set("AdminNick", "hm4");
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(String.valueOf(config)));

            }catch (IOException e){
                getLogger().info("[Spoofer] Error : " + e.getMessage());
            }

        }

    }

}
