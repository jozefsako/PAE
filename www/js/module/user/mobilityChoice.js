$(function () {

  /* Add a new mobility mobilitychoice */
  $("#btn_add_mobility_choice").on("click", function () {
    addMobilityChoice();
  });

  /* Confirm mobilitychoice */
  $("#tbody_mobility_choices").on("click", "tr a", function (event) {
    event.preventDefault();
    let id_mobility_choice = $(this).parent().parent().attr('id');
    let r = confirm(
        "Cette action va entrainer la création d'une nouvelle mobilité\nVoulez vous confirmer votre demande ?");
    if (r === true) {
      confirmMobilityChoice(id_mobility_choice);
      getAllMobilityChoices();
    }
  });

  /* Generer fichier csv */
  $("#btn_csv_file").on("click", function () {
    generateCsvFile();
  });

});

function getPrograms() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getPrograms"
    },
    success: function (resp) {
      let options = " <option hidden>Programme</option>";
      for (let item in resp) {
        options += "<option id='" + item + "'>"
            + resp[item];
      }
      $('#select_program').html(options);
    }
  });
}

function getCountries() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getCountries"
    },
    success: function (resp) {
      countries = resp;
      let options = "";
      for (let item of resp) {
        options += "<option id='" + item.countryCode3 + "' value = '"
            + item.countryCode3 + "'>"
            + item.countryName;
      }
      $('.select_countries').html(options);
    }
  });
}

function getTypes() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getTypes"
    },
    success: function (resp) {
      let options = "<option hidden>Type</option>";
      for (let item in resp) {
        options += "<option>" + resp[item];
      }
      $('#select_type').html(options);
    }
  });
}

function getOrganisations() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getOrganisations"
    },
    success: function (resp) {
      let options = "<option hidden>Partenaire</option>";
      for (let item of resp) {
        options += "<option id='" + item.idOrganisation + "'>"
            + item.businessName;
      }
      $('#select_organisation').html(options);
    }
  });
}

function addMobilityChoice() {
  let errors = [];

  let program = $("#select_program option:selected").attr('id');
  let type = $("#select_type option:selected").val();
  let country = $("#select_country option:selected").attr('id');
  let semester = $("#select_semester").val();
  let pref = $("#select_pref").val();
  let organisation = $("#select_organisation option:selected").attr('id');

  if (program === undefined || program
      === "Programme") {
    errors.push("Programme manquant");
  }
  if (type === undefined || type === "Type") {
    errors.push("Type manquant");
  }
  if (country === undefined || country
      === "Pays") {
    errors.push("Pays manquant");
  }
  if (pref === undefined || pref === "pref") {
    errors.push("Préférence manquant");
  }
  if (semester === "") {
    semester = 0;
  }
  if (errors.length === 0) {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "addMobilityChoice",
        preference: pref,
        program: program,
        type: type,
        country: country,
        organisation: organisation,
        semester: semester,
      },
      success: function () {
        getAllMobilityChoices();
        /*
         * Set animation of feedback
         */
        $("#feedback_add_mobility_choice_success").css('display', 'block');
        setTimeout(function () {
          $("#feedback_encode_data_success").addClass("animated fadeOut");
          setTimeout(function () {
            $("#feedback_add_mobility_choice_success").css('display', 'none');
          }, 1000);
        }, 3000);
        $("#feedback_encode_data_success").removeClass("animated fadeOut");
      },
      errors: function (resp) {

        errors.push("La mobilité a déjà été validée");
      }
    });
  }

  if (errors.length > 0) {
    let html_errors = "";
    for (let i = 0; i < errors.length; i++) {
      html_errors += "<strong> Erreur 0" + (i + 1) + " ! </strong>" + errors[i]
          + "<br/>";
    }

    /*
     * Set animation of feedback
     */
    $("#feedback_add_mobility_choice_fail").css('display', 'block');
    $("#div_add_mobility_choice_fail").html(html_errors);
    setTimeout(function () {
      $("#feedback_add_mobility_choice_fail").addClass("animated fadeOut");
      setTimeout(function () {
        $("#feedback_add_mobility_choice_fail").css('display', 'none');
      }, 1000);
    }, 4000);
    $("#feedback_add_mobility_choice_fail").removeClass("animated fadeOut");
  }

}

function confirmMobilityChoice(idMobility) {
  let errors = [];
  if (current_user.typeUser === "S") {
    errors.push("Vous devez être connecté en tant que professeur");
  } else {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "confirmMobility",
        mobility_id: idMobility
      },
      success: function (resp) {
        if (resp === "-1") {
          displayWarningMobilityChoce();
        }else if(resp === "-2"){
          errors.push("La mobilité a déjà été confirmée ");
          displayErrorsMobilityChoice(errors);
        } else {
          displaySuccesMobilityChoice();
        }
      },
      error: function (error) {
        errors.push(error);
      }
    });
  }

  if (errors.length > 0) {
    displayErrorsMobilityChoice(errors);
  }

}

