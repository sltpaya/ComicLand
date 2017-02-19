package org.sltpaya.comiclands.net.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecommendEntry {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("code_msg")
    @Expose
    private String codeMsg;
    @SerializedName("info")
    @Expose
    private List<Info> info = null;

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

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    //内部类
    public static class Info {

        @SerializedName("comicslist")
        @Expose
        private List<Comicslist> comicslist = null;

        @SerializedName("speciallist")
        @Expose
        private List<Speciallist> speciallist = null;
        @SerializedName("comicsviewtype")
        @Expose
        private int comicsviewtype;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("specialid")
        @Expose
        private int specialid;
        @SerializedName("vediolist")
        @Expose
        private List<Vediolist> vediolist = null;
        @SerializedName("vedioviewtype")
        @Expose
        private int vedioviewtype;
        @SerializedName("adgroupid")
        @Expose
        private int adgroupid;
        @SerializedName("adpositionlist")
        @Expose
        private List<Adpositionlist> adpositionlist = null;
        @SerializedName("adviewtype")
        @Expose
        private int adviewtype;
        @SerializedName("reviewtype")
        @Expose
        private int reviewtype;

        public List<Speciallist> getSpeciallist() {
            return speciallist;
        }

        public void setSpeciallist(List<Speciallist> speciallist) {
            this.speciallist = speciallist;
        }

        public List<Comicslist> getComicslist() {
            return comicslist;
        }

        public void setComicslist(List<Comicslist> comicslist) {
            this.comicslist = comicslist;
        }

        public int getComicsviewtype() {
            return comicsviewtype;
        }

        public void setComicsviewtype(int comicsviewtype) {
            this.comicsviewtype = comicsviewtype;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSpecialid() {
            return specialid;
        }

        public void setSpecialid(int specialid) {
            this.specialid = specialid;
        }

        public List<Vediolist> getVediolist() {
            return vediolist;
        }

        public void setVediolist(List<Vediolist> vediolist) {
            this.vediolist = vediolist;
        }

        public int getVedioviewtype() {
            return vedioviewtype;
        }

        public void setVedioviewtype(int vedioviewtype) {
            this.vedioviewtype = vedioviewtype;
        }

        public int getAdgroupid() {
            return adgroupid;
        }

        public void setAdgroupid(int adgroupid) {
            this.adgroupid = adgroupid;
        }

        public List<Adpositionlist> getAdpositionlist() {
            return adpositionlist;
        }

        public void setAdpositionlist(List<Adpositionlist> adpositionlist) {
            this.adpositionlist = adpositionlist;
        }

        public int getAdviewtype() {
            return adviewtype;
        }

        public void setAdviewtype(int adviewtype) {
            this.adviewtype = adviewtype;
        }

        public int getReviewtype() {
            return reviewtype;
        }

        public void setReviewtype(int reviewtype) {
            this.reviewtype = reviewtype;
        }
    }

    //内部类
    public static class Vediolist {

        @SerializedName("brief")
        @Expose
        private String brief;

        @SerializedName("coverhorizontal")
        @Expose
        private String coverhorizontal;

        @SerializedName("coverurl")
        @Expose
        private String coverurl;

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("playcount")
        @Expose
        private String playcount;

        @SerializedName("progresstype")
        @Expose
        private int progresstype;

        @SerializedName("totalepisode")
        @Expose
        private int totalepisode;

        @SerializedName("updateepisode")
        @Expose
        private int updateepisode;


        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaycount() {
            return playcount;
        }

        public void setPlaycount(String playcount) {
            this.playcount = playcount;
        }

        public int getProgresstype() {
            return progresstype;
        }

        public void setProgresstype(int progresstype) {
            this.progresstype = progresstype;
        }

        public int getTotalepisode() {
            return totalepisode;
        }

        public void setTotalepisode(int totalepisode) {
            this.totalepisode = totalepisode;
        }

        public int getUpdateepisode() {
            return updateepisode;
        }

        public void setUpdateepisode(int updateepisode) {
            this.updateepisode = updateepisode;
        }

    }

    //内部类
    public class Comicslist {

        @SerializedName("authoruserid")
        @Expose
        private int authoruserid;

        @SerializedName("bestdiscuss")
        @Expose
        private String bestdiscuss;

        @SerializedName("bigbook_author")
        @Expose
        private String bigbookAuthor;

        @SerializedName("bigbook_id")
        @Expose
        private int bigbookId;

        @SerializedName("bigbook_name")
        @Expose
        private String bigbookName;

        @SerializedName("bigbookview")
        @Expose
        private int bigbookview;

        @SerializedName("bigcoverurl")
        @Expose
        private String bigcoverurl;

        @SerializedName("brief")
        @Expose
        private String brief;

        @SerializedName("collectcount")
        @Expose
        private int collectcount;

        @SerializedName("coverurl")
        @Expose
        private String coverurl;

        @SerializedName("discusscount")
        @Expose
        private int discusscount;

        @SerializedName("extension")
        @Expose
        private String extension;

        @SerializedName("extensionimage")
        @Expose
        private String extensionimage;

        @SerializedName("key_name")
        @Expose
        private String keyName;

        @SerializedName("lastpartname")
        @Expose
        private String lastpartname;

        @SerializedName("progresstype")
        @Expose
        private int progresstype;

        @SerializedName("readernum")
        @Expose
        private int readernum;

        @SerializedName("recommendurl")
        @Expose
        private String recommendurl;

        @SerializedName("subject_name")
        @Expose
        private String subjectName;

        @SerializedName("subjectid")
        @Expose
        private int subjectid;

        @SerializedName("superscript")
        @Expose
        private String superscript;

        @SerializedName("updatedate")
        @Expose
        private String updatedate;

        public int getAuthoruserid() {
            return authoruserid;
        }

        public void setAuthoruserid(int authoruserid) {
            this.authoruserid = authoruserid;
        }

        public String getBestdiscuss() {
            return bestdiscuss;
        }

        public void setBestdiscuss(String bestdiscuss) {
            this.bestdiscuss = bestdiscuss;
        }

        public String getBigbookAuthor() {
            return bigbookAuthor;
        }

        public void setBigbookAuthor(String bigbookAuthor) {
            this.bigbookAuthor = bigbookAuthor;
        }

        public int getBigbookId() {
            return bigbookId;
        }

        public void setBigbookId(int bigbookId) {
            this.bigbookId = bigbookId;
        }

        public String getBigbookName() {
            return bigbookName;
        }

        public void setBigbookName(String bigbookName) {
            this.bigbookName = bigbookName;
        }

        public int getBigbookview() {
            return bigbookview;
        }

        public void setBigbookview(int bigbookview) {
            this.bigbookview = bigbookview;
        }

        public String getBigcoverurl() {
            return bigcoverurl;
        }

        public void setBigcoverurl(String bigcoverurl) {
            this.bigcoverurl = bigcoverurl;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

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

        public int getDiscusscount() {
            return discusscount;
        }

        public void setDiscusscount(int discusscount) {
            this.discusscount = discusscount;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getExtensionimage() {
            return extensionimage;
        }

        public void setExtensionimage(String extensionimage) {
            this.extensionimage = extensionimage;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getLastpartname() {
            return lastpartname;
        }

        public void setLastpartname(String lastpartname) {
            this.lastpartname = lastpartname;
        }

        public int getProgresstype() {
            return progresstype;
        }

        public void setProgresstype(int progresstype) {
            this.progresstype = progresstype;
        }

        public int getReadernum() {
            return readernum;
        }

        public void setReadernum(int readernum) {
            this.readernum = readernum;
        }

        public String getRecommendurl() {
            return recommendurl;
        }

        public void setRecommendurl(String recommendurl) {
            this.recommendurl = recommendurl;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public int getSubjectid() {
            return subjectid;
        }

        public void setSubjectid(int subjectid) {
            this.subjectid = subjectid;
        }

        public String getSuperscript() {
            return superscript;
        }

        public void setSuperscript(String superscript) {
            this.superscript = superscript;
        }

        public String getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(String updatedate) {
            this.updatedate = updatedate;
        }

    }

    //内部类
    public class Adpositionlist {

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

        @SerializedName("noticeuserid")
        @Expose
        private int noticeuserid;

        @SerializedName("starttime")
        @Expose
        private String starttime;

        @SerializedName("superscript")
        @Expose
        private String superscript;

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

        public int getNoticeuserid() {
            return noticeuserid;
        }

        public void setNoticeuserid(int noticeuserid) {
            this.noticeuserid = noticeuserid;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getSuperscript() {
            return superscript;
        }

        public void setSuperscript(String superscript) {
            this.superscript = superscript;
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

    public class Speciallist {

        @SerializedName("coverurl2")
        @Expose
        private String coverurl2;

        @SerializedName("position")
        @Expose
        private int position;

        @SerializedName("sdid")
        @Expose
        private int sdid;

        @SerializedName("coverurl")
        @Expose
        private String coverurl;

        @SerializedName("profileimageurl")
        @Expose
        private String profileimageurl;

        @SerializedName("collectcount")
        @Expose
        private int collectcount;

        @SerializedName("specialid")
        @Expose
        private int specialid;

        @SerializedName("screenname")
        @Expose
        private String screenname;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        public String getCoverurl2() {
            return coverurl2;
        }

        public void setCoverurl2(String coverurl2) {
            this.coverurl2 = coverurl2;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getSdid() {
            return sdid;
        }

        public void setSdid(int sdid) {
            this.sdid = sdid;
        }

        public String getCoverurl() {
            return coverurl;
        }

        public void setCoverurl(String coverurl) {
            this.coverurl = coverurl;
        }

        public String getProfileimageurl() {
            return profileimageurl;
        }

        public void setProfileimageurl(String profileimageurl) {
            this.profileimageurl = profileimageurl;
        }

        public int getCollectcount() {
            return collectcount;
        }

        public void setCollectcount(int collectcount) {
            this.collectcount = collectcount;
        }

        public int getSpecialid() {
            return specialid;
        }

        public void setSpecialid(int specialid) {
            this.specialid = specialid;
        }

        public String getScreenname() {
            return screenname;
        }

        public void setScreenname(String screenname) {
            this.screenname = screenname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
