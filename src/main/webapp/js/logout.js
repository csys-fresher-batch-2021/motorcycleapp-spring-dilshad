function logout() {
	localStorage.removeItem("LOGGED_IN_MEMBER");
	window.location.href = "index.jsp";
}