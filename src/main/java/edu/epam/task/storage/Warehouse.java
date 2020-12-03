package edu.epam.task.storage;

import edu.epam.task.entity.Product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private static final Logger logger = LogManager.getLogger();

    private static Warehouse warehouse;
    private final List<Product> products = new ArrayList<>();

    private Warehouse() {

    }

    public static Warehouse getInstance() {
        if (warehouse == null) {
            warehouse = new Warehouse();
            logger.info("Warehouse created");
        }
        return warehouse;
    }

    public boolean contains(Product product) {
        return products.contains(product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public void setProduct(Product product) {
        products.add(product);
    }

    public void setProduct(Product product, int index) {
        products.add(index, product);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public int getSize() {
        return products.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{\n");
        sb.append("products=").append(products).append("\n}");
        return sb.toString();
    }
}
