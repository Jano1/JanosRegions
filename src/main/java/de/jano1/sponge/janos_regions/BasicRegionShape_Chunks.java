package de.jano1.sponge.janos_regions;

import de.jano1.sponge.regions_api.RegionShape;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Chunk;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jano1 on 02.07.2017.
 */
public class BasicRegionShape_Chunks implements RegionShape {

    private List<Chunk> chunks;

    public BasicRegionShape_Chunks(){
        chunks = new ArrayList<>();
    }

    public void addChunk(Chunk c){
        chunks.remove(c);
        chunks.add(c);
    }

    public void removeChunk(Chunk c){
        chunks.remove(c);
    }

    @Override
    public boolean contains(Location location) {
        for(Chunk chunk : chunks){
            if(chunk.containsBlock(location.getBlockPosition())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addVertice(Location<World> location) {
        addChunk(locationToChunk(location));
    }

    @Override
    public void removeVertice(Location<World> location) {
        removeChunk(locationToChunk(location));
    }

    public static Chunk locationToChunk(Location<World> location){
        World w = Sponge.getGame().getServer().getWorld(location.getExtent().getUniqueId()).get();
        return w.getChunk(location.getChunkPosition()).get();
    }
}
