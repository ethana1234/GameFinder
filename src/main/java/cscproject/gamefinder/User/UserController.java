package cscproject.gamefinder.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(List<Long> uids){
        List <User> users = new ArrayList<>();
        for(Long uid: uids) {
            User user = userRepository.findById(uid).get();
            users.add(user);
        }
        return users;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUser (@RequestParam String username) {
        Future<User> user = userRepository.findUserByUsername(username);

        try {
            user.get().getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not be found");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody User user) {
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Posted");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody User user) {
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Edited");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody User user) {
        ResponseEntity res = getUser(user.getUsername());

        if(res.getStatusCode() == HttpStatus.OK) {
            User temp = (User) res.getBody();
            userRepository.deleteById(temp.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