function getAllMobilityChoices() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getAllMobilityChoices"
    },
    success: function (resp) {
      mobility_choices = resp.mobilities;
      let rows = "";
      let mobilities = resp.mobilities;
      let users = resp.users;
      let organisations = resp.organisations;

      for (let mobility of mobilities) {
        rows += "<tr id='" + mobility.idMobilityChoice + "'>";

        let user = users.find(user => user.id === mobility.idUser);
        rows += createTableRow(`${user.firstName} ${user.lastName}`);
        rows += createTableRow(mobility.preferenceOrder);
        rows += createTableRow(mobility.program);
        //country
        let country = countries.find(
            country => country.countryCode3 === mobility.country);
        rows += createTableRow(
            mobility.country === null ? "" : country.countryName);
        rows += createTableRow(
            checkNull(mobility.academicYear));
        rows += (mobility.semester === 0 ? createTableRow("") : createTableRow(
            mobility.semester));
        //partner
        let partner = organisations.find(
            organisation => organisation.id === mobility.partner);
        rows += createTableRow(
            mobility.partner === 0 ? "" : partner.businessName);
        rows += createTableRow(mobility.mobilityType);
        rows += createTableRow(
            "<a href=\"\" class=\"btn btn-success btn-circle btn-sm\"><i class=\"fas fa-check\"></i></a>");
      }
      $('#tbody_mobility_choices ').html(rows);
    }
  });
}

function generateCsvFile() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getAllMobilityChoices"
    },
    success: function (resp) {
      let rows = "";
      let mobilities = resp.mobilities;
      let users = resp.users;
      let organisations = resp.organisations;

      for (let mobility of mobilities) {
        rows += "<tr>";
        rows += createTableRow(mobility.idMobilityChoice);
        let user = users.find(user => user.id === mobility.idUser);
        rows += createTableRow(`${user.lastName}`);
        rows += createTableRow(`${user.firstName}`);
        rows += createTableRow(mobility.preferenceOrder);
        var program = "";
        if (mobility.program === "ER") {
          program = "Erasmus";
        } else if (mobility.program === "FA") {
          program = "Fame";
        }
        rows += createTableRow(program);
        //country
        let country = countries.find(
            country => country.countryCode3 === mobility.country);
        rows += createTableRow(checkNull(mobility.semester));
        //partner
        let partner = organisations.find(
            organisation => organisation.id === mobility.partner);
        rows += createTableRow(
            mobility.partner === 0 ? "" : partner.businessName);
        rows += createTableRow(mobility.mobilityType);
      }
      var table = "<table class=csvTable>" +
          "<thead>" +
          "<tr>" +
          "<th>n°candidature</th>" +
          "<th>Nom</th>" +
          "<th>Prenom</th>" +
          "<th>Preference</th>" +
          "<th>Programme</th>" +
          "<th>Semestre</th>" +
          "<th>Partenaire</th>" +
          "<th>Type</th>" +
          "</tr>" +
          "</thead>" +
          "<tbody>" +
          rows +
          "</tbody>" +
          "</table>";
      $("#content").append(table);
      $(".csvTable").table2csv({
        separator: ';',
        newline: '\n',
        quoteFields: true,

      });
      $(".csvTable").hide();
    }
  })
}

function displaySuccesMobilityChoice() {
  $("#feedback_confirm_mobility_choice_success").css('display', 'block');
  setTimeout(function () {
    $("#feedback_confirm_mobility_choice_success").addClass(
        "animated fadeOut");
    setTimeout(function () {
      $("#feedback_confirm_mobility_choice_success").css('display',
          'none');
    }, 1000);
  }, 3000);
  $("#feedback_confirm_mobility_choice_success").removeClass(
      "animated fadeOut");
}

function displayErrorsMobilityChoice(errors) {
  let html_errors = "";
  for (let i = 0; i < errors.length; i++) {
    html_errors += "<strong>Erreur 0" + (i + 1) + " : </strong> " + errors[i];
  }
  $("#div_confirm_mobility_choice_fail ").html(html_errors);

  $("#feedback_confirm_mobility_choice_fail").css('display', 'block');
  setTimeout(function () {
    $("#feedback_confirm_mobility_choice_fail").addClass(
        "animated fadeOut");
    setTimeout(function () {
      $("#feedback_confirm_mobility_choice_fail").css('display', 'none');
    }, 1000);
  }, 3000);
  $("#feedback_confirm_mobility_choice_fail").removeClass(
      "animated fadeOut");
}

function displayWarningMobilityChoce() {
  $("#feedback_taken_care_of").css('display', 'block');
  setTimeout(function () {
    $("#feedback_taken_care_of").addClass(
        "animated fadeOut");
    setTimeout(function () {
      $("#feedback_taken_care_of").css('display',
          'none');
    }, 1000);
  }, 3000);
  $("#feedback_taken_care_of").removeClass(
      "animated fadeOut");
}
