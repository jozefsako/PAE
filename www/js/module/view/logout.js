$(function () {
  /* Logout */
  $("#btn_logout").on("click", function () {
    logout();
    users = [];
  });
});

function logout() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "logout",
    },
    success: function () {
      eraseCookie(VIEW);
      location.reload();
    }
  });
}
