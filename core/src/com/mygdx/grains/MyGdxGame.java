package com.mygdx.grains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
	private float ballSize = 1;
	private SpriteBatch batch;
	private Walls Texture;
	private Walls setts;
	private Walls Count;
	private Walls CountValue;
	private Walls Bounds;
	private Walls BoundsValue;
	private Walls Gravity;
	private Walls GravityValue;
	private Walls Size;
	private Walls SizeValue;
	private Walls Friction;
	private Walls FrictionValue;
	private Walls Speed;
	private Walls SpeedValue;
	private boolean rend;

	private static float WIDTH_K;
	private static float HEIGHT_K;


	@Override
	public void create () {
		WIDTH_K = Gdx.graphics.getWidth() / 1240f;
		HEIGHT_K = Gdx.graphics.getHeight() / 720f;

		batch = new SpriteBatch();
		Balls.MakeAllBalls(100);
		Balls.config(HEIGHT_K, true, false);
		setts =         new Walls(new Vector2(0, 0), 0.5f * HEIGHT_K, 0.5f * HEIGHT_K, new Texture("settgear.png"));
		setts.touchble = true;
		Count =         new Walls(new Vector2(200 * WIDTH_K, 600 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("balls.png"));
		CountValue =    new Walls(new Vector2(250 * WIDTH_K, 500 * HEIGHT_K), 1,1, new Texture("100.png"));
		CountValue.id = 100;
		Size =          new Walls(new Vector2(200 * WIDTH_K, 350 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("size.png"));
		SizeValue =     new Walls(new Vector2(200 * WIDTH_K, 250 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("x1.png"));
		Bounds =        new Walls(new Vector2(500 * WIDTH_K, 600 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("bounds.png"));
		BoundsValue =   new Walls(new Vector2(500 * WIDTH_K, 500 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("on.png"));
		Gravity =       new Walls(new Vector2(800 * WIDTH_K, 600 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("gravity.png"));
		GravityValue =  new Walls(new Vector2(800 * WIDTH_K, 500 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("off.png"));
		Friction =      new Walls(new Vector2(500 * WIDTH_K, 350 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("friction.png"));
		FrictionValue = new Walls(new Vector2(500 * WIDTH_K, 250 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("none.png"));
		Speed =         new Walls(new Vector2(800 * WIDTH_K, 350 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("speed.png"));
		SpeedValue =    new Walls(new Vector2(800 * WIDTH_K, 250 * HEIGHT_K), 1 * HEIGHT_K,1 * HEIGHT_K, new Texture("low.png"));
		SpeedValue.id = 2;
		Texture =       new Walls(new Vector2(1000 * WIDTH_K, 400 * HEIGHT_K), 5 * HEIGHT_K,5 * HEIGHT_K, new Texture("bullet_t_3.png"));
		rend = true;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		touch();
		batch.begin();
		setts.update();
		setts.draw(batch);
		if (rend) {
			Balls.updateAll(Walls.wallsMap, Gdx.graphics.getDeltaTime());
			Balls.drawAll(batch, ballSize);
		} else {
			Count.draw(batch);
			CountValue.draw(batch);
			Size.draw(batch);
			SizeValue.draw(batch);
			Bounds.draw(batch);
			BoundsValue.draw(batch);
			Gravity.draw(batch);
			GravityValue.draw(batch);
			Friction.draw(batch);
			FrictionValue.draw(batch);
			Speed.draw(batch);
			SpeedValue.draw(batch);
			Texture.draw(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Balls.clearAllBalls();
	}

	public void touch() {
		if (Control.isClicked() && setts.overlaps(Control.TouchPoint())) {
			rend = !rend;
		}
		if (rend) {

		} else {
			if (Control.isClicked() && Texture.overlaps(Control.TouchPoint())) {
				if (Texture.id == 1) {
					Texture.texture = new Texture("bullet_t_2.png");
					Texture.id = 0;
					Balls.texture = new Texture("bullet_t_2.png");
				}  else {
					Texture.texture = new Texture("bullet_t_3.png");
					Texture.id = 1;
					Balls.texture = new Texture("bullet_t_3.png");
				}
			}
			if (Control.isClicked() && CountValue.overlaps(Control.TouchPoint())) {
				if (CountValue.id == 100) {
					CountValue.texture = new Texture("1000.png");
					CountValue.id *= 10;
					Balls.clearAllBalls();
					Balls.MakeAllBalls(CountValue.id);
				} else if (CountValue.id == 1000) {
					CountValue.texture = new Texture("5000.png");
					CountValue.id *= 5;
					Balls.clearAllBalls();
					Balls.MakeAllBalls(CountValue.id);
				}else if (CountValue.id == 5000) {
					CountValue.texture = new Texture("10000.png");
					CountValue.id *= 2;
					Balls.clearAllBalls();
					Balls.MakeAllBalls(CountValue.id);
				}else if (CountValue.id == 10000) {
					CountValue.texture = new Texture("100.png");
					CountValue.id /= 100;
					Balls.clearAllBalls();
					Balls.MakeAllBalls(CountValue.id);
				}
			}

			if (Control.isClicked() && BoundsValue.overlaps(Control.TouchPoint())) {
				if (BoundsValue.id == 1) {
					BoundsValue.texture = new Texture("off.png");
					BoundsValue.id = 0;
					Balls.isBoarders = false;
				} else {
					BoundsValue.id = 1;
					BoundsValue.texture = new Texture("on.png");
					Balls.isBoarders = true;
				}
			}
			if (Control.isClicked() && GravityValue.overlaps(Control.TouchPoint())) {
				if (GravityValue.id == 1) {
					GravityValue.texture = new Texture("on.png");
					GravityValue.id = 0;
					Balls.isGravity = true;
				} else {
					GravityValue.id = 1;
					GravityValue.texture = new Texture("off.png");
					Balls.isGravity = false;
				}
			}
			if (Control.isClicked() && FrictionValue.overlaps(Control.TouchPoint())) {
				if (FrictionValue.id == 1) {
					FrictionValue.texture = new Texture("low.png");
					FrictionValue.id = 2;
					Balls.Friction = 0.9995f;
				} else if (FrictionValue.id == 2) {
					FrictionValue.texture = new Texture("medium.png");
					FrictionValue.id = 3;

					Balls.Friction = 0.995f;
				}else if (FrictionValue.id == 3) {
					FrictionValue.texture = new Texture("high.png");
					FrictionValue.id = 4;

					Balls.Friction = 0.95f;
				}else if (FrictionValue.id == 4) {
					FrictionValue.texture = new Texture("none.png");
					FrictionValue.id = 1;

					Balls.Friction = 1;
				}
			}
			if (Control.isClicked() && SpeedValue.overlaps(Control.TouchPoint())) {
				if (SpeedValue.id == 1) {
					SpeedValue.texture = new Texture("low.png");
					SpeedValue.id = 2;
					Balls.vel_K2 = 7;
				} else if (SpeedValue.id == 2) {
					SpeedValue.texture = new Texture("medium.png");
					SpeedValue.id = 3;
					Balls.vel_K2 = 13;
				}else if (SpeedValue.id == 3) {
					SpeedValue.texture = new Texture("high.png");
					SpeedValue.id = 4;
					Balls.vel_K2 = 20;
				}else if (SpeedValue.id == 4) {
					SpeedValue.texture = new Texture("none.png");
					SpeedValue.id = 1;
					Balls.vel_K2 = 0;
				}
			}
			if (Control.isClicked() && SizeValue.overlaps(Control.TouchPoint())) {
				if (SizeValue.id == 1) {
					SizeValue.texture = new Texture("x2.png");
					SizeValue.id = 2;
					ballSize = 2;
				} else if (SizeValue.id == 2) {
					SizeValue.texture = new Texture("x3.png");
					SizeValue.id = 3;
					ballSize = 3;
				} else if (SizeValue.id == 3) {
					SizeValue.texture = new Texture("x0.5.png");
					SizeValue.id = 4;
					ballSize = 0.5f;
				} else if (SizeValue.id == 4) {
					SizeValue.texture = new Texture("x1.png");
					SizeValue.id = 1;
					ballSize = 1;
				}
			}
		}
	}
}
