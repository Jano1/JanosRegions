package de.jano1.sponge.janos_regions;

import de.jano1.sponge.regions_api.Region;
import de.jano1.sponge.regions_api.RegionBuilder;
import de.jano1.sponge.regions_api.RegionFlag;
import de.jano1.sponge.regions_api.RegionShape;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jano1 on 02.07.2017.
 */
public class BasicRegionBuilder_Chunks implements RegionBuilder {

    private Region current;
    private RegionShape shape;
    private List<RegionFlag> flags;

    @Override
    public void startBuilding() {
        shape = new BasicRegionShape_Chunks();
        current = new BasicRegion(UUID.randomUUID().toString());
        flags = new ArrayList<>();
    }

    @Override
    public void setDesiredFlag(RegionFlag flag) {
        flags.add(flag);
    }

    @Override
    public void cancelBuilding() {
        startBuilding();
    }

    @Override
    public void finishBuilding() {
        current.setShape(shape);
        current.setFlags((RegionFlag[]) flags.toArray());
    }

    @Override
    public boolean isValidBuild() {
        return true;
    }

    @Override
    public Region getRegion() {
        return current;
    }
}
