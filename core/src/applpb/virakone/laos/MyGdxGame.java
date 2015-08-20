package applpb.virakone.laos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture wallpaperTexture, cloudTexture, rilakTexture;
	private OrthographicCamera objOrthographicCamera;
	private BitmapFont nameBitmapFont;
	private int xCloudAnInt, yCloudAnInt = 700;
	private boolean cloudABoolean = true;
	private Rectangle rilakRectangle;
	private Vector3 objVector3;
	private Sound rilakSound;

	@Override
	public void create () {
		batch = new SpriteBatch();

		objOrthographicCamera = new OrthographicCamera();
		objOrthographicCamera.setToOrtho(false, 1200, 800);

		//Setup Wallpaper
		wallpaperTexture = new Texture("wall.png");

		//Setup BitmapFont
		nameBitmapFont = new BitmapFont();
		nameBitmapFont.setColor(Color.RED);
		nameBitmapFont.setScale(4);

		//Setup Cloud
		cloudTexture = new Texture("cloud.png");

		//Setup Rilak
		rilakTexture = new Texture("114790096.png");

		//Setup RilakRectangle
		rilakRectangle = new Rectangle();
		rilakRectangle.x = 494;
		rilakRectangle.y = 80;
		rilakRectangle.width = 212;
		rilakRectangle.height = 245;

		//Setup Rilakkuma Sound
		rilakSound = Gdx.audio.newSound(Gdx.files.internal("pig.wav"));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Setup Screen
		objOrthographicCamera.update();
		batch.setProjectionMatrix(objOrthographicCamera.combined);

		// เอาไว้วาด object
		batch.begin();

		//Draw Wallpaper
		batch.draw(wallpaperTexture, 0 , 0);

		//Draw Cloud
		batch.draw(cloudTexture, xCloudAnInt, yCloudAnInt);

		//Drawable BitmapFont
		nameBitmapFont.draw(batch, "Coins Mario Game", 40, 760);

		//Draw Rilakkuma
		batch.draw(rilakTexture, rilakRectangle.x, rilakRectangle.y);



		batch.end();

		//Move Cloud
		moveCloud();

		//Active When Touch Screen
		activeTouchScreen();

	}

	private void activeTouchScreen() {

		if (Gdx.input.isTouched()) {

			//Sound Effect Rilakkuma
			rilakSound.play();

			objVector3 = new Vector3();
			objVector3.set(Gdx.input.getX(), Gdx.input.getY(), 0);

			if (objVector3.x < 600) {
				if (rilakRectangle.x < 0) {
					rilakRectangle.x = 0;
				} else {
					rilakRectangle.x -= 10;
				}
			} else {
				if (rilakRectangle.x > 1000) {
					rilakRectangle.x = 1000;
				} else {
					rilakRectangle.x += 10;
				}
			}

		} //If

	}

	private void moveCloud() {
		if (cloudABoolean) {
			if (xCloudAnInt < 1040) {
				xCloudAnInt += 100 * Gdx.graphics.getDeltaTime();
			} else {
				cloudABoolean = !cloudABoolean;
			}
		} else {
			if (xCloudAnInt >0) {
				xCloudAnInt -= 100 * Gdx.graphics.getDeltaTime();
			} else {
				cloudABoolean = !cloudABoolean;
			}
		}

	} //Move Cloud
}
