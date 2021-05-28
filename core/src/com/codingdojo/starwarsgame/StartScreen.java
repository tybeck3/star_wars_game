package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class StartScreen implements Screen{

	private static final int START_BUTTON_HEIGHT = 50;
	private static final int START_BUTTON_WIDTH = 200;
	private static final int TITLE_IMAGE_HEIGHT = 200;
	private static final int TITLE_IMAGE_WIDTH = 600;
	
	final StarWarsGame game;
	Texture titleImage;
	Texture startImage;
	Texture startImageActive;
	BitmapFont scoreFont;
	OrthographicCamera camera;
	GlyphLayout highscoreOne;
	
	int scoreOne;
	
	
	public StartScreen(final StarWarsGame game) {
		this.game = game;
		
		titleImage = new Texture(Gdx.files.internal("red_five_logo.png"));
		startImage = new Texture(Gdx.files.internal("start_button.png"));
		startImageActive = new Texture(Gdx.files.internal("start_button_red.png"));
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/Minecraft.fnt"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		Preferences prefs = Gdx.app.getPreferences("starWarsGame");
		this.scoreOne = prefs.getInteger("highscoreOne", 0);
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(titleImage, Gdx.graphics.getWidth() / 2 - TITLE_IMAGE_WIDTH / 2, 260, TITLE_IMAGE_WIDTH, TITLE_IMAGE_HEIGHT);
		
		GlyphLayout highscoreOne = new GlyphLayout(scoreFont, "Highscore: " + scoreOne);
		scoreFont.draw(game.batch, highscoreOne, Gdx.graphics.getWidth() / 2 - highscoreOne.width / 2, Gdx.graphics.getHeight() - highscoreOne.height - 225);

		
		int x = Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2;
		int y = Gdx.graphics.getHeight() - START_BUTTON_HEIGHT - 80;
		if(Gdx.input.getX() < x + START_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < y + START_BUTTON_HEIGHT && Gdx.input.getY() > y) {
			game.batch.draw(startImageActive, Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - START_BUTTON_HEIGHT - 350, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
		} else {
			game.batch.draw(startImage, Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - START_BUTTON_HEIGHT - 350, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
		}
		game.batch.end();
		
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
		titleImage.dispose();     
		startImage.dispose(); 
		startImageActive.dispose();
	}
}       
