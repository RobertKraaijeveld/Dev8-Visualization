package Main;

/**
 * Created by Kraaijeveld on 2-6-2016.
 */
public class AppletMetaData
{
    private float cameraAngle;
    private float currentScale;
    private float currentWaterHeight = 0;
    private float hoursPassed = 0;
    private boolean paused = false;


    public float getCameraAngle() {
        return cameraAngle;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public float getCurrentWaterHeight() { return currentWaterHeight; }

    public float getHoursPassed() { return hoursPassed; }

    public boolean isPaused() {
        return paused;
    }


    public void setCameraAngle(float cameraAngle) { this.cameraAngle = cameraAngle; }

    public void setCurrentScale(float currentScale) { this.currentScale = currentScale; }

    public void setCurrentWaterHeight(float currentWaterHeight) { this.currentWaterHeight = currentWaterHeight; }

    public void setHoursPassed(float hoursPassed) { this.hoursPassed = hoursPassed; }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
