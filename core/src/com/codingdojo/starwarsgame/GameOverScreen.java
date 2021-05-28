package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	private static final int GAME_OVER_WIDTH = 500;
	private static final int GAME_OVER_HEIGHT = 125;
	private static final int NEW_HIGH_SCORE_WIDTH = 300;
	private static final int NEW_HIGH_SCORE_HEIGHT = 50;
	private static final int TRY_AGAIN_WIDTH = 200;
	private static final int TRY_AGAIN_HEIGHT = 50;
	private static final int QUIT_WIDTH = 100;
	private static final int QUIT_HEIGHT = 50;
	
	final StarWarsGame game;
	int score, highscore;
	Texture newHighscoreBanner;
	Texture gameOver;
	Texture tryAgain;
	Texture tryAgainActive;
	Texture quit;
	Texture quitActive;
	BitmapFont scoreFont;
	OrthographicCamera camera;
	GlyphLayout highscoreText;
	
	
	public GameOverScreen(StarWarsGame game, int score) {
		this.game = game;
		this.score = score;
		
		gameOver = new Texture(Gdx.files.internal("game_over.png"));
		tryAgain = new Texture(Gdx.files.internal("try_again.png"));
		quit = new Texture(Gdx.files.internal("quit.png"));
		tryAgainActive = new Texture(Gdx.files.internal("try_again_active.png"));
		quitActive = new Texture(Gdx.files.internal("quit_active.png"));
		
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/Minecraft.fnt"));
		
		Preferences prefs = Gdx.app.getPreferences("starWarsGame");
		this.highscore = prefs.getInteger("highscoreOne", 0);
		if(score > highscore){
			newHighscoreBanner = new Texture(Gdx.files.internal("new_high_score.png"));
			prefs.putInteger("highscoreOne", score);
			prefs.flush();
		}
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}


	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(gameOver, Gdx.graphics.getWidth() / 2 - GAME_OVER_WIDTH / 2, Gdx.graphics.getHeight() - GAME_OVER_HEIGHT - 50, GAME_OVER_WIDTH, GAME_OVER_HEIGHT);
		
		if(newHighscoreBanner != null) {
			game.batch.draw(newHighscoreBanner, Gdx.graphics.getWidth() / 2 - NEW_HIGH_SCORE_WIDTH / 2, Gdx.graphics.getHeight() - NEW_HIGH_SCORE_HEIGHT - 200, NEW_HIGH_SCORE_WIDTH, NEW_HIGH_SCORE_HEIGHT);			
		}
		
		GlyphLayout newHighscore = new GlyphLayout(scoreFont, "Your score: " + score);
		scoreFont.draw(game.batch, newHighscore, Gdx.graphics.getWidth() / 2 - newHighscore.width / 2, Gdx.graphics.getHeight() - newHighscore.height - 240);
		
		
		int x = Gdx.graphics.getWidth() / 2 - TRY_AGAIN_WIDTH / 2;
		int y = Gdx.graphics.getHeight() - TRY_AGAIN_HEIGHT - 100;
		if(Gdx.input.getX() < x + TRY_AGAIN_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < y + TRY_AGAIN_HEIGHT && Gdx.input.getY() > y) {
			game.batch.draw(tryAgainActive, Gdx.graphics.getWidth() / 2 - TRY_AGAIN_WIDTH / 2, Gdx.graphics.getHeight() - TRY_AGAIN_HEIGHT - 330, TRY_AGAIN_WIDTH, TRY_AGAIN_HEIGHT);
			if(Gdx.input.isTouched()) {
				this.dispose();
				game.batch.end();
				game.setScreen(new GameScreen(game));
				return;
			}
		} else {
			game.batch.draw(tryAgain, Gdx.graphics.getWidth() / 2 - TRY_AGAIN_WIDTH / 2, Gdx.graphics.getHeight() - TRY_AGAIN_HEIGHT - 330, TRY_AGAIN_WIDTH, TRY_AGAIN_HEIGHT);
		}
		
		int w = Gdx.graphics.getWidth() / 2 - QUIT_WIDTH / 2;
		int h = Gdx.graphics.getHeight() - QUIT_HEIGHT - 35;
		if(Gdx.input.getX() < w + QUIT_WIDTH && Gdx.input.getX() > w && Gdx.input.getY() < h + QUIT_HEIGHT && Gdx.input.getY() > h) {
			game.batch.draw(quitActive, Gdx.graphics.getWidth() / 2 - QUIT_WIDTH / 2, Gdx.graphics.getHeight() - QUIT_HEIGHT - 395, QUIT_WIDTH, QUIT_HEIGHT);
			if(Gdx.input.isTouched()) {
				this.dispose();
				game.batch.end();
				game.setScreen(new StartScreen(game));
				return;
			}
		} else {
			game.batch.draw(quit, Gdx.graphics.getWidth() / 2 - QUIT_WIDTH / 2, Gdx.graphics.getHeight() - QUIT_HEIGHT - 395, QUIT_WIDTH, QUIT_HEIGHT);
		}
		game.batch.end();

	}
	
	@Override
	public void show() {
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
		if(newHighscoreBanner != null) {
			newHighscoreBanner.dispose();
		}
		gameOver.dispose();          
		tryAgain.dispose();          
		tryAgainActive.dispose();    
		quit.dispose();              
		quitActive.dispose();;        

	}

}
