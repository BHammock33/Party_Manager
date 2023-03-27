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