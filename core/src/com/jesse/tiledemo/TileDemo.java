package com.jesse.tiledemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jesse.tiledemo.mapsystem.Map;
import com.jesse.tiledemo.mapsystem.MapLoader;
import com.jesse.tiledemo.mapsystem.MapRenderer;

public class TileDemo extends ApplicationAdapter {
	private SpriteBatch batch;
	private Map map;
	private MapRenderer renderer;
	private OrthographicCamera camera;

	@Override
	public void create() {
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w,h);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		try {
			map = MapLoader.loadMap("map1");
			renderer = new MapRenderer(batch, map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gdx.input.setInputProcessor(new InputAdapter() {
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				Vector2 mouse = new Vector2(Gdx.input.getX(), Gdx.input.getY());
				map.getTileAt(mouse,camera);
				return true;
			}
		});

	}

	@Override
	public void render() {
		handleInput();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			renderer.render();
		batch.end();
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, 3, 0);
		}
	}
}
