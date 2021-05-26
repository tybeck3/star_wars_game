package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Gdx;
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
	GlyphLayout highscoreTwo;
	GlyphLayout highscoreThree;
	int scoreOne;
	int scoreTwo;
	int scoreThree;
	
	public StartScreen(final StarWarsGame game) {
		this.game = game;
		
		titleImage = new Texture(Gdx.files.internal("red_five_logo.png"));
		startImage = new Texture(Gdx.files.internal("start_button.png"));
		startImageActive = new Texture(Gdx.files.internal("start_button_red.png"));
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/Minecraft.fnt"));
		scoreFont.getData().setScale(.75f, .75f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(titleImage, 100, 260, TITLE_IMAGE_WIDTH, TITLE_IMAGE_HEIGHT);
		
		GlyphLayout highscoreOne = new GlyphLayout(scoreFont, "Player One ___ " + scoreOne);
		scoreFont.draw(game.batch, highscoreOne, Gdx.graphics.getWidth() / 2 - highscoreOne.width / 2, Gdx.graphics.getHeight() - highscoreOne.height - 195);
		GlyphLayout highscoreTwo = new GlyphLayout(scoreFont, "Player Two ___ " + scoreTwo);
		scoreFont.draw(game.batch, highscoreTwo, Gdx.graphics.getWidth() / 2 - highscoreTwo.width / 2, Gdx.graphics.getHeight() - highscoreTwo.height - 245);
		GlyphLayout highscoreThree = new GlyphLayout(scoreFont, "Player Three ___ " + scoreThree);
		scoreFont.draw(game.batch, highscoreThree, Gdx.graphics.getWidth() / 2 - highscoreThree.width / 2, Gdx.graphics.getHeight() - highscoreTwo.height - 295);
		
		int x = Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2;
		int y = Gdx.graphics.getHeight() - START_BUTTON_HEIGHT - 55;
		if(Gdx.input.getX() < x + START_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < y + START_BUTTON_HEIGHT && Gdx.input.getY() > y) {
			game.batch.draw(startImageActive, Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - START_BUTTON_HEIGHT - 375, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
		} else {
			game.batch.draw(startImage, Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2, Gdx.graphics.getHeight() - START_BUTTON_HEIGHT - 375, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
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
		
	}
}
