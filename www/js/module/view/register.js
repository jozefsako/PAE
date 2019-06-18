$(function () {
  /* Register */
  $("#btn_register_account").on("click", function () {
    $(register());
  });
});

function register() {
  let errors = [];
  if ($("#register_firstName").val() === "" || $("#register_firstName").val()
      === null) {
    errors.push("Prénom manquant");
  }
  if ($("#register_lastName").val() === "" || $("#register_lastName").val()
      === null) {
    errors.push("Nom manquant");
  }
  if ($("#register_username").val() === "" || $("#register_username").val()
      === null) {
    errors.push("Nom d'utilisateur manquant");
  }
  if ($("#register_email").val() === "" || $("#register_email").val()
      === null) {
    errors.push("Email manquant");
  }
  if ($("#register_password").val() === "" || $("#register_password").val()
      === null) {
    errors.push("Mot de passe manquant");
  }
  if ($("#register_password").val() !== $("#register_repeat_password").val()) {
    errors.push("Les mots de passes ne corresspondent pas");
  }

  if (errors.length === 0) {
    console.log("register ajax");
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "register",
        firstname: $('#register_firstName').val(),
        lastname: $('#register_lastName').val(),
        username: $('#register_username').val(),
        email: $('#register_email').val(),
        password: $('#register_password').val(),
      },
      success: function () {
        view = "#form_user";
        setCookie(VIEW, ViewUser.HOME, DEFAULT_AGE);
        $(hideView(ViewHome.REGISTER));
        $(displayView(ViewUser.USER));
        $(displayView(ViewUser.ADD_MOBILITY_CHOICE));
        $(displayView(ViewUser.MOBILITY_CHOICES));
      },
      error: function () {
        view = "#form_register";
        alert("SIGN-IN INVALID");
      }
    });
  } else {
    let html_errors = "";
    for (let i = 0; i < errors.length; i++) {
      html_errors += "<strong> Erreur 0" + (i + 1) + " ! </strong>" + errors[i]
          + "<br/>";
    }

    /*
     * Set animation of feedback errors
     */
    $("#feedback_register_fail").css('display', 'block');
    $("#div_register_fail").html(html_errors);
    setTimeout(function () {
      $("#feedback_register_fail").addClass("animated fadeOut");
      setTimeout(function () {
        $("#feedback_register_fail").css('display', 'none');
      }, 1000);
    }, 4000);
    $("#feedback_register_fail").removeClass("animated fadeOut");
  }

}
