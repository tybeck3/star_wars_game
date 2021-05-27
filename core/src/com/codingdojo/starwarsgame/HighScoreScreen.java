package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HighScoreScreen implements Screen {
	
	private static final int HIGHSCORE_TITLE_WIDTH = 250;
	private static final int HIGHSCORE_TITLE_HEIGHT = 50;
	private Viewport viewPort;
	private Stage stage;


	StarWarsGame game;
	int score, highscoreOne, highscoreTwo, highscoreThree, highscoreFour, highscoreFive, highscoreSix, highscoreSeven, highscoreEight, highscoreNine, highscoreTen;
	Texture highscoreTitle;
	
	
	public HighScoreScreen(StarWarsGame game, int score) {
		this.game = game;
		this.score = score;

		highscoreTitle = new Texture(Gdx.files.internal("high_scores.png"));
		
	}

	public HighScoreScreen(StarWarsGame game) {

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
