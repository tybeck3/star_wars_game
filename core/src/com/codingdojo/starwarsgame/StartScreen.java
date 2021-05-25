package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class StartScreen implements Screen{

	final StarWarsGame game;
	Texture titleImage;
	Texture startImage;
	OrthographicCamera camera;
	
	public StartScreen(final StarWarsGame game) {
		this.game = game;
		
		titleImage = new Texture(Gdx.files.internal("red_five_logo.png"));
		startImage = new Texture(Gdx.files.internal("start_button.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(titleImage, 100, 250, 600, 200);
		game.batch.draw(startImage, 300, 150, 200, 50);
		game.batch.end();
		
		if(Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
}
