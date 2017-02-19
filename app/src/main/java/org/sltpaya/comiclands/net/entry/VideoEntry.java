package org.sltpaya.comiclands.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class VideoEntry {

    @SerializedName("code_msg")
    @Expose
    private String codeMsg;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("info")
    @Expose
    private List<Info> info = null;

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public class Info {

        @SerializedName("vediolist")
        @Expose
        private List<Vediolist> vediolist = null;
        @SerializedName("specialviewtype")
        @Expose
        private String specialviewtype;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private int id;

        public List<Vediolist> getVediolist() {
            return vediolist;
        }

        public void setVediolist(List<Vediolist> vediolist) {
            this.vediolist = vediolist;
        }

        public String getSpecialviewtype() {
            return specialviewtype;
        }

        public void setSpecialviewtype(String specialviewtype) {
            this.specialviewtype = specialviewtype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class Vediolist {

        @SerializedName("totalepisode")
        @Expose
        private int totalepisode;
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("playcount")
        @Expose
        private String playcount;
        @SerializedName("updateepisode")
        @Expose
        private int updateepisode;
        @SerializedName("coverhorizontal")
        @Expose
        private String coverhorizontal;
        @SerializedName("coverurl")
        @Expose
        private String coverurl;
        @SerializedName("brief")
        @Expose
        private String brief;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("progresstype")
        @Expose
        private int progresstype;

        public int getTotalepisode() {
            return totalepisode;
        }

        public void setTotalepisode(int totalepisode) {
            this.totalepisode = totalepisode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlaycount() {
            return playcount;
        }

        public void setPlaycount(String playcount) {
            this.playcount = playcount;
        }

        public int getUpdateepisode() {
            return updateepisode;
        }

        public void setUpdateepisode(int updateepisode) {
            this.updateepisode = updateepisode;
        }

        public String getCoverhorizontal() {
            return coverhorizontal;
        }

        public void setCoverhorizontal(String coverhorizontal) {
            this.coverhorizontal = coverhorizontal;
        }

        public String getCoverurl() {
            return coverurl;
        }

        public void setCoverurl(String coverurl) {
            this.coverurl = coverurl;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProgresstype() {
            return progresstype;
        }

        public void setProgresstype(int progresstype) {
            this.progresstype = progresstype;
        }

    }

}
