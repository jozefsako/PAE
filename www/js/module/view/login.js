$(function () {
  /* Login */
  $("#btn_login").on("click", function () {
    $(login());
  });
});

function login() {
  let errors = [];
  let loginEmail = $("#login_email").val();
  let loginPassword = $("#login_password").val();

  if (loginEmail === "" || loginEmail === null) {
    errors.push("Login manquant");
  }
  if (loginPassword === "" || loginPassword === null) {
    errors.push("Mot de passe manquant");
  }

  if (errors.length === 0) {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "login",
        login: $('#login_email').val(),
        password: $('#login_password').val(),
      },
      success: function () {
        $(hideView(ViewHome.LOGIN));
        $(displayView(ViewUser.USER));
        $(displayView(ViewUser.ADD_MOBILITY_CHOICE));
        $(displayView(ViewUser.MOBILITY_CHOICES));
        v.a = ViewUser.USER;
        setCookie(VIEW, ViewUser.USER, DEFAULT_AGE);
      },
      error: function () {
        errors.push("Mot de passe ou Login Invalide !");
        displayErrorsLogin(errors);
      }
    });
  } else {
    displayErrorsLogin(errors);
  }
}

function displayErrorsLogin(errors) {
  let html_errors = "";
  for (let i = 0; i < errors.length; i++) {
    html_errors += "<strong> Erreur 0" + (i + 1) + " ! </strong>" + errors[i]
        + "<br/>";
  }

  $("#feedback_login_fail").css('display', 'block');
  $("#div_login_fail").html(html_errors);
  setTimeout(function () {
    $("#feedback_login_fail").addClass("animated fadeOut");
    setTimeout(function () {
      $("#feedback_login_fail").css('display', 'none');
    }, 1000);
  }, 3000);
  $("#feedback_login_fail").removeClass("animated fadeOut");
}
