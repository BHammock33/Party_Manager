function $(x){
	return document.getElementById(x);
}

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
if (window.location.toString().includes("error")){
	alert("incorrect username or password")
}
/** 
function alertUser(msg) {
	alert(msg);
	setTimeout(function(){
		alert('close');
	}, 2000)
	}
function tempAlert(msg, duration){
	var el = document.createElement("div");
	el.setAttribute("style", "background-color: grey; color:black; width: 450px; height: 200px; position: absolute; top:0; bottom:-30; left:0; right:-50; margin:auto; border: 4px solid black;font-family:arial;font-size:25px;font-weight:bold;display: flex; align-items: center; justify-content: center; text-align: center;");
	el.innerHTML = msg;
	setTimeout(function(){
		el.parentNode.removeChild(el);
	}, duration);
	document.body.appendChild(el);
}	
*/

function demoPlayerLoginFunc(){
	let demoUserName = "DemoPlayer";
	let demoUserPW = "Tfal@x33";
	document.getElementById('username').value = demoUserName;
	document.getElementById('password').value = demoUserPW;
	document.getElementById('login').click();
}
function demoDmLoginFunc(){
	let demoUserName = "DemoDM";
	let demoUserPW = "Tfal@x33";
	document.getElementById('username').value = demoUserName;
	document.getElementById('password').value = demoUserPW;
	document.getElementById('login').click();
}