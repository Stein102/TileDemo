/*
 * Author @Jesse Stein
 * Student Number @3057267
 */
package com.jesse.tiledemo.mapsystem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Jesse
 */
public class MapRenderer {
    private SpriteBatch batch;
    private Map map;
    
    
    

    public MapRenderer(SpriteBatch batch, Map map) {
        this.batch = batch;
        this.map = map;
    }
    
    
    
    
    public void renderMapLayer(MapLayer layer){
        for(int i = 0; i < layer.getHeight(); i++){
            for(int j = 0; j < layer.getWidth(); j++){
                    Tile t = layer.getTileAt(i, j);
            		if(t!=null){
                        batch.draw(t.getTexture(),i*map.getTILE_SIZE(), j*map.getTILE_SIZE());
                    }
            }
        }  
    }
    
    public void render(){
        for(MapLayer l: map.getLayers()){
            renderMapLayer(l);
        }   
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
