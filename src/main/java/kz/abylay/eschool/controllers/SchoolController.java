package kz.abylay.eschool.controllers;

import kz.abylay.eschool.models.Student;
import kz.abylay.eschool.models.Users;
import kz.abylay.eschool.service.StudentService;
import kz.abylay.eschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SchoolController {
    private final StudentService studentService;
    private final UserService userService;

    @Autowired
    public SchoolController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("title", "Profile");
        return "profile";
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("title", "Registration");
        return "registrate";
    }

    @PostMapping("/registrateStudent")
    public String registrateStudent(@RequestParam(name = "fullname") String fullname,
                                    @RequestParam(name = "email") String email,
                                    @RequestParam(name = "password") String password) {
        Users users = new Users(null, email, password, fullname, null);
        if (userService.findUserByEmail(users.getEmail())==null){
            userService.addUser(users);
            return "redirect:/login";
        }
        return "redirect:/register";
    }

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("title", "Home");
        return "home_page";
    }

    @GetMapping("/addStudentPage")
    @PreAuthorize("isAuthenticated()")
    public String addStudentPage(Model model) {
        model.addAttribute("title", "Add student");
        return "add";
    }

    @PostMapping("/addStudent")
    @PreAuthorize("isAuthenticated()")
    public String addStudent(@RequestParam(name = "firstname", defaultValue = "") String firstname,
                             @RequestParam(name = "lastname", defaultValue = "") String lastname,
                             @RequestParam(name = "grade", defaultValue = "0") double grade) {
        if (firstname.equals("") || lastname.equals("")) {
            return "redirect:/errorpage/404";
        }
        studentService.add(new Student(null, firstname, lastname, grade));
        return "redirect:/";
    }

    @PostMapping("/delete/{student_id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteStudent(@PathVariable(name = "student_id") Long student_id) {
        Optional<Student> s = studentService.get(student_id);
        s.ifPresent(studentService::delete);
        return "redirect:/";
    }

    @GetMapping("/errorpage/{error_id}") /* {error_id} = 500 */
    public String getError(Model model, @PathVariable(name = "error_id") int errorID) {
        if (errorID == 500) {
            model.addAttribute("error", "500 ITEM NOT FOUND");
        } else if (errorID == 404) {
            model.addAttribute("error", "404 PAGE NOT FOUND");
        } else if (errorID == 403) {
            model.addAttribute("error", "403 ACCESS FORBIDDEN");
        }
        model.addAttribute("title", "Error");
        return "error_page";
    }
}
