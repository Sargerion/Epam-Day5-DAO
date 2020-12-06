package edu.epam.task.entity;

import edu.epam.task.constant.Country;
import edu.epam.task.exception.ProductException;
import edu.epam.task.util.IdGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import static edu.epam.task.constant.ProductValidator.ID_LOWER_BOUND;
import static edu.epam.task.constant.ProductValidator.UPC_LOWER_BOUND;

public class Product implements Entity {

    private static final Logger logger = LogManager.getLogger();

    private long productId;
    private String productName;
    private long UPC;
    private Country manufacturer;
    private BigDecimal price;
    private int expirationYear;
    private int count;

    public Product() {

    }

    public Product(String productName, long UPC, Country manufacturer, BigDecimal price, int expirationYear, int count) {
        this.productId = IdGenerator.generateId();
        this.productName = productName;
        this.UPC = UPC;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationYear = expirationYear;
        this.count = count;
    }

    public long getId() {
        logger.info("ProductId = {} received", productId);
        return productId;
    }

    public void setId(long productId) throws ProductException {
        if (productId < ID_LOWER_BOUND) {
            throw new ProductException("ProductId must be in bounds");
        }
        this.productId = productId;
        logger.info("ProductId {} specified", productId);
    }

    public String getProductName() {
        logger.info("ProductName - {} received", productName);
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        logger.info("ProductName - {} specified", productName);
    }

    public long getUPC() {
        logger.info("UPC = {} received", UPC);
        return UPC;
    }

    public void setUPC(long UPC) throws ProductException {
        if(UPC < UPC_LOWER_BOUND) {
            throw new ProductException("UPC must be in bounds");
        }
        this.UPC = UPC;
        logger.info("UPC = {} specified", UPC);
    }

    public Country getManufacturer() {
        logger.info("Manufacturer - {} received", manufacturer);
        return manufacturer;
    }

    public void setManufacturer(Country manufacturer) {
        this.manufacturer = manufacturer;
        logger.info("Manufacturer - {} specified", manufacturer);
    }

    public BigDecimal getPrice() {
        logger.info("Price = {} received", price);
        return price;
    }

    public void setPrice(BigDecimal price) throws ProductException {
        if(price.doubleValue() < 0) {
            throw new ProductException("Price must be positive");
        }
        this.price = price;
        logger.info("Price = {} specified", price);
    }

    public int getExpirationYear() {
        logger.info("expirationYear - {} received", expirationYear);
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) throws ProductException {
        if(expirationYear < 0 || expirationYear > 15) {
            throw new ProductException("ExpirationYear must be realistic");
        }
        this.expirationYear = expirationYear;
        logger.info("ExpirationYear - {} specified", expirationYear);
    }

    public int getCount() {
        logger.info("Count = {} received", count);
        return count;
    }

    public void setCount(int count) throws ProductException {
        if(count < 0) {
            throw new ProductException("Count must be positive");
        }
        this.count = count;
        logger.info("Count - {} specified", count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Product)) return false;
        Product product = (Product) o;
        return UPC == product.UPC &&
                manufacturer == product.manufacturer &&
                expirationYear == product.expirationYear &&
                count == product.count &&
                productName.equals(product.productName) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        int result = productName.hashCode();
        result = 31 * result + Long.hashCode(UPC) + manufacturer.hashCode()
                + price.hashCode() + Integer.hashCode(expirationYear) + Integer.hashCode(count);
        logger.info("Product hashCode = {}", result);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product {\n");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", UPC=").append(UPC);
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", price=").append(price);
        sb.append(", expirationDate=").append(expirationYear);
        sb.append(", count=").append(count);
        sb.append("\n}");
        return sb.toString();
    }
}
