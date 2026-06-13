// Script encargado de controlar la apertura y cierre del panel lateral de la página principal
// mediante eventos click y manipulación dinámica (add,remove) de elementos de clases en el CSS.

// Referencias a elementos principales del panel lateral
const openMenu = document.getElementById("open-menu");
const sidePanel = document.getElementById("side-panel");
const overlay = document.getElementById("overlay");
const closeButton = document.getElementById("close-button");

// Evento: abrir panel lateral y activar overlay
openMenu.addEventListener("click", function(){

    sidePanel.classList.add("open");
    overlay.classList.add("show");

});

// Evento: cerrar panel lateral desde botón de cierre
closeButton.addEventListener("click", function(){

    sidePanel.classList.remove("open");
    overlay.classList.remove("show");

});

// Evento: cerrar panel lateral al hacer click sobre overlay
overlay.addEventListener("click", function(){

    sidePanel.classList.remove("open");
    overlay.classList.remove("show");

});