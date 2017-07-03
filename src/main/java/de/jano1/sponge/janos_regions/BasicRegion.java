package de.jano1.sponge.janos_regions;

import de.jano1.sponge.regions_api.EmptyRegionShape;
import de.jano1.sponge.regions_api.Region;
import de.jano1.sponge.regions_api.RegionFlag;
import de.jano1.sponge.regions_api.RegionShape;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.world.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jano1 on 02.07.2017.
 */
public class BasicRegion implements Region {

    private List<String> related_plugin_ids;
    private List<String> parent_regions_ids;
    private String id;
    private RegionShape shape;
    private List<RegionFlag> flags;

    public BasicRegion(String id) {
        related_plugin_ids = new ArrayList<>();
        parent_regions_ids = new ArrayList<>();
        flags = new ArrayList<>();
        this.id = id;
        shape = new EmptyRegionShape();
    }

    @Override
    public String[] getRelatedPluginIDs() {
        return (String[]) related_plugin_ids.toArray();
    }

    @Override
    public boolean isRelatedTo(String plugin_id) {
        return related_plugin_ids.contains(plugin_id);
    }

    @Override
    public void setRelationStatusTo(String plugin_id, boolean status) {
        related_plugin_ids.remove(plugin_id);
        if (status) {
            related_plugin_ids.add(plugin_id);
        }
    }

    @Override
    public String[] getParents() {
        return (String[]) parent_regions_ids.toArray();
    }

    @Override
    public boolean hasParents() {
        return !parent_regions_ids.isEmpty();
    }

    @Override
    public boolean hasParent(String region_id) {
        return parent_regions_ids.contains(region_id);
    }

    @Override
    public void setParentalStatus(String region_id, boolean status) {
        parent_regions_ids.remove(region_id);
        if (status) {
            parent_regions_ids.add(region_id);
        }
    }

    @Override
    public void addParent(String region_id) {
        setParentalStatus(region_id, true);
    }

    @Override
    public void setShape(RegionShape shape) {
        this.shape = shape;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public RegionShape getShape() {
        return shape;
    }

    @Override
    public RegionFlag[] getFlags() {
        return (RegionFlag[]) flags.toArray();
    }

    @Override
    public boolean hasFlags() {
        return !flags.isEmpty();
    }

    @Override
    public boolean hasFlagsRelatedTo(String plugin_id) {
        return getFlagsRelatedTo(plugin_id).length > 0;
    }

    @Override
    public RegionFlag[] getFlagsRelatedTo(String plugin_id) {
        List<RegionFlag> temp_flags = new ArrayList<>();
        for (RegionFlag flag : flags) {
            if (flag.isRelatedTo(plugin_id)) {
                temp_flags.add(flag);
            }
        }
        return (RegionFlag[]) temp_flags.toArray();
    }

    @Override
    public void setFlag(RegionFlag to_set) {
        for (RegionFlag flag : flags) {
            if (flag.getKey().equals(to_set.getKey())) {
                flags.set(flags.indexOf(flag), to_set);
                return;
            }
        }
        flags.add(to_set);
    }

    @Override
    public void setFlags(RegionFlag[] flags) {
        for (RegionFlag flag : flags) {
            setFlag(flag);
        }
    }

    @Override
    public boolean containsLocation(Location location) {
        return shape.contains(location);
    }
}
