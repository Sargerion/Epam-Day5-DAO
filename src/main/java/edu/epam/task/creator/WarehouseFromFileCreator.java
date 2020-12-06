package edu.epam.task.creator;

import edu.epam.task.constant.Country;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.FileCreatorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarehouseFromFileCreator {

    private static final Logger logger = LogManager.getLogger();

    public List<Product> fillWarehouseFromFile(String inputFileName) throws FileCreatorException {
        File inputFile = new File(inputFileName);
        if (!(inputFile.exists())) {
            throw new FileCreatorException("Input file doesn't exist");
        }
        Product product = null;
        List<Product> products = new ArrayList<>();
        String line = null;
        Scanner scanner = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            while ((line = reader.readLine()) != null) {
                scanner = new Scanner(line).useDelimiter("\\s");
                while (scanner.hasNext()) {
                    product = new Product(scanner.next(), scanner.nextLong(), Country.valueOf(scanner.next()),
                            scanner.nextBigDecimal(), scanner.nextInt(), scanner.nextInt());
                }
                products.add(product);
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found");
        } catch (IOException e) {
            logger.error(e);
        }
        logger.info("Products: {} was filled from {} file successfully", products, inputFileName);
        return products;
    }
}
