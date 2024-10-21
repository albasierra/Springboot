package com.example.agenda.service;
import com.example.agenda.model.Contacto;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContactoService {
    private Map<String, Contacto> agenda = new HashMap<>();

    public Map<String, Contacto> obtenerContactos(){
        return agenda;
    }

    //Eliminar un contacto
    public boolean eliminarContacto(String nombre){
        Contacto contacto = agenda.get(nombre);

        if(contacto!= null && !contacto.isFavorito()){
            agenda.remove(nombre);
            return true;
        }

        return false;//Si es favorito no se puede eliminar
    }

    //Buscar un contacto por nombre
    public Contacto obtenerContactoPorNombre(String nombre){
        return agenda.get(nombre);
    }

    //Actualiza un contacto
    public void actualizarContacto(String nombreOriginal, Contacto contactoActualizado){
        if(!nombreOriginal.equals(contactoActualizado.getNombre())){
            agenda.remove(nombreOriginal); //eliminar el contacto original
        }
        agenda.put(contactoActualizado.getNombre(),contactoActualizado);
    }

    //cambiar el estado favorito de un contacto
    public void cambiarFavorito(String nombre){
        Contacto contacto = agenda.get(nombre);
        if(contacto!=null){
            contacto.setFavorito(!contacto.isFavorito());
        }
    }
}
