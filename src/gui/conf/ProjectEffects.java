package gui.conf;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Created by ajay on 4/22/2016.
 */
public class ProjectEffects {
    public static DropShadow dropShadow(Color color){
        DropShadow shadow = new DropShadow();
        shadow.setColor(color);
        shadow.setOffsetX(2);
        shadow.setOffsetY(3);
        return shadow;
    }
}
