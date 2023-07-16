// Modal
const modal = document.getElementById("myModal");
const modalAdd = document.getElementById("myModalAdd");
const modalEdit = document.getElementById("myModalEdit");
const modalRemove = document.getElementById("myModalRemove");
const modalMess = document.getElementById("myModalMess");


// Btn Modal
const btnAdd = document.getElementById("myBtnAdd");
const closeModalEdit = document.getElementsByClassName("btn-close-modal")[0];
const closeModalAdd = document.getElementsByClassName("btn-close-modal")[1];
const closeModalRemove = document.getElementsByClassName("btn-discard")[0];
const closeMessBtn = document.getElementsByClassName("btn-close-mess")[0];

// Func open Modal
if(btnAdd !== null){
  btnAdd.onclick = function() {
    if(modal !== null){modal.style.display = "none";}
    modalEdit.style.display = "none";
    modalRemove.style.display = "none";
    modalAdd.style.display = "inline";
  };
}

function openModalEdit(id) {
  if(modalAdd !== null){modalAdd.style.display = "none";}
  if(modal !== null){modal.style.display = "none";}
  modalEdit.style.display="inline";
  document.getElementById("editValue").setAttribute('value',id);
}

function openModalRemove(id) {
  document.getElementById("removeValue").setAttribute('value',id);
  modalRemove.style.display="inline";
}

function openModalAdminMenu(id) {
  const menuList = document.getElementsByTagName("li");
  menuList[id].className = "menu__list--item-active";
}

// Func close Modal
if(closeModalAdd != null){
    closeModalAdd.onclick = function() {
      event.preventDefault();
      modalAdd.style.display = "none";
      if(modal != null) modal.style.display = "inline";
    };
}   
closeModalEdit.onclick = function() {
    event.preventDefault();
  modalEdit.style.display = "none";
  if(modal != null) modal.style.display = "inline";
};
closeModalRemove.onclick = function() {
  modalRemove.style.display = "none";
};
closeMessBtn.onclick = function() {
  modalMess.style.display = "none";
};

window.onclick = function(event) {
  if (event.target === modal) {
    modalRemove.style.display = "none";
  }
};
