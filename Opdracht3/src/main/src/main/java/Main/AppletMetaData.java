package Main;

/**
 * Created by Kraaijeveld on 2-6-2016.
 */

public class AppletMetaData
{
    private float cameraAngle;
    private float currentScale;

    private float currentWaterHeight = 0;
    private float waterHeightIncreasePerHour;
    private float hoursPassed = 0;

    private float[] zoomLevels = new float[]{1f, 2f, 3f, 4f};
    private int currentZoomLevelIndex = 0;

    private boolean paused = false;


    public float getCameraAngle() { return cameraAngle;  }

    public float getCurrentScale() { return currentScale; }


    public float getWaterHeightIncreasePerHour() { return waterHeightIncreasePerHour; }

    public float getCurrentWaterHeight() { return currentWaterHeight; }

    public float getHoursPassed() { return hoursPassed; }



    public float[] getZoomLevels() { return zoomLevels; }

    public int getCurrentZoomLevelIndex() { return currentZoomLevelIndex; }

    public boolean isPaused() { return paused; }




    public void setCameraAngle(float cameraAngle) { this.cameraAngle = cameraAngle; }

    public void setCurrentScale(float currentScale) { this.currentScale = currentScale; }



    public void setCurrentWaterHeight(float currentWaterHeight) { this.currentWaterHeight = currentWaterHeight; }

    public void setWaterHeightIncreasePerHour(float WaterHeightIncreasePerHour) { this.waterHeightIncreasePerHour = WaterHeightIncreasePerHour; }

    public void setHoursPassed(float hoursPassed) { this.hoursPassed = hoursPassed; }



    public void setZoomLevels(float[] zoomLevels) { this.zoomLevels = zoomLevels;  }

    public void setCurrentZoomLevelIndex(int currentZoomLevelIndex) { this.currentZoomLevelIndex = currentZoomLevelIndex; }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
