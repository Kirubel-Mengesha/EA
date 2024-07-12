package shop.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.Domain.Product;
import shop.Domain.Products;
import shop.productErrorHandler.ProductErrorType;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class productController {

    private Map<Long, Product> products = new HashMap<Long, Product>();

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        if(products.containsKey(product.getProductNumber())){
            return new ResponseEntity<>(new ProductErrorType("Product with number " + product.getProductNumber() + " already exists"), HttpStatus.CONFLICT);
        }
        products.put(product.getProductNumber(), product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        Products allproducts = new Products(products.values());
        if(allproducts.getProducts().isEmpty()){
            return new ResponseEntity<>(new ProductErrorType("No products found"), HttpStatus.NOT_FOUND);
        }

       return new ResponseEntity<>(allproducts, HttpStatus.OK);
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<?> getProduct(@PathVariable long productNumber){
        Product product = products.get(productNumber);
        if(product == null){
            return new ResponseEntity<>(new ProductErrorType("Product with number " + productNumber + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productNumber}")
    public ResponseEntity<?> deleteProduct(@PathVariable long productNumber){
        Product product = products.get(productNumber);
        if(product == null){
            return new ResponseEntity<>(new ProductErrorType("Product with number " + productNumber + " not found"), HttpStatus.NOT_FOUND);
        }
        products.remove(productNumber);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{productNumber}")
    public ResponseEntity<?> updateProduct(@PathVariable long productNumber, @RequestBody Product product){
        Product oldProduct = products.get(productNumber);
        if(oldProduct == null){
            return new ResponseEntity<>(new ProductErrorType("Product with number " + productNumber + " not found"), HttpStatus.NOT_FOUND);
        }
        products.put(productNumber, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }



}
