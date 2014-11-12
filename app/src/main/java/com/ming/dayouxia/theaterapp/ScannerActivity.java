package com.ming.dayouxia.theaterapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.tools.io.AssetsManager;
import com.ming.dayouxia.theaterapp.xmlwriter.Writer;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import org.w3c.dom.Document;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ScannerActivity extends ARViewActivity
{

   private boolean dialogShown = false;

    // how many models to be detected.
    private int itemsToDetect = 3;
    private boolean firstModel = false;
    private boolean secondModel = false;
    private boolean thirdModel = false;
    private boolean shouldRunHttpRequest = true;
    private ProgressBar mProgressBar;
    private ShimmerTextView mShimmerTextView;

    private MetaioSDKCallbackHandler mCallbackHandler;

    public enum QuestionTypes{
        SINGLE,
        MULTIPLE,
        ALL
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        super.onCreate(savedInstanceState);
        //mImagePlans = new ArrayList<IGeometry>();
        mCallbackHandler = new MetaioSDKCallbackHandler();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mCallbackHandler.delete();
        mCallbackHandler = null;
    }

    @Override
    protected int getGUILayout()
    {
        return R.layout.tutorial_content_types;
    }



    private void clearAllModelHistory(){
        firstModel = false;
        secondModel = false;
        thirdModel = false;
        Log.v("yoloswag", "" + firstModel + secondModel + thirdModel);
    }


    private void popupDialog(){

        if(!dialogShown){
            dialogShown = true;
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    // create an Asynctask to grab data from database, then pop up the dialog accordingly

                    AlertDialog.Builder builder = new AlertDialog.Builder(ScannerActivity.this);
                    builder.setTitle("All images are detected!")
                            .setMessage("All images are detected!")
                            .create()
                            .show();


                }
            });

        }

    }

    @Override
    public void onDrawFrame() {
        // TODO Auto-generated method stub
        super.onDrawFrame();
    }


    @Override
    protected void onGeometryTouched(IGeometry geometry) {

    }

    private void loadAllGeometrieds(){
        // Loading image geometry
        final String imagePath = AssetsManager.getAssetPath(getApplicationContext(), "TutorialContentTypes/Assets/frame.png");

        // note coordinate system id is ONE based.
        if (imagePath != null) {
            for (int i = 1; i <= 3; i++) {
                IGeometry geometry = metaioSDK.createGeometryFromImage(imagePath);
                if (geometry != null) {
                    geometry.setScale(3.0f);
                    geometry.setCoordinateSystemID(i);
                } else {
                    MetaioDebug.log(Log.ERROR, "Error loading geometry: " + imagePath);
                }
            }
        }
    }



    public File GetConfigurationFile(Context context, String subdir) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), subdir);
        if (!file.mkdirs()) {
            //Log.e(LOG_TAG, "Directory not created");
        }


        String str = file.toString();
        Writer.Write(file.toString() + "/haha.xml");
        File[] list = file.listFiles();


        Document doc = null;
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(list[0]);
        } catch (Exception e){

        }

        return list[0];
    }

    @Override
    protected void loadContents()
    {
        try
        {
            // Load desired tracking data for planar marker tracking
            final String trackingConfigFile = AssetsManager.getAssetPath(getApplicationContext(), "TutorialContentTypes/Assets/TrackingData_MarkerlessFast.xml");
            final String fileName = GetConfigurationFile(this, "config").toString();

            File file = new File(fileName);
            String absolutePath = file.getAbsolutePath();
            final boolean result = metaioSDK.setTrackingConfiguration(file.getAbsolutePath());
            MetaioDebug.log("Tracking data loaded: " + result);
            loadAllGeometrieds();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            MetaioDebug.log(Log.ERROR, "loadContents failed, see stack trace");
        }

        // setup shimmer
        mShimmerTextView = (ShimmerTextView)findViewById(R.id.shimmer_text);
        mShimmerTextView.setText(itemsToDetect+" items to be detected");
        Shimmer shimmer = new Shimmer();
        shimmer.setDuration(3000)
               .start(mShimmerTextView);
    }




    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler()
    {
        return mCallbackHandler;
    }

    final private class MetaioSDKCallbackHandler extends IMetaioSDKCallback
    {
        @Override
        public void onSDKReady()
        {
            // show GUI after SDK is ready
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    mGUIView.setVisibility(View.VISIBLE);

                }
            });
        }


        @Override
        public void onTrackingEvent(final TrackingValuesVector trackingValues)
        {
            super.onTrackingEvent(trackingValues);

            for(int i = 0; i < trackingValues.size(); i++){

                if(trackingValues.get(i).isTrackingState()){

                    switch (trackingValues.get(i).getCoordinateSystemID()) {
                        case 1:
                            firstModel = true;
                            break;
                        case 2:
                            secondModel = true;
                            break;
                        case 3:
                            thirdModel = true;
                            break;

                        default:
                            break;
                    }
                    int numberOfModelsDetected = 0;
                    if(firstModel) numberOfModelsDetected++;
                    if(secondModel) numberOfModelsDetected++;
                    if(thirdModel) numberOfModelsDetected++;
                    final int toDetect = 3-numberOfModelsDetected;


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mShimmerTextView.setText(toDetect + " items to be detected");

                        }
                    });
                }
            }

            if(firstModel && secondModel && thirdModel){
                if(shouldRunHttpRequest){
                    popupDialog();
                }


            }
        }
    }
}
