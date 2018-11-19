package com.simonable.java_belt_exam;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simonable.java_belt_exam.models.Idea;
import com.simonable.java_belt_exam.models.User;
import com.simonable.java_belt_exam.services.IdeaService;
import com.simonable.java_belt_exam.services.UserService;


@Controller
public class RouteController {
	
	private final UserService userService;
	private final IdeaService ideaService;
	
	public RouteController(UserService userService, IdeaService ideaService) {
		this.userService = userService;
		this.ideaService = ideaService;
	}

	@GetMapping("/")
	public String login_registration(Model model) {
		System.out.println("\n\n<<-------------Login and Registration Form------------->>\n\n");
		model.addAttribute("user", new User());
		
		return "login_registration.jsp";
	}
	
	@PostMapping("/process_registration")
	public String process_registration(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		System.out.println("\n\n<<-------------Processing User Registration------------->>\n\n");
		
		System.out.println("Username: " + user.getUsername());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: " + user.getPassword());
		
		if(result.hasErrors()) {
			System.out.println("Problem found in registration.");
			return "login_registration.jsp";
		}
		
		else {
			System.out.println("Registration Succesfull!");
			userService.createUser(user);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("id", user.getId());
			session.setAttribute("user", userService.findById(user.getId()));
			
			System.out.println("id created" + session.getAttribute("id"));
			return "redirect:/dashboard";
		}
		
	}
	
	@PostMapping("process_login")
	public String process_login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		
		System.out.println("\n\n<<-------------Processing User Login------------->>\n\n");
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: "  +user.getPassword());
		
		if(result.hasErrors()) {
			System.out.println("Problem found in registration.");
			return "redirect:/";
		}
		else if(userService.authenticateUser(user.getEmail(), user.getPassword())) {
			System.out.println("Authenticating User");
			
			session.setAttribute("username", userService.findByEmail(user.getEmail()).getUsername());
			session.setAttribute("id", userService.findByEmail(user.getEmail()).getId());
			
			System.out.println(session.getAttribute("id"));
			System.out.println("Username: " + session.getAttribute("username"));
			return "redirect:/dashboard";
		}
		else {
			return "login_registration.jsp";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("\n\n<<-------------Logging Out User------------->>\n\n");
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		
//		if(session.getAttribute("id")==null) {
//			return "redirect:/";
//		}
		
		List<Idea> ideas = ideaService.findAllIdeas();
		model.addAttribute("ideas", ideas);
		
		User user=userService.findById((Long) session.getAttribute("id"));
		System.out.println("Displaying user: " + user.getUsername());
		
		List<Idea> likes = user.getIdea();
		model.addAttribute("likes", likes);
		
		
		return "index.jsp";
	}
	
	@GetMapping("/new_idea")
	public String new_idea(HttpSession session, Model model) {
		
		System.out.println("This user is: " + userService.findById((Long) session.getAttribute("id")));
		model.addAttribute("this_user", userService.findById((Long) session.getAttribute("id")));
		model.addAttribute("user", new User());
		model.addAttribute("idea", new Idea());
		
		return "new_idea.jsp";
	}
	
	@PostMapping("/process_idea")
	public String process_idea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println("Problem found in new idea.");
			return "redirect:/new_idea";
		}
		else {
			System.out.println("Creating new idea.");
			System.out.println(idea.getName());
			System.out.println(idea.getDescription());
			System.out.println(idea.getUser());
			
			ideaService.createIdea(idea);
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/update_idea")
	public String update_idea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println("Problem found in new idea.");
			return "redirect:/new_idea";
		}
		else {
			System.out.println("Creating new idea.");
			System.out.println(idea.getName());
			System.out.println(idea.getDescription());
			System.out.println(idea.getUser());
			
			ideaService.updateIdea(idea);
			return "redirect:/dashboard";
		}
	}

	@GetMapping("show_idea/{id}")
	public String show_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		
		Idea idea = ideaService.findIdeaById(id);
		model.addAttribute("idea", idea);
		
		return "edit_idea.jsp";
	}
	
	@GetMapping("delete_idea/{id}")
	public String delete_idea(HttpSession session, @PathVariable("id") Long id) {
		
		Idea this_idea = ideaService.findIdeaById(id);
		
		ideaService.deleteIdea(this_idea);
				
		return "redirect:/dashboard";
	}
	
	@GetMapping("like_idea/{user_id}/{idea_id}")
	public String like_idea(HttpSession session, @PathVariable("user_id") Long user_id, @PathVariable("idea_id") Long idea_id) {
		System.out.println("User liked an idea");
		User user=userService.findById(user_id);
		Idea idea=ideaService.findIdeaById(idea_id);
		
		List<Idea> ideas = user.getIdea();
		ideas.add(idea);
		
		userService.updateUser(user);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("unlike_idea/{user_id}/{idea_id}")
	public String unlike_idea(HttpSession session, @PathVariable("user_id") Long user_id, @PathVariable("idea_id") Long idea_id) {
		System.out.println("User unliked an idea");
		User user=userService.findById(user_id);
		Idea idea=ideaService.findIdeaById(idea_id);
		
		List<Idea> ideas = user.getIdea();
	
		System.out.println(idea.getName());
		ideas.remove(idea);
		
		
		return "redirect:/dashboard";
	}
}
