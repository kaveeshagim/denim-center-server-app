package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.RoleDao;
import lk.weerathunga.denimcenter.dao.UserDao;
import lk.weerathunga.denimcenter.entity.ERole;
import lk.weerathunga.denimcenter.entity.Material;
import lk.weerathunga.denimcenter.entity.Role;
import lk.weerathunga.denimcenter.entity.User;
import lk.weerathunga.denimcenter.login.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserDao userdao;

    @Autowired
    private RoleDao roledao;

    @GetMapping(produces = "application/json")
    public List<User> get(@RequestParam HashMap<String, String> params) {

        String username = params.get("username");
        String password = params.get("password");

        List<User> users = this.userdao.findAll();

        if (params.isEmpty()) return users;

        Stream<User> estream = users.stream();

        if (username != null) estream = estream.filter(e -> e.getUsername().equals(username));
        if (password != null) estream = estream.filter(e -> e.getPassword().equals(password));
        return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<User> get() {

        List<User> users = this.userdao.findAll();

        users = users.stream().map(
                user -> { User s = new User();
                    s.setId(user.getId());
                    s.setUsername(user.getUsername());
                    s.setPassword(user.getPassword());
                    s.setRoles(user.getRoles());

                    return s; }
        ).collect(Collectors.toList());

        return users;
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

        User user1 = userdao.findByUsername(user.getUsername());

        if (user1 != null && user.getId() != user1.getId())
            errors = errors + "<br> Existing Code";

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
        User user1 = userdao.findAllById(id);
        if (user1 == null)
            errors = errors + "<br> user Does Not Existed";
        if (errors == "") userdao.delete(user1);
        else errors = "Server Validation Errors : <br> " + errors;
        response.put("id", String.valueOf(id));
        response.put("url", "/users/" + id);
        response.put("errors", errors);
        return response;
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<?> getUserRoles(@PathVariable("userId") Integer userId) {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok(roles);
    }

    @PostMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addRolesToUser(@PathVariable("userId") Integer userId, @RequestBody Set<String> roleNames) {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        Set<Role> rolesToAdd = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roledao.findByName(ERole.valueOf(roleName.toUpperCase()))
                    .orElseThrow(() -> new RuntimeException("Error: Role '" + roleName + "' is not found."));
            rolesToAdd.add(role);
        }

        user.getRoles().addAll(rolesToAdd);
        userdao.save(user);

        return ResponseEntity.ok(new MessageResponse("Roles added to user successfully!"));
    }

    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserRoles(@PathVariable("userId") Integer userId, @RequestBody Set<String> roleNames) {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roledao.findByName(ERole.valueOf(roleName.toUpperCase()))
                    .orElseThrow(() -> new RuntimeException("Error: Role '" + roleName + "' is not found."));
            roles.add(role);
        }

        user.setRoles(roles);
        userdao.save(user);

        return ResponseEntity.ok(new MessageResponse("User roles updated successfully!"));
    }

    @DeleteMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeRolesFromUser(@PathVariable("userId") Integer userId, @RequestBody Set<String> roleNames) {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        Set<Role> rolesToRemove = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roledao.findByName(ERole.valueOf(roleName.toUpperCase()))
                    .orElseThrow(() -> new RuntimeException("Error: Role '" + roleName + "' is not found."));
            rolesToRemove.add(role);
        }

        user.getRoles().removeAll(rolesToRemove);
        userdao.save(user);

        return ResponseEntity.ok(new MessageResponse("Roles removed from user successfully!"));
    }


}