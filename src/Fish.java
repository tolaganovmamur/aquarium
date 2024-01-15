public class Fish {
    private Integer id;
    private String name;
    private long lifeDate;
    private String type;
    private Integer x;
    private Integer y;
    private boolean status = false;

    public Fish(Integer lifeDate, String type) {
        this.lifeDate = lifeDate;
        this.type = type;
    }

    public Fish() {
    }

    public Long getLifeDate() {
        return lifeDate;
    }

    public void setLifeDate(Long lifeDate) {
        this.lifeDate = lifeDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lifeDate=" + lifeDate +
                ", type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", status=" + status +
                '}';
    }
}
