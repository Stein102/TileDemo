package com.jesse.tiledemo.mapsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private Texture img;
    private String name;
    private String path;
    
    public Tile(String path) {
        img = new Texture(Gdx.files.internal(path));
        this.path = path;
    }

    public Tile(String name, String path) {
        img = new Texture(Gdx.files.internal(path));
        this.name = name;
        this.path = path;
    }
    
    

    public Texture getTexture(){
        return img;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPath(){
        return path;
    }
}

