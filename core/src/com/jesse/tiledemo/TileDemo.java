package com.jesse.tiledemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jesse.tiledemo.mapsystem.Map;
import com.jesse.tiledemo.mapsystem.MapLoader;
import com.jesse.tiledemo.mapsystem.MapRenderer;

public class TileDemo extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	private MapRenderer renderer;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		try{
			map = MapLoader.loadMap("map1.txt");
			renderer = new MapRenderer(batch, map);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Gdx.input.setInputProcessor(new InputAdapter(){
			public boolean touchDown(int screenX, int screenY, int pointer, int button){
				Vector2 mouse = new Vector2(Gdx.input.getX(), Gdx.input.getY());
				map.getTileAt(mouse);
				return true;
			}
		});
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			renderer.render();
		batch.end();
	}
}
