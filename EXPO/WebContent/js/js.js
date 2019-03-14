function vote(myvote){
	
	// Implemetation with images (not resource efficient)
	/*
    document.getElementById("vote").setAttribute("value",myvote);
    for(let i = 1; i < 6; i++) {
        if(i <= myvote) {
            document.getElementById("star"+i).outerHTML =
                "<img id=\"star"+i+"\" onclick=\"vote("+i+")\"\n" +
                "                     src=\"/EXPO/images/filledStar.jpeg\" class=\"img-fluid\"/>";
        }else {
            document.getElementById("star" + i).outerHTML =
                "<img id=\"star"+i+"\" onclick=\"vote("+i+")\"\n" +
                "                     src=\"/EXPO/images/emptyStar.jpeg\" class=\"img-fluid\"/>";        }
    }
    */
	
	// Implementation with css changes. More efficient.
	document.getElementById("vote").setAttribute("value",myvote);
    for(let i = 1; i < 6; i++) {
        if(i <= myvote) {
            document.getElementById("star"+i).style.color = "gold";
        }else {
            document.getElementById("star"+i).style.color = "lightgrey";
        }   
    }
}