package classes;

public class Goods {
    private int idgoods;
    private int code;
    private String name;
    private int weight;
    private int price;
    private int quantity;
    private Category category;
    private String img;

    public Goods(){}

    public Goods(int idgoods, int code, String name, int weight, int price, int quantity, Category category, String img) {
        this.idgoods = idgoods;
        this.code = code;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.img = img;
    }
    public Goods(int code, String name, int weight, int price, int quantity, Category category, String img) {
        this.code = code;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.img = img;
    }

    public int getIdgoods() {
        return idgoods;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public void setIdgoods(int idgoods) {
        this.idgoods = idgoods;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "idgoods=" + idgoods +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (idgoods != goods.idgoods) return false;
        if (code != goods.code) return false;
        if (weight != goods.weight) return false;
        if (price != goods.price) return false;
        if (quantity != goods.quantity) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (category != null ? !category.equals(goods.category) : goods.category != null) return false;
        return img != null ? img.equals(goods.img) : goods.img == null;
    }

    @Override
    public int hashCode() {
        int result = idgoods;
        result = 31 * result + code;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + price;
        result = 31 * result + quantity;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }
}
