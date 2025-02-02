package Demo.Bachatt.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProfileController {

    @GetMapping("/profile")
    public String profile(){
        return  "profile";
    }

    @DeleteMapping("/profile")
    public String deleteProfile(){
        return  "profile";
    }
}
