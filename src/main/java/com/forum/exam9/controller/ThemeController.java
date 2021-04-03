package com.forum.exam9.controller;

import com.forum.exam9.dto.ThemeDto;
import com.forum.exam9.model.Theme;
import com.forum.exam9.services.ThemeService;
import com.forum.exam9.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class ThemeController {
    private ThemeService themeService;
    private UserService userService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }
@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/themes")
    public String getThemes(Model model, @RequestParam(name = "pageNo",required = false) Integer pageNo){
        if(pageNo==null){
            pageNo=1;
        }
        var pageable=themeService.findPaginated(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", pageable.getTotalPages());
        model.addAttribute("totalItems", pageable.getTotalElements());
        var dtos=pageable.getContent()
                .stream().map(ThemeDto::fromAll).collect(Collectors.toList());
        model.addAttribute("listThemes",dtos);
        return "theme";
    }
    @GetMapping("create")
    public String get(Model model){
        model.addAttribute("theme",new Theme());
        return "addTheme";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute @Valid Theme theme, BindingResult result,
                         Principal principal){
        if(principal.getName()==null){
            throw new RuntimeException("secured");
        }
        if(result.hasErrors()){
            return "addTheme";
        }
        var user=userService.findByUserName(principal.getName()).orElseThrow(()->
                new RuntimeException("not-allowed"));
        theme.setUser(user);
        themeService.save(theme);
        return "redirect:/themes";
    }
}
