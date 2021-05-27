package com.codingdojo.starwarsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



//THIS IS OUR MAIN SPOT FOR GAME CODE
public class StarWarsGame extends Game {


//Take this project one step at a time:
	//1. X Wing instead of bucket(tutorial), tie fighters instead of drops.
	//2. X Wing shoots. Consider where the xwing is, and different direction than tie fighter direction.
	//3. Sides of trench(closer boundaries) turrets generated on sides. How do they shoot?
	//4. After x amount of time, does exhaust port appear?
	//5. If time, how do we escalate difficulty? More tie's generated over time? 
	
	
	public BitmapFont font;
	public SpriteBatch batch;
	

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new StartScreen(this));
	}
	

	public void render() {
		super.render();
	}
	
	
	public void dispose() {
		batch.dispose();
		font.dispose();
		
	}
	
	
}
