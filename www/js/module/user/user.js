$(function () {

  /*
   * Display Info User
   */
  $("#tbody_users").on("click", "tr a", function (event) {
    event.preventDefault();
    let tr = $(this).parent().parent();
    let id_user = tr.attr('id');
    if (current_user.typeUser !== EnumMobilityConfirmation.Student) {
      displayView(ViewUser.USER_SEARCH);
    }
    if (current_user.typeUser === EnumMobilityConfirmation.Reponsible) {
      displayView(ViewUser.USER_STATUS);
    }
    hideView(ViewUser.INFOUSER);
    viewUser(id_user);
  });

  /* On btn return info user */
  $("#btn_update_data").on("click", function (event) {
    event.preventDefault();
    hideView(ViewUser.ENCODE_DATA);
    displayView(ViewUser.INFOUSER);
  });

  /*
   * Form EncodeData
   */
  $("#btn_encode_data").on("click", function (event) {
    event.preventDefault();
    encodeData();
  });

  /*
   * Search Users
   */
  $("#searchUser_button").on("click", function (event) {
    event.preventDefault();
    searchUser();
    displayView(ViewUser.INFOUSER);
  });

  /*
   * Update User Responsibility
   */
  $("#btn_update_status_user").on("click", function (event) {
    event.preventDefault();
    encodeData();
  });

});

function viewUser(idUser) {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "viewUser",
      idUser: idUser,
    },
    success: function (resp) {
      displayView(ViewUser.ENCODE_DATA);
      $("#div_btn_encode_data").css("display", "none");
      $("#div_btn_update_data").css("display", "block");
      showUserData(resp);
    }
  });
}

function getUserById(idUser) {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "viewUser",
      idUser: idUser,
    },
    success: function (resp) {
      selected_user = resp;
    }
  });
}

function showUserData(resp) {
  user_version_number = resp.versionNumber;
  if (resp.birthdate !== null && resp.birthdate !== undefined) {
    $('#encode_birthdate').val(resp.birthdate.year + "-" + ("0"
        + (resp.birthdate.monthValue)).slice(
        -2) + "-" + ("0" + resp.birthdate.dayOfMonth).slice(-2)
    );
  }
  JSONToForm(resp, "#form_encode_data");
  $('#encode_nationality').val(resp.nationality);
  $("#select_typeUser").val(resp.typeUser);
}

function getUser() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getuser"
    },
    success: function (resp) {
      current_user = resp;

      /* Set the header */
      let whoami_short = (resp.firstName[0] + "."
          + resp.lastName[0]).toUpperCase();
      $("#WHOAMI_SHORT").html(whoami_short);
      $("#WHOAMI_FULL").html(resp.firstName + " " + resp.lastName);

      showUserData(resp);
    },
    error: function (error) {
    }
  });
}

function getAllUsers() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getAllUsers",
    },
    success: function (resp) {
      console.log("success getAllUsers");
      users = resp;
    }
  });
}

function getAndDisplayAllUsers() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getAllUsers",
    },
    success: function (resp) {
      users = resp;
      displayUsers(resp);
    }
  });
}

function displayUsers(users) {
  if (isIterable(users)) {
    let rows = "";
    for (let user of users) {
      rows += "<tr id='" + user.idUser + "'>";
      rows += "<td>" + user.idUser;
      rows += "<td>" + user.lastName;
      rows += "<td>" + user.firstName;
      rows += "<td>" + user.email;
      rows += "<td>" + user.username;
      rows += "<td>" + user.typeUser;
      rows += "<td>" + infoButton();
    }
    $("#tbody_users ").html(rows);
  } else {

  }
}

function encodeData() {
  let inputsElements = $("#form_encode_data input");
  for (let i = 0; i < inputsElements.length; i++) {
    inputsElements.eq(i).css("border-color", "#d1d3e2");
  }
  let user = FormToJSON($("#form_encode_data"));
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "encodedata",
      typeUser: $("#select_typeUser").val(),
      user: user
    },
    success: function (resp) {
      for (let i = 0; i < resp.length; i++) {
        $("#encode_" + resp[i]).css("border-color", "red");
      }
      showUserData(resp);
      $("#feedback_encode_data_success").css('display', 'block');
      setTimeout(function () {
        $("#feedback_encode_data_success").addClass("animated fadeOut");
      }, 3000);
      $("#feedback_encode_data_success").removeClass("animated fadeOut");
    },
    error: function () {
      let confirm_encodeData = confirm(
          "Votre version n'est pas à jour!\nVoulez-vous récupérer la dernière version ? ( Vos modifications seront supprimées)");
      if (confirm_encodeData == true) {
        refresh();
      }
    }
  });
}

function searchUser() {
  let firstName = $("#input_firstname_student").val();
  let lastName = $("#input_lastname_student").val();
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "researchStudents",
      firstNameCriteria: firstName,
      lastNameCriteria: lastName
    },
    success: function (resp) {
      displayUsers(resp);
    },
    error: function (error) {
    }
  });
}
