function getSelection(){
	selectElement = document.querySelector('#parties');
	output = selectElement.value;
	document.querySelector('.output').textContent = output;
}

function onChange(){
					var strUser = document.getElementById("parties").value;
					console.log(strUser);
				}
var row = 1, table = document.querySelector('table');
var button = document.querySelector('.button');
button.addEventListener('click', function(){
	if(!table.rows[row % 8]){
		table.appendChild(document.createElement('tr'));
	}
	table.rows[row++ % 8].appendChild(document.createElement('td'));
});				
