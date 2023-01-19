package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setData(5);
        return hello;
    }

    private static class Hello {
        private String name;
        private int data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setData(int data) {
            this.data = data;
        }
        public int getData() {
            return data;
        }
    }
}
