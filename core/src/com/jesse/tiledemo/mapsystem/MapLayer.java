
package com.jesse.tiledemo.mapsystem;

import java.awt.Point;

public class MapLayer {
    private int depth;
    private String layerName;
    private Tile[][] layerData;

    public MapLayer(String name){
        layerName = name;
    }
    
    public MapLayer(String name, Tile[][] data) {
        layerName = name;
        layerData = data;
    }
    
    public MapLayer(String name, Tile[][] data, int depth){
        layerName = name;
        layerData = data;
        this.depth = depth;
    }
    
    public MapLayer(String name, int x, int y){
       layerName = name;
       layerData = new Tile[y][x];
    }
    
    public Tile getTileAt(int x, int y){
        return layerData[x][y];
    }
    
    public void setTileAt(Tile tile, int x, int y){
        layerData[y][x] = tile;
    }
    
    public int getHeight(){
        return layerData.length;
    }
    
    public int getWidth(){
        return layerData[0].length;
    }
    
    public String getName(){
        return layerName;
    }
    
    public void setDepth(int depth){
        this.depth = depth;
    }
    
    public int getDepth(){
        return depth;
    }
    
    public void print(){
        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                System.out.print(layerData[j][i].getName()+",");
            }
            System.out.println();
        }
    }
}
