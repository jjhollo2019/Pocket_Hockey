/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight School
 */

package com.example.flight_training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * This class serves as an interface between the rest of the application and the data stored for
 * highscores.
 */
public class HighScoreDatabase {
    private ArrayList<HighScoreEntry> scores;

    /**
     * @param context current context of the application
     * @post creates a new database containing values for the 3 highscores
     */
    public HighScoreDatabase(Context context){
        scores = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            scores.add(new HighScoreEntry());
            scores.get(i).fetchData(context, i + 1);
        }
    }

    /**
     * @return scores
     * @post returns scores
     */
    public ArrayList<HighScoreEntry> getHighScores(){
        return scores;
    }

    /**
     * @pre 0 < level
     * @param level highest level player reached during game
     * @return true if new level reached should be set as a highscore, false otherwise
     */
    public boolean checkHighScores(Context context, int level){
        boolean updateScores = false;

        for (int i = 0; i < 3; i++){
            scores.get(i).fetchData(context, i + 1);
            if (level > scores.get(i).getScore()) updateScores = true;
        }

        return updateScores;
    }

    /**
     * @param context current context of the application
     * @param level user reached by the end of their game
     * @param initials entered by the user to be stored with their score
     * @post stores the top 3 highscores in order {initials\n, level\n, image_file_prefix}
     */
    public void setNewHighScore(Context context, Integer level, String initials){
        for (int i = 0; i < 3; i++){
            scores.get(i).fetchData(context, i + 1);
        }

        File directory = new File(context.getFilesDir(), "highscores");
        // Create a directory to hold the highscores
        if (!directory.exists()) directory.mkdir();

        int newRank;
        // You don't need #3 anymore
        File oldfile = new File(directory, "highscore3.txt");
        oldfile.delete();
        // Check against #1
        if (level > scores.get(0).getScore()){
            newRank = 1;

            // Rename #2 to #3
            oldfile = new File(directory, "highscore2.txt");
            File newfile = new File(directory, "highscore3.txt");
            oldfile.renameTo(newfile);

            // Rename #1 to #2
            oldfile = new File(directory, "highscore1.txt");
            newfile = new File(directory, "highscore2.txt");
            oldfile.renameTo(newfile);
        }
        // Check against #2
        else if (level > scores.get(1).getScore()){
            newRank = 2;

            // Rename #2 to #3
            oldfile = new File(directory, "highscore2.txt");
            File newfile = new File(directory, "highscore3.txt");
            oldfile.renameTo(newfile);
        }
        // Replace #3
        else{
            newRank = 3;
        }

        dispatchTakePictureIntent(context);

        try{
            // Write new data to its respective file
            File outFile = new File(directory, "highscore" + newRank + ".txt");
            FileWriter writer = new FileWriter(outFile);
            String addNewLine = initials + "\n";
            // Store initials
            writer.append(addNewLine);
            addNewLine = level.toString() + "\n";
            // Store score
            writer.append(addNewLine);
            // Store corresponding image file prefix
            writer.append(imageFileName);
            writer.flush();
            writer.close();
            Log.e("HighScoreDatabase", "File written");
        }
        catch(IOException e){
            Log.e("HighScoreDatabase", "Exception: " + e.getMessage());
        }
    }

    private String currentPhotoPath;
    private String imageFileName;

    /**
     * @param context current context of the application
     * @return newly created file to store an image in
     * @post returns a new file with unique date-/time-stamp to store an image in
     * @throws IOException in the case the file creation fails
     */
    private File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir + "/" + imageFileName + ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * @param context current context of the application
     * @post launches camera app to acquire picture of user
     */
    private void dispatchTakePictureIntent(Context context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(context);
            }
            catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("Camera Launcher", "Failed to create image file: " + ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                try{
                    ((Activity) context).startActivityForResult(takePictureIntent, 1);
                    Log.e("Camera Launcher", "Past startActivityForResult()");
                }
                catch(Exception e){
                    Log.e("Camera Launcher", "Failed to launch camera: " + e.getMessage());
                }
            }
        }
    }
}
