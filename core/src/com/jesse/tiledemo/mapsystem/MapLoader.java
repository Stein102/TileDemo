package com.jesse.tiledemo.mapsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {
    
    private static ArrayList<Tile> tiles;
    private static ArrayList<MapLayer> layers;
    
    public static Map loadMap(String path) throws FileNotFoundException, IOException{
        tiles = new ArrayList<Tile>();
        layers = new ArrayList<MapLayer>();
        
        FileHandle file = Gdx.files.internal("maps\\"+path);
        String[] fileData = file.readString().split("[\\r\\n]+");
        
   
        int x = 0;
        int y = 0;
        int tileX = 0;
        int tileY = 0;
        String name = "";
        String commandType = "";
        String commaSeperated[];
        
        for(String command: fileData){
            
            if(command.equals("DEFINE PROPERTIES"))
                commandType = "properties";
            else if(command.equals("DEFINE TILES"))
                commandType ="tiles";
            else if(command.equals("DEFINE LAYER")){
                commandType ="layer";
                tileX = 0;
                tileY = 0;
            }
            else{
 
                int trim = 0;
                if(commandType.equals("properties")){
                        if(command.startsWith("name:")){
                            trim = "name:".length();
                            name = command.substring(trim);
                        }else if(command.startsWith("x:")){
                            trim = "x:".length();
                            x = Integer.parseInt(command.substring(trim));
                        }else if(command.startsWith("y:")){
                            trim = "y:".length();
                            y = Integer.parseInt(command.substring(trim));
                        }
                }else if(commandType.equals("tiles")){
                        commaSeperated = command.split(",");
                        String tileName ="";
                        String tilePath ="";
                        for(String s : commaSeperated){
                            if(s.contains("[")){
                                s = s.replace("[", "");
                            }
                            if(s.contains("]"))
                                s = s.replace("]", "");                     
                            if(s.startsWith("name:")){
                                trim = "name:".length();
                                tileName = s.substring(trim);
                            }else if(s.startsWith("path:")){ 
                                trim = "path:".length();
                                tilePath = s.substring(trim);
                            Tile t = new Tile(tileName,tilePath);
                            tiles.add(t);
                            }
                        }
                }else if(commandType.equals("layer")){
                    String layerName;
                    if(command.startsWith("name:")){
                         trim = "name:".length();
                         layerName = command.substring(trim);
                         MapLayer layer = new MapLayer(layerName,x,y);
                         layers.add(layer);
                    }else{
                     tileX = 0;
                     commaSeperated = command.split(",");
                     for(String s : commaSeperated){
                             MapLayer temp = layers.get(layers.size()-1);
                             temp.setTileAt(searchTiles(s), tileX, tileY); 
                             layers.set(layers.size()-1, temp);
                             tileX++;
                        } 
                    tileY++;
                    }
            
            }
            
        }   
    }
    Map m = new Map(name,x,y);
    m.tiles.addAll(tiles);
    m.setLayers(layers);
    return m;
    }
    
    public static void saveMap(Map map, String path){
        FileHandle mapFile = Gdx.files.local("maps\\"+path);
        String outputString = "DEFINE PROPERTIES\r\n";
        outputString+="name:"+map.getName()+"\r\n";
        outputString+="x:"+map.getTilesX()+"\r\n";
        outputString+="y:"+map.getTilesY()+"\r\n";
        outputString+="DEFINE TILES\r\n";
        for(Tile t: tiles){
            outputString += "[name:"+t.getName()+",path:"+t.getPath()+"]\r\n";
        }
        for(MapLayer m: map.layers){
            outputString+="DEFINE LAYER\r\n";
            outputString+="name:"+m.getName()+"\r\n";
            for(int i = 0; i < m.getHeight(); i++){
                for(int j = 0; j < m.getWidth(); j++){
                    if(i == m.getHeight()-1 && j == m.getWidth()-1){
                        outputString += m.getTileAt(j, i).getName()+"\r\n";
                    }else{
                        outputString += m.getTileAt(j, i).getName()+",";
                    }
                }
                outputString+="\r\n";
            }
        }
        
        mapFile.writeString(outputString, false);
    }
    
    
    
    
    
    
    
    
    
    private static Tile searchTiles(String name){
        for(Tile tile: tiles){
            if(tile.getName().equals(name)){
                return tile; 
            }
        }   
        return null;
    }
}