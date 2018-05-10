package win.whitelife.base.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author wuzefeng
 * 2018/5/9
 */
public class Voice extends RealmObject{

    private String name;      //名字
    private String filePath;  //文件本地地址
    private String url;       //文件网络地址
    private String title;     //文件标题
    private String description;
    private long createTime;     //创建时间
    private long updateTime;
    @PrimaryKey
    private long id;            //id
    private long duration;      //语音持续时间


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
