/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.list01;

/**
 *
 * @author Eduardo
 */
public class MyList <T> {
    Node first;
    Node last;
    Node pointer; //Apuntador auxiliar
    //Constructor
    MyList(){
        first = null;
        last = null;
        pointer = null;
    }
    
    MyList(T d){ //Crear una lista mandando el contenido del primer elemento
        Node node = new Node(d);
        first = node;
        last = node;
        node.next = null;
    }
    
    public boolean isEmpty(){
        return first == null;
        /*if (first == null && last == null){
            return true;
        } else {
            return false;
        }*/
    }
    
    public void insertFirst(T d){
        Node node = new Node(d);
        if (isEmpty()) { //Caso Lista Vacia
            first = node;
            last = node;
            node.next = null;
        } else { //Caso Lista con elementos
            node.next = first;
            first = node;
        }
    }
    
    public void insertLast(T d){
        Node node = new Node(d); //Al crearlo ya esta apuntando a null 
        if (isEmpty()) { //Caso: Lista vacia
            first = node;
            last = node;
            node.next = null;
        } else { //Caso: lista con elementos
            last.next = node;
            last = node; //Aqui este nodo tiene la propiedad de que su .next es null por el constructor
        }
    }
    //Eliminar al inicio
    public void deleteFirst(){
        if (!isEmpty()) { //Verificar que no este vacia
            if (first.next == null){ //Caso: Si la lista solo tiene un elemento
                first = null; //Hacemos que first y last sean nulos
                last = null;
            } else { //Caso: Si la lista tiene mas de un elemento
                first = first.next; //Nuestro apuntador First apunta ahora al siguiente elemento
                                    //No es necesario borrar el elemento, porque se pierde la referencia 
            }
        }
    }
    
    public void deleteLast() {
        if (!isEmpty()) {
            if (first == last) { //Misma condicion para Caso: Lista de un elemento 
                first = last = null; 
            } else { //Buscamos dentro del arreglo
                pointer = first; //Poner el apuntador en el primer sitio para empezar a recorrer
                //Validaremos tambien si solo hay dos elementos
                while (pointer.next != last){ //Mientras que´el siguiente elemento del pointer (no quiero llegar al ultimo) 
                    pointer = pointer.next;   //Recorreremos la lista y se detiene en el elemento anterior al ultimo    
                }
                last = pointer;
                last.next = null;
                pointer = null; //Lo eliminamos por ser un atributo y no queremos que tenga un valor en otra parte del programa
            }
        }
    }
    
    public boolean deleteNode(T n){
        Node p = fetchBack(n); 
        if (p != null) { //Si encuentra el elemento
            if (p.data == n){ //Si fue el primero o unico elemento de la lista...
                deleteFirst();
            } else { //Si esta dentro de la lista
                p.next = p.next.next; //Estamos en el anterior al que se desea eliminar, entonces nuestro siguiente sera el siguiente 
            }                           //del siguiente, se salta el elemento
            return true; //Encontro el elemento en cualquiera de los casos y lo borro 
        }
        return false; //No se encontro 
    }
    
    public Node fetch(T n){
        //Si no esta vacia la lista 
        if (!isEmpty()) {
            if (first==last) { //Si hay un solo  elemento en la lista Y es el elemento buscado 
                if (first.data == n){
                    return first;
                }
                return null;
            } else {
                Node node = fetchBack(n);
                if (node != null) {
                    return node.next; 
                }
            }
            /*pointer = first; //Iniciamos el puntero 
            while (pointer.next != last){ //Mientras que el puntero sea diferente al ultimo 
                if (pointer.data == n){ //La data del puntero siguiente es la que buscamos en n
                    return pointer; //Regresamos el apuntador (que nos da al anterior)
                }
                pointer = pointer.next;
            }*/
            
        }
        return null;
    }
    
 
    
    public Node fetchBack (T n){ //Buscamos el nodo anterior al que buscamos 
        pointer = first;
        if (!isEmpty()) {
            if (first == last) { //Si hay un solo elemento
                if (first.data == n){ //Es el elemento que busco?
                    return first; //Se devuelve a si mismo 
                } else {
                    return null;
                }  
            } else { //Si hay mas elementos en la lista
                if (first.data == n) { //El elemento que busco es el primero?
                    return first;
                } else {
                    pointer = first; //Iniciamos el puntero 
                    while (pointer.next != last){ //Mientras que el puntero sea diferente al ultimo 
                        if (pointer.next.data == n){ //La data del puntero siguiente es la que buscamos en n
                            return pointer; //Regresamos el apuntador (que nos da al anterior)
                        }
                        pointer = pointer.next; //En caso de que no, mueve el puntero 
                    }
                }
            }   
        }
        return null; //No se encontro el elemento 
    } 
}
    
    //Los metodos deben hacer una sola cosa, si necesitamos hacer mas , creamos otro metodo
