package de.jano1.sponge.janos_regions;

import de.jano1.sponge.regions_api.RegionFlag;

import java.util.List;

/**
 * Created by Jano1 on 02.07.2017.
 */
public class BasicRegionFlag implements RegionFlag {

    private List<String> related_plugin_ids;
    private String key;
    private String value;

    public BasicRegionFlag(String key, String value) {
        this.key = key;
        this.value = value;
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
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
