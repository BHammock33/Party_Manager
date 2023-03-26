function hideButton(){
	document.getElementById('join-party').style.display = 'none';
}


let xpButton = document.querySelector('.xp-button');
	xpButton.addEventListener('click', addXPClick);
	
let xp = document.getElementById('xp-submit');
let xpAmountHidden = document.querySelector('.hidden-xp');
let xpHidden = (xpAmountHidden.value);
let allXp = document.querySelectorAll('xpModifier.amount');

	function addXPClick(){
		console.log(xp.value);
		xpHidden = (xp.value);
		console.log(xpHidden);
		allXp = (xp.value);
		console.log(allXp.value);
		
	}

