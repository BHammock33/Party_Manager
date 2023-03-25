function hideButton(){
	document.getElementById('join-party').style.display = 'none';
}


var xpButton = document.querySelector(".xp-button");
	xpButton.addEventListener('click', fun1);
	var xp = document.getElementById('xp-submit');
	
	function fun1(){
		console.log(xp.value);
	}

