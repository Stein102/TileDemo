package com.jesse.tiledemo.mapsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.awt.Point;
import java.util.ArrayList;


public class Map {
    private int tilesX,tilesY;
    private String mapName;
    private int TILE_SIZE = 32;
    ArrayList<MapLayer> layers;
    ArrayList<Tile> tiles;
    
    public Map(String name, int width, int height) {
        mapName = name;
        tilesX = width;
        tilesY = height;
        layers = new ArrayList<MapLayer>();
        tiles = new ArrayList<Tile>();
    }
    
    public void getTileAt(Vector2 mouse){
    	mouse.y = Gdx.graphics.getHeight()-mouse.y;
    	int tileX = (int) (mouse.x/TILE_SIZE);
    	int tileY = (int) (mouse.y/TILE_SIZE);
    	System.out.println("("+tileX+" , "+tileY+")");
    }
    
    
    
    
    
    public int getTilesX(){
        return tilesX;
    }
    
    public int getTilesY(){
        return tilesY;
    }
    
    public void setLayers(ArrayList<MapLayer> layers){
        this.layers = layers;
    }
    
    public String getName(){
        return mapName;
    }

    public ArrayList<MapLayer> getLayers() {
        return layers;
    }


	public int getTILE_SIZE() {
		return TILE_SIZE;
	}


	public void setTILE_SIZE(int tILE_SIZE) {
		TILE_SIZE = tILE_SIZE;
	}
    
}
