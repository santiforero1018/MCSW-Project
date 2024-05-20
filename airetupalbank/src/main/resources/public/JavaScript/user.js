
function getUserData() {
    fetch('/v1/users/{usu@outlook.com}', {  
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + authToken 
        }
    })
    .then(response => response.json())
    .then(data => {
       
        displayUserData(data);
    })
    .catch(error => console.error('Error al obtener los datos del usuario:', error));
}


function displayUserData(userData) {
   
    document.getElementById('user-name').textContent = userData.name;
    document.getElementById('user-email').textContent = userData.email;
  
}

document.addEventListener('DOMContentLoaded', getUserData);
