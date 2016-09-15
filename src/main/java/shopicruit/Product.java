package shopicruit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private long id;
    private String title;
    private String product_type;
    private Variant[] variants;

    public Product() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public Variant[] getVariants() {
        return variants;
    }

    public void setVariants(Variant[] variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        StringBuilder strVariants = new StringBuilder();
        for (Variant variant : variants) {
            strVariants.append(variant.toString());
        }
        return "product {" + "\"id\": "+ id + ", \"product_type\": " + product_type + ", \"variants\": [" + strVariants + "]}";
    }
}
