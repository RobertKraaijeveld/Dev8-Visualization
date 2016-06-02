package Main;

/**
 * Created by Kraaijeveld on 2-6-2016.
 */
public class AppletMetaData
{
    private float cameraAngle;
    private float currentScale;
    private boolean paused;

    public float getCameraAngle() {
        return cameraAngle;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public boolean isPaused() {
        return paused;
    }


    public void setCameraAngle(float cameraAngle) {
        this.cameraAngle = cameraAngle;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
