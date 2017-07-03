package de.jano1.sponge.janos_regions;

import com.sun.org.apache.regexp.internal.RE;
import de.jano1.sponge.regions_api.Region;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jano1 on 02.07.2017.
 */
public class BasicRegionIO {

    private static File file = new File("regions/save.dat");

    public static void save(Region[] regions) throws IOException {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream output_stream = new FileOutputStream(file);
        ObjectOutputStream object_writer = new ObjectOutputStream(output_stream);
        for(Region r : regions){
            object_writer.writeObject(r);
        }
        object_writer.flush();
        object_writer.close();
        output_stream.close();
    }

    public static List<Region> load() throws IOException, ClassNotFoundException {
        if(!file.exists()) {
            return new ArrayList<>();
        }
        FileInputStream input_stream = new FileInputStream(file);
        ObjectInputStream object_reader = new ObjectInputStream(input_stream);
        Region loaded = null;
        List<Region> regions = new ArrayList<>();
        while((loaded = (Region) object_reader.readObject()) != null){
            regions.add(loaded);
        }
        return regions;
    }
}
