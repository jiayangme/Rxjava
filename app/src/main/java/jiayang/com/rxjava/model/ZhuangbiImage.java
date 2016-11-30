package jiayang.com.rxjava.model;

/**
 * Created by xiangkai on 2016/11/29.
 */
public class ZhuangbiImage {

    /**
     * id : 1652
     * name : null
     * description : 你不要脸的样子真可爱
     * disk : qiniu
     * path : i/2016-01-26-ecbb0a587442f5bdbeadfc4a65a56a30.
     * size : 10100
     * user_id : 1
     * created_at : 2016-01-26 09:59:49
     * updated_at : 2016-01-26 10:15:57
     * uploadable_id : null
     * uploadable_type : null
     * image_url : http://zhuangbi.idagou.com/i/2016-01-26-ecbb0a587442f5bdbeadfc4a65a56a30.
     */

    private int id;
    private Object name;
    private String description;
    private String disk;
    private String path;
    private int size;
    private int user_id;
    private String created_at;
    private String updated_at;
    private Object uploadable_id;
    private Object uploadable_type;
    private String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getUploadable_id() {
        return uploadable_id;
    }

    public void setUploadable_id(Object uploadable_id) {
        this.uploadable_id = uploadable_id;
    }

    public Object getUploadable_type() {
        return uploadable_type;
    }

    public void setUploadable_type(Object uploadable_type) {
        this.uploadable_type = uploadable_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
