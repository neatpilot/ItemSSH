
function appendPostParameter(form, action, value) {
	form.action = action;
	form.method = "post";
	// 构造hidden field
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden"); // 
	hiddenField.setAttribute("name", "command");
	hiddenField.setAttribute("value", value);
	form.appendChild(hiddenField); // append the newly created control to the form
	return;
}