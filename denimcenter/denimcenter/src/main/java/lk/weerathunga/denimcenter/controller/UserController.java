package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.UserDao;
import lk.weerathunga.denimcenter.entity.User;
import lk.weerathunga.denimcenter.login.entity.AuthRequest;
import lk.weerathunga.denimcenter.login.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserDao userdao;

    @GetMapping(produces = "application/json")
    public List<User> get(@RequestParam HashMap<String, String> params) {

        String username = params.get("username");
        String password = params.get("password");
        String userstatusid = params.get("userstatusid");


        List<User> users = this.userdao.findAll();

        if (params.isEmpty()) return users;

        Stream<User> estream = users.stream();

        if (username != null) estream = estream.filter(e -> e.getUsername().equals(username));
        if (password != null) estream = estream.filter(e -> e.getPassword().equals(password));
        if (userstatusid != null)
            estream = estream.filter(e -> e.getUserstatus().getId() == Integer.parseInt(userstatusid));


        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody User user) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        if (userdao.findByUsername(user.getUsername()) != null)
            errors = errors + "<br> Existing Username";

        if (errors == "")
            userdao.save(user);
        else errors = "Server Validation Errors : <br> " + errors;

        response.put("id", String.valueOf(user.getId()));
        response.put("url", "/users/" + user.getId());
        response.put("errors", errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody User user) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        User prod1 = userdao.findByUsername(user.getUsername());

        //System.out.println("ID-"+user.getId()+"-"+user.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        if (prod1 != null && user.getId() != prod1.getId())
            errors = errors + "<br> Existing Code";

        //System.out.println(user.getFirstname());
        //System.out.println("Err-"+errors);

        if (errors == "") userdao.save(user);
        else errors = "Server Validation Errors : <br> " + errors;

        response.put("id", String.valueOf(user.getId()));
        response.put("url", "/users/" + user.getId());
        response.put("errors", errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> delete(@PathVariable Integer id) {
        System.out.println(id);
        HashMap<String, String> response = new HashMap<>();
        String errors = "";
        User emp1 = userdao.findAllById(id);
        if (emp1 == null)
            errors = errors + "<br> user Does Not Existed";
        if (errors == "") userdao.delete(emp1);
        else errors = "Server Validation Errors : <br> " + errors;
        response.put("id", String.valueOf(id));
        response.put("url", "/users/" + id);
        response.put("errors", errors);
        return response;
    }

}