package beans;

/**
 * 设备费
 */
public class Equipment {
    private Integer id;
    private String name;
    private String type;
    private String img;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Equipment(String name, String type, String img) {
        this.name = name;
        this.type = type;
        this.img = img;
    }

    public Equipment() {
    }
}
