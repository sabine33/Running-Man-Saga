import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sabinApps.mylibgdx.MenuScreen;


/**
 * Created by programmer on 2/8/2015.
 */
public class Desktop  {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Running Man";
        cfg.width = 800;
        cfg.height = 480;
        cfg.useGL20=false;
        cfg.useCPUSynch=true;
        new LwjglApplication(new MenuScreen(), cfg);
    }
}
