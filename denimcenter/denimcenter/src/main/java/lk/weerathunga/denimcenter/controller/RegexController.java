package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.entity.*;
import lk.weerathunga.denimcenter.util.RegexProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping(value = "/regexes")
public class RegexController {

    @GetMapping(path = "/employee", produces = "application/json")
    public HashMap<String, HashMap<String, String>> employee() { return RegexProvider.get(new Employee());}

    @GetMapping(path = "/product", produces = "application/json")
    public HashMap<String, HashMap<String, String>> product() { return RegexProvider.get(new Product());}

    @GetMapping(path = "/material", produces = "application/json")
    public HashMap<String, HashMap<String, String>> material() { return RegexProvider.get(new Material());}

    @GetMapping(path = "/supplier", produces = "application/json")
    public HashMap<String, HashMap<String, String>> supplier() { return RegexProvider.get(new Supplier());}

    @GetMapping(path = "/grn", produces = "application/json")
    public HashMap<String, HashMap<String, String>> grn() { return RegexProvider.get(new Grn());}

    @GetMapping(path = "/invoice", produces = "application/json")
    public HashMap<String, HashMap<String, String>> invoice() { return RegexProvider.get(new Invoice());}
    @GetMapping(path = "/productionorder", produces = "application/json")
    public HashMap<String, HashMap<String, String>> productionorder() { return RegexProvider.get(new Productionorder());}
    @GetMapping(path = "/porder", produces = "application/json")
    public HashMap<String, HashMap<String, String>> porder() { return RegexProvider.get(new Porder());}
    @GetMapping(path = "/corder", produces = "application/json")
    public HashMap<String, HashMap<String, String>> corder() { return RegexProvider.get(new Corder());}
    @GetMapping(path = "/customerpayment", produces = "application/json")
    public HashMap<String, HashMap<String, String>> customerpayment() { return RegexProvider.get(new Customerpayment());}
    @GetMapping(path = "/supplierpayment", produces = "application/json")
    public HashMap<String, HashMap<String, String>> supplierpayment() { return RegexProvider.get(new Supplierpayment());}
    @GetMapping(path = "/customerreturn", produces = "application/json")
    public HashMap<String, HashMap<String, String>> customerreturn() { return RegexProvider.get(new Customerreturn());}
    @GetMapping(path = "/supplierreturn", produces = "application/json")
    public HashMap<String, HashMap<String, String>> supplierreturn() { return RegexProvider.get(new Supplierreturn());}
    @GetMapping(path = "/materialmovement", produces = "application/json")
    public HashMap<String, HashMap<String, String>> materialmovement() { return RegexProvider.get(new Materialmovement());}
    @GetMapping(path = "/productmovement", produces = "application/json")
    public HashMap<String, HashMap<String, String>> productmovement() { return RegexProvider.get(new Productmovement());}
    @GetMapping(path = "/user", produces = "application/json")
    public HashMap<String, HashMap<String, String>> user() { return RegexProvider.get(new User());}
    @GetMapping(path = "/materialinventory", produces = "application/json")
    public HashMap<String, HashMap<String, String>> materialinventory() { return RegexProvider.get(new Materialinventory());}
    @GetMapping(path = "/productinventory", produces = "application/json")
    public HashMap<String, HashMap<String, String>> productinventory() { return RegexProvider.get(new Productinventory());}

    @GetMapping(path = "/billofmaterial", produces = "application/json")
    public HashMap<String, HashMap<String, String>> billofmaterial() { return RegexProvider.get(new Billofmaterial());}
}

