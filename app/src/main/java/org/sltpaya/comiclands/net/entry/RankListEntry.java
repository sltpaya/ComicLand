package org.sltpaya.comiclands.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class RankListEntry {

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

        @SerializedName("specials")
        @Expose
        private List<Special> specials = null;

        public List<Special> getSpecials() {
            return specials;
        }

        public void setSpecials(List<Special> specials) {
            this.specials = specials;
        }

    }

    public class Special {

        @SerializedName("collectcount")
        @Expose
        private int collectcount;
        @SerializedName("coverurl")
        @Expose
        private String coverurl;
        @SerializedName("coverurl2")
        @Expose
        private String coverurl2;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("position")
        @Expose
        private int position;
        @SerializedName("sdid")
        @Expose
        private String sdid;
        @SerializedName("specialid")
        @Expose
        private int specialid;

        public int getCollectcount() {
            return collectcount;
        }

        public void setCollectcount(int collectcount) {
            this.collectcount = collectcount;
        }

        public String getCoverurl() {
            return coverurl;
        }

        public void setCoverurl(String coverurl) {
            this.coverurl = coverurl;
        }

        public String getCoverurl2() {
            return coverurl2;
        }

        public void setCoverurl2(String coverurl2) {
            this.coverurl2 = coverurl2;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getSdid() {
            return sdid;
        }

        public void setSdid(String sdid) {
            this.sdid = sdid;
        }

        public int getSpecialid() {
            return specialid;
        }

        public void setSpecialid(int specialid) {
            this.specialid = specialid;
        }

    }
}
