function vote(myvote) {

	document.getElementById("vote").setAttribute("value", myvote);
	for (let i = 1; i < 6; i++) {
		if (i <= myvote) {
			document.getElementById("star" + i).style.color = "gold";
		} else {
			document.getElementById("star" + i).style.color = "lightgrey";
		}
	}
}

