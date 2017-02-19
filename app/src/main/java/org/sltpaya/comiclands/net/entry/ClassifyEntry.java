package org.sltpaya.comiclands.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class ClassifyEntry {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("code_msg")
    @Expose
    private String codeMsg;
    @SerializedName("info")
    @Expose
    private Info info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public class Info {

        @SerializedName("adlistjson")
        @Expose
        private List<Adlistjson> adlistjson = null;

        public List<Adlistjson> getAdlistjson() {
            return adlistjson;
        }

        public void setAdlistjson(List<Adlistjson> adlistjson) {
            this.adlistjson = adlistjson;
        }

    }


    public class Adlistjson {

        @SerializedName("cornermark")
        @Expose
        private String cornermark;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("endtime")
        @Expose
        private String endtime;
        @SerializedName("height")
        @Expose
        private int height;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("imageurl")
        @Expose
        private String imageurl;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("starttime")
        @Expose
        private String starttime;
        @SerializedName("targetargument")
        @Expose
        private String targetargument;
        @SerializedName("targetmethod")
        @Expose
        private int targetmethod;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("width")
        @Expose
        private int width;

        public String getCornermark() {
            return cornermark;
        }

        public void setCornermark(String cornermark) {
            this.cornermark = cornermark;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getTargetargument() {
            return targetargument;
        }

        public void setTargetargument(String targetargument) {
            this.targetargument = targetargument;
        }

        public int getTargetmethod() {
            return targetmethod;
        }

        public void setTargetmethod(int targetmethod) {
            this.targetmethod = targetmethod;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

    }

}
