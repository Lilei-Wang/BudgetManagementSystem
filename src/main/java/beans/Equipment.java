package beans;

/**
 * 设备费
 */
public class Equipment extends Item{

    private String type;
    private String img;

    @Override
    public String toString() {
        return "Equipment{" +
                "type='" + type + '\'' +
                ", img='" + img + '\'' +
                "} " + super.toString();
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
}
