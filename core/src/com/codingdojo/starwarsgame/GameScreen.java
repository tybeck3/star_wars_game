package com.codingdojo.starwarsgame;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

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
	private Sound xwingFire;
	private Texture xwingBlast;
	BitmapFont scoreFont;
	int shotsFired;



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
		xwingFire = Gdx.audio.newSound(Gdx.files.internal("sound_effects/xwing_fire.mp3"));
		tieFighterExplosion = Gdx.audio.newSound(Gdx.files.internal("sound_effects/tieFighterExplode.mp3"));
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/minecraft.fnt"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		shotsFired = 0;
		tieFightersDestroyed = 0;
		
		
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
		tieFighter.x = MathUtils.random(0, 800-44);
		tieFighter.y = 480;
		tieFighter.width = 44;
		tieFighter.height = 48;
		tieFighters.add(tieFighter);
		lastDropTime = TimeUtils.nanoTime();
	}

	private void fireWeapon() {
		Rectangle bullet = new Rectangle();
		bullet.x = xwing.x;
		bullet.y = xwing.y + 30;
		bullet.width = 3;
		bullet.height = 14;
		bullets.add(bullet);
		xwingFire.play(0.1f);
		lastBulletTime = TimeUtils.nanoTime();
	}
	
	private void fireWeaponAlternate() {
		Rectangle bullet = new Rectangle();
		bullet.x = xwing.x + 61;
		bullet.y = xwing.y + 30;
		bullet.width = 3;
		bullet.height = 14;
		bullets.add(bullet);
		xwingFire.play(0.1f);
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
		
		GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + tieFightersDestroyed);
		scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, Gdx.graphics.getHeight() - scoreLayout.height - 10);
		
		game.batch.end();


		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			xwing.x = touchPos.x - 64/2;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) xwing.x -= 400 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) xwing.x += 400 * Gdx.graphics.getDeltaTime();

		if(xwing.x < 0) xwing.x = 0;
		if(xwing.x > 800 - 64) xwing.x = 800 - 64;

		if(TimeUtils.nanoTime() - lastDropTime > 500000000) spawnTieFighter();
		
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if(TimeUtils.nanoTime() - lastBulletTime > 125000000) {
					if(shotsFired % 2 == 0) {
						fireWeapon();
						shotsFired++;
					} else {
						fireWeaponAlternate();
						shotsFired++;
					}
				}
			
		}

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
			if(bullet.y + 64 > 800) iter.remove();


		}
		
		for (Rectangle bullet: bullets) {
			for (Rectangle fighter: tieFighters) {
				if(fighter.overlaps(bullet)) {
					fighter.setY(3000000);
					bullet.setY(3000000);
                    tieFighterExplosion.play(0.05f);
                 
					tieFightersDestroyed += 100;
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
