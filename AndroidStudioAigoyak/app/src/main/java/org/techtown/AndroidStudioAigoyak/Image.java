package org.techtown.AndroidStudioAigoyak;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Image {

    public int code;
    public String shape;
    public String colorF;
    public String colorB;
    public String lineF;
    public String lineB;
    public String form;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Image() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Image(int code, String shape, String colorF, String colorB, String lineF, String lineB, String form) {
        this.code = code;
        this.shape = shape;
        this.colorF = colorF;
        this.colorB = colorB;
        this.lineF = lineF;
        this.lineB = lineB;
        this.form = form;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("shape", shape);
        result.put("colorF", colorF);
        result.put("colorB", colorB);
        result.put("lineF", lineF);
        result.put("lineB", lineB);
        result.put("form",form);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }
}
