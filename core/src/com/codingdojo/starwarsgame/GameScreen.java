package com.codingdojo.starwarsgame;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.codingdojo.starwarsgame.entities.Bullet;

public class GameScreen implements Screen{
	final StarWarsGame game;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle xwing;
	private Rectangle tieFighter;
	private Array<Rectangle> tieFighters;
	private Rectangle bullet;
	private Array<Rectangle> bullets;
	private long lastDropTime;
	private long lastBulletTime;
	private int tieFightersDestroyed;
	private Texture tieImage;
	private Texture xWing;
	private Texture background;
	private Sound backgroundMusic;
	private Sound tieFighterExplosion;
	private long backgroundSoundId;
	private Sound xwingExplosion;
	private Texture xwingBlast;




	public GameScreen(final StarWarsGame game) {
		this.game = game;

		
		//Game Textures
		tieImage = new Texture(Gdx.files.internal("tie_interceptor.png"));
		xWing = new Texture(Gdx.files.internal("x_wing.png"));
		background = new Texture(Gdx.files.internal("star-wars-background.jpg"));
		xwingBlast = new Texture(Gdx.files.internal("xwing_bullet.png"));
		//find audio for star wars theme.
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("sound_effects/background_music.wav"));
		backgroundSoundId = backgroundMusic.play(0.1f);
		backgroundMusic.setLooping(backgroundSoundId, true);
		xwingExplosion = Gdx.audio.newSound(Gdx.files.internal("sound_effects/xwing_explode.mp3"));
		tieFighterExplosion = Gdx.audio.newSound(Gdx.files.internal("sound_effects/tieFighterExplode.mp3"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		
		xwing = new Rectangle();
		xwing.x= 800 / 2 - 64 / 2;
		xwing.y = 20;
		xwing.width = 64;
		xwing.height = 64;

		bullets = new Array<Rectangle>();
		bullet = new Rectangle();

		tieFighter = new Rectangle();
		tieFighters = new Array<Rectangle>();
		spawnTieFighter();
	}
	
	private void spawnTieFighter() {
		Rectangle tieFighter = new Rectangle();
		tieFighter.x = MathUtils.random(0, 800-64);
		tieFighter.y = 480;
		tieFighter.width = 64;
		tieFighter.height = 64;
		tieFighters.add(tieFighter);
		lastDropTime = TimeUtils.nanoTime();
	}

	private void fireWeapon() {
		Rectangle bullet = new Rectangle();
		bullet.x = xwing.x + 20;
		bullet.y = xwing.y;
		bullet.width = 64;
		bullet.height = 64;
		bullets.add(bullet);
		lastBulletTime = TimeUtils.nanoTime();
	}
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(background, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(xWing, xwing.x, xwing.y);

		for(Rectangle fighters: tieFighters) {
			game.batch.draw(tieImage, fighters.x, fighters.y);
		}
		for(Rectangle bullet: bullets) {
			game.batch.draw(xwingBlast, bullet.x, bullet.y);
		}

		game.batch.end();


		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			xwing.x = touchPos.x - 64/2;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) xwing.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) xwing.x += 200 * Gdx.graphics.getDeltaTime();

		if(xwing.x < 0) xwing.x = 0;
		if(xwing.x > 800 - 64) xwing.x = 800 - 64;

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnTieFighter();
		if(TimeUtils.nanoTime() - lastBulletTime > 1000000000) fireWeapon();

		for (Iterator<Rectangle> iter = tieFighters.iterator(); iter.hasNext(); ) {
			Rectangle interceptor = iter.next();
			interceptor.y -= 200 * Gdx.graphics.getDeltaTime();
			if(interceptor.y + 64 < 0) iter.remove();

			// Destroy lukes xwing with audio.
			if(interceptor.overlaps(xwing)) {
				iter.remove();
				xWing = new Texture(Gdx.files.internal("blown_up.png"));
				xwingExplosion.play();
				backgroundMusic.stop();
				stopRendering();
				
			}
		}
		// Shooting code
		for (Iterator<Rectangle> iter = bullets.iterator(); iter.hasNext(); ) {
			Rectangle bullet = iter.next();
			bullet.y += 400 * Gdx.graphics.getDeltaTime();
			if(bullet.y + 64 < 0) iter.remove();

			// suppose to Destroy tieFighters on impact
		}
		
		for (Rectangle bullet: bullets) {
			for (Rectangle fighter: tieFighters) {
				if(fighter.overlaps(bullet)) {
//					tieImage = new Texture(Gdx.files.internal("blown_up.png"));
					tieFighterExplosion.play();
					System.out.println("hit");
				}
			}
		}
		
	}



	public void stopRendering() {
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		game.setScreen(new GameOverScreen(game, tieFightersDestroyed));
		dispose();


	}

	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void show() {
		// WHERE WE PLAY BACKGROUND "MUSIC"		
	}
	
	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		backgroundMusic.dispose();
		background.dispose();
		xWing.dispose();
		
		tieImage.dispose();
	}

}
