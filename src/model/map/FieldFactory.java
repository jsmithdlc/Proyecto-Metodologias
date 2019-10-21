package model.map;

import java.util.ArrayList;
import java.util.List;

public class FieldFactory {
    private List<Location> locations = new ArrayList<>();
    private Field map;
    private long seed = -1;

    public Field createMap(int size){
        locations.clear();
        for(int i = 0; i < size; i++){
            for(int j = 0;j < size;j++){
                locations.add(new Location(i,j));
            }
        }
        map = new Field();
        if(this.seed!=-1){
            map.setSeed(seed);
        }
        map.addCells(false,locations.toArray(new Location[0]));
        return map;
    }

    public void setSeed(long seed){
        this.seed = seed;
    }

    public long getSeed(){
        return this.seed;
    }
}
