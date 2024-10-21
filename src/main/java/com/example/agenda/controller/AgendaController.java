package com.example.agenda.controller;


import com.example.agenda.model.Contacto;
import com.example.agenda.service.ContactoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/agenda")
public class AgendaController {
    private final ContactoService contactoService;

    public AgendaController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping("/")
    public String mostrarContactos(Model model, @ModelAttribute("message") String message){
        model.addAttribute("agenda", contactoService);
        model.addAttribute("message", message);
        return "agenda";
    }

    //mostrar el formulario para agregar un nuevo contacto
    @GetMapping("/nuevo")
    public String nuevoContactoForm(Model model){
        model.addAttribute("contacto", new Contacto());
        return "nuevoContacto";
    }

    @PostMapping("/guardar")
    public String guardarContacto(@ModelAttribute Contacto contacto, RedirectAttributes redirectAttributes){
        contactoService.guardarContacto(contacto);
        redirectAttributes.addFlashAttribute("message", "Contacto guardado con éxito");
        return "redirect:/agenda/";
    }

    @PostMapping("/eliminar/{nombre}")
    public String eliminarContacto(@PathVariable("nombre") String nombre, RedirectAttributes redirectAttributes){
        boolean eliminado = contactoService.eliminarContacto(nombre);
        if(eliminado){
            redirectAttributes.addFlashAttribute("message", "Contacto eliminado con éxito");
        }else {
            redirectAttributes.addFlashAttribute("message", "No se pudo eliminar un contacto");
        }

        return "redirect:/agenda";
    }
}
