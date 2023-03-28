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

 

const coin = document.getElementById('coin-submit');
function hideZero(){
	coin.dataset.placeholder = element.placeholder
	coin.placeholder = '';
}

function validate(evt) {
  var theEvent = evt || window.event;

  // Handle paste
  if (theEvent.type === 'paste') {
      key = event.clipboardData.getData('text/plain');
  } else {
  // Handle key press
      var key = theEvent.keyCode || theEvent.which;
      key = String.fromCharCode(key);
  }
  var regex = /[0-9]|\./;
  if( !regex.test(key) ) {
    theEvent.returnValue = false;
    if(theEvent.preventDefault) theEvent.preventDefault();
  }
}