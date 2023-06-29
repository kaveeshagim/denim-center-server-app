package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.ProductDao;
import lk.weerathunga.denimcenter.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductDao productdao;

    // Retrieves a list of products based on the given parameter
    @GetMapping(produces = "application/json")
    public List<Product> get(@RequestParam HashMap<String, String> params) {

        // Parameter extraction
        String code = params.get("code");
        String name = params.get("name");
        String genderid = params.get("genderid");
        String agecategoryid = params.get("agecategoryid");
        String colorid = params.get("colorid");
        String sizeid = params.get("sizeid");
        String typeid = params.get("typeid");

        List<Product> products = this.productdao.findAll();

        // Filtering the products based on the given parameters
        if(params.isEmpty()) return products;

        Stream<Product> estream = products.stream();

        if(code!=null) estream = estream.filter(e -> e.getCode().equals(code));
        if(name!=null) estream = estream.filter(e -> e.getName().contains(name));
        if(genderid!=null) estream = estream.filter(e -> e.getGender().getId()==Integer.parseInt(genderid));
        if(agecategoryid!=null) estream = estream.filter(e -> e.getAgecategory().getId()==Integer.parseInt(agecategoryid));
        if(colorid!=null) estream = estream.filter(e -> e.getColor().getId()==Integer.parseInt(colorid));
        if(sizeid!=null) estream = estream.filter(e -> e.getSize().getId()==Integer.parseInt(sizeid));
        if(typeid!=null) estream = estream.filter(e -> e.getType().getId()==Integer.parseInt(typeid));

        return estream.collect(Collectors.toList());
    }

    // Retrieves a list of products
    @GetMapping(path = "/list", produces = "application/json")
    public List<Product> get() {

        List<Product> products = this.productdao.findAll();

        // Mapping the products to a simplified form
        products = products.stream().map(
                product -> { Product s = new Product();
                    s.setId(product.getId());
                    s.setCode(product.getCode());
                    s.setPrice(product.getPrice());
                    return s; }
        ).collect(Collectors.toList());

        return products;
    }

    // Adds a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Product product){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        // Validating the product code
        if(productdao.findByCode(product.getCode())!=null)
            errors = errors+"<br> Existing Code";

        if(errors=="")
            productdao.save(product);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(product.getId()));
        response.put("url","/products/"+product.getId());
        response.put("errors",errors);

        return response;
    }

    // Updates an existing product
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Product product){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Product prod1 = productdao.findByCode(product.getCode());

        // Validating the product code
        if(prod1!=null && product.getId()!=prod1.getId())
            errors = errors+"<br> Existing Code";

        if(errors=="") productdao.save(product);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(product.getId()));
        response.put("url","/products/"+product.getId());
        response.put("errors",errors);

        return response;
    }

    // Deletes a product based on the provided ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Product prod1 = productdao.findAllById(id);
        if(prod1==null)
            errors = errors+"<br> product Does Not Existed";
        if(errors=="") productdao.delete(prod1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/products/"+id);
        response.put("errors",errors);
        return response;
    }
}
