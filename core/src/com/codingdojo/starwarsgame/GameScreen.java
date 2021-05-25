package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen{
	final StarWarsGame game;
	
	
	OrthographicCamera camera;
	Rectangle bucket;
	Array<Rectangle> tieFighters;
	long lastDropTime;
	int tieFightersDestroyed;
	
	public GameScreen(final StarWarsGame game) {
		this.game = game;
		
		//Change to tie fighters
//		dropImage = new Texture(Gdx.files.internal("drop.png"));
//		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		
		//find audio for this
//		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
//		rainMusic.setLooping(true);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		//REPLACE WITH LUKE'S XWING
//		bucket = new Rectangle();
//		bucket.x= 800 / 2 - 64 / 2;
//		bucket.y = 20;
//		bucket.width = 64;
//		bucket.height = 64;
		
		tieFighters = new Array<Rectangle>();
		spawnTieFighter();
	}
	
	private void spawnTieFighter() {
//		REFERENCE CODE FOR WHAT IS USED ON THE RAINDROPS
//		Rectangle raindrop = new Rectangle();
//		raindrop.x = MathUtils.random(0, 800-64);
//		raindrop.y = 480;
//		raindrop.width = 64;
//		raindrop.height = 64;
//		raindrops.add(raindrop);
//		lastDropTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void render(float delta) {
		//I TOOK OUT TUTORIAL CODE FROM HERE	
		
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
		//WE HAVE TO CALL THIS MANUALLY FROM SOMEWHERE. NOT SURE HOW
	}

}
