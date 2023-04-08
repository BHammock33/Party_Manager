var eyeIcons = document.querySelectorAll('.fa-eye')

eyeIcons.forEach((eyeIcon) => {
	eyeIcon.addEventListener('click', () => {
		if (eyeIcon.classList.contains('fa-eye')) {
			eyeIcon.classList.replace('fa-eye', 'fa-eye-slash')
			if (eyeIcon.getAttribute('id') === 'passwordEyeIcon') {
				document.querySelector("#password").type = 'text'
				document.querySelector("#confirmPassword").type = 'text'
			}
		} else {
			eyeIcon.classList.replace('fa-eye-slash', 'fa-eye')
			if (eyeIcon.getAttribute('id') === 'passwordEyeIcon') {
				document.querySelector("#password").type = 'password'
				document.querySelector("#confirmPassword").type = 'password'
			}
		}
	})
})

var password = document.getElementById("password"), confirm_password = document.getElementById("confirmPassword");

function validatePassword() {
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("Passwords don't match");
	} else {
		confirm_password.setCustomValidity("");
	}
}
password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

let textBoxes = document.querySelectorAll('.text-only');
let regex = new RegExp('^[A-Za-z]+$');
textBoxes.forEach((textBox) =>{
	 textBox.addEventListener('onkeydown', lettersOnly);
	
})  

const alphaOnlyInput = docume

function lettersOnly(txtInput){
	let textInput = document.querySelectorAll('.text-only');
	textInput.forEach((input) => {
		input.addEventListener('input', (e)=>{
			let charCode = e.keyCode;
			if ((charCode >= 65 && charCode <=90) || charCode == 8 || charCode == 32){
				null;
			}else{
				e.preventDefault();
			}
		});
	});
}
	
	