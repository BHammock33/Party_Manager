function getSelection(){
	selectElement = document.querySelector('#parties');
	output = selectElement.value;
	document.querySelector('.output').textContent = output;
}

function onChange(){
					var strUser = document.getElementById("parties").value;
					console.log(strUser);
				}