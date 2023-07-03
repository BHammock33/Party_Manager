var eyeIcons = document.querySelectorAll('.fa-eye')

eyeIcons.forEach( (eyeIcon) => {
	eyeIcon.addEventListener('click', () => {
		if(eyeIcon.classList.contains('fa-eye')){
			eyeIcon.classList.replace('fa-eye', 'fa-eye-slash')
			if(eyeIcon.getAttribute('id') === 'passwordEyeIcon'){
				document.querySelector("#password").type= 'text'
			}
		}else{
			eyeIcon.classList.replace('fa-eye-slash', 'fa-eye')
			if(eyeIcon.getAttribute('id') === 'passwordEyeIcon'){
				document.querySelector("#password").type= 'password'
			}
		}	
	})
})

function getCurrentURL(){
	return window.location.href
}
var url = getCurrentURL.toString()
function showError(){
	if(url == "https://partymanager-production.up.railway.app/login?error"){
		alert("incorrect username or password")
	}
}
function alertUser(msg) {
	alert(msg);
	}