package de.jano1.sponge.janos_regions;

import com.google.inject.Inject;
import de.jano1.sponge.regions_api.RegionsAPI;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.plugin.Dependency;

@Plugin(
        id = "janos_regions",
        name = "JanosRegions",
        description = "Implements the RegionsAPI",
        authors = {
                "Jano1"
        },
        dependencies = {
                @Dependency(id = "regions_api")
        }
)
public class JanosRegions {

    @Inject
    private Logger logger;


    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        RegionsAPI regions_api = (RegionsAPI) Sponge.getPluginManager().getPlugin("regions_api").get();
        regions_api.registerProvider(this,new BasicRegionService());
    }

}
